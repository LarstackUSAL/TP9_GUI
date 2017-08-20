package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import ar.edu.usal.tp9.model.dao.FacturasDao;
import ar.edu.usal.tp9.model.dao.HotelesDao;
import ar.edu.usal.tp9.model.dao.PaquetesDao;
import ar.edu.usal.tp9.model.dao.PasajerosDao;
import ar.edu.usal.tp9.model.dao.TablasMaestrasDao;
import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.PaquetesConEstadias;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.model.interfaces.ICalculoImporte;
import ar.edu.usal.tp9.utils.Validador;
import ar.edu.usal.tp9.view.IngresoView;

public class IngresoController implements ActionListener, ICalculoImporte {
	
	private IngresoView ingresoView;

	public void setView(IngresoView ingresoView) {
		
		this.ingresoView = ingresoView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("Cancelar".equals(e.getActionCommand())) {
			
			int rta = JOptionPane.showConfirmDialog(null, "Los datos seran borrados. Confirma?", 
					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);

			if (rta == JOptionPane.YES_OPTION) 
				ingresoView.limpiar();

		} else if ("Calcular".equals(e.getActionCommand())) {
			
			if(ingresoView.validar()){
				
				double importe = this.calcularImporte();
				
				this.ingresoView.mostrarImporte(importe);
			}
			
		}else if ("Aceptar".equals(e.getActionCommand())) {
			
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
		
		paquete.setCantidadDias(Integer.valueOf(this.ingresoView.getTxtCantidadDias().getText()));
		
		Calendar fechaHoraSalida = Validador.stringToCalendar(this.ingresoView.getTxtFechaSalida().getText().trim(), "dd/MM/yyyy");
		paquete.setFechaHoraSalida(fechaHoraSalida);
		
		String horaCombo = ((String)this.ingresoView.getComboHoras().getSelectedItem()).trim();
		int hora = Integer.valueOf(horaCombo.substring(0, 2));
		int minutos = Integer.valueOf(horaCombo.substring(3, 5));
		Validador.setearHora(hora, minutos, fechaHoraSalida);
		
		double importe = Double.parseDouble(this.ingresoView.getTxtImporte().getText().trim());
		paquete.setImporte(importe);
		
		ArrayList<String> localidades = new ArrayList<String>(); 
		ListModel model = this.ingresoView.getListaLocalidadesCopia().getModel();
		for (int i = 0; i < model.getSize(); i++) {
			
			localidades.add((String) model.getElementAt(i));
		}
		
		paquete.setLocalidades(localidades);
		
		PasajerosDao pasajerosDao = PasajerosDao.getInstance();
		Pasajeros pasajero = pasajerosDao.getPasajeroByNombre((String)(this.ingresoView.getCmbPasajeros().getSelectedItem()));
		paquete.setPasajero(pasajero);
		
		paquete.setQuiereAbonoTransporteLocal(this.ingresoView.getQuiereAbonoTransporteLocal().isSelected());
		paquete.setQuiereVisitasGuiadas(this.ingresoView.getQuiereVisitasGuiadas().isSelected());
		paquete.setTieneSeguro(this.ingresoView.getGrpSeguro().getSelection().getActionCommand().trim().
				equals("Si") ? true : false);
		
		//Se genera la factura correspondiente.
		FacturasDao facturasDao = FacturasDao.getInstance();
		paquete.generarFactura();
		
		PaquetesDao paquetesDao = PaquetesDao.getInstance();
		paquetesDao.getPaquetes().add(paquete);
		
		return paquetesDao.persistirPaquetes();
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
	
	@Override
	public double calcularImporte() {

		double importeTotal = 0;
		double importeHotel = 0;
		
		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		HashMap<String, Double> localidadesImportesMap = tablasMaestrasDao.getLocalidadesImportesMap();
		ListModel modelo = this.ingresoView.getModelo();
		
		ArrayList<String> localidadesSeleccionadas = new ArrayList<String>();
		
		for (int i = 0; i < modelo.getSize(); i++) {
			
			localidadesSeleccionadas.add((String)modelo.getElementAt(i));
		}
		
		double totalImporteLocalidades = 0;

		for (int i = 0; i < localidadesSeleccionadas.size(); i++) {
			
			totalImporteLocalidades += localidadesImportesMap.get(localidadesSeleccionadas.get(i));
		}
		
		importeTotal = importeTotal + totalImporteLocalidades; 
		
		if (this.ingresoView.getRdbSi().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_SEGURO;
			
		}
		
		if (this.ingresoView.getQuiereVisitasGuiadas().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_GUIA;
			
		}
		
		if (this.ingresoView.getQuiereAbonoTransporteLocal().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_ABONO_TRANSPORTE;
			
		} 	
		
		if (this.ingresoView.getCmbHoteles().getSelectedIndex() != 0) {
			
			HotelesDao hotelesDao = HotelesDao.getInstance();
			
			importeHotel = hotelesDao.getHotelByNombre(((String)this.ingresoView.getCmbHoteles().getSelectedItem()).trim()).getImporte();
			
			importeTotal += importeHotel * (Double.valueOf(ingresoView.getTxtCantidadDias().getText()));
			
			if (this.ingresoView.getEsPensionCompleta().isSelected()) {
				
				importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_PENSION_COMPLETA;
				
			}
			
		}		
		
		return importeTotal;
		
	}
}
