package ar.edu.usal.tp9.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ar.edu.usal.tp9.controller.IngresoController;
import ar.edu.usal.tp9.model.dao.PasajerosDao;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.utils.GuiUtilities;
import ar.edu.usal.tp9.utils.Validador;

public class IngresoView {

	private JFrame ventana = new JFrame("Ingreso de datos");

	private JLabel lblPasajero = new JLabel("Pasajeros: ");
//	private JComboBox cmbPasajeros;
	private JList listaPasajerosOriginal;
	private JScrollPane scrPane2Original;
	private JButton btn2Agregar = new JButton("Agregar >>");
	private JButton btn2Quitar = new JButton("<< Quitar");
	private DefaultListModel listModelPasajeros;
	private JList listaPasajerosCopia;
	private JScrollPane scrPane2Copia;
	private JPanel pnl2Copia;

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
	private JComboBox cmbHorarios;
	private JComboBox cmbHoras;
	private DefaultComboBoxModel comboModel;
	private JLabel lblCantidadDias = new JLabel("Cantidad de dias: ");
	private JTextField txtCantidadDias = new JTextField(Constants.TEXTO_ANCHO * 3/4);
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

	private JButton btnAceptar =  new JButton("Calcular Importe");
	private JButton btnCancelar =  new JButton("Cancelar");

	private IngresoController ingresoController;

	
	public IngresoView(IngresoController ingresoController) {

		ingresoController.setView(this);
		this.ingresoController = ingresoController;

		GuiUtilities.aplicarFormatoVentana(ventana);

		cmbHorarios = new JComboBox(this.ingresoController.getTurnosFromTxt());

		int opcionSeleccion = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

		PasajerosDao pasajerosDao = PasajerosDao.getInstance();
		ArrayList<Pasajeros> pasajerosList = pasajerosDao.getPasajeros();
		
		Object[] pasajeros = pasajerosList.toArray();

		listaPasajerosOriginal = new JList(pasajeros);

		GuiUtilities.aplicarFormatoLista(ventana, listaPasajerosOriginal, opcionSeleccion);
		scrPane2Original = new JScrollPane(listaPasajerosOriginal); 

		GuiUtilities.setearComandoBoton(btn2Agregar, "AgregarPasajero", ingresoController);
		GuiUtilities.setearComandoBoton(btn2Quitar, "Quitar", ingresoController);

		listModelPasajeros = new DefaultListModel();

		listaPasajerosCopia = new JList(listModelPasajeros);
		GuiUtilities.aplicarFormatoLista(ventana, listaPasajerosCopia, opcionSeleccion);
		scrPane2Copia = new JScrollPane(listaPasajerosCopia); 

		pnl2Copia = new JPanel();
		GuiUtilities.aplicarFormatoPanel(ventana, pnl2Copia, listaPasajerosOriginal, listaPasajerosCopia);

		listaLocalidadesOriginal = new JList(this.ingresoController.getLocalidadesFromTxt());
		GuiUtilities.aplicarFormatoLista(ventana, listaLocalidadesOriginal, opcionSeleccion);
		scrPaneOriginal = new JScrollPane(listaLocalidadesOriginal); 

		GuiUtilities.setearComandoBoton(btnAgregar, "AgregarLocalidad", ingresoController);
		GuiUtilities.setearComandoBoton(btnQuitar, "Quitar", ingresoController);

		listModel = new DefaultListModel();

		listaLocalidadesCopia = new JList(listModel);
		GuiUtilities.aplicarFormatoLista(ventana, listaLocalidadesCopia, opcionSeleccion);
		scrPaneCopia = new JScrollPane(listaLocalidadesCopia); 

		pnlCopia = new JPanel();
		GuiUtilities.aplicarFormatoPanel(ventana, pnlCopia, listaLocalidadesOriginal, listaLocalidadesCopia);

		GuiUtilities.aplicarFormatoTextField(ventana, txtFechaSalida);
		txtFechaSalida.setText("dd/mm/yyyy");

		txtFechaSalida.addMouseListener(new MouseAdapter() { 

			public void mousePressed(MouseEvent e) { 

				txtFechaSalida.setText("");
			}
		});

		cmbHorarios.setActionCommand("Seleccionar");
		cmbHorarios.addActionListener(ingresoController);

		comboModel = new DefaultComboBoxModel();
		cmbHoras = new JComboBox(comboModel);
		cmbHoras.setMaximumRowCount(Constants.ITEMS_MOSTRAR);

		GuiUtilities.aplicarFormatoTextField(ventana, txtCantidadDias);
		txtCantidadDias.setText("1");

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

		GuiUtilities.setearComandoBoton(btnAceptar, "Calcular", ingresoController);
		GuiUtilities.setearComandoBoton(btnCancelar, "Cancelar", ingresoController);		

		Component[] componentesArray = {lblPasajero, scrPane2Original, btn2Agregar, btn2Quitar, 
				pnl2Copia, scrPane2Copia, lblLocalidades, scrPaneOriginal, 
				btnAgregar, btnQuitar, pnlCopia, scrPaneCopia, lblFechaSalida, txtFechaSalida, 
				lblHorarioSalida, cmbHorarios, cmbHoras, lblCantidadDias, txtCantidadDias, 
				lblSeguro, rdbOcultoSeguro, rdbSi, rdbNo, leyenda, quiereAbonoTransporteLocal, 
				quiereVisitasGuiadas, lblHoteles, cmbHoteles, esPensionCompleta, lblImporte, 
				txtImporte, btnAceptar, btnCancelar};

		GuiUtilities.agregarComponentesVentana(ventana, componentesArray);

		ventana.setVisible(true);

	}

