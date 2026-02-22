package com.proyectofinal.alquiler.service;
import com.proyectofinal.alquiler.entity.Pago;
import com.proyectofinal.alquiler.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PagoService {
    @Autowired private PagoRepository pagoRepository;
    public List<Pago> list() { return pagoRepository.findAll(); }
    public void save(Pago pago) { pagoRepository.save(pago); }
}