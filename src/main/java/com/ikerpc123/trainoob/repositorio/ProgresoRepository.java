package com.ikerpc123.trainoob.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.trainoob.modelo.Progreso;

@Repository
public interface ProgresoRepository extends JpaRepository<Progreso, Integer>{

	List<Progreso> findByJugadorId(int jugadorId);
    List<Progreso> findByEntrenamientoId(int entrenamientoId);
}
