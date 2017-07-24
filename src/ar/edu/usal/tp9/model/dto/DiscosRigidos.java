package ar.edu.usal.tp9.model.dto;

public class DiscosRigidos {

	private int rpm;
	private int capacidad;
	private String tipo;
	
	public DiscosRigidos() {}

	public DiscosRigidos(int rpm, int capacidad, String tipo) {
		this.rpm = rpm;
		this.capacidad = capacidad;
		this.tipo = tipo;
	}

	public int getRpm() {
		return rpm;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		
}
