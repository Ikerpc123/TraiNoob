package com.ikerpc123.trainoob.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.trainoob.modelo.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
}
