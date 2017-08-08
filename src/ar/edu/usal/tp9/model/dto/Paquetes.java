package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

public class Paquetes {

	private String origen;
	private String destino;
	private Calendar fechaHoraSalida;
	private Calendar fechaHoraLlegada;
	private double importe;
	private boolean tieneSeguro;
	private int cantidadPasajeros;
	private ArrayList<Pasajeros> pasajeros;
	private Facturas factura;
	
	public Paquetes(){}
	
	public Paquetes(String origen, String destino, Calendar fechaHoraSalida,
			Calendar fechaHoraLlegada, double importe, boolean tieneSeguro,
			int cantidadPasajeros, ArrayList<Pasajeros> pasajeros) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.importe = importe;
		this.tieneSeguro = tieneSeguro;
		this.cantidadPasajeros = cantidadPasajeros;
		this.pasajeros = pasajeros;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Calendar getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(Calendar fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public Calendar getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(Calendar fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public boolean isTieneSeguro() {
		return tieneSeguro;
	}

	public void setTieneSeguro(boolean tieneSeguro) {
		this.tieneSeguro = tieneSeguro;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public void setCantidadPasajeros(int cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}

	public ArrayList<Pasajeros> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(ArrayList<Pasajeros> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Facturas getFactura() {
		return factura;
	}
	
}
