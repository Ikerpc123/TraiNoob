package com.ikerpc123.trainoob.servicio;

import java.util.List;
import java.util.Optional;

import com.ikerpc123.trainoob.modelo.Entrenamiento;

public interface EntrenamientoService {

	Entrenamiento guardarEntrenamiento(Entrenamiento entrenamiento);

	List<Entrenamiento> obtenerEntrenamientosPorEntrenador(int entrenadorId);

	void eliminarEntrenamientoPorId(int id);

	Entrenamiento obtenerPorId(int id);

}
