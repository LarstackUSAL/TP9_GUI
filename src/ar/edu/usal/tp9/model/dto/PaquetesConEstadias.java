package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;

public class PaquetesConEstadias extends Paquetes {

	private ArrayList<Hoteles> hoteles;
	private boolean esPensionCompleta;
		
	public PaquetesConEstadias() {
		super();
	}
	
	public PaquetesConEstadias(ArrayList<Hoteles> hoteles,
			boolean esPensionCompleta) {
		super();
		this.hoteles = hoteles;
		this.esPensionCompleta = esPensionCompleta;		
	}
	
	public ArrayList<Hoteles> getHoteles() {
		return hoteles;
	}
	
	public void setHoteles(ArrayList<Hoteles> hoteles) {
		this.hoteles = hoteles;
	}
	
	public boolean isEsPensionCompleta() {
		return esPensionCompleta;
	}
	
	public void setEsPensionCompleta(boolean esPensionCompleta) {
		this.esPensionCompleta = esPensionCompleta;
	}
	
}
