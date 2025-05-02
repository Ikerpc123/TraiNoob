package com.ikerpc123.trainoob.servicio;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Jugador;

public interface JugadorService {

	Jugador guardarJugador(Jugador jugador);

	Jugador registrarJugadorConUsuario(String nombre, int edad, String categoria, String email, String contrasena, Entrenador entrenador);

}
