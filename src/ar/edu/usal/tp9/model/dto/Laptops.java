package ar.edu.usal.tp9.model.dto;

public class Laptops extends Computadoras {

	private boolean tieneWebCam;
	private boolean tieneWifi;
	private boolean tieneBluetooth;
	private String resolucionPantalla;
	
	public Laptops() {}

	public Laptops(boolean tieneWebCam, boolean tieneWifi, boolean tieneBluetooth, String resolucionPantalla) {
		this.tieneWebCam = tieneWebCam;
		this.tieneWifi = tieneWifi;
		this.tieneBluetooth = tieneBluetooth;
		this.resolucionPantalla = resolucionPantalla;
	}

	public boolean isTieneWebCam() {
		return tieneWebCam;
	}

	public boolean isTieneWifi() {
		return tieneWifi;
	}

	public boolean isTieneBluetooth() {
		return tieneBluetooth;
	}

	public String getResolucionPantalla() {
		return resolucionPantalla;
	}

	public void setTieneWebCam(boolean tieneWebCam) {
		this.tieneWebCam = tieneWebCam;
	}

	public void setTieneWifi(boolean tieneWifi) {
		this.tieneWifi = tieneWifi;
	}

	public void setTieneBluetooth(boolean tieneBluetooth) {
		this.tieneBluetooth = tieneBluetooth;
	}

	public void setResolucionPantalla(String resolucionPantalla) {
		this.resolucionPantalla = resolucionPantalla;
	}
			
}
