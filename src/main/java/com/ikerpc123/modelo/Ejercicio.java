package com.ikerpc123.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private int duracion;

    @ManyToMany(mappedBy = "ejercicios")
    private List<Entrenamiento> entrenamientos;

    // Métodos adicionales
    public void modificarEjercicio(String nuevoNombre, String nuevaDescripcion, int nuevaDuracion) {
        this.nombre = nuevoNombre;
        this.descripcion = nuevaDescripcion;
        this.duracion = nuevaDuracion;
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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}
	
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}
        
}

