package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.tp9.exception.PaqueteNoEncontradoException;
import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.PaquetesConEstadias;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;

public class PaquetesDao {

	private static PaquetesDao paquetesDaoInstance = null;

	private ArrayList<Paquetes> paquetes;
	private static int nextIdPaquetes = 0;

	private PaquetesDao(){

		this.paquetes = new ArrayList<Paquetes>();

		this.loadPaquetes();
		loadNextId();
	}

	public static PaquetesDao getInstance(){

		if(paquetesDaoInstance==null){

			paquetesDaoInstance = new PaquetesDao();
		}

		return paquetesDaoInstance;
	}

	private void loadPaquetes() {

		File paquetesTxt = new File("./archivos/PAQUETES.txt");
		Scanner paquetesScanner;

		try {

			try {
				paquetesTxt.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de paquetes.");
			}

			paquetesScanner = new Scanner(paquetesTxt);

			while(paquetesScanner.hasNextLine()){

				Paquetes paquete;

				String linea = paquetesScanner.nextLine();
				String[] lineaArray = linea.split(";");

				//id
				int idPaquete = Integer.parseInt(lineaArray[0].trim());

				//fecha
				Calendar fechaHoraSalida = Validador.stringToCalendar(lineaArray[1].trim(), "dd/MM/yyyy");

				//hora
				String horaCombo = lineaArray[2].trim();
				int hora = Integer.valueOf(horaCombo.substring(0, 2));
				int minutos = Integer.valueOf(horaCombo.substring(2, 4)); //hhmm
				Validador.setearHora(hora, minutos, fechaHoraSalida);

				//importe
				double importe = Double.parseDouble(lineaArray[3].trim());

				//pasajero
				PasajerosDao pasajerosDao = PasajerosDao.getInstance();
				Pasajeros pasajero = pasajerosDao.getPasajeroByNombre(lineaArray[4].trim());

				//abono transporte local
				boolean quiereAbonoTransporteLocal = Boolean.parseBoolean(lineaArray[5].trim());

				//visitas guiadas
				boolean quiereVisitasGuiadas = Boolean.parseBoolean(lineaArray[6].trim());

				//seguro
				boolean tieneSeguro = Boolean.parseBoolean(lineaArray[7].trim());

				//localidades
				ArrayList<String> localidades = this.loadLocalidades(idPaquete);

				//cantidad dias
				int cantidadDias = Integer.valueOf(lineaArray[8].trim());

				//hotel
				Hoteles hotel = null;
				boolean esPensionCompleta = false;
				HotelesDao hotelesDao = HotelesDao.getInstance();
				if(lineaArray[9] != null){

					hotel = hotelesDao.getHotelByNombre(lineaArray[9].trim());

					//pension completa
					esPensionCompleta = Boolean.parseBoolean(lineaArray[10].trim());

					paquete = new PaquetesConEstadias();
					((PaquetesConEstadias)paquete).setHotel(hotel);
					((PaquetesConEstadias)paquete).setEsPensionCompleta(esPensionCompleta);

				}else{

					paquete = new Paquetes();
				}

				paquete.setQuiereAbonoTransporteLocal(quiereAbonoTransporteLocal);
				paquete.setQuiereVisitasGuiadas(quiereVisitasGuiadas);
				paquete.setTieneSeguro(tieneSeguro);	
				paquete.setFechaHoraSalida(fechaHoraSalida);				
				paquete.setImporte(importe);
				paquete.setLocalidades(localidades);				
				paquete.setPasajero(pasajero);
				paquete.setCantidadDias(cantidadDias);

				this.paquetes.add(paquete);
			}

			paquetesScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch (FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");
		}
	}

	private ArrayList<String> loadLocalidades(int idPaquete) {

		File localidadesTxt = new File("./archivos/PAQUETES_LOCALIDADES.txt");
		Scanner localidadesScanner;

		ArrayList<String> localidadesPaqueteList = new ArrayList<>();

		try {

			try {
				localidadesTxt.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de localidades.");
			}

			localidadesScanner = new Scanner(localidadesTxt);

			while(localidadesScanner.hasNextLine()){

				String lineaPaqueteLocalidades = localidadesScanner.nextLine().trim();
				String[] arrayPaqueteLocalidades = lineaPaqueteLocalidades.split(";");

				if(Integer.parseInt(arrayPaqueteLocalidades[0].trim()) == idPaquete){

					for (int i = 1; i < arrayPaqueteLocalidades.length; i++) {

						localidadesPaqueteList.add(arrayPaqueteLocalidades[i].trim());
					}

					break;
				}
			}

			localidadesScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch (FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");
		}

		return localidadesPaqueteList;
	}

	public boolean persistirPaquetes() {

		boolean persistenciaOk = false;

		FileWriter paquetesFile;
		PrintWriter paquetesOut;

		FileWriter localidadesPaquetesFile;
		PrintWriter localidadesPaquetesOut;

		FileWriter facturasFile;
		PrintWriter facturasOut;

		try {

			paquetesFile = new FileWriter("./archivos/PAQUETES.txt");
			paquetesOut = new PrintWriter(paquetesFile);

			localidadesPaquetesFile = new FileWriter("./archivos/PAQUETES_LOCALIDADES.txt");
			localidadesPaquetesOut = new PrintWriter(localidadesPaquetesFile);

			facturasFile = new FileWriter("./archivos/FACTURAS.txt");
			facturasOut = new PrintWriter(facturasFile);

			for(int i=0; i < this.paquetes.size(); i++)
			{
				Paquetes paquete = this.paquetes.get(i);

				String fecha = Validador.calendarToString(paquete.getFechaHoraSalida(), "dd/MM/yyyy");
				String hora = Validador.fillString(Validador.HoraCalendarToString(paquete.getFechaHoraSalida()), 5, "0", true);

				String paqueteString =
						paquete.getId() + ";" +
								fecha + ";" +
								hora + ";" +
								String.valueOf(paquete.getImporte()) + ";" +
								paquete.getPasajero().getNombreApellido().trim() + ";" +
								String.valueOf(paquete.isQuiereAbonoTransporteLocal())+ ";" +
								String.valueOf(paquete.isQuiereVisitasGuiadas())+ ";" +
								String.valueOf(paquete.isTieneSeguro())+ ";" +
								String.valueOf(paquete.getCantidadDias());

				if(paquete instanceof PaquetesConEstadias){

					paqueteString = paqueteString + ";" + ((PaquetesConEstadias)paquete).getHotel().getNombre().trim();
					paqueteString = paqueteString + ";" + String.valueOf(((PaquetesConEstadias)paquete).isEsPensionCompleta());
				}

				paquetesOut.println(paqueteString);

				String localidades = String.valueOf(paquete.getId());

				for (int j = 0; j < paquete.getLocalidades().size(); j++) {

					localidades += ";";
					localidades += paquete.getLocalidades().get(j).trim();
				}

				//Se actualiza el archivo de localidades del paquete.
				localidadesPaquetesOut.println(localidades);

				//Se actualiza el archivo de facturas.
				facturasOut.println(
						String.valueOf(paquete.getFacturas().getNumero()) + ";" +
								String.valueOf(paquete.getId()) + ";" +
								Validador.calendarToString(paquete.getFacturas().getFecha(), "dd/MM/yyyy") + ";" +
								paquete.getFacturas().getPasajero().getNombreApellido() + ";" +
								String.valueOf(paquete.getFacturas().getTipo()) + ";" +
								String.valueOf(paquete.getFacturas().getImporte())
						);				
			}

			facturasOut.close();
			facturasFile.close();			
			localidadesPaquetesOut.close();
			localidadesPaquetesFile.close();
			paquetesOut.close();
			paquetesFile.close();

			//Se actualiza el archivo de id paquete.
			FileWriter idPaquetesFile = new FileWriter("./archivos/ID_PAQUETES.txt");
			PrintWriter idPaquetesOut = new PrintWriter(idPaquetesFile);

			idPaquetesOut.println(nextIdPaquetes);
			idPaquetesOut.close();
			idPaquetesFile.close();

			//Se actualiza el archivo de numero factura.
			FileWriter idFacturasFile = new FileWriter("./archivos/ID_FACTURAS.txt");
			PrintWriter idFacturasOut = new PrintWriter(idFacturasFile);

			idFacturasOut.println(FacturasDao.getIdFacturaActual());
			idFacturasOut.close();
			idFacturasFile.close();

			persistenciaOk = true;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return persistenciaOk;
	}

	private static void loadNextId() {

		File idPaquetesFile = new File("./archivos/ID_PAQUETES.txt");
		Scanner paquetesScanner;

		try {

			try {
				idPaquetesFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de id.");
			}

			paquetesScanner = new Scanner(idPaquetesFile);

			nextIdPaquetes = paquetesScanner.nextInt();

			paquetesScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch(FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");

		}catch(Exception e){

			e.printStackTrace();
		}

	}

	public Paquetes getPaqueteById(int id) {

		Iterator paquetesIterator = this.paquetes.iterator();

		while (paquetesIterator.hasNext()) {

			Paquetes paquete = (Paquetes) paquetesIterator.next();

			if(paquete.getId() == id){

				return paquete;
			}
		}

		return null;
	}

	public static int getNextIdPaquetes() {

		nextIdPaquetes++;

		return nextIdPaquetes;
	}

	public static int getIdPaquetesActual(){

		return nextIdPaquetes;
	}

	public ArrayList<Paquetes> getPaquetes() {
		return paquetes;
	}

	public Paquetes getPaqueteByPasajeroLocalidad(Pasajeros pasajero,
			String localidadString) throws PaqueteNoEncontradoException {

		for (int i = 0; i < this.paquetes.size(); i++) {

			ArrayList<String> localidades = paquetes.get(i).getLocalidades();
			Pasajeros pasajeroIterado = paquetes.get(i).getPasajero();  

			if(pasajeroIterado == pasajero){

				for (int j = 0; j < localidades.size(); j++) {

					if(localidades.get(j).equals(localidadString)){

						return paquetes.get(i);
					}
				}
			}
		}

		throw new PaqueteNoEncontradoException();
	}

	public ArrayList<Paquetes> getPaqueteByPasajero(Pasajeros pasajero) {

		ArrayList<Paquetes> paquetesArray = new ArrayList<Paquetes>();

		for (int i = 0; i < this.paquetes.size(); i++) {

			Pasajeros pasajeroIterado = paquetes.get(i).getPasajero();  

			if(pasajeroIterado == pasajero){

				paquetesArray.add(paquetes.get(i));

			}
		}

		return paquetesArray;
	}
}
