package ejercicio2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Estudiante;
import persistence.Persistencia;

public class Metodos {

    ArrayList<Estudiante> estudiantes;

    public Metodos() {
        estudiantes = new ArrayList<>();
    }
    
    public void cargarDatos(){
    	try {
			estudiantes = Persistencia.cargarEstudiantes();
			Persistencia.guardaRegistroLog("estudiantes cargados", 1, "cargar estudiantes");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void agregarEstudiante(Estudiante estudiante) {
        // Verificar si ya existe un estudiante con el mismo identificador
        for (Estudiante e : estudiantes) {
            if (e.getCodigo() == estudiante.getCodigo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Estudiante duplicado");
                alert.setHeaderText("El estudiante con identificación " + e.getCodigo() + " ya existe");
                alert.showAndWait();
                return;
            }
        }
        
        // Si no existe, agregar el estudiante a la lista y guardar en el archivo
        estudiantes.add(estudiante);
        try {
            Persistencia.guardarEstudiantes(estudiantes);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Estudiante agregado");
            alert.setHeaderText("El estudiante se agregó correctamente");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al guardar los estudiantes: " + e.getMessage());
            alert.showAndWait();
        }
    }



    public Estudiante buscarEstudiante(int codigo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigo() == codigo) {
                return estudiante;
            }
        }
        return null;
    }
}
