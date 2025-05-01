package com.ikerpc123.trainoob.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikerpc123.trainoob.modelo.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer> {
}
