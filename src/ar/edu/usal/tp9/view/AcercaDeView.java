package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class AcercaDeView {

//	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout(FlowLayout.RIGHT, 5, 10);
	private static final String LEYENDA_NOMBRE = "Nombre: ";
	private static final String LEYENDA_VERSION = "Version: ";
	private static final String LEYENDA_ANIO = "Año: ";
	private static final String LEYENDA_AUTORES = "Autores: ";
	private static final String NOMBRE = "TP 9";
	private static final String VERSION = "1.0";
	private static final String ANIO = "2017";
	private static final String AUTORES = "Sandobal - Ricciotti";
		
	private JFrame ventana = new JFrame("Acerca de"); 	 
	private JLabel leyendaNombre = new JLabel(LEYENDA_NOMBRE);
	private JTextArea nombre = new JTextArea(NOMBRE);
	private JLabel leyendaVersion = new JLabel(LEYENDA_VERSION);
	private JTextArea version = new JTextArea(VERSION);
	private JLabel leyendaAnio = new JLabel(LEYENDA_ANIO);
	private JTextArea anio = new JTextArea(ANIO);
	private JLabel leyendaAutores = new JLabel(LEYENDA_AUTORES);
	private JTextArea autores = new JTextArea(AUTORES, 2, 1);
	
	private JLabel[] componentesTitulosArray = {leyendaNombre, leyendaVersion, leyendaAnio, leyendaAutores};
	private JTextArea[] componentesArray = {nombre, version, anio, autores};
	
	public AcercaDeView() {
		
		ventana.setSize(350, Constants.VENTANA_ALTO / 2);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		
		GuiUtilities.aplicarFormatoComponentes(ventana, componentesArray);
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesTitulosArray, componentesArray);
		
		ventana.setVisible(true); 

	}
	
}