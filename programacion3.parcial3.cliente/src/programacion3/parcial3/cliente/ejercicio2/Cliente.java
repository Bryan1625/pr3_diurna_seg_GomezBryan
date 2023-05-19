package programacion3.parcial3.cliente.ejercicio2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente{

	Socket miSocket;
	String frase;
	String host;
	int puerto;
	Socket socketComunicacion;
	DataOutputStream flujoSalida;
	DataInputStream flujoEntrada;
	String cadena;

	public Cliente(String frase, String host, int puerto,String cadena) {
		this.puerto = puerto;
		this.host = host;
		this.frase = frase;
		this.cadena = cadena;
	}

	public void iniciarCliente() {

		try {
			System.out.println("conectando con el servidor");
			crearConexion();

			flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());			
			flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());

			enviarDatosPrimitivos(frase);
			enviarDatosPrimitivos(cadena);

			System.out.println("recibiendo datos del servidor");
			recibirDatosPrimitivos();


			flujoEntrada.close();
			flujoSalida.close();
			socketComunicacion.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void enviarDatosPrimitivos(String mensaje) throws IOException {

		flujoSalida.writeUTF(mensaje);
	}

	private void recibirDatosPrimitivos() throws IOException {

		System.out.println("Datos recibidos del servidor: " + flujoEntrada.readUTF());
	}

	private void crearConexion() throws IOException {
		socketComunicacion = new Socket(host, puerto);
	}

}
