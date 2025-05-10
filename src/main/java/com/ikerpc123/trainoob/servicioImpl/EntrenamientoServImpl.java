package com.ikerpc123.trainoob.servicioImpl;

import java.util.List;
import java.util.Optional;

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
    
    @Override
    public List<Entrenamiento> obtenerEntrenamientosPorEntrenador(int entrenadorId) {
        return entrenamientoRepo.findByEntrenadorId(entrenadorId);
    }
    
    @Override
    public void eliminarEntrenamientoPorId(int id) {
    	entrenamientoRepo.deleteById(id);
    }
    
    @Override
    public Entrenamiento obtenerPorId(int id) {
        return entrenamientoRepo.findById(id).orElse(null);
    }

}
