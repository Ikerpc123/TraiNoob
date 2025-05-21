package com.ikerpc123.trainoob.controlador;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikerpc123.trainoob.modelo.Ejercicio;
import com.ikerpc123.trainoob.modelo.Entrenador;
import com.ikerpc123.trainoob.modelo.Entrenamiento;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.repositorio.EntrenadorRepository;
import com.ikerpc123.trainoob.servicio.EjercicioService;
import com.ikerpc123.trainoob.servicio.EntrenamientoService;
import com.ikerpc123.trainoob.servicio.JugadorService;
import com.ikerpc123.trainoob.servicio.UsuarioService;

@Controller
@RequestMapping("/entrenador")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;
    
    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    private EntrenadorRepository entrenadorRepository;
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/crear-entrenamiento")
    public String mostrarFormulario(Model model) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        Entrenador entrenador = entrenadorRepository.findByUsuarioEmail(email);
        
        List<Jugador> jugadores = jugadorService.buscarPorEntrenador(entrenador);
        
        model.addAttribute("entrenamiento", new Entrenamiento());
        model.addAttribute("jugadorCount", jugadores.size());
        return "crearEntrenamiento";
    }

    @PostMapping("/crear-entrenamiento")
    public String crearEntrenamiento(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam String descripcion) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Entrenador entrenador = entrenadorRepository.findByUsuarioEmail(email);
        if (entrenador == null) return "redirect:/login";
        
        List<Jugador> jugadoresDelEntrenador = jugadorService.buscarPorEntrenador(entrenador);

        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setNombre(nombre);
        entrenamiento.setFecha(fecha);
        entrenamiento.setDescripcion(descripcion);
        entrenamiento.setEntrenador(entrenador);
        entrenamiento.setJugadores(jugadoresDelEntrenador);

        entrenamientoService.guardarEntrenamiento(entrenamiento);

        return "redirect:/menuEntrenador";
    }
    
    @GetMapping("/entrenamientos")
    public String verEntrenamientos(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Entrenador entrenador = entrenadorRepository.findByUsuarioEmail(email);
        if (entrenador == null) return "redirect:/login";

        List<Entrenamiento> entrenamientos = entrenamientoService.obtenerEntrenamientosPorEntrenador(entrenador.getId());
        model.addAttribute("entrenamientos", entrenamientos);

        return "listarEntrenamientos";
    }
    
    @GetMapping("/eliminar-entrenamiento/{id}")
    public String eliminarEntrenamiento(@PathVariable int id) {
        entrenamientoService.eliminarEntrenamientoPorId(id);
        return "redirect:/entrenador/entrenamientos";
    }
    
    @GetMapping("/editar-entrenamiento/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerPorId(id);
        model.addAttribute("entrenamiento", entrenamiento);
        return "editarEntrenamiento";
    }

    @PostMapping("/editar-entrenamiento")
    public String actualizarEntrenamiento(@ModelAttribute("entrenamiento") Entrenamiento entrenamiento) {
        Entrenamiento original = entrenamientoService.obtenerPorId(entrenamiento.getId());

        original.setNombre(entrenamiento.getNombre());
        original.setFecha(entrenamiento.getFecha());
        original.setDescripcion(entrenamiento.getDescripcion());

        entrenamientoService.guardarEntrenamiento(original);

        return "redirect:/entrenador/entrenamientos?success3=true";
    }

    @GetMapping("/asignar-ejercicios/{id}")
    public String mostrarFormularioAsignacion(@PathVariable("id") int id, Model model) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerPorId(id);
        List<Ejercicio> ejercicios = ejercicioService.obtenerTodos();

        model.addAttribute("entrenamiento", entrenamiento);
        model.addAttribute("ejercicios", ejercicios);
        return "asignarEjercicios";
    }
    
    @PostMapping("/asignar-ejercicios")
    public String asignarEjercicios(@RequestParam int entrenamientoId, @RequestParam List<Integer> ejercicioIds) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerPorId(entrenamientoId);
        List<Ejercicio> ejerciciosSeleccionados = ejercicioService.buscarPorIds(ejercicioIds);
        entrenamiento.setEjercicios(ejerciciosSeleccionados);
        entrenamientoService.guardarEntrenamiento(entrenamiento);
        return "redirect:/entrenador/entrenamientos?success1=true";
    }

}
