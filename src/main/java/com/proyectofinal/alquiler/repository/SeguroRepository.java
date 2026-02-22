package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "seguros", path = "seguros")
@CrossOrigin("*")
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
}