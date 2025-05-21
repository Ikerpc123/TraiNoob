package com.ikerpc123.trainoob.servicio;

import java.util.List;
import java.util.Optional;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Usuario;

public interface JugadorService {

	Jugador guardarJugador(Jugador jugador);

	Jugador registrarJugadorConUsuario(String nombre, int edad, String categoria, String email, String contrasena, Entrenador entrenador);

	List<Jugador> buscarPorEntrenador(Entrenador entrenador);

	Jugador buscarPorUsuario(Optional<Usuario> usuario);

}
