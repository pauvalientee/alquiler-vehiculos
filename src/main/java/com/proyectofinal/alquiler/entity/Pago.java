package com.proyectofinal.alquiler.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double importe;
    private LocalDateTime fechaPago;
    private String metodoPago;

    @OneToOne
    @JoinColumn(name = "alquiler_id")
    @JsonBackReference
    private Alquiler alquiler;
}