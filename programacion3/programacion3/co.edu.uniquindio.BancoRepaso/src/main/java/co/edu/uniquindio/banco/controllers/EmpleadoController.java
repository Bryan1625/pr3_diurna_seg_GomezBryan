package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.model.Empleado;
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

    }

    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
        txtFNombreEmpleado1.setText(empleado.getNombre());
        txtFApellidosEmpleado1.setText(empleado.getNombre());
        txtFCedulaEmpleado1.setText(empleado.getNombre());
        txtFDireccionEmpleado1.setText(empleado.getNombre());
    }

}
