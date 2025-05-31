package com.ikerpc123.trainoob.configuracion;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                         HttpServletResponse response, 
                                         Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (hasRole(authorities, "ROLE_ENTRENADOR")) {
            response.sendRedirect("/menuEntrenador");
        } else if (hasRole(authorities, "ROLE_JUGADOR")) {
            response.sendRedirect("/menuJugador");
        } else {
            response.sendRedirect("/");
        }
    }

    private boolean hasRole(Collection<? extends GrantedAuthority> authorities, String role) {
        return authorities.stream().anyMatch(auth -> auth.getAuthority().equals(role));
    }
}
