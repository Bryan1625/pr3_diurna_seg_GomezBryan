package empresaDePaqueteria.model;

import java.util.ArrayList;

public class Repartidor {

	private String nombre;
	private String id;
	private ArrayList<String> paquetes;

	public void recogerPaquetes(ArrayList<String> paquetes){
		for (int i = 0; i < 5; i++) {
			this.paquetes.add(paquetes.get(i));
		}
	}
}
