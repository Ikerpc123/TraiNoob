package com.ikerpc123.trainoob.modelo;

import java.util.List;

import jakarta.persistence.*;

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

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

    // Métodos de negocio si es necesario
    public void crearEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamientos.add(entrenamiento);
    }

    public void asignarEjercicio(Ejercicio ejercicio, Entrenamiento entrenamiento) {
        entrenamiento.addEjercicio(ejercicio);
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
	
	public List<Jugador> getEquipo() {
		return equipo;
	}
	
	public void setEquipo(List<Jugador> equipo) {
		this.equipo = equipo;
	}
	
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}
	
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

