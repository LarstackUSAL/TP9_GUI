package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import ar.edu.usal.tp9.view.ConsultaMasivaView;

public class ConsultaMasivaController implements ActionListener {
	
	private ConsultaMasivaView consultaMasivaView;

	public void setView(ConsultaMasivaView consultaMasivaView) {

		this.consultaMasivaView = consultaMasivaView;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Consultar".equals(e.getActionCommand())) {
		
//			ArrayList <String[]> lista = new ArrayList <String[]> ();
//			
//			Archivos a = new Archivos("C:\\Archivos","Ingresos.txt");
			int contadorReg = 0;
//			lista = a.LeerSeparadoCaracter(";");
//			consultaMasivaView.getContadorRegTotal().setText(String.valueOf(lista.size()));
			
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel = (DefaultTableModel)consultaMasivaView.getTablaResultado().getModel();
			
			this.addColumnasTabla(tableModel);
			
			String pasajero = consultaMasivaView.getPasajero().getText();
			
//			for(String[] registro: lista) {
//				
//				if (registro[0].contains(pasajero)) {
//					
//					contadorReg++;
//					tableModel.addRow(registro);
//					
//				}
//				
//				if (pasajero == "") {
//					
//					contadorReg++;
//					tableModel.addRow(registro);
//				}
//					
//			}
			
			consultaMasivaView.getContadorRegBusqueda().setText(String.valueOf(contadorReg));
					
		}
		
	}

	private void addColumnasTabla(DefaultTableModel tableModel) {
		
		tableModel.addColumn("Pasajero");
		tableModel.addColumn("Localidad/es");
		tableModel.addColumn("Fecha/Hora Salida");
		tableModel.addColumn("Fecha/Hora Llegada");
		tableModel.addColumn("Seguro");
		tableModel.addColumn("Abono Transporte");
		tableModel.addColumn("Guia");
		tableModel.addColumn("Hotel/es");
		tableModel.addColumn("Pension completa");
		tableModel.addColumn("Importe");
		
	}

}
