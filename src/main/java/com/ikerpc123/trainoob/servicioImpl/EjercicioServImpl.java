package com.ikerpc123.trainoob.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Ejercicio;
import com.ikerpc123.trainoob.repositorio.EjercicioRepository;
import com.ikerpc123.trainoob.servicio.EjercicioService;

@Service
public class EjercicioServImpl implements EjercicioService {

	@Autowired
    private EjercicioRepository ejercicioRepo;

    @Override
    public List<Ejercicio> obtenerTodos() {
        return ejercicioRepo.findAll();
    }
    
    @Override
    public Ejercicio guardar(Ejercicio ejercicio) {
        return ejercicioRepo.save(ejercicio);
    }
}
