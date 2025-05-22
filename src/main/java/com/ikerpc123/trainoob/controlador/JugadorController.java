package com.ikerpc123.trainoob.controlador;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.ikerpc123.trainoob.modelo.Entrenamiento;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Progreso;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.EntrenadorRepository;
import com.ikerpc123.trainoob.servicio.EntrenamientoService;
import com.ikerpc123.trainoob.servicio.JugadorService;
import com.ikerpc123.trainoob.servicio.ProgresoService;
import com.ikerpc123.trainoob.servicio.UsuarioService;

@Controller
public class JugadorController {
	
	@Autowired
    private ProgresoService progresoService;
	
	@Autowired
    private JugadorService jugadorService;
	
	@Autowired
    private UsuarioService usuarioService;

	@Autowired
    private EntrenamientoService entrenamientoService;
	
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
    public String menuEntrenador(Model model) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	Optional<Usuario> usuario = usuarioService.findByEmail(email);
        Jugador jugador = jugadorService.buscarPorUsuario(usuario);

        model.addAttribute("jugador", jugador);
        model.addAttribute("entrenamientos", jugador.getEntrenamientos());
        model.addAttribute("progresos", jugador.getProgresos());
        return "menuJugador";
    }
    
    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        Jugador jugador = jugadorService.buscarPorUsuario(usuario);
        model.addAttribute("jugador", jugador);
        return "perfilJugador";
    }
    
    @GetMapping("/progreso")
    public String verProgresoJugador(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuario = usuarioService.findByEmail(email);

        if (usuario == null) return "redirect:/login";

        Jugador jugador = jugadorService.buscarPorUsuario(usuario);
        List<Progreso> progresos = progresoService.buscarPorJugadorId(jugador.getId());

        model.addAttribute("progresos", progresos);
        return "progreso";
    }
    
    @GetMapping("/calendario")
    public String verCalendario(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        
        if (usuario.isPresent()) {
            Jugador jugador = jugadorService.buscarPorUsuario(usuario);
            
            List<Entrenamiento> entrenamientos = jugador.getEntrenamientos(); 

            model.addAttribute("entrenamientos", entrenamientos);
        } else {
            model.addAttribute("entrenamientos", Collections.emptyList());
        }

        return "calendario";
    }

}