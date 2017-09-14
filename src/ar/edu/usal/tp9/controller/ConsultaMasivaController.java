package ar.edu.usal.tp9.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ar.edu.usal.tp9.exception.PaqueteNoEncontradoException;
import ar.edu.usal.tp9.model.dao.PaquetesDao;
import ar.edu.usal.tp9.model.dao.PasajerosDao;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.PaquetesConEstadias;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;
import ar.edu.usal.tp9.view.ConsultaMasivaView;

public class ConsultaMasivaController implements ActionListener {

	private ConsultaMasivaView consultaMasivaView;

	public void setView(ConsultaMasivaView consultaMasivaView) {

		this.consultaMasivaView = consultaMasivaView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Consultar".equals(e.getActionCommand())) {

			ArrayList<Paquetes> paquetesEncontrados = new ArrayList<Paquetes>();
			ArrayList<String[]> registros = new ArrayList<String[]>();
			String cantidadRegistros = "";
			String cantidadRegistrosTotales = "";

			String pasajeroTxt = consultaMasivaView.getPasajero().getText();
			PaquetesDao paquetesDao = PaquetesDao.getInstance();

			if(pasajeroTxt != null && pasajeroTxt.trim().isEmpty()){

				PasajerosDao pasajerosDao = PasajerosDao.getInstance();
				Pasajeros pasajeroEncontrado = pasajerosDao.buscarPasajero(pasajeroTxt.trim());

				paquetesEncontrados = paquetesDao.getPaqueteByPasajero(pasajeroEncontrado);

				if(paquetesEncontrados != null && !paquetesEncontrados.isEmpty()){

					for (int i = 0; i < paquetesEncontrados.size(); i++) {

						Paquetes paqueteIterado = paquetesEncontrados.get(i);

						String hotelString = "";
						String pensionCompleta = "";

						if(paqueteIterado instanceof PaquetesConEstadias){

							hotelString = ((PaquetesConEstadias) paqueteIterado).getHotel().getNombre();
							pensionCompleta = ((PaquetesConEstadias) paqueteIterado).isEsPensionCompleta() ? "SI" : "NO";
						}

						String[] registro = {
//								lr
//								paqueteIterado.getPasajero().getNombreApellido().trim(),					
								Validador.ListToString(paqueteIterado.getLocalidades()),
								Validador.calendarToString(paqueteIterado.getFechaHoraSalida(), "dd/MM/yyyy"),
								String.valueOf(paqueteIterado.getCantidadDias()),
								paqueteIterado.isTieneSeguro() ? "SI" : "NO",
										paqueteIterado.isQuiereAbonoTransporteLocal() ? "SI" : "NO",
												paqueteIterado.isQuiereVisitasGuiadas() ? "SI" : "NO",
														hotelString,
														pensionCompleta,
														String.valueOf(paqueteIterado.getImporte())
						};

						registros.add(registro);
					}
				}
			}else{

				ArrayList<Paquetes> paquetesList = paquetesDao.getPaquetes();

				for (int i = 0; i < paquetesList.size(); i++) {

					Paquetes paqueteIterado = paquetesList.get(i);

					String hotelString = "";
					String pensionCompleta = "";

					if(paqueteIterado instanceof PaquetesConEstadias){

						hotelString = ((PaquetesConEstadias) paqueteIterado).getHotel().getNombre();
						pensionCompleta = ((PaquetesConEstadias) paqueteIterado).isEsPensionCompleta() ? "SI" : "NO";
					}

					String[] registro = {
//							lr
//							paqueteIterado.getPasajero().getNombreApellido().trim(),					
							Validador.ListToString(paqueteIterado.getLocalidades()),
							Validador.calendarToString(paqueteIterado.getFechaHoraSalida(), "dd/MM/yyyy"),
							String.valueOf(paqueteIterado.getCantidadDias()),
							paqueteIterado.isTieneSeguro() ? "SI" : "NO",
									paqueteIterado.isQuiereAbonoTransporteLocal() ? "SI" : "NO",
											paqueteIterado.isQuiereVisitasGuiadas() ? "SI" : "NO",
													hotelString,
													pensionCompleta,
													String.valueOf(paqueteIterado.getImporte())
					};

					registros.add(registro);
				}
			}
			cantidadRegistros = String.valueOf(registros.size());
			cantidadRegistrosTotales = String.valueOf(paquetesDao.getPaquetes().size());

			this.consultaMasivaView.mostrarRegistros(registros, cantidadRegistros, cantidadRegistrosTotales);	
		}
	}

}
