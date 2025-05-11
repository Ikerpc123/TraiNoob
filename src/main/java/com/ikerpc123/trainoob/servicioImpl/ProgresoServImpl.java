package com.ikerpc123.trainoob.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Progreso;
import com.ikerpc123.trainoob.repositorio.ProgresoRepository;
import com.ikerpc123.trainoob.servicio.ProgresoService;

@Service
public class ProgresoServImpl implements ProgresoService{

	@Autowired
    private ProgresoRepository progresoRepo;

    @Override
    public void guardar(Progreso progreso) {
    	progresoRepo.save(progreso);
    }

    @Override
    public List<Progreso> buscarPorJugadorId(int id) {
        return progresoRepo.findByJugadorId(id);
    }

    @Override
    public List<Progreso> buscarPorEntrenamientoId(int id) {
        return progresoRepo.findByEntrenamientoId(id);
    }
}
