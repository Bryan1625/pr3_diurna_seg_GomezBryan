package co.edu.uniquindio.market_place;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;
import co.edu.uniquindio.market_place.threads.Hilo_CargarResourceXML;
import co.edu.uniquindio.market_place.threads.Metodos;
import co.edu.uniquindio.market_place.threads.Solicitud;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    int puerto;
    ModelFactoryController model;

    public void start() {
        try {
        	model = ModelFactoryController.getInstance();
            serverSocket = new ServerSocket(puerto);
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                // Esperar a que un cliente se conecte
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Configurar los flujos de entrada y salida
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                // Leer la solicitud del cliente
                Solicitud peticion = (Solicitud) inputStream.readObject();
                System.out.println("Received request: " + peticion.getPeticion());

                // Manejar la solicitud según el tipo
                if (peticion.getPeticion().equals("opcion1")) {
                    // Ejecutar el método correspondiente para la opción 1
                    ejecutarOpcion1();
                } else if (peticion.getPeticion().equals("opcion2")) {
                    // Ejecutar el método correspondiente para la opción 2
                    ejecutarOpcion2();
                } else if (peticion.getPeticion().equals("opcion3")) {
                    // Ejecutar el método correspondiente para la opción 3
                    ejecutarOpcion3();
                } else {
                    // Opción inválida
                    System.out.println("Invalid request.");
                }

                // Cerrar la conexión con el cliente
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Server(int puerto) {
		this.puerto = puerto;
	}

    private void ejecutarOpcion1() {
        // Lógica para la opción 1
        System.out.println("Executing option 1...");
    }

    private void ejecutarOpcion2() {
        // Lógica para la opción 2
        System.out.println("Executing option 2...");
    }

    private void ejecutarOpcion3() {
        Hilo_CargarResourceXML hilo = new Hilo_CargarResourceXML(model);
        Thread thread = new Thread(hilo);
        thread.start();
    }
}
