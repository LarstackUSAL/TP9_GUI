package ar.edu.usal.tp9.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private static final int VENTANA_ANCHO = 500;
	private static final int VENTANA_ALTO = 250;
	private static final FlowLayout ESTILO_LAYOUT = new FlowLayout();

	public Menu() {

		super("TP9 - GUI");

		JMenu sistemasMenu = new JMenu("Sistemas");
		sistemasMenu.setMnemonic('S');

		JMenuItem acercaDeItem = new JMenuItem("Acerca de");
		acercaDeItem.setMnemonic('A');
		sistemasMenu.add(acercaDeItem);
		acercaDeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				JOptionPane.showMessageDialog(Menu.this, "Acerca de...");
				new AcercaDe();
				
			}
		});

		JMenuItem salirItem = new JMenuItem("Salir");
		salirItem.setMnemonic('S');
		sistemasMenu.add(salirItem);
		salirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		
		JMenu operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setMnemonic('O');

		JMenuItem ingresoItem = new JMenuItem("Ingreso");
		ingresoItem.setMnemonic('I');
		operacionesMenu.add(ingresoItem);
		
		JMenuItem consyActItem = new JMenuItem("Consulta & Actualización");
		consyActItem.setMnemonic('C');
		operacionesMenu.add(consyActItem);
		
		JMenuItem consMasivaItem = new JMenuItem("Consulta Masiva");
		consMasivaItem.setMnemonic('C');
		operacionesMenu.add(consMasivaItem);
		
	
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(operacionesMenu);
		barra.add(sistemasMenu);

		
	}

	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(VENTANA_ANCHO, VENTANA_ALTO);
		menu.setVisible(true);
		menu.setLayout(ESTILO_LAYOUT);

	}

}
