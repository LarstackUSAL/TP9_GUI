package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;

public class Paquetes {

	private ArrayList<Pasajeros> pasajeros;
	private ArrayList<Traslados> traslados;
	private boolean tieneSeguro;
	private int cantidadDias;
	private double importe;
	
	public Paquetes() {
		super();
	}

	public Paquetes(ArrayList<Pasajeros> pasajeros, 
			boolean tieneSeguro, int cantidadDias, double importe) {
		super();
		this.pasajeros = pasajeros;
		this.tieneSeguro = tieneSeguro;
		this.cantidadDias = cantidadDias;
		this.importe = importe;
	}

	public ArrayList<Pasajeros> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(ArrayList<Pasajeros> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public boolean isTieneSeguro() {
		return tieneSeguro;
	}

	public void setTieneSeguro(boolean tieneSeguro) {
		this.tieneSeguro = tieneSeguro;
	}

	public int getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public ArrayList<Traslados> getTraslados() {
		return traslados;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
}
