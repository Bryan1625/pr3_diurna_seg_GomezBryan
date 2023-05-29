package programacion3.parcial3.cliente.ejercicio3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import programacion3.parcial3.cliente.persistence.Persistencia;

public class  ClienteHiloTrabajoGrado extends Thread {
	
	static Universidad universidad;
	private int indiceTrabajoGrado;
	
	public void run() {
		Scanner scanner = new Scanner(System.in);

		try {
			Universidad universidad= new Universidad();
			universidad.trabajosGrado=Persistencia.cargarTrabajosGrado();
			imprimirTrabajosGrado(universidad.getTrabajosGrado());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Que trabajo desea revisar: ");
		indiceTrabajoGrado= scanner.nextInt()-1;
	}
	
	public static void imprimirTrabajosGrado(List<TrabajoGrado>trabajosGrado) throws FileNotFoundException, IOException {
		int i=1;		
		for(TrabajoGrado trabajo:trabajosGrado) {
			System.out.println(i+") Titulo: "+trabajo.getTitulo()+"\nFecha: "+trabajo.getFecha()+"\nDescripcion: "+trabajo.getDescripcion()+"\n");
			i++;
		} 
	}

	public int getIndiceTrabajoGrado() {
		return indiceTrabajoGrado;
	}

	public void setIndiceTrabajoGrado(int indiceTrabajoGrado) {
		this.indiceTrabajoGrado = indiceTrabajoGrado;
	}
	
	
}
