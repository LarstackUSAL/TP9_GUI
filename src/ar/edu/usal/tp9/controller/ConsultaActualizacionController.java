package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ar.edu.usal.tp9.exception.PaqueteNoEncontradoException;
import ar.edu.usal.tp9.model.dao.HotelesDao;
import ar.edu.usal.tp9.model.dao.PaquetesDao;
import ar.edu.usal.tp9.model.dao.PasajerosDao;
import ar.edu.usal.tp9.model.dao.TablasMaestrasDao;
import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.PaquetesConEstadias;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;
import ar.edu.usal.tp9.view.ConsultaActualizacionView;

public class ConsultaActualizacionController implements ActionListener {
	
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
//			 y si hace click en modificar se habilitan
			
			
		} else if ("Anulacion".equals(e.getActionCommand())) {
			
			
			
		}
		
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
