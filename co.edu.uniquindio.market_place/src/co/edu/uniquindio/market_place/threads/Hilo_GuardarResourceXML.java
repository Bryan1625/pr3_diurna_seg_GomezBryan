package co.edu.uniquindio.market_place.threads;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_GuardarResourceXML extends Thread{

	Metodos m;
	
	public Hilo_GuardarResourceXML(Metodos m) {
		// TODO Auto-generated constructor stub
		this.m = m;
	}
	
	@Override
	public void run() {
		m.guardarResourceXML();
	}
}
