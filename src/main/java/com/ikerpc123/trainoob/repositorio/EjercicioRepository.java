package com.ikerpc123.trainoob.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.trainoob.modelo.Ejercicio;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
	
}

