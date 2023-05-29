package co.edu.uniquindio.market_place.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_CargarResourceXML extends Solicitud{
	
	ModelFactoryController modelFactory;
	Socket socket;
	int puerto;
	String host;
	ObjectInputStream flujoEntrada;

	public Hilo_CargarResourceXML(String peticion, Metodos m, ModelFactoryController modelFactory) {
		super(peticion, m);
		this.modelFactory = modelFactory;
	}

	@Override
	public void run() {
		try {
			System.out.println("conectando con el servidor");
			crearConexion();
			
			flujoEntrada = new ObjectInputStream(socket.getInputStream());
			
			cargarModeloXML();
			
			cerrarConexion();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cerrarConexion() {
		try {
			socket.close();
			flujoEntrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarModeloXML(){
		try {
			modelFactory = (ModelFactoryController) flujoEntrada.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void crearConexion() throws IOException {
		socket = new Socket(host, puerto);
	}
	
	
}
