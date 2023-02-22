package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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


    //Banco
    @FXML private Button btnVerListaEmpleados;
    @FXML private Button btnVerListaClientes;
    @FXML private Button btnVerListaTransacciones;
    @FXML private Button btnVerListaCuentas;


    ObservableList<Empleado> empleados;
    ObservableList<Transaccion> transacciones;
    ObservableList<Cuenta> cuentas;
    ObservableList<Cliente> clientes;

    private final Banco banco = new Banco();

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
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
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



}
