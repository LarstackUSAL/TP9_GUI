package ar.edu.usal.tp9.model.dao;

import java.util.ArrayList;

import ar.edu.usal.tp9.model.dto.Paquetes;

public class PaquetesDao {

	private static PaquetesDao paquetesDaoInstance = null;
	
	private ArrayList<Paquetes> paquetes;
	
	private PaquetesDao(){
		
		this.paquetes = new ArrayList<Paquetes>();
		 
		this.loadPaquetes();
	}

	public static PaquetesDao getInstance(){
		
		if(paquetesDaoInstance==null){
			
			paquetesDaoInstance = new PaquetesDao();
		}
		
		return paquetesDaoInstance;
	}
	
	private void loadPaquetes() {
		
//		File paquetesTxt = new File("./archivos/PAQUETES.txt");
//		Scanner paquetesScanner;
//		
//		try {
//			
//			try {
//				paquetesTxt.createNewFile();
//			
//			} catch (IOException e) {
//
//				System.out.println("Se ha verificado un error al cargar el archivo de paquetes.");
//			}
//			
//			paquetesScanner = new Scanner(paquetesTxt);
//			
//			while(paquetesScanner.hasNextLine()){
//				
//				Paquetes paquetes;
//				
//				String linea = paquetesScanner.nextLine();
//				String[] lineaArray = linea.split(";");
//
//				hotel.setNombre(lineaArray[0].trim());
//				hotel.setEstrellas(Integer.valueOf(lineaArray[1].trim()));				
//				
//				ArrayList<String> localidades = new ArrayList<String>();
//				
//				for (int i = 2; i < lineaArray.length; i++) {
//					
//					localidades.add(lineaArray[i].trim());
//				}				
//				hotel.setLocalidades(localidades);
//				
//				this.hoteles.add(hotel);
//			}
//			
//			hotelesScanner.close();
//			
//		}catch(InputMismatchException e){
//			
//			System.out.println("Se ha encontrado un tipo de dato insesperado.");
//			
//		}catch (FileNotFoundException e) {
//			
//			System.out.println("No se ha encontrado el archivo.");
//		}
	}
	
	public void persistirInformacion() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Paquetes> getPaquetes() {
		return paquetes;
	}
}
