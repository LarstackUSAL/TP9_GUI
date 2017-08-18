package ar.edu.usal.tp9.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.usal.tp9.model.dto.Hoteles;

public class HotelesDao {
	
	private static HotelesDao hotelesDaoInstance = null;

	private ArrayList<Hoteles> hoteles;

	private HotelesDao(){
		
		this.hoteles = new ArrayList<Hoteles>();
		
		this.loadHoteles();
	}

	public static HotelesDao getInstance(){
		
		if(hotelesDaoInstance==null){
			
			hotelesDaoInstance = new HotelesDao();
		}
		
		return hotelesDaoInstance;
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
	
	public Hoteles getHotelByNombre(String hotelString) {

		Iterator hotelesIterator = this.hoteles.iterator();
		
		while (hotelesIterator.hasNext()) {
			
			Hoteles hotel = (Hoteles) hotelesIterator.next();
			
			if(hotel.getNombre().trim().equals(hotelString.trim())){
				
				return hotel;
			}
		}
		
		return null;
	}

	public ArrayList<Hoteles> getHoteles() {
		return hoteles;
	}
}
