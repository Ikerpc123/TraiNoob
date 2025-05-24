package com.ikerpc123.trainoob.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Progreso;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.servicio.EntrenadorService;
import com.ikerpc123.trainoob.servicio.JugadorService;
import com.ikerpc123.trainoob.servicio.ProgresoService;
import com.ikerpc123.trainoob.servicio.UsuarioService;

@Controller
public class EntrenadorController {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	EntrenadorService entrenadorService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	JugadorService jugadorService;
	@Autowired
	ProgresoService progresoService;

	@GetMapping("/registroEntrenador")
    public String registroEntrenador() {
        return "registroEntrenador";
    }
	
	@PostMapping("/registroEntrenador")
	public String registro(@RequestParam String nombre,
	                       @RequestParam String email,
	                       @RequestParam String password,
	                       Model model) {

	    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    Pattern patronEmail = Pattern.compile(emailRegex);

	    String nombreRegex = "^[a-zA-Z\\s]+$";
	    Pattern patronNombre = Pattern.compile(nombreRegex);

	    Matcher validarEmail = patronEmail.matcher(email);
	    Matcher validarNombre = patronNombre.matcher(nombre);

	    if (!validarEmail.matches()) {
	        model.addAttribute("error", "Ingrese un correo electrónico válido.");
	        return "registro";
	    }

	    if (!validarNombre.matches()) {
	        model.addAttribute("error", "Ingrese un nombre válido. (Sin números ni caracteres especiales)");
	        return "registro";
	    }
	    
	    // Encriptar la contraseña antes de guardarla
        String passwordEncriptada = passwordEncoder.encode(password);

	    // Validaciones correctas: registrar
	    Usuario nuevoUsuario = usuarioService.crearUsuario(nombre, email, passwordEncriptada, "ENTRENADOR");
	    entrenadorService.registrarEntrenador(nuevoUsuario);

	    return "redirect:/login";
	}
	
	@GetMapping("/menuEntrenador")
    public String menuEntrenador() {
        return "menuEntrenador";
    }
	
	@GetMapping("/evolucion")
	public String verEstadisticasJugadores(Model model) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Usuario usuario = usuarioService.findByEmail(email).orElseThrow();
	    Entrenador entrenador = entrenadorService.findByUsuario(usuario);

	    List<Jugador> jugadores = jugadorService.buscarPorEntrenador(entrenador);

	    List<Map<String, Object>> estadisticas = new ArrayList<>();

	    for (Jugador jugador : jugadores) {
	        List<Progreso> progresos = progresoService.buscarPorJugadorId(jugador.getId());

	        double promedio = progresos.stream()
	                                   .mapToInt(Progreso::getPuntuacion)
	                                   .average()
	                                   .orElse(0);

	        String ultimoComentario = progresos.isEmpty()
	            ? "Sin evaluar"
	            : progresos.get(progresos.size() - 1).getComentario();

	        Map<String, Object> datos = new HashMap<>();
	        datos.put("jugador", jugador.getNombre());
	        datos.put("total", progresos.size());
	        datos.put("promedio", promedio);
	        datos.put("comentario", ultimoComentario);

	        estadisticas.add(datos);
	    }

	    model.addAttribute("estadisticas", estadisticas);
	    return "statJugadores";
	}
}
