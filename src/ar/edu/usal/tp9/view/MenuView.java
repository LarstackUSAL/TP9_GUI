package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ar.edu.usal.tp9.controller.MenuController;
import ar.edu.usal.tp9.utils.GuiUtilities;


public class MenuView extends JFrame {

	private static final int VENTANA_ANCHO = 500;
	private static final int VENTANA_ALTO = 250;
	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout();
	private MenuController menuController;
	

	public MenuView(MenuController menuController) {

		super("TP9 - GUI");
		
		menuController.setView(this);
		this.menuController = menuController;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(VENTANA_ANCHO, VENTANA_ALTO);
		
		this.setLayout(ESTILO_LAYOUT);

		
		JMenu sistemasMenu = new JMenu("Sistemas");
		sistemasMenu.setMnemonic('S');

		JMenuItem acercaDeItem = GuiUtilities.setearDatosJMenuItem("Acerca de", menuController, sistemasMenu);
		JMenuItem salirItem = GuiUtilities.setearDatosJMenuItem("Salir", menuController, sistemasMenu);

		JMenu operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setMnemonic('O');

		JMenuItem ingresoItem = GuiUtilities.setearDatosJMenuItem("Ingreso", menuController, operacionesMenu);		
		JMenuItem consyActItem = GuiUtilities.setearDatosJMenuItem("Consulta y Actualizacion", menuController, operacionesMenu);
		JMenuItem consMasivaItem = GuiUtilities.setearDatosJMenuItem("Consulta Masiva", menuController, operacionesMenu);
		
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(operacionesMenu);
		barra.add(sistemasMenu);

		this.setVisible(true);
	}

}
