package ar.edu.usal.tp9.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ar.edu.usal.tp9.controller.IngresoController;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class IngresoView {
		
	private JFrame ventana = new JFrame("Ingreso de datos");
	
	private JLabel lblPasajero = new JLabel("Pasajero: ");
	private JComboBox cmbPasajeros;
	
	private JLabel lblLocalidades = new JLabel("Localidades: ");
	private JList listaLocalidadesOriginal;
	private JScrollPane scrPaneOriginal;
	private JButton btnAgregar = new JButton("Agregar >>");
	private JButton btnQuitar = new JButton("<< Quitar");
	private JList listaLocalidadesCopia;
	private JScrollPane scrPaneCopia;
	private DefaultListModel listModel;
	private JPanel pnlCopia;
	
	private JLabel lblFechaSalida = new JLabel("Fecha salida: ");
	private JTextField txtFechaSalida = new JTextField(Constants.TEXTO_ANCHO * 3/4);
	
	private JLabel lblHorarioSalida = new JLabel("Horario salida: ");
	private JComboBox cmbHorarios = new JComboBox(Constants.strHorarios);
	private JComboBox cmbHoras;
	private DefaultComboBoxModel comboModel;

	private JLabel lblFechaLlegada = new JLabel("Fecha llegada: ");
	private JTextField txtFechaLlegada = new JTextField(Constants.TEXTO_ANCHO * 3/4);

	private JLabel lblSeguro = new JLabel("Quiere seguro?: ");
	private JRadioButton rdbSi;
	private JRadioButton rdbNo;
	private JRadioButton rdbOcultoSeguro;
	private ButtonGroup grpSeguro;
		
	private static final String LEYENDA = "Brindar copia de condiciones y limites del contrato de seguro junto " +
			"con los datos de contacto ante cualquier emergencia"; 	 
	private JTextArea leyenda = new JTextArea(LEYENDA, 4, Constants.TEXTO_ANCHO);

	private JCheckBox quiereAbonoTransporteLocal = new JCheckBox("Requiere abono transporte");
	
	private JCheckBox quiereVisitasGuiadas = new JCheckBox("Requiere guia");
	
	private JLabel lblHoteles = new JLabel("Hotel/es: ");
	private JComboBox cmbHoteles;
	
	private JCheckBox esPensionCompleta = new JCheckBox("Pension completa");
	
	private JLabel lblImporte = new JLabel("Importe: ");
	private JTextField txtImporte = new JTextField(Constants.TEXTO_ANCHO * 3/4);
	
	private JButton btnAceptar =  new JButton("Aceptar");
	private JButton btnCancelar =  new JButton("Cancelar");

	private IngresoController ingresoController;

	/*
	 * El soft es para una asociacion que organiza viajes. Por lo tanto carga las personas en el jlist para crear el grupo.
	 */
	
	
	/*
	 * Falta tp: componentes obligatorios, restantes datos miembros, generar factura, ventana edición, ventana consulta 
	 * masiva, package dao, package interfaces, package exception, borrar comentarios, rehacer diag clases, armar intro tp, 
	 * archivos, unificar funciones aplicacion formatos)
	 */

	public IngresoView(IngresoController ingresoController) {
		
		ingresoController.setView(this);
		this.ingresoController = ingresoController;
		
		GuiUtilities.aplicarFormatoVentana(ventana);
		
		cmbPasajeros = new JComboBox(this.ingresoController.getPasajerosFromTxt());

		int opcionSeleccion = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
		
		listaLocalidadesOriginal = new JList(this.ingresoController.getLocalidadesFromTxt());
		GuiUtilities.aplicarFormatoLista(ventana, listaLocalidadesOriginal, opcionSeleccion);
		scrPaneOriginal = new JScrollPane(listaLocalidadesOriginal); 

		GuiUtilities.setearComandoBoton(btnAgregar, "Agregar", ingresoController);
		GuiUtilities.setearComandoBoton(btnQuitar, "Quitar", ingresoController);
		
		listModel = new DefaultListModel();
		
		listaLocalidadesCopia = new JList(listModel);
		GuiUtilities.aplicarFormatoLista(ventana, listaLocalidadesCopia, opcionSeleccion);
		scrPaneCopia = new JScrollPane(listaLocalidadesCopia); 
		
		pnlCopia = new JPanel();
		GuiUtilities.aplicarFormatoPanel(ventana, pnlCopia, listaLocalidadesOriginal, listaLocalidadesCopia);
				
		GuiUtilities.aplicarFormatoTextField(ventana, txtFechaSalida);
		txtFechaSalida.setText("dd/mm/yyyy");
//		txtFechaSalida.setActionCommand("Click");
//		txtFechaSalida.addActionListener(ingresoController);
//		hay que hacer un click event para que borre ejemplo modelo
		
		cmbHorarios.setActionCommand("Seleccionar");
		cmbHorarios.addActionListener(ingresoController);
		
		comboModel = new DefaultComboBoxModel();
		cmbHoras = new JComboBox(comboModel);
		cmbHoras.setMaximumRowCount(Constants.ITEMS_MOSTRAR);
		
		GuiUtilities.aplicarFormatoTextField(ventana, txtFechaLlegada);
		txtFechaLlegada.setText("dd/mm/yyyy");
//		hay que hacer un click event para que borre ejemplo modelo
//		falta agregar el horario de llegada en base a los viajes disponibles
		
		rdbSi = new JRadioButton("Si");
		GuiUtilities.aplicarFormatoRadioButton(ventana, rdbSi, rdbSi.getText(), ingresoController);
		rdbNo = new JRadioButton("No");
		GuiUtilities.aplicarFormatoRadioButton(ventana, rdbNo, rdbNo.getText(), ingresoController);
		rdbOcultoSeguro = new JRadioButton("");
		rdbOcultoSeguro.setVisible(false);
		
		grpSeguro = new ButtonGroup();
		this.addBotonesGrupo(grpSeguro);
		
		GuiUtilities.aplicarFormatoTextArea(ventana, leyenda);
		leyenda.setBorder(BorderFactory.createLineBorder(Color.RED));
		leyenda.setVisible(false);
		
		cmbHoteles = new JComboBox(this.ingresoController.getHotelesFromTxt());
		
		GuiUtilities.aplicarFormatoTextField(ventana, txtImporte);
		txtImporte.setEditable(false);
//		txtImporte.setText(((Double)ingresoController.calcularImporte()).toString());
//		de que manera se ejecuta la funcion para saber el importe en base a las selecciones anteriores?
//		hay que implementar interfaz
		
		GuiUtilities.setearComandoBoton(btnAceptar, "Aceptar", ingresoController);
		GuiUtilities.setearComandoBoton(btnCancelar, "Cancelar", ingresoController);
		
		Component[] componentesArray = {lblPasajero, cmbPasajeros, lblLocalidades, scrPaneOriginal, 
				btnAgregar, btnQuitar, pnlCopia, scrPaneCopia, lblFechaSalida, txtFechaSalida, 
				lblHorarioSalida, cmbHorarios, cmbHoras, lblFechaLlegada, txtFechaLlegada, 
				lblSeguro, rdbOcultoSeguro, rdbSi, rdbNo, leyenda, quiereAbonoTransporteLocal, 
				quiereVisitasGuiadas, lblHoteles, cmbHoteles, esPensionCompleta, lblImporte, 
				txtImporte, btnAceptar, btnCancelar};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);
		
		ventana.setVisible(true);
		
	}
	
	public void limpiar() {
		
		String output =  "dd/mm/yyyy";
		
		cmbPasajeros.setSelectedIndex(0);
		listaLocalidadesOriginal.clearSelection();
		listModel.removeAllElements();
		txtFechaSalida.setText(output);
		cmbHorarios.setSelectedIndex(0);
		comboModel.removeAllElements();
		txtFechaLlegada.setText(output);
		cmbHoteles.setSelectedIndex(0);
		txtImporte.setText("");
		rdbOcultoSeguro.setSelected(true);
		esPensionCompleta.setSelected(false);
		quiereAbonoTransporteLocal.setSelected(false);
		quiereVisitasGuiadas.setSelected(false);
		
	}
	
	private void addBotonesGrupo(ButtonGroup grupoBotones) {

		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);
				
	}

	public JList getListaLocalidadesOriginal() {
		return listaLocalidadesOriginal;		
	}

	public DefaultListModel getModelo() {
		return listModel;
	}

	public void cerrar() {
		ventana.dispose();
	}

	public JRadioButton getRdbSi() {
		return rdbSi;
	}
	
	public JRadioButton getRdbNo() {
		return rdbNo;
	}

	public JTextArea getLeyenda() {
		return leyenda;
	}

	public JComboBox getComboHorarios() {
		return cmbHorarios;
	}

	public int getComboHorariosIndex() {
		return getComboHorarios().getSelectedIndex();
	}

	public JComboBox getComboHoras() {
		return cmbHoras;
	}

	public DefaultComboBoxModel getComboModel() {
		return comboModel;
	}

	public JList getListaLocalidadesCopia() {
		return listaLocalidadesCopia;
	}

	public JTextField getTxtImporte() {
		return txtImporte;
	}

}
