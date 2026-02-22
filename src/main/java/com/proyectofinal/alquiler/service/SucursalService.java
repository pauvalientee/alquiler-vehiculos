package com.proyectofinal.alquiler.service;
import com.proyectofinal.alquiler.entity.Sucursal;
import com.proyectofinal.alquiler.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SucursalService {
    @Autowired private SucursalRepository sucursalRepository;
    public List<Sucursal> list() { return sucursalRepository.findAll(); }
    public void save(Sucursal sucursal) { sucursalRepository.save(sucursal); }
}