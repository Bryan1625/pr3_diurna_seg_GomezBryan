package ejercicio2;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Estudiante;
import persistence.Persistencia;

public class Main {

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNota1;
	@FXML
	private TextField txtNota2;
	@FXML
	private TextField txtNota3;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnBuscar;
	
	Metodos m = new Metodos();

	private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

	@FXML
	void initialize(){
//		inicializarDatos();
		m.cargarDatos();
		estudiantes = m.estudiantes;
	}

	@FXML
	private void agregarAction() {
		String nombre = txtNombre.getText();
		int codigo = Integer.parseInt(txtCodigo.getText());
		double nota1 = Double.parseDouble(txtNota1.getText());
		double nota2 = Double.parseDouble(txtNota2.getText());
		double nota3 = Double.parseDouble(txtNota3.getText());
		
		Estudiante e = new Estudiante(codigo,nombre,nota1,nota2,nota3);

		m.agregarEstudiante(e);
		
		txtNombre.setText("");
		txtCodigo.setText("");
		txtNota1.setText("");
		txtNota2.setText("");
		txtNota3.setText("");

	}

	@FXML
	private void buscarAction() {
	    int codigo = Integer.parseInt(txtCodigo.getText());
	    Estudiante e = m.buscarEstudiante(codigo);
	    if (e == null) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Estudiante no encontrado");
	        alert.setContentText("No se encontró un estudiante con el código ingresado.");
	        alert.showAndWait();
	    } else {
	        txtNombre.setText(e.getNombre());
	        txtCodigo.setText(Integer.toString(e.getCodigo()));
	        txtNota1.setText(Double.toString(e.getNota1()));
	        txtNota2.setText(Double.toString(e.getNota2()));
	        txtNota3.setText(Double.toString(e.getNota3()));
	    }
	}


	public void inicializarDatos() {

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
