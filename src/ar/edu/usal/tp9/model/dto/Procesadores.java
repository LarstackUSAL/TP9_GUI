package ar.edu.usal.tp9.model.dto;

public class Procesadores {
	
	private String marca;
	private String modelo;
	private double frecuencia;
	
	public Procesadores() {}

	public Procesadores(String marca, String modelo, double frecuencia) {
		this.marca = marca;
		this.modelo = modelo;
		this.frecuencia = frecuencia;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public double getFrecuencia() {
		return frecuencia;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setFrecuencia(double frecuencia) {
		this.frecuencia = frecuencia;
	}
	
}
