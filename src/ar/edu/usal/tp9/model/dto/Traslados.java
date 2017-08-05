package ar.edu.usal.tp9.model.dto;

import java.util.Calendar;

public class Traslados {

	private String origen;
	private String destino;
	private Calendar fechaHoraSalida;
	private Calendar fechaHoraLlegada;
	private String tipoTransporte;
	private String empresa;
	private double importe;
	
	public Traslados(String origen, String destino, Calendar fechaHoraSalida,
			Calendar fechaHoraLlegada, String tipoTransporte, String empresa, double importe) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.tipoTransporte = tipoTransporte;
		this.empresa = empresa;
		this.importe = importe;
	}
	public Traslados() {
		super();
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
	public String getTipoTransporte() {
		return tipoTransporte;
	}
	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
	
}
