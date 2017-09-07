package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.usal.tp9.model.dto.Facturas;
import ar.edu.usal.tp9.model.dto.Paquetes;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;

public class FacturasDao {

	private static int nextIdFactura = 0;

	private static FacturasDao facturasDaoInstance = null;
		
	private FacturasDao(){
				 
		this.loadFacturas();
		loadNextId();
	}

	public static FacturasDao getInstance(){
		
		if(facturasDaoInstance==null){
			
			facturasDaoInstance = new FacturasDao();
		}
		
		return facturasDaoInstance;
	}
	
	private void loadFacturas() {
		
		File facturasTxt = new File("./archivos/FACTURAS.txt");
		Scanner facturasScanner;
		
		try {
			
			try {
				facturasTxt.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de facturas.");
			}
			
			facturasScanner = new Scanner(facturasTxt);
			
			while(facturasScanner.hasNextLine()){
				
				String linea = facturasScanner.nextLine();
				String[] lineaArray = linea.split(";");

				int numeroFactura = Integer.valueOf(lineaArray[0].trim());
				int idPaquete = Integer.valueOf(lineaArray[1].trim());
				Calendar fecha = Validador.stringToCalendar(lineaArray[2], "dd/MM/yyyy");
				
				PasajerosDao pasajerosDao = PasajerosDao.getInstance();
				Pasajeros pasajero = pasajerosDao.getPasajeroByNombre(lineaArray[3].trim());
				char tipo = lineaArray[4].trim().charAt(0);
				double importe = Double.valueOf(lineaArray[5].trim());
				
				PaquetesDao paquetesDao = PaquetesDao.getInstance();
				
				Paquetes paquete = paquetesDao.getPaqueteById(idPaquete);
				paquete.generarFactura(numeroFactura, idPaquete, fecha, tipo, importe);
				
			}
			
			facturasScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
	}
	
	private static void loadNextId() {

		File idFacturaFile = new File("./archivos/ID_FACTURAS.txt");
		Scanner facturaScanner;

		try {

			try {
				idFacturaFile.createNewFile();

			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de id.");
			}

			facturaScanner = new Scanner(idFacturaFile);

			nextIdFactura = facturaScanner.nextInt();

			facturaScanner.close();

		}catch(InputMismatchException e){

			System.out.println("Se ha encontrado un tipo de dato insesperado.");

		}catch(FileNotFoundException e) {

			System.out.println("No se ha encontrado el archivo.");

		}catch(Exception e){

			e.printStackTrace();
		}

	}

	public static int getNextIdFactura() {

		nextIdFactura++;

		return nextIdFactura;
	}

	public static int getIdFacturaActual(){

		return nextIdFactura;
	}

//	public void generarFactura(Paquetes paquete) {
//		
//		Facturas factura = paquete.generarFactura();
//	}
}
