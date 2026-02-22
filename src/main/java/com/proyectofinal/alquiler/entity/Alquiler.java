package com.proyectofinal.alquiler.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "alquiler")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precioTotal;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference(value = "alquiler-cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonBackReference(value = "alquiler-vehiculo")
    private Vehiculo vehiculo;
}