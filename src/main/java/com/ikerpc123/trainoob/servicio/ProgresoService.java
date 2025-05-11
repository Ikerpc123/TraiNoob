package com.ikerpc123.trainoob.servicio;

import java.util.List;

import com.ikerpc123.trainoob.modelo.Progreso;

public interface ProgresoService {

	void guardar(Progreso progreso);

	List<Progreso> buscarPorJugadorId(int id);

	List<Progreso> buscarPorEntrenamientoId(int id);

}
