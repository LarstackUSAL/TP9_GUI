package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.usal.tp9.model.dto.Hoteles;
import ar.edu.usal.tp9.model.dto.Pasajeros;
import ar.edu.usal.tp9.utils.Validador;

public class TablasMaestrasDao {

	private static TablasMaestrasDao tablasMaestrasDaoInstance = null;
	
	private HashMap<String,Double> localidadesImportesMap;
	private HashMap<String,ArrayList<String>> turnoHorariosMap;

	private TablasMaestrasDao(){
		
		this.turnoHorariosMap = new HashMap<String, ArrayList<String>>();
		this.localidadesImportesMap = new HashMap<String, Double>();
		
		this.loadLocalidades();
		this.loadHorariosViajes();
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
				
//				String localidad = localidadesScanner.nextLine().trim();				
//				this.localidades.add(localidad);
				
				String linea = localidadesScanner.nextLine();
				String[] lineaArray = linea.split(";");
				
				String localidad = lineaArray[0].trim();
				Double importe = Double.valueOf(lineaArray[1].trim());
				
				this.localidadesImportesMap.put(localidad, importe);
			}
			
			localidadesScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
	}	
	
	public void loadHorariosViajes(){
		
		File horariosTxt = new File("./archivos/HORARIOS.txt");
		Scanner horariosScanner;
		
		try {
			
			try {
				horariosTxt.createNewFile();
			
			} catch (IOException e) {

				System.out.println("Se ha verificado un error al cargar el archivo de horarios.");
			}
			
			horariosScanner = new Scanner(horariosTxt);
						
			while(horariosScanner.hasNextLine()){
				
				String linea = horariosScanner.nextLine();
				int id = Integer.valueOf(linea.substring(0, 4));
				String horario = linea.substring(4, 9).trim();
				String turno = linea.substring(9, 24).trim();
				
				if(this.turnoHorariosMap.get(turno) != null){
					
					this.turnoHorariosMap.get(turno).add(horario);
				}else{
					
					this.turnoHorariosMap.put(turno, new ArrayList<String>());
					this.turnoHorariosMap.get(turno).add(horario);
				}
			}
			
			horariosScanner.close();
			
		}catch(InputMismatchException e){
			
			System.out.println("Se ha encontrado un tipo de dato insesperado.");
			
		}catch (FileNotFoundException e) {
			
			System.out.println("No se ha encontrado el archivo.");
		}
	}

	public ArrayList<String> getLocalidades() {
		
		Object[] localidadesArray = this.localidadesImportesMap.keySet().toArray();
		ArrayList<String> localidadesList = new ArrayList<String>();
		
		for (int i = 0; i < localidadesArray.length; i++) {
			localidadesList.add((String)localidadesArray[i]);
		}
		
		return localidadesList;
	}

	public HashMap<String, ArrayList<String>> getTurnoHorariosMap() {
		
		return turnoHorariosMap;
	}

	public HashMap<String, Double> getLocalidadesImportesMap() {
		return localidadesImportesMap;
	}
}
