package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.entity.Alquiler;
import com.proyectofinal.alquiler.service.AlquilerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operaciones")
@CrossOrigin(origins = "*")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Alquiler alquiler) {
        if (alquiler.getVehiculo() == null || alquiler.getVehiculo().getId() == null) {
            return new ResponseEntity<>(new Mensaje("El id del vehículo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (alquiler.getCliente() == null || alquiler.getCliente().getId() == null) {
            return new ResponseEntity<>(new Mensaje("El id del cliente es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        try {
            Alquiler nuevo = alquilerService.crearAlquiler(alquiler);
            return new ResponseEntity<>(nuevo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizar(@PathVariable("id") Long id) {
        try {
            Alquiler finalizado = alquilerService.finalizarAlquiler(id);
            return new ResponseEntity<>(finalizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("No existe el alquiler con id " + id), HttpStatus.NOT_FOUND);
        }
    }
}