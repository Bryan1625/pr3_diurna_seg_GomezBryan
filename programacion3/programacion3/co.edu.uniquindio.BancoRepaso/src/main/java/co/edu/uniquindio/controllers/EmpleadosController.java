package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Optional;

public class EmpleadosController {

    //Empleados
    @FXML
    private TableView<Empleado> tblEmpleados;
    @FXML private TableColumn tblColCedulaEmpleado;
    @FXML private TableColumn tblColNombreEmpleado;
    @FXML private TextField txtFNombreEmpleado;
    @FXML private TextField txtFCedulaEmpleado;
    @FXML private TextField txtFApellidosEmpleado;
    @FXML private TextField txtFDireccionEmpleado;
    @FXML private Button btnIngresarEmpleado;
    @FXML private Button btnBuscarEmpleado;
    @FXML private Button btnEliminarEmpleado;
    @FXML private Button btnVerEmpleado;

    ObservableList<Empleado> empleados;

    @FXML
    public void initialize(){
        this.empleados = FXCollections.observableArrayList();
        this.tblColCedulaEmpleado.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.tblColNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    public void initAtributos(ObservableList<Empleado> empleados){
        this.empleados = empleados;
    }

    public void onBuscarEmpleadoClick(ActionEvent actionEvent) {
        String cedula = txtFCedulaEmpleado.getText();

        if (!cedula.isEmpty()) {
            for (Empleado empleado : empleados) {
                if (empleado.getCedula() == Integer.parseInt(cedula)) {
                    txtFNombreEmpleado.setText(empleado.getNombre());
                    txtFApellidosEmpleado.setText(empleado.getApellidos());
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empleado no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("No se encontró ningún empleado con la cédula " + cedula);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cédula vacía");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una cédula");
            alert.showAndWait();
        }
    }

    public void onEliminarEmpleadoClick(ActionEvent actionEvent) {
        Empleado empleadoSeleccionado = tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que quieres eliminar a este empleado?");
            alert.setContentText(empleadoSeleccionado.getNombre() + " " + empleadoSeleccionado.getApellidos());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                empleados.remove(empleadoSeleccionado);
                tblEmpleados.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona un empleado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un empleado de la lista.");
            alert.showAndWait();
        }
    }

    public void onVerEmpleadoClick(ActionEvent actionEvent) {
        Empleado empleado = tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/empleadoUI.fxml"));
                Parent parent = loader.load();
                EmpleadoController empleadoController = loader.getController();
                empleadoController.setEmpleado(empleado);
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Información del empleado");
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un empleado primero.");
            alert.showAndWait();
        }
    }
    public void onIngresarEmpleadoClick(ActionEvent actionEvent) {
        String nombre = txtFNombreEmpleado.getText();
        int cedula = Integer.parseInt(txtFCedulaEmpleado.getText());
        String apellidos = txtFApellidosEmpleado.getText();
        String direccion = txtFDireccionEmpleado.getText();
        String cedulaString = "" + cedula;

        if (!StringUtils.isNumeric(cedulaString)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La cédula debe ser un número", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (nombre.isEmpty() || apellidos.isEmpty() || cedulaString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Todos los campos son obligatorios", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        for (Empleado emp : empleados) {
            if (emp.getCedula() == cedula) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El empleado ya existe", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }
        Empleado empleado = new Empleado(nombre, apellidos, cedula, direccion);

        empleados.add(empleado);
        tblEmpleados.setItems(empleados);
        txtFNombreEmpleado.clear();
        txtFApellidosEmpleado.clear();
        txtFCedulaEmpleado.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "El empleado ha sido agregado correctamente", ButtonType.OK);
        alert.showAndWait();





    }

}
