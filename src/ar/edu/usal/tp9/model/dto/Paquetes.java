package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

public class Paquetes {

	private ArrayList<String> localidades;
	private Calendar fechaHoraSalida;
	private Calendar fechaHoraLlegada;
	private double importe;
	private boolean tieneSeguro;
	private int cantidadPasajeros;	//es necesario este atributo???
	private Pasajeros pasajero;
	private Facturas factura;
	private boolean esViajeInternacional;	//es necesario este atributo???
	private boolean quiereVisitasGuiadas;
	private boolean quiereAbonoTransporteLocal;
	
	public Paquetes(){}
	
	public Paquetes(ArrayList<String> localidades, Calendar fechaHoraSalida,
			Calendar fechaHoraLlegada, double importe, boolean tieneSeguro,
			int cantidadPasajeros, Pasajeros pasajero, boolean esViajeInternacional, 
			boolean quiereVisitasGuiadas, boolean quiereAbonoTransporteLocal) {
		super();
		this.localidades = localidades;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.importe = importe;
		this.tieneSeguro = tieneSeguro;
		this.cantidadPasajeros = cantidadPasajeros;
		this.pasajero = pasajero;
		this.esViajeInternacional = esViajeInternacional;
		this.quiereVisitasGuiadas = quiereVisitasGuiadas;
		this.quiereAbonoTransporteLocal = quiereAbonoTransporteLocal;
		
	}

	public ArrayList<String> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(ArrayList<String> localidades) {
		this.localidades = localidades;
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

	public Pasajeros getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajeros pasajero) {
		this.pasajero = pasajero;
	}

	public Facturas getFacturas() {
		return factura;
	}

	public boolean isEsViajeInternacional() {
		return esViajeInternacional;
	}

	public void setEsViajeInternacional(boolean esViajeInternacional) {
		this.esViajeInternacional = esViajeInternacional;
	}

	public boolean isQuiereVisitasGuiadas() {
		return quiereVisitasGuiadas;
	}

	public void setQuiereVisitasGuiadas(boolean quiereVisitasGuiadas) {
		this.quiereVisitasGuiadas = quiereVisitasGuiadas;
	}

	public boolean isQuiereAbonoTransporteLocal() {
		return quiereAbonoTransporteLocal;
	}

	public void setQuiereAbonoTransporteLocal(boolean quiereAbonoTransporteLocal) {
		this.quiereAbonoTransporteLocal = quiereAbonoTransporteLocal;
	}
	
}
