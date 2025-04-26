package com.ikerpc123.trainoob.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikerpc123.trainoob.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);

	@Query("SELECT u FROM Usuario u WHERE u.nombre = :usuario")
	Usuario findByUsuario(@Param("usuario") String usuario);
}
