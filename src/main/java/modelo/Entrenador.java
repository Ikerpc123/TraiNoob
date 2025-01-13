package modelo;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "entrenador")
    private List<Jugador> equipo;

    @OneToMany(mappedBy = "entrenador")
    private List<Entrenamiento> entrenamientos;

    // Métodos de negocio si es necesario
    public void crearEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    public void asignarEjercicio(Ejercicio ejercicio, Entrenamiento entrenamiento) {
        entrenamiento.addEjercicio(ejercicio);
    }

    // Getters y Setters
}

