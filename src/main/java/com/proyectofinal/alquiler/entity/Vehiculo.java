package com.proyectofinal.alquiler.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String matricula;

    @Column(name = "disponible")
    private Boolean disponible = true;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "alquiler-vehiculo")
    private List<Alquiler> alquileres;
}