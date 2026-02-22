package com.proyectofinal.alquiler.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seguro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Double precioDia;
    private String cobertura;
}