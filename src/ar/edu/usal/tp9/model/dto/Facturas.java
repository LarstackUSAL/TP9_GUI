package ar.edu.usal.tp9.model.dto;

import java.util.Calendar;

public class Facturas {

	private Calendar fecha;
	private Pasajeros pasajero;
	private char tipo;
	private int numero;
	private double importe;
	
	public Facturas() {}
	
	public Facturas(Calendar fecha, Pasajeros pasajero, char tipo, int numero, double importe) {
		super();
		this.fecha = fecha;
		this.pasajero = pasajero;
		this.tipo = tipo;
		this.numero = numero;
		this.importe = importe;		
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Pasajeros getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajeros pasajero) {
		this.pasajero = pasajero;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
}
