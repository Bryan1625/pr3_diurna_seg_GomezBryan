package ejercicio2parte2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Programa;
import persistence.Persistencia;

public class Metodos {

	private ArrayList<Programa> programas;

	public Metodos() {
		this.programas = new ArrayList<Programa>();
	}

	public void agregarPrograma(Programa programa) {
		if (buscarPorCodigo(programa.getCodigo()) != null) {
			Alert alerta = new Alert(Alert.AlertType.ERROR, "El programa ya existe.", ButtonType.OK);
			alerta.showAndWait();
		} else {
			boolean agregado = this.programas.add(programa);
			if (agregado) {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Éxito");
				alerta.setHeaderText("Programa agregado correctamente");
				alerta.setContentText(
						"El programa con código " + programa.getCodigo() + " fue agregado correctamente.");
				alerta.showAndWait();
			} else {
				Alert alerta = new Alert(Alert.AlertType.ERROR, "No se pudo agregar el programa.", ButtonType.OK);
				alerta.showAndWait();
			}
		}
	}

	public ArrayList<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(ArrayList<Programa> programas) {
		this.programas = programas;
	}

	public Programa buscarPorCodigo(String codigo) {
		for (Programa programa : this.programas) {
			if (programa.getCodigo().equals(codigo)) {
				return programa;
			}
		}
		return null;
	}
	
	void guardarXML(){
		try {
			setProgramas(Persistencia.cargarProgramas());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Persistencia.guardarRecursoMetodosXML(this);
    }
	

}
