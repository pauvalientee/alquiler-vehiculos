package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "sucursales", path = "sucursales")
@CrossOrigin("*")
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}