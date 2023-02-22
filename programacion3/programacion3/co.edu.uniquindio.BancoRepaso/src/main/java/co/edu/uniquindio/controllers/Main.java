package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.*;
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

import java.io.IOException;
import java.util.Optional;

public class Main {



    //Cuentas
    @FXML private TextField txtFNombreCliente2;
    @FXML private TextField txtFCedulaCliente2;
    @FXML private TextField txtFNumeroCuenta;
    @FXML private Button btnBuscarCuenta;
    @FXML private TableView <Cuenta> tblCuentas;
    @FXML private TableColumn tblColNumeroCuenta;
    @FXML private TableColumn tblColSaldo;
    private Cliente propietario;

    //Empleados
    @FXML private TableView <Empleado> tblEmpleados;
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

    //Transacciones
    @FXML private TableView <Transaccion> tblTransacciones;
    @FXML private TableColumn<String,String> tblColTipoTransaccion;
    @FXML private TableColumn tblColFechaTransaccion;
    @FXML private TableColumn tblColHoraTransaccion;
    @FXML private TableColumn tblColValorTransaccion;
    @FXML private TableColumn tblColEstadoTransaccion;
    @FXML private TextField txtFTipoTransaccion;
    @FXML private TextField txtFFechaTransaccion;
    @FXML private TextField txtFValorTransaccion;
    @FXML private TextField txtFEstadoTransaccion;
    @FXML private Button btnBuscarTransaccion;
    @FXML private Button btnVerTransaccion;


    //Banco
    @FXML private Button btnVerListaEmpleados;
    @FXML private Button btnVerListaClientes;
    @FXML private Button btnVerListaTransacciones;
    @FXML private Button btnVerListaCuentas;


    ObservableList<Empleado> empleados;
    ObservableList<Transaccion> transacciones;
    ObservableList<Cuenta> cuentas;

    private Banco banco = new Banco();

    @FXML
    public void initialize() {



    }

    public void onVerListaEmpleadosClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/empleadosUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onVerListaClientesClick(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/clientesUI.fxml"));
            Parent root = loader.load();
            ClientesController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void onVerListaTransaccionesClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/transaccionesUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onVerListaCuentasClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/cuentasUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onBuscarCuentaClick(ActionEvent actionEvent) {
        String numeroCuenta = txtFNumeroCuenta.getText();
        if (numeroCuenta.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Número de cuenta no ingresado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese el número de cuenta que desea buscar.");
            alert.showAndWait();
            return;
        }
        Cuenta cuentaBuscada = buscarCuenta(numeroCuenta);
        propietario = cuentaBuscada.getPropietario();
        if (cuentaBuscada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cuenta no encontrada");
            alert.setHeaderText(null);
            alert.setContentText("La cuenta con el número " + numeroCuenta + " no se encuentra registrada en el sistema.");
            alert.showAndWait();
            return;
        }
        txtFNombreCliente2.setText(propietario.getNombre());
        txtFCedulaCliente2.setText(propietario.getCedula());
    }

    private Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }


    public void onIngresarEmpleadoClick(ActionEvent actionEvent) {
        String nombre = txtFNombreEmpleado.getText();
        String cedula = txtFCedulaEmpleado.getText();
        String apellidos = txtFApellidosEmpleado.getText();
        String direccion = txtFDireccionEmpleado.getText();

        Empleado empleado = new Empleado(nombre, apellidos, cedula, direccion);

        empleados.add(empleado);

        txtFNombreEmpleado.clear();
        txtFApellidosEmpleado.clear();
        txtFCedulaEmpleado.clear();
    }

    public void onBuscarEmpleadoClick(ActionEvent actionEvent) {
    }

    public void onEliminarEmpleadoClick(ActionEvent actionEvent) {
    }

    public void onVerEmpleadoClick(ActionEvent actionEvent) {
    }

    public void onBuscarTransaccion(ActionEvent actionEvent) {
    }

    public void onVerTransaccion(ActionEvent actionEvent) {
    }


}
