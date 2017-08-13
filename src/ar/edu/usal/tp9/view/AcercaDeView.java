package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import ar.edu.usal.tp9.utils.ConstantsUtils;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class AcercaDeView {

	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout();
	private static final String LEYENDA_NOMBRE = "Nombre: ";
	private static final String LEYENDA_VERSION = "Version: ";
	private static final String LEYENDA_ANIO = "Año: ";
	private static final String LEYENDA_AUTORES = "Autores: ";
	private static final String NOMBRE = "TP 9";
	private static final String AUTORES = "Sandobal - Ricciotti";
	private static final String VERSION = "1.0";
	private static final String ANIO = "2017";
	
	private JFrame ventana = new JFrame("Acerca de"); 	 
	private JTextArea leyendaNombre = new JTextArea(LEYENDA_NOMBRE,2, ConstantsUtils.TEXTO_ANCHO);
	private JTextArea nombre = new JTextArea(NOMBRE);
	private JTextArea leyendaVersion = new JTextArea(LEYENDA_VERSION,2, ConstantsUtils.TEXTO_ANCHO);
	private JTextArea version = new JTextArea(VERSION);
	private JTextArea leyendaAnio = new JTextArea(LEYENDA_ANIO,2, ConstantsUtils.TEXTO_ANCHO);
	private JTextArea anio = new JTextArea(ANIO);
	private JTextArea leyendaAutores = new JTextArea(LEYENDA_AUTORES,2, ConstantsUtils.TEXTO_ANCHO);
	private JTextArea autores = new JTextArea(AUTORES);
	
	private JTextArea[] componentesLeyendasArray = {leyendaNombre, leyendaVersion, leyendaAnio, leyendaAutores};
	private JTextArea[] componentesArray = {nombre, version, anio, autores};
	
	public AcercaDeView() {
		
		ventana.setSize(ConstantsUtils.VENTANA_ANCHO, ConstantsUtils.VENTANA_ALTO);
		ventana.setLayout(ESTILO_LAYOUT);
		
		GuiUtilities.aplicarFormatoComponentes(ventana, componentesLeyendasArray, true);
		GuiUtilities.aplicarFormatoComponentes(ventana, componentesArray, false);
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesLeyendasArray, componentesArray);
		
		ventana.setVisible(true); 

	}
}