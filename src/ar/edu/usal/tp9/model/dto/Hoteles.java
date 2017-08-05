package ar.edu.usal.tp9.model.dto;

public class Hoteles {

	private String nombre;
	private String localidad;
	private int estrellas;
	
	public Hoteles() {
		super();
	}

	public Hoteles(String nombre, String localidad, int estrellas) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.estrellas = estrellas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
	
}
