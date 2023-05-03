package co.edu.uniquindio.market_place.threads;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_GuardarResourceXML extends Thread{

	Metodos m;
	
	public Hilo_GuardarResourceXML(ModelFactoryController model) {
		// TODO Auto-generated constructor stub
		m = new Metodos(model);
	}
	
	@Override
	public void run() {
		m.guardarResourceXML();
	}
}
