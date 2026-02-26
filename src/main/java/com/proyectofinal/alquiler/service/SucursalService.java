package com.proyectofinal.alquiler.service;

import com.proyectofinal.alquiler.entity.Sucursal;
import com.proyectofinal.alquiler.entity.Vehiculo;
import com.proyectofinal.alquiler.repository.SucursalRepository;
import com.proyectofinal.alquiler.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Sucursal> list() { return sucursalRepository.findAll(); }

    public void save(Sucursal sucursal) { sucursalRepository.save(sucursal); }

    public void asignarVehiculo(Long sucursalId, Long vehiculoId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + sucursalId));
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + vehiculoId));

        if (!sucursal.getVehiculos().contains(vehiculo)) {
            sucursal.getVehiculos().add(vehiculo);
            sucursalRepository.save(sucursal);
        }
    }

    public List<Vehiculo> getVehiculos(Long sucursalId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + sucursalId));
        return sucursal.getVehiculos();
    }
}