package com.ikerpc123.trainoob.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikerpc123.trainoob.configuracion.ServicioSecurity;
import com.ikerpc123.trainoob.modelo.Entrenamiento;
import com.ikerpc123.trainoob.modelo.Jugador;
import com.ikerpc123.trainoob.modelo.Progreso;
import com.ikerpc123.trainoob.modelo.Usuario;
import com.ikerpc123.trainoob.servicio.EntrenamientoService;
import com.ikerpc123.trainoob.servicio.JugadorService;
import com.ikerpc123.trainoob.servicio.ProgresoService;
import com.ikerpc123.trainoob.servicio.UsuarioService;

@Controller
@RequestMapping("entrenador")
public class ProgresoController {

	@Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private ProgresoService progresoService;
    

    @GetMapping("/evaluar-progreso/{id}")
    public String mostrarFormulario(@PathVariable("id") int id, Model model) {
        Entrenamiento entrenamiento = entrenamientoService.obtenerPorId(id);
        model.addAttribute("entrenamiento", entrenamiento);
        return "evaluarProgreso";
    }

    @PostMapping("/evaluar-progreso")
    public String guardarProgreso(
            @RequestParam("entrenamientoId") int entrenamientoId,
            @RequestParam("jugadorIds") List<Integer> jugadorIds,
            @RequestParam("comentarios") List<String> comentarios,
            @RequestParam("puntuaciones") List<Integer> puntuaciones) {

        Entrenamiento entrenamiento = entrenamientoService.obtenerPorId(entrenamientoId);

        for (int i = 0; i < jugadorIds.size(); i++) {
            Progreso p = new Progreso();
            Jugador jugador = new Jugador();
            jugador.setId(jugadorIds.get(i));

            p.setJugador(jugador);
            p.setEntrenamiento(entrenamiento);
            p.setComentario(comentarios.get(i));
            p.setPuntuacion(puntuaciones.get(i));

            progresoService.guardar(p);
        }

        return "redirect:/entrenador/entrenamientos?success2=true";
    }
}
