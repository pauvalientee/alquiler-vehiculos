package com.proyectofinal.alquiler.service;

import com.proyectofinal.alquiler.repository.AlquilerRepository;
import com.proyectofinal.alquiler.repository.VehiculoRepository;
import com.proyectofinal.alquiler.entity.Alquiler;
import com.proyectofinal.alquiler.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Transactional
    public Alquiler crearAlquiler(Alquiler alquiler) {
        Vehiculo vehiculo = vehiculoRepository.findById(alquiler.getVehiculo().getId())
                .orElseThrow(() -> new RuntimeException("Error: El vehículo con ese ID no existe"));

        if (vehiculo.getDisponible() != null && !vehiculo.getDisponible()) {
            throw new RuntimeException("El vehículo seleccionado ya está alquilado");
        }

        vehiculo.setDisponible(false);
        vehiculoRepository.save(vehiculo);

        alquiler.setEstado("ACTIVO");
        return alquilerRepository.save(alquiler);
    }
    @Transactional
    public Alquiler finalizarAlquiler(Long alquilerId) {
        Alquiler alquiler = alquilerRepository.findById(alquilerId)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún alquiler con el ID: " + alquilerId));
        alquiler.setEstado("FINALIZADO");

        Vehiculo vehiculo = alquiler.getVehiculo();
        if (vehiculo != null) {
            vehiculo.setDisponible(true);
            vehiculoRepository.save(vehiculo);
        }

        return alquilerRepository.save(alquiler);
    }
}