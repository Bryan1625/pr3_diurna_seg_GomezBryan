package co.edu.uniquindio.market_place;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.threads.HiloServidorCliente;

public class Server {

	HiloServidorCliente hiloServidorCliente;
	boolean escuchando=true;
	ServerSocket serverComunicacion=null;
    ServerSocket serverTransferenciaObjeto=null;
    DataInputStream flujoEntradaComunicacion=null;
    DataOutputStream flujoSalidaComunicacion=null;
    ObjectOutputStream flujoSalidaObjeto=null;
    ObjectInputStream flujoEntradaObjeto=null;
    
    ArrayList<HiloServidorCliente> aplicacionesClientesActivas = new ArrayList<HiloServidorCliente>();

    public Server() {
        
    }

    public void runServer() {
    	escuchando = true;
        try {
            serverComunicacion = new ServerSocket(8081);
            serverTransferenciaObjeto = new ServerSocket(8082);

            while (escuchando) {
                // Esperar a que un cliente se conecte
                Socket socketComunicacion = null;
                Socket socketTransferenciaObjetos = null;
                
                try {
					System.out.println("Servidor esperando conexion del cliente");
					socketComunicacion = serverComunicacion.accept();
					socketTransferenciaObjetos = serverTransferenciaObjeto.accept();
					System.out.println("Conexión establecida");

					flujoEntradaComunicacion=new DataInputStream(socketComunicacion.getInputStream());
					flujoSalidaComunicacion=new DataOutputStream(socketComunicacion.getOutputStream());
					flujoSalidaObjeto = new ObjectOutputStream(socketTransferenciaObjetos.getOutputStream());
					flujoEntradaObjeto = new ObjectInputStream(socketTransferenciaObjetos.getInputStream());

					iniciarHiloServidorCliente();

				} 
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private void iniciarHiloServidorCliente() {
		
		hiloServidorCliente = new HiloServidorCliente();
		hiloServidorCliente.inicializarConexion(flujoEntradaComunicacion,flujoSalidaComunicacion,flujoSalidaObjeto,flujoEntradaObjeto,this);
		hiloServidorCliente.start();
	}

   

   
}

