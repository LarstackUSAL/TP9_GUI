package ar.edu.usal.tp9.utils;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GuiUtilities {

	public static void aplicarFormatoComponentes(JFrame ventana, JTextArea[] componentesArray, boolean esTitulo) {

		for (int i = 0; i < componentesArray.length; i++) {
			
			aplicarFormato(ventana, componentesArray[i], esTitulo);
		}
		
	}

	public static void agregarComponentesVentana(JFrame ventana,
			Component[] componentesLeyendasArray, JTextArea[] componentesArray) {
		
		for (int i = 0; i < componentesArray.length; i++) {
			
			ventana.add(componentesLeyendasArray[i]);
			ventana.add(componentesArray[i]);
		}
	}

	public static void aplicarFormato(JFrame ventana, JTextArea textArea, boolean esTitulo){
		
		textArea.setEditable(false);	
		textArea.setLineWrap(true);	
		textArea.setWrapStyleWord(true); 
		textArea.setCaretPosition(SwingConstants.LEFT);
		textArea.setBackground(ventana.getBackground());
		
		if(esTitulo){
			textArea.setFont(new Font(textArea.getFont().getName(), Font.ITALIC+Font.BOLD, 
				textArea.getFont().getSize()));
		}		
	}

	public static JMenuItem setearDatosJMenuItem(String nombreItem, ActionListener listener, JMenu menu) {
		
		JMenuItem menuItem = new JMenuItem(nombreItem);
		menuItem.setMnemonic(nombreItem.charAt(0));
		menuItem.setActionCommand(nombreItem);
		menuItem.addActionListener(listener);
		menu.add(menuItem);
		
		return menuItem;
	}
	
}
