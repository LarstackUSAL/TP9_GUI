package ar.edu.usal.tp9.model.dto;

import java.util.Calendar;

public class Facturas {

	private char tipo;
	private double importe;
	private int numero;
	private Calendar fecha;
	
	public Facturas(char tipo, double importe, int numero, Calendar fecha) {
		super();
		this.tipo = tipo;
		this.importe = importe;
		this.numero = numero;
		this.fecha = fecha;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	
	
}
