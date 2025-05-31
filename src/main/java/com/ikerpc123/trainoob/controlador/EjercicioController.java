package com.ikerpc123.trainoob.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikerpc123.trainoob.modelo.Ejercicio;
import com.ikerpc123.trainoob.servicio.EjercicioService;

@Controller
@RequestMapping("/entrenador")
public class EjercicioController {
	
	@Autowired
    private EjercicioService ejercicioService;

    @GetMapping("/biblioteca-ejercicios")
    public String verBiblioteca(Model model) {
        model.addAttribute("ejercicios", ejercicioService.obtenerTodos());
        return "bibliotecaEjercicios";
    }
    
    @GetMapping("/crear-ejercicio")
    public String mostrarFormulario(Model model) {
        model.addAttribute("ejercicio", new Ejercicio());
        return "crearEjercicio";
    }
    
    @PostMapping("/crear-ejercicio")
    public String crearEjercicio(@ModelAttribute("ejercicio") Ejercicio ejercicio) {
        ejercicioService.guardar(ejercicio);
        return "redirect:/entrenador/biblioteca-ejercicios?success=true";
    }

}
