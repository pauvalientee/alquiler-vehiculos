package com.proyectofinal.alquiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sucursal")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String ciudad;

    @ManyToMany
    @JoinTable(
            name = "sucursal_vehiculo",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "vehiculo_id")
    )
    @JsonIgnore
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "sucursal")
    @JsonIgnore
    private List<Alquiler> alquileres;
}