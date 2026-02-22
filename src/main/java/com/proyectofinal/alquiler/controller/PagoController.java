package com.proyectofinal.alquiler.controller;
import com.proyectofinal.alquiler.dto.Mensaje;
import com.proyectofinal.alquiler.entity.Pago;
import com.proyectofinal.alquiler.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pago")
@CrossOrigin(origins = "*")
public class PagoController {
    @Autowired private PagoService pagoService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Pago pago) {
        if(pago.getImporte() == null || pago.getImporte() <= 0)
            return new ResponseEntity<>(new Mensaje("El importe debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        pagoService.save(pago);
        return new ResponseEntity<>(new Mensaje("Pago registrado con éxito"), HttpStatus.OK);
    }
}