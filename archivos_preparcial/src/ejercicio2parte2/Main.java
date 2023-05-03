package ejercicio2parte2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Programa;
import persistence.Persistencia;

public class Main {

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField modalidadTextField;

    @FXML
    private Button agregarButton;

    @FXML
    private Button buscarButton;

    private Metodos metodos;
    
    @FXML
    void initialize(){
    	metodos = new Metodos();
//    	metodos.guardarXML();
    	metodos = Persistencia.cargarRecursoMetodosXML();
    }

    @FXML
    void agregarPrograma(ActionEvent event) {
        String codigo = codigoTextField.getText();
        String nombre = nombreTextField.getText();
        String tipo = modalidadTextField.getText();
        Programa programa = new Programa(codigo, nombre, tipo);
        metodos.agregarPrograma(programa);
        guardarXML();
    }

    @FXML
    void buscarPrograma(ActionEvent event) {
        String codigo = codigoTextField.getText();
        Programa programa = metodos.buscarPorCodigo(codigo);
        if (programa != null) {
            nombreTextField.setText(programa.getNombre());
            modalidadTextField.setText(programa.getModalidad());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Programa no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("No se ha encontrado ningún programa con ese código.");
            alert.showAndWait();
        }
    }
    
    void guardarXML(){
    	Persistencia.guardarRecursoMetodosXML(metodos);
    }
    
    
}

