package ar.edu.usal.tp9.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import ar.edu.usal.tp9.model.dao.TablasMaestrasDao;
import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Constants;
import ar.edu.usal.tp9.view.IngresoView;

public class IngresoController implements ActionListener {
	
	private IngresoView ingresoView;

	public void setView(IngresoView ingresoView) {
		
		this.ingresoView = ingresoView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("Cancelar".equals(e.getActionCommand())) {
			
//			int rta = JOptionPane.showConfirmDialog((Component)e.getSource(, "Los datos seran borrados. Confirma?",
//					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);
			int rta = JOptionPane.showConfirmDialog(null, "Los datos seran borrados. Confirma?", 
					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);

			if (rta == JOptionPane.YES_OPTION) 
				ingresoView.limpiar();

		} else if ("Aceptar".equals(e.getActionCommand())) {

//			ingresoView.validar(); //3 componentes distintos
//			Guardar();
//			implentar generacion de factura a partir de los datos guardados, a traves de interfaz?
//			Mensaje si todo lo anterior esta ok:
			if(true) {
				JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Mensaje", 
						JOptionPane.INFORMATION_MESSAGE);
				ingresoView.cerrar();
			}

		} else if ("Agregar".equals(e.getActionCommand())) {
			
			ArrayList<Object> elementosSeleccionados = (ArrayList<Object>) ingresoView.getListaLocalidadesOriginal().getSelectedValuesList();
			for(Object elemSelec : elementosSeleccionados){
				
				if(!(ingresoView.getModelo().contains(((String) elemSelec))))
					ingresoView.getModelo().addElement((String) elemSelec);
			}
		
		} else if ("Quitar".equals(e.getActionCommand())) {
			
			int[] elementosSeleccionados = ingresoView.getListaLocalidadesCopia().getSelectedIndices();
			
			for(int i = elementosSeleccionados.length-1; i>=0; i--)
				ingresoView.getModelo().remove(elementosSeleccionados[i]);
		
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
				for (int j = 0; j < Constants.strHorasManana.length; j++) {
					ingresoView.getComboHoras().addItem(Constants.strHorasManana[j]);
				}

			} else if (ingresoView.getComboHorariosIndex() == 2) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < Constants.strHorasTarde.length; j++) {
					ingresoView.getComboHoras().addItem(Constants.strHorasTarde[j]);
				}
	
			} else if (ingresoView.getComboHorariosIndex() == 3) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < Constants.strHorasNoche.length; j++) {
					ingresoView.getComboHoras().addItem(Constants.strHorasNoche[j]);
				}
	
			} 
			
		}
						
	}

//	Falta hacer
	public double calcularImporte() {
		return 0;
	}

	public Object[] getLocalidadesFromTxt() {
		
		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		
		return tablasMaestrasDao.getLocalidades().toArray();
	}

	public Object[] getPasajerosFromTxt() {

		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		Iterator it = tablasMaestrasDao.getPasajeros().iterator();
		
		ArrayList<String> nombresPasajeros = new ArrayList<String>();
		nombresPasajeros.add("Seleccionar");
		
		while (it.hasNext()) {
		
			nombresPasajeros.add(((Pasajeros) it.next()).getNombreApellido());
			
		}
		
		return nombresPasajeros.toArray();
	}

	public Object[] getHotelesFromTxt() {
		
		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		Iterator it = tablasMaestrasDao.getHoteles().iterator();
		
		ArrayList<String> nombresHoteles = new ArrayList<String>();
		nombresHoteles.add("Seleccionar");
		
		while (it.hasNext()) {
		
			nombresHoteles.add(((Hoteles) it.next()).getNombre());
			
		}
		
		return nombresHoteles.toArray();
	}

}
