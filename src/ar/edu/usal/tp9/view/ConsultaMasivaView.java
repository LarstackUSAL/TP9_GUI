package ar.edu.usal.tp9.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import ar.edu.usal.tp9.controller.ConsultaMasivaController;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class ConsultaMasivaView {

	private JFrame ventana = new JFrame("Consulta masiva");

	private JLabel lblPasajero = new JLabel("Pasajero: ");
	private JTextField txtPasajero = new JTextField(Constants.TEXTO_ANCHO);

	private JButton btnConsultar =  new JButton("Consultar");

	private JScrollPane scrollPane;

	private JTable tablaResultado = new JTable();

	private JLabel lblContRegBusqueda = new JLabel("Cantidad registros encontrados: ");
	private JTextArea contadorRegBusqueda = new JTextArea();

	private JLabel lblContRegTotal = new JLabel("Cantidad registros total: ");
	private JTextArea contadorRegTotal = new JTextArea();

	private ConsultaMasivaController consultaMasivaController;

	private JTextArea[] componentesTextosArray = {contadorRegBusqueda, contadorRegTotal};
	private DefaultTableModel tableModel = new DefaultTableModel();

	public ConsultaMasivaView(ConsultaMasivaController consultaMasivaController) {

		consultaMasivaController.setView(this);
		this.consultaMasivaController = consultaMasivaController;

		this.tableModel = (DefaultTableModel)this.getTablaResultado().getModel();
		this.addColumnasTabla(tableModel);

		GuiUtilities.aplicarFormatoVentana(ventana);

		GuiUtilities.aplicarFormatoTextField(ventana, txtPasajero);

		GuiUtilities.aplicarFormatoComponentes(ventana, componentesTextosArray);

		GuiUtilities.setearComandoBoton(btnConsultar, "Consultar", consultaMasivaController);

		scrollPane = new JScrollPane(tablaResultado);

		Component[] componentesArray = {lblPasajero, txtPasajero, btnConsultar, scrollPane, lblContRegBusqueda, 
				contadorRegBusqueda, lblContRegTotal, contadorRegTotal};

		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);

		ventana.setVisible(true);

	}

	private void addColumnasTabla(DefaultTableModel tableModel) {

		tableModel.setColumnIdentifiers(
				new String[]{
						"Pasajero",
						"Localidad/es",
						"Fecha/Hora Salida",
						"Cantidad dias",
						"Seguro",
						"Abono Transporte",
						"Guia",
						"Hotel",
						"Pension completa",
						"Importe"
				});

	}

	public JTable getTablaResultado() {

		return tablaResultado;

	}

	public JTextField getPasajero() {

		return txtPasajero;

	}

	public JTextArea getContadorRegTotal() {

		return contadorRegTotal;

	}

	public JTextArea getContadorRegBusqueda() {

		return contadorRegBusqueda;

	}

	public void mostrarRegistros(ArrayList<String[]> registros,
			String cantidadRegistros, String cantidadRegistrosTotales) {

		for (int i = 0; i < registros.size(); i++) {

			this.tableModel.addRow(registros.get(i));
		}

		this.contadorRegBusqueda.setText(cantidadRegistros);
		this.contadorRegTotal.setText(cantidadRegistrosTotales);
	}
}
