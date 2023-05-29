package programacion3.parcial3.servidor.ejercicio3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import programacion3.parcial3.cliente.ejercicio3.Universidad;
import programacion3.parcial3.servidor.ejercicio3.persistence.Persistencia;

public class Servidor {
	Universidad universidad = new Universidad();
    File localFileTg;
    File localFileAutores;
    ServerSocket server;
    Socket socket;
    BufferedInputStream entradaLocalArchivo;
    BufferedOutputStream flujoSalidaArchivo;
    DataOutputStream flujoSalidaDatos;
    DataInputStream flujoEntrada;
    ObjectOutputStream flujoSalida;
    int puerto = 0;
    int in;
    byte[] byteArray;

    String rutaTg = "archivos\\trabajosGrado.txt";
    String rutaAutores = "archivos\\autores.txt";

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void runServer() throws IOException {
        server = new ServerSocket(puerto);
        socket = server.accept();
        System.out.println("Conectado al cliente");
        localFileTg = new File(rutaTg);
        localFileAutores = new File(rutaAutores);

        try {
            recibirNombreArchivo();
            enviarArchivoTrabajosGrado();
            socket.close();
            socket = server.accept();
            flujoEntrada = new DataInputStream(socket.getInputStream());
            flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            recibirBandera();
            enviarArchivoAutores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recibirNombreArchivo() throws IOException {
        flujoEntrada = new DataInputStream(socket.getInputStream());
        String nombreArchivo = flujoEntrada.readUTF();
        System.out.println("Nombre del archivo recibido: " + nombreArchivo);
    }

    public void enviarArchivoTrabajosGrado() throws IOException, Exception {
        if (!localFileTg.exists()) {
            System.err.println("El archivo de trabajos de grado no existe en la ruta especificada.");
            return;
        }

        entradaLocalArchivo = new BufferedInputStream(new FileInputStream(localFileTg));

        flujoSalidaArchivo = new BufferedOutputStream(socket.getOutputStream());

        flujoSalidaDatos = new DataOutputStream(socket.getOutputStream());

        flujoSalidaDatos.writeUTF(localFileTg.getName());

        // Enviamos el fichero
        byteArray = new byte[8192];
        while ((in = entradaLocalArchivo.read(byteArray)) != -1) {
            flujoSalidaArchivo.write(byteArray, 0, in);
        }

        entradaLocalArchivo.close();
        flujoSalidaArchivo.close();
        flujoSalidaDatos.close();
    }

    public void enviarArchivoAutores() throws IOException, Exception {
        entradaLocalArchivo = new BufferedInputStream(new FileInputStream(localFileAutores));

        flujoSalidaArchivo = new BufferedOutputStream(socket.getOutputStream());

        flujoSalidaDatos = new DataOutputStream(socket.getOutputStream());

        flujoSalidaDatos.writeUTF(localFileAutores.getName());

        // Enviamos el fichero
        byteArray = new byte[8192];
        while ((in = entradaLocalArchivo.read(byteArray)) != -1) {
            flujoSalidaArchivo.write(byteArray, 0, in);
        }

        entradaLocalArchivo.close();
        flujoSalidaArchivo.close();
        flujoSalidaDatos.close();
    }

    private void recibirBandera() throws IOException {
        boolean bandera = flujoEntrada.readBoolean();
        System.out.println("Bandera leída: " + bandera);
    }

    public static void main(String[] args) {
        int puerto = 1234; // Reemplaza el puerto con el que estás utilizando
        Servidor servidor = new Servidor(puerto);

        try {
            servidor.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarUniversidad(){
		try {
			Persistencia.cargarDatosTrabajoGrado(universidad);
			Persistencia.cargarDatosAutores(universidad);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
