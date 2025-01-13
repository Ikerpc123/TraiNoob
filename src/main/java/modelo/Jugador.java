package modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int edad;
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    @OneToMany(mappedBy = "jugador")
    private List<Progreso> progresos;

    @ManyToMany(mappedBy = "jugadores")
    private List<Entrenamiento> entrenamientos;

    // Métodos de negocio si es necesario
    public void consultarProgreso() {
        // Lógica para consultar el progreso
    }

    public void verEntrenamientos() {
        // Lógica para ver entrenamientos
    }

    // Getters y Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Entrenador getEntrenador() {
		return entrenador;
	}
	
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	
	public List<Progreso> getProgresos() {
		return progresos;
	}
	
	public void setProgresos(List<Progreso> progresos) {
		this.progresos = progresos;
	}
	
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}
	
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}
}

