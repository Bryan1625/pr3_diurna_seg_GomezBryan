package programacion3.parcial3.servidor.ejercicio2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.Normalizer;

public class Servidor {

	String host = "localhost";
	int puerto = 8081;
	ServerSocket server;

	Socket socketComunicacion;

	DataOutputStream flujoSalida;
	DataInputStream flujoEntrada;
	BufferedReader entrada;

	String mensajeCliente;
	String resultado = "";
	String vocales = "vocales: ";
	String consonantes = "consonantes: ";

	public Servidor() {
		// TODO Auto-generated constructor stub
	}

	public void iniciarServidor() {

		try {
			server = new ServerSocket(puerto);
			while (true) {
				System.out.println("Esperando al cliente");
				socketComunicacion = server.accept();

				flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
				flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());

				mensajeCliente = recibirDatosPrimitivos();
				if (isPalindroma(mensajeCliente)) {
					resultado += mensajeCliente + " es palindroma\n";
				} else {
					resultado += mensajeCliente + " no es palindroma\n";
				}
				mensajeCliente = recibirDatosPrimitivos();
				vocalesYConsonantes(mensajeCliente, mensajeCliente.length()-1);
				resultado += vocales + "\n" + consonantes;

				System.out.println("enviando datos del cliente");
				enviarDatosPrimitivos();

				flujoEntrada.close();
				flujoSalida.close();
				socketComunicacion.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String recibirDatosPrimitivos() throws IOException {

		return flujoEntrada.readUTF();
	}

	private void enviarDatosPrimitivos() throws IOException {

		flujoSalida.writeUTF(resultado);
		System.out.println("Se envio el resultado");
	}

	public boolean isPalindroma(String frase) {
		// Elimina espacios en blanco y elimina tildes, lo deja en minuscula
		String fraseSinEspacios = frase.replaceAll("\\s+", "").toLowerCase();
		fraseSinEspacios = Normalizer.normalize(fraseSinEspacios, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

		// revierte la frase
		StringBuilder fraseInvertida = new StringBuilder(fraseSinEspacios);
		fraseInvertida.reverse();

		return fraseSinEspacios.equals(fraseInvertida.toString());
	}

	public void vocalesYConsonantes(String cadena, int i) {
		if (i < 0) {
			return;
		} else {

			if (isVocal(cadena.charAt(i))) {
				vocales += cadena.charAt(i);
			} else {
				consonantes += cadena.charAt(i);
			}
			i--;
			vocalesYConsonantes(cadena, i);
		}
	}

	public boolean isVocal(char letra) {
		String l = "" + letra;
		l = l.toLowerCase();
		if (l.equals("a") || l.equals("e") || l.equals("i") || l.equals("o") || l.equals("u")) {
			return true;
		}
		return false;
	}

}