package programacion3.parcial3.cliente.ejercicio3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import programacion3.parcial3.cliente.persistence.Persistencia;

public class ClienteHiloAutores extends Thread {
	static Universidad universidad;
	int indice;
	
	public ClienteHiloAutores(int indice) {
		this.indice=indice;
	}
	
	public void run() {
		try {
			Universidad universidad= new Universidad();
			universidad.trabajosGrado=Persistencia.cargarTrabajosGrado();
			imprimirDatosAutores(universidad.getTrabajosGrado().get(indice));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void imprimirDatosAutores(TrabajoGrado trabajo) {
		List<Autor>autores= trabajo.getAutores();
		System.out.println("Autores: ");
		for(Autor autor: autores) {
			System.out.println("Nombre: "+autor.getNombre()+"\nApellido: "+autor.getApellido()+"\nCedula: "+autor.getCedula()+"\nPrograma: "+autor.getPrograma()+"\nTitulo: "+autor.getTitulo()+"\n");
		}
	}
}
