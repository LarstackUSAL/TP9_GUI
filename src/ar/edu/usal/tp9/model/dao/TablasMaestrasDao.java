package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;

public class TablasMaestrasDao {

	private static TablasMaestrasDao tablasMaestrasDaoInstance = null;
	
	private ArrayList<String> localidades;
	private ArrayList<Pasajeros> pasajeros;
	private ArrayList<Hoteles> hoteles;
	
	private TablasMaestrasDao(){
		
		this.localidades = new ArrayList<String>();
		this.pasajeros = new ArrayList<Pasajeros>();
		this.hoteles = new ArrayList<Hoteles>();
		
		this.loadLocalidades();
		this.loadPasajeros();
		this.loadHoteles();
	}

	public static TablasMaestrasDao getInstance(){
		
		if(tablasMaestrasDaoInstance==null){
			
			tablasMaestrasDaoInstance = new TablasMaestrasDao();
		}
		
		return tablasMaestrasDaoInstance;
	}
	
	private void loadLocalidades() {
		
		File localidadesTxt = new File("./archivos/LOCALIDADES.txt");
		Scanner localidadesScanner;
		
		try {
			
			try {
				localidadesTxt.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de localidades.");
			}
			
			localidadesScanner = new Scanner(localidadesTxt);
			
			while(localidadesScanner.hasNextLine()){
				
				String localidad = localidadesScanner.nextLine().trim();
				
				this.localidades.add(localidad);
			}
			
			localidadesScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
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
	
	private void loadHoteles() {
		
		File hotelesTxt = new File("./archivos/HOTELES.txt");
		Scanner hotelesScanner;
		
		try {
			
			try {
				hotelesTxt.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de hoteles.");
			}
			
			hotelesScanner = new Scanner(hotelesTxt);
			
			while(hotelesScanner.hasNextLine()){
				
				Hoteles hotel = new Hoteles();
				
				String linea = hotelesScanner.nextLine();
				String[] lineaArray = linea.split(";");

				hotel.setNombre(lineaArray[0].trim());
				hotel.setEstrellas(Integer.valueOf(lineaArray[1].trim()));				
				
				ArrayList<String> localidades = new ArrayList<String>();
				
				for (int i = 2; i < lineaArray.length; i++) {
					
					localidades.add(lineaArray[i].trim());
				}				
				hotel.setLocalidades(localidades);
				
				this.hoteles.add(hotel);
			}
			
			hotelesScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
	}

	public ArrayList<String> getLocalidades() {
		return localidades;
	}

	public ArrayList<Pasajeros> getPasajeros() {
		return pasajeros;
	}

	public ArrayList<Hoteles> getHoteles() {
		
		return hoteles;
	}

}
