package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;

public class Hoteles {

	private String nombre;
	private ArrayList<String> localidades;
	private int estrellas;
	
	public Hoteles() {
		super();
		
		this.localidades = new ArrayList<String>(); 
	}

	public Hoteles(String nombre, ArrayList<String> localidades, int estrellas) {
		super();
		this.nombre = nombre;
		this.localidades = localidades;
		this.estrellas = estrellas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(ArrayList<String> localidades) {
		this.localidades = localidades;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
}
