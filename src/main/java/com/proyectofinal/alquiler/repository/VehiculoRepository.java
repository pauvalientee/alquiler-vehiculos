package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "vehiculos", path = "vehiculos")
@CrossOrigin("*")
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}