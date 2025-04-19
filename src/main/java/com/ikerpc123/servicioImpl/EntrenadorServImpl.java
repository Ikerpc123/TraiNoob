package com.ikerpc123.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.modelo.Entrenador;
import com.ikerpc123.modelo.Usuario;
import com.ikerpc123.repositorio.EntrenadorRepository;
import com.ikerpc123.servicio.EntrenadorService;

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
}

