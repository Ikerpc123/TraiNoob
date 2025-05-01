package com.ikerpc123.trainoob.controlador;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Entrenamiento;
import com.ikerpc123.trainoob.repositorio.EntrenadorRepository;
import com.ikerpc123.trainoob.servicio.EntrenamientoService;

@Controller
@RequestMapping("/entrenador")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping("/crear-entrenamiento")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenamiento", new Entrenamiento());
        return "crearEntrenamiento";
    }

    @PostMapping("/crear-entrenamiento")
    public String crearEntrenamiento(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam String descripcion) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // el email del usuario autenticado

        Entrenador entrenador = entrenadorRepository.findByUsuarioEmail(email);
        if (entrenador == null) return "redirect:/login";

        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setNombre(nombre);
        entrenamiento.setFecha(fecha);
        entrenamiento.setDescripcion(descripcion);
        entrenamiento.setEntrenador(entrenador);

        entrenamientoService.guardarEntrenamiento(entrenamiento);

        return "redirect:/menuEntrenador";
    }
}
