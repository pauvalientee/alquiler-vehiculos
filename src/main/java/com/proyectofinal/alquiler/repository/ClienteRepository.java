package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}