	public void limpiar() {

		this.habilitarCampos(true);
		
		String output =  "dd/mm/yyyy";

//		cmbPasajeros.setSelectedIndex(0);
		listModelPasajeros.removeAllElements();
		listaLocalidadesOriginal.clearSelection();
		listModel.removeAllElements();
		txtFechaSalida.setText(output);
		cmbHorarios.setSelectedIndex(0);
		comboModel.removeAllElements();
		txtCantidadDias.setText("1");
		cmbHoteles.setSelectedIndex(0);
		txtImporte.setText("");
		rdbOcultoSeguro.setSelected(true);
		esPensionCompleta.setSelected(false);
		quiereAbonoTransporteLocal.setSelected(false);
		quiereVisitasGuiadas.setSelected(false);
		this.btnAceptar.setText("Calcular Importe");
		GuiUtilities.setearComandoBoton(btnAceptar, "Calcular", ingresoController);
	}
	
	public void habilitarCampos(boolean b) {

//		cmbPasajeros.setEnabled(b);
		listaPasajerosOriginal.setEnabled(b);
		listaPasajerosCopia.setEnabled(b);
		listaLocalidadesOriginal.setEnabled(b);
		listaLocalidadesCopia.setEnabled(b);
		txtFechaSalida.setEnabled(b);
		cmbHorarios.setEnabled(b);
		cmbHoras.setEnabled(b);
		txtCantidadDias.setEnabled(b);
		cmbHoteles.setEnabled(b);
		txtImporte.setEnabled(b);
		rdbOcultoSeguro.setEnabled(b);
		esPensionCompleta.setEnabled(b);
		quiereAbonoTransporteLocal.setEnabled(b);
		quiereVisitasGuiadas.setEnabled(b);
		btnAgregar.setEnabled(b);
		btnQuitar.setEnabled(b);
		rdbSi.setEnabled(b);
		rdbNo.setEnabled(b);
	}

	private void addBotonesGrupo(ButtonGroup grupoBotones) {

		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);

	}

	public JList getListaLocalidadesOriginal() {
		return listaLocalidadesOriginal;		
	}

	public DefaultListModel getModeloLocalidades() {
		return listModel;
	}
	
	public DefaultListModel getModeloPasajeros() {
		return listModelPasajeros;
	}

	public JFrame getVentana() {
		return ventana;
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

	public JTextField getTxtFechaSalida() {
		return txtFechaSalida;
	}

//	public JComboBox getCmbPasajeros() {
//		return cmbPasajeros;
//	}
	
	public JList getListaPasajerosCopia() {
		return listaPasajerosCopia;
	}

	public JButton getBtn2Quitar() {
		return btn2Quitar;
	}

	public void setBtn2Quitar(JButton btn2Quitar) {
		this.btn2Quitar = btn2Quitar;
	}

	public JList getListaPasajerosOriginal() {
		return listaPasajerosOriginal;
	}

	public JCheckBox getQuiereAbonoTransporteLocal() {
		return quiereAbonoTransporteLocal;
	}

	public JCheckBox getQuiereVisitasGuiadas() {
		return quiereVisitasGuiadas;
	}

	public ButtonGroup getGrpSeguro() {
		return grpSeguro;
	}

	public JComboBox getCmbHoteles() {
		return cmbHoteles;
	}

	public JTextField getTxtCantidadDias() {
		return txtCantidadDias;
	}

	public JCheckBox getEsPensionCompleta() {
		return esPensionCompleta;
	}

	public boolean validar() {

		ArrayList<String> errores = new ArrayList<>();
		boolean datosValidos = true;

		if(this.listModel.getSize() < 1){

			errores.add("localidades");
		}
		
//		if(this.cmbPasajeros.getSelectedIndex() <= 0){
//
//			errores.add("pasajero");
//		}
		
		if(this.listModelPasajeros.getSize() < 1){

			errores.add("pasajeros");
		}
		
		if(this.txtFechaSalida.getText().trim().isEmpty()
				|| this.txtFechaSalida.getText().trim().equals("dd/mm/yyyy")
				){

			errores.add("fecha salida");
		}else{

			try{
				int dia = Integer.valueOf(this.txtFechaSalida.getText().trim().substring(0,2));
				int mes = Integer.valueOf(this.txtFechaSalida.getText().trim().substring(3,5));
				int anio = Integer.valueOf(this.txtFechaSalida.getText().trim().substring(6,10));

				if(!((dia > 0 && dia <=31) && (mes > 0 && mes <= 12) && (anio >= 2017 && anio <= 9999))){

					errores.add("fecha salida");
				}
			}catch(NumberFormatException e){

				errores.add("fecha salida");
			}
		}

		if(this.cmbHorarios.getSelectedIndex() <= 0){

			errores.add("turno");
		}
		if(this.cmbHoras.getSelectedIndex() <= 0){

			errores.add("hora");
		}
		try{

			Integer cantidadDias = Integer.valueOf(txtCantidadDias.getText());

			if(cantidadDias < 1){

				errores.add("cantidad de dias");	
			}

		}catch(Exception ex){

			errores.add("cantidad de dias");
		}

		if(!errores.isEmpty()){

			String error = "Los datos de:\n";

			for (int i = 0; i < errores.size(); i++) {

				error = error + "\n- " + errores.get(i);
			}

			error += "\n\nson invalidos.";

			datosValidos = false;

			JOptionPane.showMessageDialog(null, error, "Datos obligatorios", 
					JOptionPane.INFORMATION_MESSAGE);
		}

		return datosValidos;
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
		this.cerrar();
	}

	public void mostrarImporte(double importe) {

		this.txtImporte.setText(String.valueOf(importe));
		
		this.habilitarCampos(false);
		
		this.btnAceptar.setText("Aceptar");
		GuiUtilities.setearComandoBoton(btnAceptar, "Aceptar", ingresoController);

	}

}
