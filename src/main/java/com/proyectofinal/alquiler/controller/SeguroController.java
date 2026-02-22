package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seguro")
@CrossOrigin(origins = "*")
public class SeguroController {
    @Autowired
    private SeguroService seguroService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody com.proyectofinal.alquiler.entity.Seguro seguro) {
        if(org.apache.commons.lang3.StringUtils.isBlank(seguro.getTipo()))
            return new ResponseEntity<>(new Mensaje("El tipo de seguro es obligatorio"), HttpStatus.BAD_REQUEST);
        seguroService.save(seguro);
        return new ResponseEntity<>(new Mensaje("Seguro creado"), HttpStatus.OK);
    }
}
