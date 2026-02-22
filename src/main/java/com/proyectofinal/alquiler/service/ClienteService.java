package com.proyectofinal.alquiler.service;

import com.proyectofinal.alquiler.entity.Cliente;
import com.proyectofinal.alquiler.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> list() { return clienteRepository.findAll(); }

    public Optional<Cliente> getOne(Long id) { return clienteRepository.findById(id); }

    public void save(Cliente cliente) { clienteRepository.save(cliente); }

    public void delete(Long id) { clienteRepository.deleteById(id); }

    public boolean existsById(Long id) { return clienteRepository.existsById(id); }
}