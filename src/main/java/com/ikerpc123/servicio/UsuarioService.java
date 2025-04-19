package com.ikerpc123.servicio;

import com.ikerpc123.modelo.Usuario;

public interface UsuarioService {

	Usuario crearUsuario(String nombre, String email, String password, String rol);

}
