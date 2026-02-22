package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sucursal")
@CrossOrigin(origins = "*")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody com.proyectofinal.alquiler.entity.Sucursal sucursal) {
        if(org.apache.commons.lang3.StringUtils.isBlank(sucursal.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre de la sucursal es obligatorio"), HttpStatus.BAD_REQUEST);
        sucursalService.save(sucursal);
        return new ResponseEntity<>(new Mensaje("Sucursal guardada"), HttpStatus.OK);
    }
}
