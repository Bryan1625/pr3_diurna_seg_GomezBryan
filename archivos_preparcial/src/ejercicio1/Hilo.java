package ejercicio1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Estudiante;
import persistence.Persistencia;

public class Hilo extends Thread{

	
	public Hilo() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public void run() {
        try {
            ArrayList<Estudiante> estudiantes = Persistencia.cargarEstudiantes();
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante.getCodigo() + "; " + estudiante.getNombre() + "; " + estudiante.getNota1() + "; " + estudiante.getNota2() + "; " + estudiante.getNota3());
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Hubo un error al leer el archivo.");
        }
    }
}
