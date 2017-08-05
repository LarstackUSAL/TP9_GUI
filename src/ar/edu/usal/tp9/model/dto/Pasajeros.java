package ar.edu.usal.tp9.model.dto;

import java.util.Calendar;

public class Pasajeros {
	
	private String nombreApellido;
	private Calendar fechaNacimiento;
	private int dni;
	private String email;
	
	
	public Pasajeros(String nombreApellido, Calendar fechaNacimiento, int dni,
			String email) {
		super();
		this.nombreApellido = nombreApellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.email = email;
	}
	public Pasajeros() {
		super();
	}
	
	public String getNombreApellido() {
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
