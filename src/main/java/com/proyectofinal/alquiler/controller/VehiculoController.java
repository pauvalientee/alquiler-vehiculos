package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.entity.Vehiculo;
import com.proyectofinal.alquiler.service.VehiculoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Vehiculo vehiculo) {
        if(StringUtils.isBlank(vehiculo.getMatricula()))
            return new ResponseEntity<>(new Mensaje("La matrícula es obligatoria"), HttpStatus.BAD_REQUEST);

        vehiculoService.save(vehiculo);
        return new ResponseEntity<>(new Mensaje("Vehículo registrado"), HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!vehiculoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El vehículo no existe"), HttpStatus.NOT_FOUND);

        Vehiculo vehiculo = vehiculoService.getOne(id).get();
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Vehiculo>> list() {
        List<Vehiculo> lista = vehiculoService.list();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}