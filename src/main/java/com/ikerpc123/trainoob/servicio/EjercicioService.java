package com.ikerpc123.trainoob.servicio;

import java.util.List;

import com.ikerpc123.trainoob.modelo.Ejercicio;

public interface EjercicioService {

	List<Ejercicio> obtenerTodos();

	Ejercicio guardar(Ejercicio ejercicio);

	List<Ejercicio> buscarPorIds(List<Integer> ejercicioIds);


}
