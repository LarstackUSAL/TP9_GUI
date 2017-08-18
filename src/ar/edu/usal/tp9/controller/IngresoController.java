package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import ar.edu.usal.tp9.model.dao.HotelesDao;
import ar.edu.usal.tp9.model.dao.PaquetesDao;
import ar.edu.usal.tp9.model.dao.PasajerosDao;
import ar.edu.usal.tp9.model.dao.TablasMaestrasDao;
import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.PaquetesConEstadias;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;
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
			
			boolean persistenciaOk = false;
			
			if(ingresoView.validar()){
				
				persistenciaOk = this.guardarPaquete();
			}

			if(persistenciaOk) {

				this.ingresoView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			}else{
				
				this.ingresoView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
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
			
			TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
			
			if (ingresoView.getComboHorariosIndex() == 1) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < tablasMaestrasDao.getTurnoHorariosMap().get("Manana").size(); j++) {
					ingresoView.getComboHoras().addItem(tablasMaestrasDao.getTurnoHorariosMap().get("Manana").get(j));
				}

			} else if (ingresoView.getComboHorariosIndex() == 2) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < tablasMaestrasDao.getTurnoHorariosMap().get("Tarde").size(); j++) {
					ingresoView.getComboHoras().addItem(tablasMaestrasDao.getTurnoHorariosMap().get("Tarde").get(j));
				}
	
			} else if (ingresoView.getComboHorariosIndex() == 3) {
				ingresoView.getComboModel().removeAllElements();
				for (int j = 0; j < tablasMaestrasDao.getTurnoHorariosMap().get("Noche").size(); j++) {
					ingresoView.getComboHoras().addItem(tablasMaestrasDao.getTurnoHorariosMap().get("Noche").get(j));
				}
	
			} 
			
		}
						
	}

	private boolean guardarPaquete() {

		Paquetes paquete;
		
		if(this.ingresoView.getCmbHoteles().getSelectedIndex() > 0){
			
			paquete = new PaquetesConEstadias();
			
			HotelesDao hotelesDao = HotelesDao.getInstance();
			
			((PaquetesConEstadias) paquete).setHotel(hotelesDao.getHotelByNombre(((String)this.ingresoView.getCmbHoteles().getSelectedItem()).trim()));
			((PaquetesConEstadias) paquete).setEsPensionCompleta(this.ingresoView.getEsPensionCompleta().isSelected());
			
		}else{
			
			paquete = new Paquetes();
		}

		paquete.setId(PaquetesDao.getNextIdPaquetes());

		Calendar fechaHoraSalida = Validador.stringToCalendar(this.ingresoView.getTxtFechaSalida().getText().trim(), "dd/MM/yyyy");
		paquete.setFechaHoraSalida(fechaHoraSalida);
		
		String horaCombo = ((String)this.ingresoView.getComboHoras().getSelectedItem()).trim();
		int hora = Integer.valueOf(horaCombo.substring(0, 2));
		int minutos = Integer.valueOf(horaCombo.substring(3, 5));
		Validador.setearHora(hora, minutos, fechaHoraSalida);
		
		double importe = Double.parseDouble(this.ingresoView.getTxtImporte().getText().trim());
		paquete.setImporte(importe);
		
		ArrayList<String> localidades = (ArrayList<String>) this.ingresoView.getListaLocalidadesCopia().getSelectedValuesList();
		paquete.setLocalidades(localidades);
		
		PasajerosDao pasajerosDao = PasajerosDao.getInstance();
		Pasajeros pasajero = pasajerosDao.getPasajeroByNombre((String)(this.ingresoView.getCmbPasajeros().getSelectedItem()));
		paquete.setPasajero(pasajero);
		
		paquete.setQuiereAbonoTransporteLocal(this.ingresoView.getQuiereAbonoTransporteLocal().isSelected());
		paquete.setQuiereVisitasGuiadas(this.ingresoView.getQuiereVisitasGuiadas().isSelected());
		paquete.setTieneSeguro(this.ingresoView.getGrpSeguro().getSelection().getActionCommand().trim().
				equals("Si") ? true : false);
		
		//Se genera la factura correspondiente.
		paquete.generarFactura();
		
		PaquetesDao paquetesDao = PaquetesDao.getInstance();
		paquetesDao.getPaquetes().add(paquete);
		
		return paquetesDao.persistirPaquetes();
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

		PasajerosDao pasajerosDao = PasajerosDao.getInstance();
		Iterator it = pasajerosDao.getPasajeros().iterator();
		
		ArrayList<String> nombresPasajeros = new ArrayList<String>();
		nombresPasajeros.add("Seleccionar");
		
		while (it.hasNext()) {
		
			nombresPasajeros.add(((Pasajeros) it.next()).getNombreApellido());
			
		}
		
		return nombresPasajeros.toArray();
	}

	public Object[] getHotelesFromTxt() {
		
		HotelesDao hotelesDao = HotelesDao.getInstance();
		Iterator it = hotelesDao.getHoteles().iterator();
		
		ArrayList<String> nombresHoteles = new ArrayList<String>();
		nombresHoteles.add("Seleccionar");
		
		while (it.hasNext()) {
		
			nombresHoteles.add(((Hoteles) it.next()).getNombre());
			
		}
		
		return nombresHoteles.toArray();
	}

	public Object[] getTurnosFromTxt() {
		
		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		HashMap turnosHorariosMap = tablasMaestrasDao.getTurnoHorariosMap();
		
		return turnosHorariosMap.keySet().toArray();
	}

}
