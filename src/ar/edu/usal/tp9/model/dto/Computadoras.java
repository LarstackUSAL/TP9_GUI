package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;

public class Computadoras {

	private Procesadores procesador;
	private int capacidadRam;
	private DiscosRigidos discoRigido;
	private ArrayList<PuertosUsb> puertosUsbIntegrados = new ArrayList<PuertosUsb>();
	private String arquitectura;
	
	public Computadoras() {}
	
	public Computadoras(Procesadores procesador, int capacidadRam, DiscosRigidos discoRigido, 
			PuertosUsb[] puertosUsbIntegrados, String arquitectura) {
		
		super();
		this.procesador = procesador;
		this.capacidadRam = capacidadRam;
		this.discoRigido = discoRigido;
		//this.puertosUsbIntegrados = puertosUsbIntegrados;
		this.arquitectura = arquitectura;
	}

	public Procesadores getProcesador() {
		return procesador;
	}
	
	public int getCapacidadRam() {
		return capacidadRam;
	}
	
	public DiscosRigidos getDiscoRigido() {
		return discoRigido;
	}
	
	public ArrayList<PuertosUsb> getPuertosUsbIntegrados() {
		return puertosUsbIntegrados;
	}

	public String getArquitectura() {
		return arquitectura;
	}
	
	public void setProcesador(Procesadores procesador) {
		this.procesador = procesador;
	}
	
	public void setCapacidadRam(int capacidadRam) {
		this.capacidadRam = capacidadRam;
	}
	
	public void setDiscoRigido(DiscosRigidos discoRigido) {
		this.discoRigido = discoRigido;
	}
	
//	public void setPuertosUsbIntegrados(ArrayList<PuertosUsb> puertosUsbIntegrados) {
//		this.puertosUsbIntegrados = puertosUsbIntegrados;
//	}
	
	public PuertosUsb createPuertosUsbIntegrados(double version) {
		
		PuertosUsb puertoUsb = new PuertosUsb(version); 
		this.puertosUsbIntegrados.add(puertoUsb);
		
		return puertoUsb;
	}

	public void setArquitectura(String arquitectura) {
		this.arquitectura = arquitectura;
	}
	 	
}
