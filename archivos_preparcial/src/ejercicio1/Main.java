package ejercicio1;
import java.util.ArrayList;

import model.Estudiante;
import persistence.Persistencia;

public class Main {

	public static void main(String[] args) {
		inicializarDatos();
		Hilo hilo = new Hilo();
		hilo.start();

	}

	public static void inicializarDatos() {

		Estudiante estudiante1 = new Estudiante(1, "Juan", 3.5, 4.0, 3.8);
		Estudiante estudiante2 = new Estudiante(2, "Maria", 4.0, 3.7, 4.2);
		Estudiante estudiante3 = new Estudiante(3, "Pedro", 2.5, 3.0, 3.2);

		ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
		listaEstudiantes.add(estudiante1);
		listaEstudiantes.add(estudiante2);
		listaEstudiantes.add(estudiante3);

		try {
			Persistencia.guardarEstudiantes(listaEstudiantes);
		} catch (Exception e) {
			System.out.println("Error al guardar los estudiantes: " + e.getMessage());
		}
	}
}
