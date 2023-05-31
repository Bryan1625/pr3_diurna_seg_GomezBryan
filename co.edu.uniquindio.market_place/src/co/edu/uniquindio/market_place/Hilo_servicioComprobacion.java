package co.edu.uniquindio.market_place;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_servicioComprobacion extends Thread{
	
	boolean modeloDiferente=false;
	ModelFactoryController m;
	DataInputStream flujoEntrada;
	ObjectOutputStream flujoSalidaModelo;
	Socket socket;
	

	
	public Hilo_servicioComprobacion(ModelFactoryController m, DataInputStream flujoEntrada,Socket socket,ObjectOutputStream flujoSalidaModelo) {
		this.m = m;
		this.flujoEntrada = flujoEntrada;
		this.socket = socket;
		this.flujoSalidaModelo = flujoSalidaModelo;
	}
	
	 @Override
	    public void run() {
	        while (true) {
	            try {
	                socket = new Socket("localhost", 8083);
	                iniciarConexion();
	                comprobarCambios();
	                if (modeloDiferente) {
	                    actualizarModelo();
	                }
	                cerrarConexion();
	                Thread.sleep(1000);
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	
	private void cerrarConexion() {
		try {
			flujoEntrada.close();
			flujoSalidaModelo.close();
	        socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private void iniciarConexion() {
		try {
			flujoEntrada = new DataInputStream(socket.getInputStream());
			flujoSalidaModelo = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void comprobarCambios() {
		try {
			flujoSalidaModelo.writeObject(m.getMarketPlace());
			modeloDiferente = flujoEntrada.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actualizarModelo(){
		m.guardarResourceXML();
	}
}
