package com.proyectofinal.alquiler.controller;

import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.entity.Cliente;
import com.proyectofinal.alquiler.service.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        if(StringUtils.isBlank(cliente.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(cliente.getDni()))
            return new ResponseEntity<>(new Mensaje("El DNI es obligatorio"), HttpStatus.BAD_REQUEST);

        clienteService.save(cliente);
        return new ResponseEntity<>(new Mensaje("Cliente guardado"), HttpStatus.OK);
    }
}