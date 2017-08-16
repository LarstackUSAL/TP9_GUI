package ar.edu.usal.tp9.view;

import java.awt.Color;
import java.awt.Component;

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
import javax.swing.SwingConstants;

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
	
	private JPanel pnlPaqueteBuscado = new JPanel();
	//////////////7
	private JLabel lblPasajeroPaquete = new JLabel("Pasajero: ");
	private JComboBox cmbPasajerosPaquete;
	
	private JLabel lblLocalidadesPaquete = new JLabel("Localidades: ");
	private JList listaLocalidadesOriginal;
	private JScrollPane scrPanelOriginal;
	private JButton btnAgregar = new JButton("Agregar >>");
	private JButton btnQuitar = new JButton("<< Quitar");
	private JList listaLocalidadesCopia;
	private JScrollPane scrPanelCopia;
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
	
	////////////////
	private ConsultaActualizacionController consultaActualizacionController;

	public ConsultaActualizacionView(ConsultaActualizacionController consultaActualizacionController) {
		
		//debe consultar los datos de un paquete con filtros por pasajero y por localidad/fecha de salida
		
		consultaActualizacionController.setView(this);
		this.consultaActualizacionController = consultaActualizacionController;
		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		
		cmbPasajeros = new JComboBox(this.consultaActualizacionController.getPasajerosFromTxt());
		
		cmbLocalidades = new JComboBox(this.consultaActualizacionController.getLocalidadesFromTxt());
		
		btnConsultar.setActionCommand("Consultar");
		btnConsultar.addActionListener(consultaActualizacionController);
		
		btnModificar.setActionCommand("Modificacion");
		btnModificar.addActionListener(consultaActualizacionController);

		btnAnular.setActionCommand("Anulacion");
		btnAnular.addActionListener(consultaActualizacionController);
		
		btnAceptar.setActionCommand("Aceptar");
		btnAceptar.addActionListener(consultaActualizacionController);
		
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.addActionListener(consultaActualizacionController);

		Component[] componentesArray = {lblPasajero, cmbPasajeros, lblLocalidades, cmbLocalidades, btnConsultar, btnModificar, btnAnular};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);
		
		ocultarVisibilizarBotonesVentana(false);
		////////////////
//		pnlPaqueteBuscado.setBackground(ventana.getBackground());
		
		cmbPasajerosPaquete = new JComboBox(this.consultaActualizacionController.getPasajerosFromTxt());

		this.listaLocalidadesOriginal = new JList(this.consultaActualizacionController.getLocalidadesFromTxt());
		
		listaLocalidadesOriginal.setVisibleRowCount(Constants.ITEMS_MOSTRAR);
		listaLocalidadesOriginal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.scrPanelOriginal = new JScrollPane(listaLocalidadesOriginal); 

		btnAgregar.setActionCommand("Agregar");
		btnAgregar.addActionListener(consultaActualizacionController);
		
		btnQuitar.setActionCommand("Quitar");
		btnQuitar.addActionListener(consultaActualizacionController);

		this.listModel = new DefaultListModel();
		pnlCopia = new JPanel();
		pnlCopia.setBorder(listaLocalidadesOriginal.getBorder());
		pnlCopia.setBackground(listaLocalidadesOriginal.getBackground());
		pnlCopia.setForeground(listaLocalidadesOriginal.getForeground());
		
		listaLocalidadesCopia = new JList(listModel);
		listaLocalidadesCopia.setVisibleRowCount(Constants.ITEMS_MOSTRAR);
		listaLocalidadesCopia.setOpaque(true);
		listaLocalidadesCopia.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.scrPanelCopia = new JScrollPane(listaLocalidadesCopia); 
		pnlCopia.add(listaLocalidadesCopia);
		
//		txtFechaSalida.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtFechaSalida.setText("dd/mm/yyyy");
		txtFechaSalida.setCaretPosition(SwingConstants.CENTER);
		txtFechaSalida.setHorizontalAlignment(JTextField.CENTER);
//		txtFechaSalida.setActionCommand("Click");
//		txtFechaSalida.addActionListener(ingresoController);
//		hay que hacer un click event para que borre ejemplo modelo
		
		cmbHorarios.setActionCommand("Seleccionar");
		cmbHorarios.addActionListener(consultaActualizacionController);
		
		this.comboModel = new DefaultComboBoxModel();
		cmbHoras = new JComboBox(comboModel);
		cmbHoras.setMaximumRowCount(Constants.ITEMS_MOSTRAR);
		
		txtFechaLlegada.setText("dd/mm/yyyy");
		txtFechaLlegada.setCaretPosition(SwingConstants.CENTER);
		txtFechaLlegada.setHorizontalAlignment(JTextField.CENTER);
//		hay que hacer un click event para que borre ejemplo modelo
//		falta agregar el horario de llegada en base a los viajes disponibles
		
		rdbSi = new JRadioButton("Si");
		rdbSi.setMnemonic('S');
		rdbSi.setActionCommand("Si");
		rdbSi.addActionListener(consultaActualizacionController);
		rdbNo = new JRadioButton("No");
		rdbNo.setMnemonic('N');
		rdbNo.setActionCommand("No");
		rdbNo.addActionListener(consultaActualizacionController);
		rdbOcultoSeguro = new JRadioButton("");
		rdbOcultoSeguro.setVisible(false);
		
		grpSeguro = new ButtonGroup();
		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);
		
		GuiUtilities.aplicarFormatoTextArea(ventana, leyenda);
		leyenda.setBackground(Color.RED);
		
		cmbHoteles = new JComboBox(this.consultaActualizacionController.getHotelesFromTxt());
		
		txtImporte.setBorder(txtFechaSalida.getBorder());
		txtImporte.setCaretPosition(SwingConstants.CENTER);
		txtImporte.setHorizontalAlignment(JTextField.CENTER);
		txtImporte.setEditable(false);
//		txtImporte.setText(((Double)ingresoController.calcularImporte()).toString());
//		de que manera se ejecuta la funcion para saber el importe en base a las selecciones anteriores?
//		hay que implementar interfaz
		
		Component[] componentesPaqueteArray = {lblPasajeroPaquete, cmbPasajerosPaquete, lblLocalidadesPaquete, 
				scrPanelOriginal, btnAgregar, btnQuitar, pnlCopia, scrPanelCopia, lblFechaSalida, txtFechaSalida, 
				lblHorarioSalida, cmbHorarios, cmbHoras, lblFechaLlegada, txtFechaLlegada, lblSeguro, 
				rdbOcultoSeguro, rdbSi, rdbNo, leyenda, quiereAbonoTransporteLocal, quiereVisitasGuiadas, 
				lblHoteles, cmbHoteles, esPensionCompleta, lblImporte, txtImporte, btnAceptar, btnCancelar};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesPaqueteArray);
		ocultarVisibilizarComponentesVentana(componentesPaqueteArray, true, false);
		
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
	
}