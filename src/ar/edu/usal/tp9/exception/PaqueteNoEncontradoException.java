package ar.edu.usal.tp9.exception;

public class PaqueteNoEncontradoException extends Exception {

	private String message = "No hay paquetes que correspondan con los parametros ingresados";
	
	@Override
	public String getMessage() {
		
		return message;
	}
}
