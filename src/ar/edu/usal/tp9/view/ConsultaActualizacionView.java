package ar.edu.usal.tp9.view;

import javax.swing.JFrame;

import ar.edu.usal.tp9.controller.ConsultaActualizacionController;
import ar.edu.usal.tp9.utils.Constants;

public class ConsultaActualizacionView {
	
	private JFrame ventana = new JFrame("Consulta y actualizacion");
	
	private ConsultaActualizacionController consultaActualizacionController;

	public ConsultaActualizacionView(ConsultaActualizacionController consultaActualizacionController) {
		
		//debe consultar los datos de un paquete con filtros por pasajero y por localidad/fecha de salida
		
		consultaActualizacionController.setView(this);
		this.consultaActualizacionController = consultaActualizacionController;
		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		
		
		
		ventana.setVisible(true);

	}	

}
