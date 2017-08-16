package ar.edu.usal.tp9.utils;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuiUtilities {

	public static void aplicarFormatoComponentes(JFrame ventana, JTextArea[] componentesArray) {

		for (int i = 0; i < componentesArray.length; i++)
			aplicarFormatoTextArea(ventana, componentesArray[i]);
		
	}

	public static void agregarComponentesVentana(JFrame ventana, Component[] componentesArray) {
		
		agregarComponentesVentana(ventana, componentesArray, null);
		
	}
	

	public static void agregarComponentesVentana(JPanel pnlPaqueteBuscado, Component[] componentesPaqueteArray) {
		
		agregarComponentesVentana(pnlPaqueteBuscado, componentesPaqueteArray, null);
		
	}
	
	public static void agregarComponentesVentana(Object ventana,
			Component[] componentesTitulosArray, JTextArea[] componentesArray) {
		
		for (int i = 0; i < componentesTitulosArray.length; i++) {
			
			if(ventana instanceof JPanel)
				((JPanel)ventana).add(componentesTitulosArray[i]);
			else
				((JFrame)ventana).add(componentesTitulosArray[i]);
			
			if(componentesArray != null){
				if(ventana instanceof JPanel)
					((JPanel)ventana).add(componentesArray[i]);
				else
					((JFrame)ventana).add(componentesArray[i]);
			}
			
		}
		
	}

	public static void aplicarFormatoTextArea(JFrame ventana, JTextArea textArea){
		
		textArea.setEditable(false);	
		textArea.setLineWrap(true);	
		textArea.setWrapStyleWord(true); 
		textArea.setCaretPosition(SwingConstants.LEFT);
		textArea.setBackground(ventana.getBackground());
		textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.setFont(new Font(textArea.getFont().getName(), Font.ITALIC, 
				textArea.getFont().getSize()));
		
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
