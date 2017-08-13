package ar.edu.usal.tp9.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ar.edu.usal.tp9.utils.ConstantsUtils;
import ar.edu.usal.tp9.view.IngresoView;

public class IngresoController implements ActionListener{
	
	private IngresoView ingresoView;

	public void setView(IngresoView ingresoView) {
		
		this.ingresoView = ingresoView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("Cancelar".equals(e.getActionCommand())) {

			int rta = JOptionPane.showConfirmDialog((Component)e.getSource(), "Los datos seran borrados. Confirma?", 
					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);

			if (rta == JOptionPane.YES_OPTION) 
				ingresoView.limpiar();

		} else if ("Aceptar".equals(e.getActionCommand())) {

//			ingresoView.validar(); //3 componentes distintos
//			Guardar();
//			Mensaje si todo lo anterior esta ok:
			if(true) {
				JOptionPane.showMessageDialog((Component)e.getSource(), "Datos guardados con exito", "Mensaje", 
						JOptionPane.INFORMATION_MESSAGE);
				ingresoView.cerrar();
			}

		} else if ("Agregar".equals(e.getActionCommand())) {
			
			ArrayList<Object> elementosSeleccionados = (ArrayList<Object>) ingresoView.getListaLocalidadesOriginal().getSelectedValuesList();
			for(Object elemSelec : elementosSeleccionados)
//				CORREGIR - AGREGAR SOLO LOS ELEMENTOS QUE NO ESTEN EN LA LISTA
				ingresoView.getModelo().addElement((String) elemSelec); 
			
		} else if ("Si".equals(e.getActionCommand())) {
						
			if (ingresoView.getRdbSi().isSelected()) {
				ingresoView.getLeyenda().setVisible(true);
			} 
			
		} else if ("No".equals(e.getActionCommand())) {
			
			if (ingresoView.getRdbNo().isSelected()) {
				ingresoView.getLeyenda().setVisible(false);
			} 
			
		} else if ("Seleccionar".equals(e.getActionCommand())) {
			
			if (ingresoView.getComboHorariosIndex() == 1) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < ConstantsUtils.strHorasMañana.length; j++) {
					ingresoView.getComboHoras().addItem(ConstantsUtils.strHorasMañana[j]);
				}
	
			} else if (ingresoView.getComboHorariosIndex() == 2) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < ConstantsUtils.strHorasTarde.length; j++) {
					ingresoView.getComboHoras().addItem(ConstantsUtils.strHorasTarde[j]);
				}
	
			} else if (ingresoView.getComboHorariosIndex() == 3) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < ConstantsUtils.strHorasNoche.length; j++) {
					ingresoView.getComboHoras().addItem(ConstantsUtils.strHorasNoche[j]);
				}
	
			}
			
		}
			
	}

//	Falta hacer
	public double calcularImporte() {
		return 0;
	}

}
