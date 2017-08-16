package ar.edu.usal.tp9.view;

import javax.swing.JFrame;

import ar.edu.usal.tp9.controller.ConsultaMasivaController;
import ar.edu.usal.tp9.utils.Constants;

public class ConsultaMasivaView {
	
	private JFrame ventana = new JFrame("Consulta masiva");
	
	private ConsultaMasivaController consultaMasivaController;

	public ConsultaMasivaView(ConsultaMasivaController consultaMasivaController) {

		consultaMasivaController.setView(this);
		this.consultaMasivaController = consultaMasivaController;
		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		
		
		
		ventana.setVisible(true);
		
	}

}
