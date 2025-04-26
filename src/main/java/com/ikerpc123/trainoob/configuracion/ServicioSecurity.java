package com.ikerpc123.trainoob.configuracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.UsuarioRepository;

@Service
public class ServicioSecurity implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private String usuario;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario credencial = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        usuario = email;
        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE_" + credencial.getRol()));

        return new User(credencial.getEmail(), credencial.getContrasena(), roles);
    }

    public String getUsuario() {
        return usuario;
    }
}

