package com.ikerpc123.trainoob.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.trainoob.modelo.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
}
