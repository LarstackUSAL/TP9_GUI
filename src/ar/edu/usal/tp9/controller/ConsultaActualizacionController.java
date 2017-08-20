package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

import ar.edu.usal.tp9.exception.PaqueteNoEncontradoException;
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
import ar.edu.usal.tp9.view.ConsultaActualizacionView;

public class ConsultaActualizacionController implements ActionListener, ICalculoImporte {
	
	private ConsultaActualizacionView consultaActualizacionView;

	public void setView(ConsultaActualizacionView consultaActualizacionView) {
		
		this.consultaActualizacionView = consultaActualizacionView;
		
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
	
	public Object[] getLocalidadesFromTxt() {
		
		TablasMaestrasDao tablasMaestrasDao = TablasMaestrasDao.getInstance();
		tablasMaestrasDao.getLocalidades().add(0,"Seleccionar");
		
		return tablasMaestrasDao.getLocalidades().toArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Consultar".equals(e.getActionCommand())) {
			
			String pasajeroString = ((String) this.consultaActualizacionView.getCmbPasajeros().getSelectedItem()).trim();
			PasajerosDao pasajerosDao = PasajerosDao.getInstance();
			Pasajeros pasajero = pasajerosDao.getPasajeroByNombre(pasajeroString);
			
			String localidadString = ((String) this.consultaActualizacionView.getCmbLocalidades().getSelectedItem()).trim();
			
			PaquetesDao paquetesDao = PaquetesDao.getInstance();
			
			try {
			
				Paquetes paqueteEncontrado = paquetesDao.getPaqueteByPasajeroLocalidad(pasajero, localidadString);
				
				String hotel = null;
				boolean pensionCompleta = false;
				
				if(paqueteEncontrado instanceof PaquetesConEstadias){
					
					hotel = ((PaquetesConEstadias)paqueteEncontrado).getHotel().getNombre();
					pensionCompleta = ((PaquetesConEstadias)paqueteEncontrado).isEsPensionCompleta();
				}
				
				this.consultaActualizacionView.setIdPaqueteEncontrado(paqueteEncontrado.getId());
				this.consultaActualizacionView.fillForm(
						paqueteEncontrado.getPasajero().getNombreApellido(),
						paqueteEncontrado.getLocalidades().toArray(),
						Validador.calendarToString(paqueteEncontrado.getFechaHoraSalida(), "dd/MM/yyyy"),
						Validador.HoraCalendarToString(paqueteEncontrado.getFechaHoraSalida()),
						paqueteEncontrado.isTieneSeguro(),
						paqueteEncontrado.isQuiereAbonoTransporteLocal(),
						paqueteEncontrado.isQuiereVisitasGuiadas(),
						hotel,
						pensionCompleta,
						String.valueOf(paqueteEncontrado.getImporte())
				);
				
			} catch (PaqueteNoEncontradoException ex) {
				
				this.consultaActualizacionView.mostrarMensajeDialog(ex.getMessage(), "Paquete No Encontrado");
			}
					
		} else if ("Modificacion".equals(e.getActionCommand())) {

			this.consultaActualizacionView.ocultarVisibilizarComponentesVentana(
					this.consultaActualizacionView.getComponentesPaqueteEncontrado(), true, true);
			
		} else if ("Anulacion".equals(e.getActionCommand())) {
			
			int rta = JOptionPane.showConfirmDialog(null, "Quiere borrar el paquete?", 
					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);

			if (rta == JOptionPane.YES_OPTION){
			
				PaquetesDao paquetesDao = PaquetesDao.getInstance();
				Paquetes paquete = paquetesDao.getPaqueteById(this.consultaActualizacionView.getIdPaqueteEncontrado());
				paquetesDao.getPaquetes().remove(paquete);
				
				boolean persistenciaOk = paquetesDao.persistirPaquetes();
				
				if(persistenciaOk) {

					this.consultaActualizacionView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
					this.consultaActualizacionView.limpiar();
					this.consultaActualizacionView.ocultarVisibilizarComponentesVentana(
					this.consultaActualizacionView.getComponentesPaqueteEncontrado(), false, false);
				}else{
					
					this.consultaActualizacionView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
				}
			}
		} else if ("Calcular".equals(e.getActionCommand())) {
			
			if(this.consultaActualizacionView.validar()){
				
				double importe = this.calcularImporte();
				
				this.consultaActualizacionView.mostrarImporte(importe);
			}
			
		}else if ("Aceptar".equals(e.getActionCommand())) {
			
			PaquetesDao paquetesDao = PaquetesDao.getInstance();
			Paquetes paquete = paquetesDao.getPaqueteById(this.consultaActualizacionView.getIdPaqueteEncontrado());
			
			boolean persistenciaOk = false;
			
			if(this.consultaActualizacionView.validar()){
				
				persistenciaOk = this.guardarPaquete(paquete);
			}

			if(persistenciaOk) {

				this.consultaActualizacionView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			}else{
				
				this.consultaActualizacionView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
			}
			
		} else if ("Cancelar".equals(e.getActionCommand())) {
			
			
			int rta = JOptionPane.showConfirmDialog(null, "Los datos seran borrados. Confirma?", 
					"Confirmacion", JOptionPane.OK_CANCEL_OPTION);

			if (rta == JOptionPane.YES_OPTION)
				this.consultaActualizacionView.limpiar();
		}
	}

