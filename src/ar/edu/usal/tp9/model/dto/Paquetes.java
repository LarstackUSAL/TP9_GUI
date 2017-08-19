package ar.edu.usal.tp9.model.dto;

import java.util.ArrayList;
import java.util.Calendar;

import ar.edu.usal.tp9.model.dao.FacturasDao;
import ar.edu.usal.tp9.model.interfaces.ICalculoImporte;
import ar.edu.usal.tp9.utils.Constants;

public class Paquetes {

	private ArrayList<String> localidades;
	private Calendar fechaHoraSalida;
	private int cantidadDias;
	private double importe;
	private boolean tieneSeguro;
	private Pasajeros pasajero;
	private Facturas factura;
	private boolean quiereVisitasGuiadas;
	private boolean quiereAbonoTransporteLocal;
	private int id;
	
	public Paquetes(){}
	
	public Paquetes(int id, ArrayList<String> localidades, Calendar fechaHoraSalida,
			int cantidadDias, double importe, boolean tieneSeguro,
			Pasajeros pasajero, boolean quiereVisitasGuiadas, boolean quiereAbonoTransporteLocal) {
		super();
		this.id = id;
		this.localidades = localidades;
		this.fechaHoraSalida = fechaHoraSalida;
		this.setCantidadDias(cantidadDias);
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

	public void generarFactura() {
		
		this.factura = new Facturas();
		
		this.factura.setNumero(FacturasDao.getNextIdFactura());
		this.factura.setFecha(Calendar.getInstance());
		this.factura.setImporte(this.importe);
		this.factura.setPasajero(this.pasajero);
		this.factura.setTipo(Constants.TIPO_FACTURA);
		
	}
	
	public void generarFactura(int numeroFactura, int idPaquete, Calendar fecha, Pasajeros pasajero, char tipo,
			double importe) {

		this.factura = new Facturas();
		
		this.factura.setFecha(fecha);
		this.factura.setImporte(importe);
		this.factura.setNumero(numeroFactura);
		this.factura.setPasajero(pasajero);
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
