package repaso.conceptos;

import repaso.conceptos.model.Avion;
import repaso.conceptos.model.Carro;
import repaso.conceptos.model.Vehiculo;

public class Main {

	public static void main(String[] args) {
		repasoHerencia();
		repasoInterface();
	}

	private static void repasoInterface() {
		
		
	}

	private static void repasoHerencia() {
		Carro carro = new Carro();
		carro.setNombre("Mi carro");
		System.out.println(carro.getNombre());
		carro.encenderMotorElectrico("Iniciando encendido", 30000);
		
		Avion avion = new Avion();
		avion.setNombre("Mi avion");
		System.out.println(avion.getNombre());
		avion.encenderVehiculo();		
		
		Vehiculo vehiculo = new Avion();				
		vehiculo.setNombre("Otro Avion");
		System.out.println(vehiculo.getNombre());

	}

	
	
//	Vehiculo vehiculo = new Vehiculo();
//	vehiculo.setNombre("Avion");
//	
//	
//	Vehiculo vehiculo2 = new Vehiculo();
//	vehiculo2.setNombre("Carro");
//	
//	
//	if(vehiculo.getNombre().contentEquals("Avion"))
//		System.out.println("Es un avion");
//	
//	if(vehiculo2.getNombre().contentEquals("Avion"))
//		System.out.println("Es un avion");
	
}
