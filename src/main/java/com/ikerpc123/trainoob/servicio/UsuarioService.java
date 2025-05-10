package com.ikerpc123.trainoob.servicio;

import java.util.Optional;

import com.ikerpc123.trainoob.modelo.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(String nombre, String email, String password, String rol);

	Optional<Usuario> findByEmail(String email);

}
