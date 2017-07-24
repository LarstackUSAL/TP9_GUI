package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AcercaDe {

	private static final int VENTANA_ANCHO = 275; 
	private static final int VENTANA_ALTO = 250;	
	private static final int ANCHO_TEXTO = 20; 	
	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout();
	private static final String LEYENDA_NOMBRE = "Nombre: ";
	private static final String LEYENDA_VERSION = "Versión: ";
	private static final String LEYENDA_ANIO = "Año: ";
	private static final String LEYENDA_AUTORES = "Autores: ";
	
	private JFrame ventana = new JFrame("Acerca de"); 	 
	private JTextArea leyendaNombre = new JTextArea(LEYENDA_NOMBRE,2, ANCHO_TEXTO);
	private Font leyendaNombreFont = new Font(leyendaNombre.getFont().getName(), Font.ITALIC+Font.BOLD, 
			leyendaNombre.getFont().getSize());  
	private JTextArea leyendaVersion = new JTextArea(LEYENDA_VERSION,2, ANCHO_TEXTO);
	private Font leyendaVersionFont = new Font(leyendaVersion.getFont().getName(), Font.ITALIC+Font.BOLD, 
			leyendaVersion.getFont().getSize());  
	private JTextArea leyendaAnio = new JTextArea(LEYENDA_ANIO,2, ANCHO_TEXTO);
	private Font leyendaAnioFont = new Font(leyendaAnio.getFont().getName(), Font.ITALIC+Font.BOLD, 
			leyendaAnio.getFont().getSize());
	private JTextArea leyendaAutores = new JTextArea(LEYENDA_AUTORES,2, ANCHO_TEXTO);
	private Font leyendaAutoresFont = new Font(leyendaAutores.getFont().getName(), Font.ITALIC+Font.BOLD, 
			leyendaAutores.getFont().getSize());
	
	public AcercaDe() {
		
		ventana.setSize(VENTANA_ANCHO, VENTANA_ALTO);
//		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leyendaNombre.setEditable(false);	
		leyendaNombre.setLineWrap(true);	
		leyendaNombre.setWrapStyleWord(true); 
		leyendaNombre.setCaretPosition(SwingConstants.LEFT);
		leyendaNombre.setFont(leyendaNombreFont);
		leyendaNombre.setBackground(ventana.getBackground());
		
		leyendaVersion.setEditable(false);	
		leyendaVersion.setLineWrap(true);	
		leyendaVersion.setWrapStyleWord(true); 
		leyendaVersion.setCaretPosition(SwingConstants.LEFT);
		leyendaVersion.setFont(leyendaVersionFont);
		leyendaVersion.setBackground(ventana.getBackground());
		
		leyendaAnio.setEditable(false);	
		leyendaAnio.setLineWrap(true);	
		leyendaAnio.setWrapStyleWord(true); 
		leyendaAnio.setCaretPosition(SwingConstants.LEFT);
		leyendaAnio.setFont(leyendaAnioFont);
		leyendaAnio.setBackground(ventana.getBackground());
		
		leyendaAutores.setEditable(false);	
		leyendaAutores.setLineWrap(true);	
		leyendaAutores.setWrapStyleWord(true); 
		leyendaAutores.setCaretPosition(SwingConstants.LEFT);
		leyendaAutores.setFont(leyendaAutoresFont);
		leyendaAutores.setBackground(ventana.getBackground());
		
		ventana.setLayout(ESTILO_LAYOUT);
		ventana.add(leyendaNombre);
		ventana.add(leyendaVersion);
		ventana.add(leyendaAnio);
		ventana.add(leyendaAutores);
		ventana.setVisible(true); 

	}
	
	public static void main(String args[]) {

		new AcercaDe();
		
	}
	
}