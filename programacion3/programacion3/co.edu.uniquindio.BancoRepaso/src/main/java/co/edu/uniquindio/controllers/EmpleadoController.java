package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Empleado;
import co.edu.uniquindio.model.Transaccion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EmpleadoController {

    //Empleado
    @FXML
    private TextField txtFNombreEmpleado1;
    @FXML private TextField txtFApellidosEmpleado1;
    @FXML private TextField txtFCedulaEmpleado1;
    @FXML private TextField txtFDireccionEmpleado1;

    private Empleado empleado;

    @FXML
    public void initialize(){
        txtFNombreEmpleado1.setText(empleado.getNombre());
        txtFApellidosEmpleado1.setText(empleado.getNombre());
        txtFCedulaEmpleado1.setText(empleado.getNombre());
        txtFDireccionEmpleado1.setText(empleado.getNombre());
    }

    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
    }

}
