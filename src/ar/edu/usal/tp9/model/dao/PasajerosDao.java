package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;

public class PasajerosDao {

	private static PasajerosDao pasajerosDaoInstance = null;

	private ArrayList<Pasajeros> pasajeros;

	private PasajerosDao(){
		
		this.pasajeros = new ArrayList<Pasajeros>();
		
		this.loadPasajeros();
	}

	public static PasajerosDao getInstance(){
		
		if(pasajerosDaoInstance==null){
			
			pasajerosDaoInstance = new PasajerosDao();
		}
		
		return pasajerosDaoInstance;
	}
	
	private void loadPasajeros() {
		
		File clientesTxt = new File("./archivos/CLIENTES.txt");
		Scanner clientesScanner;
		
		try {
			
			try {
				clientesTxt.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de clientes.");
			}
			
			clientesScanner = new Scanner(clientesTxt);
			
			while(clientesScanner.hasNextLine()){
				
				String linea = clientesScanner.nextLine();
				String[] clientesArray = linea.split(";");
				
				String nombreApellido = clientesArray[0].trim();
				Calendar fechaNacimiento = Validador.stringToCalendar(clientesArray[1].trim(), "yyyyMMdd");
				int dni = Integer.valueOf(clientesArray[2].trim());
				String email = clientesArray[3].trim();
				
				this.pasajeros.add(new Pasajeros(nombreApellido, fechaNacimiento, dni, email));
			}
			
			clientesScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
	}
	
	public ArrayList<Pasajeros> getPasajeros() {
		return pasajeros;
	}

	public Pasajeros getPasajeroByNombre(String pasajeroString) {

		Iterator pasajerosIterator = this.pasajeros.iterator();
		
		while (pasajerosIterator.hasNext()) {
			
			Pasajeros pasajero = (Pasajeros) pasajerosIterator.next();
			
			if(pasajero.getNombreApellido().trim().equals(pasajeroString.trim())){
				
				return pasajero;
			}
		}
		
		return null;
	}
	
	public Pasajeros getPasajeroByDocumento(int pasajeroDocumento) {

		Iterator pasajerosIterator = this.pasajeros.iterator();
		
		while (pasajerosIterator.hasNext()) {
			
			Pasajeros pasajero = (Pasajeros) pasajerosIterator.next();
			
			if(pasajero.getDni() == pasajeroDocumento){
				
				return pasajero;
			}
		}
		
		return null;
	}

	public Pasajeros buscarPasajeroByNombre(String pasajeroBuscado) {

		for (int i = 0; i < this.pasajeros.size(); i++) {
			
			String pasajeroTmp = new String(this.pasajeros.get(i).getNombreApellido().trim());
			
			int indice = pasajeroTmp.toLowerCase().indexOf(pasajeroBuscado.toLowerCase());
			
			if(indice != -1){
				
				return this.pasajeros.get(i);
			}
			
		}
		
		return null;
	}
}
