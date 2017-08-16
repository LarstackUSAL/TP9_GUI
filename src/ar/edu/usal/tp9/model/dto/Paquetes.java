package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

public class Paquetes {

	private ArrayList<String> localidades;
	private Calendar fechaHoraSalida;
	private Calendar fechaHoraLlegada;
	private double importe;
	private boolean tieneSeguro;
	private Pasajeros pasajero;
	private Facturas factura;
	private boolean quiereVisitasGuiadas;
	private boolean quiereAbonoTransporteLocal;
	
	public Paquetes(){}
	
	public Paquetes(ArrayList<String> localidades, Calendar fechaHoraSalida,
			Calendar fechaHoraLlegada, double importe, boolean tieneSeguro,
			Pasajeros pasajero, boolean quiereVisitasGuiadas, boolean quiereAbonoTransporteLocal) {
		super();
		this.localidades = localidades;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.importe = importe;
		this.tieneSeguro = tieneSeguro;
		this.pasajero = pasajero;
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

	public Pasajeros getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajeros pasajero) {
		this.pasajero = pasajero;
	}

	public Facturas getFacturas() {
		return factura;
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
