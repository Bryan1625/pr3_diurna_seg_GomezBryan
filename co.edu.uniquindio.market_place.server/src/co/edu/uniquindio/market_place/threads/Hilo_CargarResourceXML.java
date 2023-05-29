package co.edu.uniquindio.market_place.threads;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_CargarResourceXML extends Solicitud{
	
	ModelFactoryController modelFactory;
	
	public Hilo_CargarResourceXML(ModelFactoryController modelFactory) {
	    this.modelFactory = modelFactory;
	}

	
	@Override
	public void run() {
		modelFactory.setmarketPlace(getM().cargarResourceXML());
	}
}
