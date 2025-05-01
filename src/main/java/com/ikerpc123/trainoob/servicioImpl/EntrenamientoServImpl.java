package com.ikerpc123.trainoob.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Entrenamiento;
import com.ikerpc123.trainoob.repositorio.EntrenamientoRepository;
import com.ikerpc123.trainoob.servicio.EntrenamientoService;

@Service
public class EntrenamientoServImpl implements EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepo;

    @Override
    public Entrenamiento guardarEntrenamiento(Entrenamiento entrenamiento) {
        return entrenamientoRepo.save(entrenamiento);
    }
}
