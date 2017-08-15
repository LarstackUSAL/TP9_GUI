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

import ar.edu.usal.tp9.controller.IngresoController;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class IngresoView {
		
	private JFrame ventana = new JFrame("Ingreso de datos del paquete");
	
	private JLabel lblPasajero = new JLabel("Pasajero: ");
	private JLabel lblLocalidades = new JLabel("Localidades: ");
	private JList listaLocalidadesOriginal;
	private JScrollPane scrPanelOriginal;
	private JButton btnAgregar = new JButton("Agregar >>");
	private JButton btnQuitar = new JButton("<< Quitar");
	private JList listaLocalidadesCopia;
	private JScrollPane scrPanelCopia;
	private DefaultListModel listModel;
	private JPanel pnlCopia;
	
	private JLabel lblFechaSalida = new JLabel("Fecha salida (dd/mm/yyyy): ");
	private JTextField txtFechaSalida = new JTextField(Constants.TEXTO_ANCHO);
	
	private JLabel lblHorarioSalida = new JLabel("Horario salida: ");
	private JComboBox cmbHorarios = new JComboBox(Constants.strHorarios);
	private JComboBox cmbHoras;
	private DefaultComboBoxModel comboModel;

	private JLabel lblFechaLlegada = new JLabel("Fecha llegada (dd/mm/yyyy): ");
	private JTextField txtFechaLlegada = new JTextField(Constants.TEXTO_ANCHO);

	private JComboBox cmbPasajeros;
	
	private JLabel lblSeguro = new JLabel("Quiere seguro?: ");
	private JRadioButton rdbSi;
	private JRadioButton rdbNo;
	private JRadioButton rdbOcultoSeguro;
	private ButtonGroup grpSeguro;

	private JComboBox cmbHoteles;
	private JCheckBox esPensionCompleta = new JCheckBox("Pension completa: ");
	private JCheckBox quiereVisitasGuiadas = new JCheckBox("Requiere guia: ");
	private JCheckBox quiereAbonoTransporteLocal = new JCheckBox("Requiere abono transporte: ");
	
	private static final String LEYENDA = "Brindar copia de condiciones y limites del contrato de seguro junto " +
			"con los datos de contacto ante cualquier emergencia"; 	 
	private JTextArea leyenda = new JTextArea(LEYENDA, 4, Constants.TEXTO_ANCHO);
	
	private JLabel lblImporte = new JLabel("Importe: ");
	private JTextField txtImporte = new JTextField(Constants.TEXTO_ANCHO);
	
	private JButton btnAceptar =  new JButton("Aceptar");
	private JButton btnCancelar =  new JButton("Cancelar");

	private IngresoController ingresoController;

//	LA PANTALLA GRAFICA PARA EL INGRESO DE TODA LA INFORMACION DEBE INCLUIR:
//	FALTA ALINEAR LAS VENTANATAS Y LOS MENSAJES EN EL CENTRO
/*
 * El soft es para una asociacion que organiza viajes. Por lo tanto carga las personas en el jlist para crear el grupo.
 */

	public IngresoView(IngresoController ingresoController) {
		
		ingresoController.setView(this);
		this.ingresoController = ingresoController;
		
		GuiUtilities.aplicarFormato(ventana, leyenda);
		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);

		this.listaLocalidadesOriginal = new JList(this.ingresoController.getLocalidadesFromTxt());
		
		listaLocalidadesOriginal.setVisibleRowCount(Constants.ITEMS_MOSTRAR);
		listaLocalidadesOriginal.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.scrPanelOriginal = new JScrollPane(listaLocalidadesOriginal); 

		btnAgregar.setActionCommand("Agregar");
		btnAgregar.addActionListener(ingresoController);
		
		btnQuitar.setActionCommand("Quitar");
		btnQuitar.addActionListener(ingresoController);

		this.listModel = new DefaultListModel();
		pnlCopia = new JPanel();
		pnlCopia.setBorder(listaLocalidadesOriginal.getBorder());
		pnlCopia.setBackground(listaLocalidadesOriginal.getBackground());
		pnlCopia.setForeground(listaLocalidadesOriginal.getForeground());
		
		listaLocalidadesCopia = new JList(listModel);
		listaLocalidadesCopia.setVisibleRowCount(Constants.ITEMS_MOSTRAR);
//		listaLocalidadesCopia.setFixedCellWidth(200);
//		listaLocalidadesCopia.setFixedCellHeight(15);
		listaLocalidadesCopia.setOpaque(true);
		listaLocalidadesCopia.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.scrPanelCopia = new JScrollPane(listaLocalidadesCopia); 
		pnlCopia.add(listaLocalidadesCopia);
		
		cmbHorarios.setActionCommand("Seleccionar");
		cmbHorarios.addActionListener(ingresoController);
		
		this.comboModel = new DefaultComboBoxModel();
		cmbHoras = new JComboBox(comboModel);
		cmbHoras.setMaximumRowCount(Constants.ITEMS_MOSTRAR);

		cmbPasajeros = new JComboBox(this.ingresoController.getPasajerosFromTxt());
		
		rdbSi = new JRadioButton("Si");
		rdbSi.setMnemonic('S');
		rdbSi.setActionCommand("Si");
		rdbSi.addActionListener(ingresoController);
		rdbNo = new JRadioButton("No");
		rdbNo.setMnemonic('N');
		rdbNo.setActionCommand("No");
		rdbNo.addActionListener(ingresoController);
		rdbOcultoSeguro = new JRadioButton("");
		rdbOcultoSeguro.setVisible(false);
		
		grpSeguro = new ButtonGroup();
		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);
		
		GuiUtilities.aplicarFormato(ventana, leyenda);
		leyenda.setBackground(Color.RED);
		leyenda.setVisible(false);
		
		cmbHoteles = new JComboBox(this.ingresoController.getHotelesFromTxt());
		
		txtImporte.setEditable(false);
		txtImporte.setText(((Double)ingresoController.calcularImporte()).toString());
		
		btnAceptar.setActionCommand("Aceptar");
		btnAceptar.addActionListener(ingresoController);
		
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.addActionListener(ingresoController);
		
		Component[] componentesArray = {lblPasajero, cmbPasajeros, lblLocalidades, scrPanelOriginal, 
				btnAgregar, btnQuitar, pnlCopia, scrPanelCopia,  
				lblHorarioSalida, cmbHorarios, cmbHoras, lblFechaSalida, txtFechaSalida, lblFechaLlegada, 
				txtFechaLlegada, lblSeguro, rdbOcultoSeguro, rdbSi, rdbNo, leyenda,
				quiereAbonoTransporteLocal, quiereVisitasGuiadas, cmbHoteles,
				esPensionCompleta, lblImporte, txtImporte, btnAceptar, btnCancelar};
		
		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);
		
		ventana.setVisible(true);
		
	}
	
	public void limpiar() {
		
		listaLocalidadesOriginal.clearSelection();
		listModel.removeAllElements();
		cmbHorarios.removeAllItems();
		comboModel.removeAllElements();
		String output =  "";
		txtFechaSalida.setText(output);
		txtFechaLlegada.setText(output);
		txtImporte.setText(output);
		rdbOcultoSeguro.setSelected(true);
		
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

}
