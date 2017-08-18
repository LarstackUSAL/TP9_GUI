package ar.edu.usal.tp9.model.dto;


public class PaquetesConEstadias extends Paquetes {

	private Hoteles hotel;
	private boolean esPensionCompleta;
		
	public PaquetesConEstadias() {
		super();
	}
	
	public PaquetesConEstadias(Hoteles hotel,
			boolean esPensionCompleta) {
		super();
		this.hotel = hotel;
		this.esPensionCompleta = esPensionCompleta;		
	}
	
	public Hoteles getHotel() {
		return hotel;
	}
	
	public void setHotel(Hoteles hotel) {
		this.hotel = hotel;
	}
	
	public boolean isEsPensionCompleta() {
		return esPensionCompleta;
	}
	
	public void setEsPensionCompleta(boolean esPensionCompleta) {
		this.esPensionCompleta = esPensionCompleta;
	}
	
}
