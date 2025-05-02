package com.ikerpc123.trainoob.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.repositorio.EntrenadorRepository;
import com.ikerpc123.trainoob.servicio.JugadorService;

@Controller
public class JugadorController {
	 @Autowired
	    private JugadorService jugadorService;

	    @Autowired
	    private EntrenadorRepository entrenadorRepository;

	    @GetMapping("/registroJugador")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("entrenadores", entrenadorRepository.findAll());
	        return "registroJugador";
	    }

	    @PostMapping("/registroJugador")
	    public String registrarJugador(@RequestParam String nombre,
	                                   @RequestParam int edad,
	                                   @RequestParam String categoria,
	                                   @RequestParam String email,
	                                   @RequestParam String password,
	                                   @RequestParam int entrenadorId,
	                                   Model model) {

	        Entrenador entrenador = entrenadorRepository.findById(entrenadorId).orElse(null);
	        if (entrenador == null) {
	            model.addAttribute("error", "Entrenador no válido");
	            return "registroJugador";
	        }

	        jugadorService.registrarJugadorConUsuario(nombre, edad, categoria, email, password, entrenador);
	        return "redirect:/login";
	    }
	    
	    @GetMapping("/menuJugador")
	    public String menuEntrenador() {
	        return "menuJugador";
	    }
}