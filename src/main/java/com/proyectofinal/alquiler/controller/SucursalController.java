package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.entity.Sucursal;
import com.proyectofinal.alquiler.entity.Vehiculo;
import com.proyectofinal.alquiler.service.SucursalService;
import com.proyectofinal.alquiler.service.VehiculoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
@CrossOrigin(origins = "*")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Sucursal sucursal) {
        if (StringUtils.isBlank(sucursal.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre de la sucursal es obligatorio"), HttpStatus.BAD_REQUEST);
        sucursalService.save(sucursal);
        return new ResponseEntity<>(new Mensaje("Sucursal guardada"), HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Sucursal>> list() {
        return new ResponseEntity<>(sucursalService.list(), HttpStatus.OK);
    }

    @PostMapping("/{sucursalId}/vehiculo/{vehiculoId}")
    public ResponseEntity<?> asignarVehiculo(
            @PathVariable Long sucursalId,
            @PathVariable Long vehiculoId) {
        try {
            sucursalService.asignarVehiculo(sucursalId, vehiculoId);
            return new ResponseEntity<>(new Mensaje("Vehículo asignado a la sucursal"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<?> vehiculosDeSucursal(@PathVariable Long id) {
        try {
            List<Vehiculo> vehiculos = sucursalService.getVehiculos(id);
            return new ResponseEntity<>(vehiculos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}