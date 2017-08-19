package ar.edu.usal.tp9.model.dto;


public class Hoteles {

	private String nombre;
	private double importe;
	private int estrellas;
	
	public Hoteles() {
		
		super();
		
		this.nombre = "";
		this.setImporte(0.0);
		this.estrellas = 1;
	}

	public Hoteles(String nombre, double importe, int estrellas) {
		super();
		this.nombre = nombre;
		this.setImporte(importe);
		this.estrellas = estrellas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
}
