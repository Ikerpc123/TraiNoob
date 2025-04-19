package com.ikerpc123.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);
}
