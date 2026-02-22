package com.proyectofinal.alquiler.service;

import com.proyectofinal.alquiler.entity.Vehiculo;
import com.proyectofinal.alquiler.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> list() { return vehiculoRepository.findAll(); }
    public void save(Vehiculo vehiculo) { vehiculoRepository.save(vehiculo); }
    public boolean existsById(Long id) { return vehiculoRepository.existsById(id); }
    public java.util.Optional<Vehiculo> getOne(Long id) { return vehiculoRepository.findById(id); }
}