	private boolean guardarPaquete(Paquetes paquete) {
		
		if(this.consultaActualizacionView.getCmbHoteles().getSelectedIndex() > 0){
			
			if(!(paquete instanceof PaquetesConEstadias)){
				
				PaquetesDao paquetesDao = PaquetesDao.getInstance();
				paquetesDao.getPaquetes().remove(paquete);
				
				paquete = new PaquetesConEstadias();
			}
			
			HotelesDao hotelesDao = HotelesDao.getInstance();
			
			((PaquetesConEstadias) paquete).setHotel(hotelesDao.getHotelByNombre(((String)this.consultaActualizacionView.getCmbHoteles().getSelectedItem()).trim()));
			((PaquetesConEstadias) paquete).setEsPensionCompleta(this.consultaActualizacionView.getEsPensionCompleta().isSelected());
			
		}

		paquete.setId(PaquetesDao.getNextIdPaquetes());

		paquete.setCantidadDias(Integer.valueOf(this.consultaActualizacionView.getTxtCantidadDias().getText()));

		Calendar fechaHoraSalida = Validador.stringToCalendar(this.consultaActualizacionView.getTxtFechaSalida().getText().trim(), "dd/MM/yyyy");
		paquete.setFechaHoraSalida(fechaHoraSalida);
		
		String horaCombo = ((String)this.consultaActualizacionView.getComboHoras().getSelectedItem()).trim();
		int hora = Integer.valueOf(horaCombo.substring(0, 2));
		int minutos = Integer.valueOf(horaCombo.substring(3, 5));
		Validador.setearHora(hora, minutos, fechaHoraSalida);
		
		double importe = Double.parseDouble(this.consultaActualizacionView.getTxtImporte().getText().trim());
		paquete.setImporte(importe);
		
		ArrayList<String> localidades = (ArrayList<String>) this.consultaActualizacionView.getListaLocalidadesCopia().getSelectedValuesList();
		paquete.setLocalidades(localidades);
		
		PasajerosDao pasajerosDao = PasajerosDao.getInstance();
		Pasajeros pasajero = pasajerosDao.getPasajeroByNombre((String)(this.consultaActualizacionView.getCmbPasajerosPaquete().getSelectedItem()));
		paquete.setPasajero(pasajero);
		
		paquete.setQuiereAbonoTransporteLocal(this.consultaActualizacionView.getQuiereAbonoTransporteLocal().isSelected());
		paquete.setQuiereVisitasGuiadas(this.consultaActualizacionView.getQuiereVisitasGuiadas().isSelected());
		paquete.setTieneSeguro(this.consultaActualizacionView.getGrpSeguro().getSelection().getActionCommand().trim().
				equals("Si") ? true : false);
		
		//Se genera la factura correspondiente.
		FacturasDao facturasDao = FacturasDao.getInstance();
		paquete.generarFactura();
		
		PaquetesDao paquetesDao = PaquetesDao.getInstance();
		paquetesDao.getPaquetes().add(paquete);
		
		return paquetesDao.persistirPaquetes();
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
		ListModel modelo = this.consultaActualizacionView.getModelo();
		
		ArrayList<String> localidadesSeleccionadas = new ArrayList<String>();
		
		for (int i = 0; i < modelo.getSize(); i++) {
			
			localidadesSeleccionadas.add((String)modelo.getElementAt(i));
		}
		
		double totalImporteLocalidades = 0;

		for (int i = 0; i < localidadesSeleccionadas.size(); i++) {
			
			totalImporteLocalidades += localidadesImportesMap.get(localidadesSeleccionadas.get(i));
		}
		
		importeTotal = importeTotal + totalImporteLocalidades; 
		
		if (this.consultaActualizacionView.getRdbSi().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_SEGURO;
			
		}
		
		if (this.consultaActualizacionView.getQuiereVisitasGuiadas().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_GUIA;
			
		}
		
		if (this.consultaActualizacionView.getQuiereAbonoTransporteLocal().isSelected()) {
			
			importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_ABONO_TRANSPORTE;
			
		} 	
		
		if (this.consultaActualizacionView.getCmbHoteles().getSelectedIndex() != 0) {
			
			HotelesDao hotelesDao = HotelesDao.getInstance();
			
			importeHotel = hotelesDao.getHotelByNombre(((String)this.consultaActualizacionView.getCmbHoteles().getSelectedItem()).trim()).getImporte();
			
			importeTotal += importeHotel * (Double.valueOf(consultaActualizacionView.getTxtCantidadDias().getText()));
			
			if (this.consultaActualizacionView.getEsPensionCompleta().isSelected()) {
				
				importeTotal += importeTotal * ICalculoImporte.PORCENTAJE_PENSION_COMPLETA;
				
			}
			
		}		
		
		return importeTotal;
		
	}
}
