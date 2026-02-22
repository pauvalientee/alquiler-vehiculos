package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "alquileres", path = "alquileres")
@CrossOrigin("*")
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

}