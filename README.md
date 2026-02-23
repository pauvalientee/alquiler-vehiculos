# API REST para Gestión de Alquiler de Vehículos

## 1. Descripción del proyecto
Este proyecto es una API REST desarrollada en Spring Boot (Java) diseñada para gestionar de forma integral el proceso de alquiler de una flota de vehículos. Proporciona los servicios necesarios para administrar clientes, vehículos, reservas y cobros.

## 2. Explicación del problema de negocio
Una empresa de alquiler de vehículos necesita digitalizar su operativa para llevar un control estricto de su flota. El principal problema que resuelve esta API es controlar la disponibilidad en tiempo real de los coches, evitando que un mismo vehículo se alquile a dos clientes a la vez. Además, gestiona el ciclo completo del servicio: desde el registro del cliente, pasando por la creación y finalización del alquiler, hasta el procesamiento del pago correspondiente.

## 3. Instrucciones de ejecución
1. **Requisitos previos:** Tener instalado Java (JDK) y un entorno Docker con MySQL corriendo.
2. **Base de Datos:** El proyecto se conecta a una base de datos MySQL llamada `proyectofinal_damb`. Asegúrese de que las credenciales en el archivo `application.properties` (usuario y contraseña) coinciden con su entorno local.
3. **Ejecución:** Ejecute la clase principal de la aplicación Spring Boot desde su IDE (IntelliJ/Eclipse). La API se iniciará y estará disponible en `http://localhost:8080`.

## 4. Listado y descripción de los endpoints principales

### Clientes
* **`POST /api/cliente/nuevo`**: Registra un nuevo cliente en el sistema.

### Vehículos
* **`POST /api/vehiculo/nuevo`**: Registra un nuevo vehículo en la flota.
* **`GET /api/vehiculo/lista`**: Devuelve el listado completo de vehículos y su estado actual (disponible o alquilado).
* **`GET /api/vehiculo/detalle/{id}`**: Consulta la disponibilidad y los datos detallados de un vehículo específico.

### Operaciones (Alquiler)
* **`POST /api/operaciones/crear`**: Crea un nuevo alquiler vinculando un cliente y un vehículo. Si el vehículo no está disponible, la operación es rechazada con un Bad Request.
* **`PUT /api/operaciones/finalizar/{id}`**: Finaliza un alquiler activo, marcando su estado como "FINALIZADO" y liberando el vehículo para que vuelva a estar disponible.

### Pagos
* **`POST /api/pago/nuevo`**: Procesa el registro de un pago vinculándolo mediante una relación a un alquiler específico.