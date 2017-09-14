package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

import ar.edu.usal.tp9.model.dao.FacturasDao;
import ar.edu.usal.tp9.utils.Constants;

public class Paquetes {

	protected ArrayList<String> localidades;
	protected Calendar fechaHoraSalida;
	protected int cantidadDias;
	protected double importe;
	protected boolean tieneSeguro;
	protected ArrayList<Pasajeros> pasajeros;
	protected Facturas factura;
	protected boolean quiereVisitasGuiadas;
	protected boolean quiereAbonoTransporteLocal;
	protected int id;
	
	public Paquetes(){}
	
	public Paquetes(int id, ArrayList<String> localidades, Calendar fechaHoraSalida,
			int cantidadDias, double importe, boolean tieneSeguro,
			ArrayList<Pasajeros> pasajeros, boolean quiereVisitasGuiadas, boolean quiereAbonoTransporteLocal) {
		super();
		this.id = id;
		this.localidades = localidades;
		this.fechaHoraSalida = fechaHoraSalida;
		this.setCantidadDias(cantidadDias);
		this.importe = importe;
		this.tieneSeguro = tieneSeguro;
		this.pasajeros = pasajeros;
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

//	public Pasajeros getPasajero() {
//		return pasajeros;
//	}
//
//	public void setPasajero(Pasajeros pasajero) {
//		this.pasajero = pasajero;
//	}
	
	public ArrayList<Pasajeros> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(ArrayList<Pasajeros> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public void generarFactura() {
		
		this.factura = new Facturas();
		
		this.factura.setNumero(FacturasDao.getNextIdFactura());
		this.factura.setFecha(Calendar.getInstance());
		this.factura.setImporte(this.importe);
		this.factura.setTipo(Constants.TIPO_FACTURA);
		
	}

	public void generarFactura(int numeroFactura, int idPaquete, Calendar fecha, char tipo,
			double importe) {

		this.factura = new Facturas();
		
		this.factura.setFecha(fecha);
		this.factura.setImporte(importe);
		this.factura.setNumero(numeroFactura);
//		this.factura.setPasajero(pasajero);
		this.factura.setTipo(tipo);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}
}
