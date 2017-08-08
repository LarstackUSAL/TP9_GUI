package ar.edu.usal.tp9.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ar.edu.usal.tp9.controller.IngresoController;
import ar.edu.usal.tp9.utils.ConstantsUtils;
import ar.edu.usal.tp9.utils.GuiUtilities;

public class IngresoView {
		
	private JFrame ventana = new JFrame("Ingreso de datos");
	
	private static final String LEYENDA = "A continuacion ingrese los datos del paquete"; 	 
	private JTextArea leyenda = new JTextArea(LEYENDA,2, ConstantsUtils.ANCHO_TEXTO);
	
	private JLabel lblOrigen = new JLabel("Origen");
	private JTextField txtOrigen = new JTextField(ConstantsUtils.ANCHO_TEXTO);  
	
	private JLabel lblDestino = new JLabel("Destino");
	private JTextField txtDestino = new JTextField(ConstantsUtils.ANCHO_TEXTO);

	private JLabel lblFechaSalida = new JLabel("Fecha salida (dd/mm/yyyy)");
	private JTextField txtFechaSalida = new JTextField(ConstantsUtils.ANCHO_TEXTO);

	private JLabel lblFechaLlegada = new JLabel("Fecha llegada (dd/mm/yyyy)");
	private JTextField txtFechaLlegada = new JTextField(ConstantsUtils.ANCHO_TEXTO);

	private JLabel lblSeguro = new JLabel("Quiere seguro?");
	private JRadioButton rdbSi;
	private JRadioButton rdbNo;
	private JRadioButton rdbOcultoSeguro;
	private ButtonGroup grpSeguro;
	
	private JButton btnAceptar =  new JButton("Aceptar");
	private JButton btnCancelar =  new JButton("Cancelar");

	private IngresoController ingresoController;
	
	public IngresoView(IngresoController ingresoController) {
		
		ingresoController.setView(this);
		this.ingresoController = ingresoController;
		
		ventana.setSize(ConstantsUtils.VENTANA_ANCHO, ConstantsUtils.VENTANA_ALTO);
		ventana.setLayout(ConstantsUtils.ESTILO_LAYOUT);
		
		GuiUtilities.aplicarFormato(ventana, leyenda, true);
		
		rdbSi = new JRadioButton("Si");
		rdbSi.setMnemonic('S');
		rdbNo = new JRadioButton("No");
		rdbNo.setMnemonic('N');
		rdbOcultoSeguro = new JRadioButton("");
		rdbOcultoSeguro.setVisible(false);
		
		grpSeguro = new ButtonGroup();
		grpSeguro.add(rdbOcultoSeguro);
		grpSeguro.add(rdbSi);
		grpSeguro.add(rdbNo);
		
		ventana.add(leyenda);
		ventana.add(lblOrigen);
		ventana.add(txtOrigen);
		ventana.add(lblDestino);
		ventana.add(txtDestino);
		ventana.add(lblFechaSalida);
		ventana.add(txtFechaSalida);
		ventana.add(lblFechaLlegada);
		ventana.add(txtFechaLlegada);
		ventana.add(lblSeguro);
		ventana.add(rdbOcultoSeguro);
		ventana.add(rdbSi);
		ventana.add(rdbNo);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);
		
		ventana.setVisible(true);
		
		//ventanaPrincipal.add(ventana);

	}
	
}
