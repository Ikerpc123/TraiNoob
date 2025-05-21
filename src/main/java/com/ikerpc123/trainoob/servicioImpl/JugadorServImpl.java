package com.ikerpc123.trainoob.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.JugadorRepository;
import com.ikerpc123.trainoob.repositorio.UsuarioRepository;
import com.ikerpc123.trainoob.servicio.JugadorService;

@Service
public class JugadorServImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Jugador registrarJugadorConUsuario(String nombre, int edad, String categoria, String email, String contrasena, Entrenador entrenador) {
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuario.setRol("JUGADOR");
        usuarioRepo.save(usuario);

        // Crear jugador
        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setEdad(edad);
        jugador.setCategoria(categoria);
        jugador.setUsuario(usuario);
        jugador.setEntrenador(entrenador);
        return jugadorRepo.save(jugador);
    }
    
    @Override
    public Jugador guardarJugador(Jugador jugador) {
        return jugadorRepo.save(jugador);
    }

	@Override
	public List<Jugador> buscarPorEntrenador(Entrenador entrenador) {
		return jugadorRepo.findByEntrenador(entrenador);
	}
	
	@Override
	public Jugador buscarPorUsuario(Optional<Usuario> usuario) {
		return jugadorRepo.findByUsuario(usuario);
	}
	
}
