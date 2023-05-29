package programacion3.parcial3.cliente.ejercicio3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import programacion3.parcial3.servidor.ejercicio3.persistence.Persistencia;

public class Cliente extends Thread{
	ArrayList<TrabajoGrado>trabajosGrado;
	ArrayList<TrabajoGrado>autores;
	Socket socket;
	
	DataInputStream flujoEntradaDatos;
	DataOutputStream flujoSalida;
	ObjectInputStream flujoEntrada;
	BufferedInputStream flujoEntradaArchivo;
	BufferedOutputStream salidaLocalArchivo;
	Universidad universidad;

	
	//DataOutputStream flujoSalidaDatos;
	int puerto=0;
	int in;
	byte[] receivedData;
	
	String rutaRecibidos = "C:\\td\\archivos";
	String filename;
	
	public Cliente(int puerto) throws UnknownHostException, IOException {
		this.puerto=puerto;
	}
	
	public void crearConexion() throws UnknownHostException, IOException{
		socket = new Socket("localhost", puerto);
		System.out.println("Encendido cliente");
		System.out.println("Esperando el servidor");
	}
	
	public void cerrarConexion() {
	    try {
	        socket.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void recibirArchivo() throws IOException {
		receivedData = new byte[1024];

		flujoEntradaArchivo = new BufferedInputStream(socket.getInputStream());
		flujoEntradaDatos   =new DataInputStream(socket.getInputStream());

		//Recibimos el nombre del fichero
		filename = flujoEntradaDatos.readUTF();
		filename = filename.substring(filename.indexOf('\\')+1,filename.length());

		rutaRecibidos = rutaRecibidos +"\\"+filename;

		//Para guardar fichero recibido
		salidaLocalArchivo = new BufferedOutputStream(new FileOutputStream(rutaRecibidos));

		while ((in = flujoEntradaArchivo.read(receivedData)) != -1)
		{
			salidaLocalArchivo.write(receivedData,0,in);
		}
		rutaRecibidos = "C:\\td\\archivos";
		salidaLocalArchivo.close();
		flujoEntradaDatos.close();
		System.out.println("Archivo recibido");
    }
	
	private void enviarBandera() throws IOException {
		
		boolean bandera =true;
		flujoSalida.writeBoolean(bandera);
		System.out.println("Enviando bandera");
	}
	
	public void iniciarCliente() {
		try {
			crearConexion();
			//recibir archivo trabajos de grado
			recibirArchivo();
			cerrarConexion();
			
			ClienteHiloTrabajoGrado hilo= new ClienteHiloTrabajoGrado();
			hilo.start();
			hilo.join();
			
			crearConexion();
			flujoSalida = new DataOutputStream(socket.getOutputStream());
			flujoEntrada = new ObjectInputStream(socket.getInputStream());
			enviarBandera();
			
			//recibir archivo autores
			recibirArchivo();
			cerrarConexion();
			
			ClienteHiloAutores hilo2= new ClienteHiloAutores(hilo.getIndiceTrabajoGrado());
			hilo2.start();
			hilo2.join();
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recibirUniversidad(){
		try {
			flujoEntrada.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
