package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "progresos")
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id")
    private Entrenamiento entrenamiento;

    private int puntuacion;
    private String comentario;

    // Métodos adicionales
    public void registrarProgreso(int nuevaPuntuacion, String nuevoComentario) {
        this.puntuacion = nuevaPuntuacion;
        this.comentario = nuevoComentario;
    }

    // Getters y Setters
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}
	
	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}

