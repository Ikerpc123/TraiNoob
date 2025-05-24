package com.ikerpc123.trainoob.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.EntrenadorRepository;
import com.ikerpc123.trainoob.servicio.EntrenadorService;

@Service
public class EntrenadorServImpl implements EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Override
    public Entrenador registrarEntrenador(Usuario usuario) {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(usuario.getNombre());
        entrenador.setUsuario(usuario);
        return entrenadorRepository.save(entrenador);
    }
    
    @Override
    public Entrenador findByUsuario(Usuario usuario)
    {
		return entrenadorRepository.findByUsuarioEmail(usuario.getEmail());
    }
}

