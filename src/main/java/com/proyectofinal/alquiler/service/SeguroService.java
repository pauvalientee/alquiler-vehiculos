package com.proyectofinal.alquiler.service;
import com.proyectofinal.alquiler.entity.Seguro;
import com.proyectofinal.alquiler.repository.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SeguroService {
    @Autowired private SeguroRepository seguroRepository;
    public List<Seguro> list() { return seguroRepository.findAll(); }
    public void save(Seguro seguro) { seguroRepository.save(seguro); }
}