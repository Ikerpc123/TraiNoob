package com.ikerpc123.trainoob.configuracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikerpc123.trainoob.modelo.Ejercicio;
import com.ikerpc123.trainoob.repositorio.EjercicioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class InicioEjercicio {
	
	@Autowired
    private EjercicioRepository ejercicioRepo;
	
	@PostConstruct
    public void cargarEjerciciosIniciales() {
        if (!ejercicioRepo.findAll().isEmpty())
    	{
        	System.err.println("Los ejercicios ya están creados");
        	return;
    	};

        List<Ejercicio> ejercicios = List.of(
            crear("Tiro con defensor", "El jugador botará intentando quitarse o romper la defensa para terminar en un tiro", 5, "/media/ejercicios/tiro.png"),
            
            crear("Uno contra uno a todo el campo", "Dos jugadores compiten en 1c1 comenzando desde línea de fondo. Uno ataca mientras el otro defiende. Se colocan conos para guiar el recorrido. Se trabaja el manejo de balón, defensa y finalización.",
            		10, "/media/ejercicios/1c1.jpeg"),
            
            crear("Drill de Australia", "Los jugadores reciben pase desde la esquina, lanzan a canasta y luego corren al lado opuesto de la cancha para repetir el ejercicio. Se enfoca en tiro, pase rápido y resistencia.",
            		8, "/media/ejercicios/australia.png"),
            
            crear("Finalización desde pase bajo el aro", "El jugador 5 da un pase al centro mientras los jugadores 6 y 1 cortan desde el perímetro hacia el aro para una bandeja o tiro cercano. Mejora la reacción, la finalización y la visión de pase.",
            		6, "/media/ejercicios/entradas.png"),
            
            crear("Conducción en zig-zag", "Los jugadores driblan alrededor de conos en un patrón en zig-zag hasta finalizar con entrada a canasta. Mejora el control de balón y los cambios de dirección.",
            		7, "/media/ejercicios/dribling.jpeg")
        );

        ejercicioRepo.saveAll(ejercicios);
        System.err.println("Ejercicios creados correctamente");
        
    }
	
	
	private Ejercicio crear(String nombre, String descripcion, int duracion, String imagenUrl) {
        Ejercicio ej = new Ejercicio();
        ej.setNombre(nombre);
        ej.setDescripcion(descripcion);
        ej.setDuracion(duracion);
        ej.setImagenUrl(imagenUrl);
        return ej;
    }

}
