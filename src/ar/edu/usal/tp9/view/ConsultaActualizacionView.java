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

import ar.edu.usal.tp9.controller.ConsultaActualizacionController;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class ConsultaActualizacionView {
	
	private JFrame ventana = new JFrame("Consulta y actualizacion");
	
	private JLabel lblPasajero = new JLabel("Seleccionar pasajero: ");
	private JComboBox cmbPasajeros;
	
	private JLabel lblLocalidades = new JLabel("Seleccionar localidad: ");
	private JComboBox cmbLocalidades;
	
	private JButton btnConsultar =  new JButton("Consultar");
	private JButton btnModificar =  new JButton("Modificacion");
	private JButton btnAnular =  new JButton("Anulacion");
	private JButton btnAceptar =  new JButton("Aceptar");
	private JButton btnCancelar =  new JButton("Cancelar");
	
	//////////////
	private JLabel lblPasajeroPaquete = new JLabel("Pasajero: ");
	private JComboBox cmbPasajerosPaquete;
	
	private JLabel lblLocalidadesPaquete = new JLabel("Localidades: ");
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
	private JComboBox cmbHorarios;
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
	////////////////
	
	private ConsultaActualizacionController consultaActualizacionController;

	public ConsultaActualizacionView(ConsultaActualizacionController consultaActualizacionController) {
		
		consultaActualizacionController.setView(this);
		this.consultaActualizacionController = consultaActualizacionController;
		
		GuiUtilities.aplicarFormatoVentana(ventana);
		
		cmbHorarios = new JComboBox(this.consultaActualizacionController.getTurnosFromTxt());

		cmbPasajeros = new JComboBox(this.consultaActualizacionController.getPasajerosFromTxt());
		
		cmbLocalidades = new JComboBox(this.consultaActualizacionController.getLocalidadesFromTxt());
		
		GuiUtilities.setearComandoBoton(btnConsultar, "Consultar", consultaActualizacionController);
		GuiUtilities.setearComandoBoton(btnModificar, "Modificacion", consultaActualizacionController);
		GuiUtilities.setearComandoBoton(btnAnular, "Anulacion", consultaActualizacionController);
		
		Component[] componentesArray = {lblPasajero, cmbPasajeros, lblLocalidades, cmbLocalidades, 
				btnConsultar, btnModificar, btnAnular};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);
		
		ocultarVisibilizarBotonesVentana(false);

		////////////////
		cmbPasajerosPaquete = new JComboBox(this.consultaActualizacionController.getPasajerosFromTxt());

		int opcionSeleccion = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
		
		listaLocalidadesOriginal = new JList(this.consultaActualizacionController.getLocalidadesFromTxt());
		GuiUtilities.aplicarFormatoLista(ventana, listaLocalidadesOriginal, opcionSeleccion);
		scrPaneOriginal = new JScrollPane(listaLocalidadesOriginal); 

		GuiUtilities.setearComandoBoton(btnAgregar, "Agregar", consultaActualizacionController);
		GuiUtilities.setearComandoBoton(btnQuitar, "Quitar", consultaActualizacionController);

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
		cmbHorarios.addActionListener(consultaActualizacionController);
		
		comboModel = new DefaultComboBoxModel();
		cmbHoras = new JComboBox(comboModel);
		cmbHoras.setMaximumRowCount(Constants.ITEMS_MOSTRAR);
		
		GuiUtilities.aplicarFormatoTextField(ventana, txtFechaLlegada);
		txtFechaLlegada.setText("dd/mm/yyyy");
//		hay que hacer un click event para que borre ejemplo modelo
//		falta agregar el horario de llegada en base a los viajes disponibles
		
		rdbSi = new JRadioButton("Si");
		GuiUtilities.aplicarFormatoRadioButton(ventana, rdbSi, rdbSi.getText(), consultaActualizacionController);
		rdbNo = new JRadioButton("No");
		GuiUtilities.aplicarFormatoRadioButton(ventana, rdbNo, rdbNo.getText(), consultaActualizacionController);
		rdbOcultoSeguro = new JRadioButton("");
		rdbOcultoSeguro.setVisible(false);
		
		grpSeguro = new ButtonGroup();
		this.addBotonesGrupo(grpSeguro);
		
		GuiUtilities.aplicarFormatoTextArea(ventana, leyenda);
		leyenda.setBorder(BorderFactory.createLineBorder(Color.RED));
		leyenda.setVisible(false);
		
		cmbHoteles = new JComboBox(this.consultaActualizacionController.getHotelesFromTxt());
		
		GuiUtilities.aplicarFormatoTextField(ventana, txtImporte);
		txtImporte.setEditable(false);
//		txtImporte.setText(((Double)ingresoController.calcularImporte()).toString());
//		de que manera se ejecuta la funcion para saber el importe en base a las selecciones anteriores?
//		hay que implementar interfaz
		
		GuiUtilities.setearComandoBoton(btnAceptar, "Aceptar", consultaActualizacionController);
		GuiUtilities.setearComandoBoton(btnCancelar, "Cancelar", consultaActualizacionController);
		
		Component[] componentesPaqueteArray = {lblPasajeroPaquete, cmbPasajerosPaquete, lblLocalidadesPaquete, 
				scrPaneOriginal, btnAgregar, btnQuitar, pnlCopia, scrPaneCopia, lblFechaSalida, txtFechaSalida, 
				lblHorarioSalida, cmbHorarios, cmbHoras, lblFechaLlegada, txtFechaLlegada, lblSeguro, 
				rdbOcultoSeguro, rdbSi, rdbNo, leyenda, quiereAbonoTransporteLocal, quiereVisitasGuiadas, 
				lblHoteles, cmbHoteles, esPensionCompleta, lblImporte, txtImporte, btnAceptar, btnCancelar};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesPaqueteArray);
		this.ocultarVisibilizarComponentesVentana(componentesPaqueteArray, true, false);
		
		ventana.setVisible(true);

	}

	public void ocultarVisibilizarBotonesVentana(boolean visible){
		
		btnModificar.setVisible(visible);
		btnAnular.setVisible(visible);

	}

	public void ocultarVisibilizarComponentesVentana(Component[] componentes, boolean visible, boolean habilitado) {
		
		for (int i = 0; i < componentes.length; i++) {
				
			componentes[i].setVisible(visible);
			componentes[i].setEnabled(habilitado);
			
		}
		
		leyenda.setVisible(false);
		
	}
	
	private void addBotonesGrupo(ButtonGroup grupoBotones) {

		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);
				
	}
	
}