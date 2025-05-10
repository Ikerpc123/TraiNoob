package com.ikerpc123.trainoob.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.UsuarioRepository;
import com.ikerpc123.trainoob.servicio.UsuarioService;

@Service
public class UsuarioServImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(String nombre, String email, String password, String rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasena(password);
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

	@Override
	public  Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}

