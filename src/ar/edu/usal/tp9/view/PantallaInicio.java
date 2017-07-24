package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PantallaInicio {

	private static final int VENTANA_ANCHO = 275; 
	private static final int VENTANA_ALTO = 250;	
	private static final int ANCHO_TEXTO = 20; 	
	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout();

	private static final String LEYENDA = "Seleccionar una opcion de menu";
	private JFrame ventana = new JFrame("TP9"); 	 
	private JTextArea leyenda = new JTextArea(LEYENDA,2, ANCHO_TEXTO);
	
	public PantallaInicio() {
		
		ventana.setSize(VENTANA_ANCHO, VENTANA_ALTO);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leyenda.setEditable(false);	
		leyenda.setLineWrap(true);	
		leyenda.setWrapStyleWord(true); 	
		leyenda.setBackground(ventana.getBackground());
		ventana.setLayout(ESTILO_LAYOUT);
		ventana.add(leyenda);
		ventana.setVisible(true); 
		
	}
	
	public static void main(String args[]) {

		new PantallaInicio();
		
	}
	
}
