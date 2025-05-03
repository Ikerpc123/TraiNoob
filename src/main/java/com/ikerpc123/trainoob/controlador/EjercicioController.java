package com.ikerpc123.trainoob.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
