# 🚗 API REST — Gestión de Alquiler de Vehículos

## 1. Descripción del proyecto

API REST desarrollada con **Spring Boot (Java)** para gestionar de forma integral el proceso de alquiler de una flota de vehículos. El sistema cubre el ciclo completo del negocio: registro de clientes y vehículos, creación y finalización de alquileres, procesamiento de pagos, gestión de seguros y administración de sucursales.

El proyecto sigue una **arquitectura por capas** (Controller → Service → Repository → Entity), garantizando una separación clara de responsabilidades y facilitando el mantenimiento y escalabilidad del código.

---

## 2. Problema de negocio

Una empresa de alquiler de vehículos necesita digitalizar su operativa para llevar un control estricto de su flota. Los problemas concretos que resuelve esta API son:

- **Control de disponibilidad en tiempo real:** el sistema impide que un vehículo sea alquilado por dos clientes simultáneamente. Al crear un alquiler, el vehículo se marca automáticamente como no disponible; al finalizarlo, vuelve a estar libre.
- **Gestión del ciclo completo del alquiler:** desde el registro del cliente hasta el cobro del servicio.
- **Organización por sucursales:** los vehículos pueden estar asignados a una o varias sucursales, y cada alquiler queda vinculado a la sucursal donde se originó.
- **Cobertura de seguros:** cada vehículo puede tener asociado un seguro con su tipo, precio diario y cobertura.

---

## 3. Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java (JDK) | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | — |
| Spring Data REST | — |
| Hibernate | 7.2.4 |
| MySQL Connector | 9.6.0 |
| Lombok | 1.18.42 |
| Apache Commons Lang | 3.12.0 |

---

## 4. Modelo de datos

El sistema cuenta con las siguientes entidades y relaciones:

| Entidad | Relaciones |
|---|---|
| `Cliente` | OneToMany con `Alquiler` |
| `Vehiculo` | OneToMany con `Alquiler`, OneToOne con `Seguro`, ManyToMany con `Sucursal` |
| `Alquiler` | ManyToOne con `Cliente`, ManyToOne con `Vehiculo`, ManyToOne con `Sucursal` |
| `Pago` | OneToOne con `Alquiler` |
| `Seguro` | OneToOne con `Vehiculo` |
| `Sucursal` | ManyToMany con `Vehiculo`, OneToMany con `Alquiler` |

La tabla intermedia `sucursal_vehiculo` es generada automáticamente por Hibernate para gestionar la relación ManyToMany entre `Sucursal` y `Vehiculo`.

---

## 5. Instrucciones de ejecución

1. **Requisitos previos:** tener instalado Java 21 y MySQL en ejecución.
2. **Base de datos:** crear una base de datos MySQL llamada `proyectofinal_damb`.
3. **Configuración:** revisar el archivo `src/main/resources/application.properties` y ajustar usuario y contraseña de MySQL si es necesario:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/proyectofinal_damb
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```
4. **Ejecución:** arrancar la clase principal `AlquilerVehiculosApplication` desde el IDE. Hibernate creará las tablas automáticamente.
5. **Acceso:** la API estará disponible en `http://localhost:8080`.

---

## 6. Endpoints y pruebas

### 🧑 Clientes

**Registrar un nuevo cliente**
```
POST http://localhost:8080/api/cliente/nuevo
```
Body:
```json
{
    "nombre": "Pau Valiente",
    "dni": "128754397X",
    "telefono": "633934628",
    "email": "pau@correo.com"
}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Cliente guardado" }
```

---

### 🚙 Vehículos

**Registrar un nuevo vehículo**
```
POST http://localhost:8080/api/vehiculo/nuevo
```
Body:
```json
{
    "marca": "Kia",
    "modelo": "Ceed",
    "matricula": "2025-MWM",
    "disponible": true
}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Vehículo registrado" }
```

---

**Consultar disponibilidad de un vehículo**
```
GET http://localhost:8080/api/vehiculo/detalle/1
```
Respuesta exitosa `200 OK`:
```json
{
    "id": 1,
    "marca": "Kia",
    "modelo": "Ceed",
    "matricula": "2025-MWM",
    "disponible": true
}
```

---

**Listar todos los vehículos**
```
GET http://localhost:8080/api/vehiculo/lista
```
Devuelve el listado completo de vehículos con su estado de disponibilidad actual.

---

### 📋 Alquileres

**Crear un nuevo alquiler**
```
POST http://localhost:8080/api/operaciones/crear
```
Body:
```json
{
    "fechaInicio": "2026-02-23",
    "fechaFin": "2026-03-02",
    "precioTotal": 300.0,
    "estado": "ACTIVO",
    "cliente": { "id": 1 },
    "vehiculo": { "id": 1 }
}
```
Respuesta exitosa `200 OK`: devuelve el objeto alquiler creado con estado `ACTIVO` y el vehículo marcado como no disponible.

Error si el vehículo ya está alquilado `400 BAD REQUEST`:
```json
{ "mensaje": "El vehículo seleccionado ya está alquilado" }
```

---

**Finalizar un alquiler**
```
PUT http://localhost:8080/api/operaciones/finalizar/1
```
Respuesta exitosa `200 OK`: devuelve el alquiler con estado `FINALIZADO` y el vehículo liberado (disponible = true).

Error si no existe `404 NOT FOUND`:
```json
{ "mensaje": "No existe el alquiler con id 1" }
```

---

### 💳 Pagos

**Registrar un pago**
```
POST http://localhost:8080/api/pago/nuevo
```
Body:
```json
{
    "importe": 300.0,
    "metodoPago": "TARJETA",
    "fechaPago": "2026-02-23T12:00:00",
    "alquiler": { "id": 1 }
}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Pago registrado con éxito" }
```

---

### 🛡️ Seguros

**Crear un seguro para un vehículo**
```
POST http://localhost:8080/api/seguro/nuevo
```
Body:
```json
{
    "tipo": "Todo Riesgo",
    "precioDia": 15.0,
    "cobertura": "Daños propios y robo",
    "vehiculo": { "id": 1 }
}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Seguro creado" }
```

---

### 🏢 Sucursales

**Registrar una sucursal**
```
POST http://localhost:8080/api/sucursal/nuevo
```
Body:
```json
{
    "nombre": "Sucursal Este",
    "direccion": "Calle Principal"
}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Sucursal guardada" }
```

---

**Asignar un vehículo a una sucursal (ManyToMany)**
```
POST http://localhost:8080/api/sucursal/{sucursalId}/vehiculo/{vehiculoId}
```
Respuesta exitosa `200 OK`:
```json
{ "mensaje": "Vehículo asignado a la sucursal" }
```

---

**Ver vehículos de una sucursal**
```
GET http://localhost:8080/api/sucursal/{id}/vehiculos
```
Devuelve el listado de vehículos asignados a esa sucursal.

---

## 7. Gestión de errores

La API devuelve mensajes de error claros en formato JSON para las situaciones más comunes:

| Situación | Código HTTP |
|---|---|
| Recurso no encontrado | `404 NOT FOUND` |
| Datos obligatorios ausentes | `400 BAD REQUEST` |
| Vehículo no disponible | `400 BAD REQUEST` |
| Importe de pago incorrecto | `400 BAD REQUEST` |