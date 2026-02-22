package com.proyectofinal.alquiler.repository;

import com.proyectofinal.alquiler.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "pagos", path = "pagos")
@CrossOrigin("*")
public interface PagoRepository extends JpaRepository<Pago, Long> {
}