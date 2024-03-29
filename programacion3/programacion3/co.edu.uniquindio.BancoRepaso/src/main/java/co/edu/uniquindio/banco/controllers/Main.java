package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {


    @FXML private Button btnVerListaEmpleados;
    @FXML private Button btnVerListaClientes;
    @FXML private Button btnVerListaTransacciones;
    @FXML private Button btnVerListaCuentas;


    private final Banco banco = new Banco();

    ObservableList<Empleado> empleados;
    ObservableList<Cliente> clientes;
    ObservableList<Cuenta> cuentas;
    ObservableList<Transaccion> transacciones;


    @FXML
    public void initialize() {
        empleados = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList();
        cuentas = FXCollections.observableArrayList();
        transacciones = FXCollections.observableArrayList();
    }

    public void onVerListaEmpleadosClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/empleadosUI.fxml"));
            Parent root = loader.load();
            EmpleadosController controlador = loader.getController();
            controlador.initAtributos(empleados);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onVerListaClientesClick(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/clientesUI.fxml"));
            Parent root = loader.load();
            ClientesController controlador = loader.getController();
            controlador.initAtributos(clientes,cuentas);
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/transaccionesUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onVerListaCuentasClick(ActionEvent actionEvent) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/cuentasUI.fxml"));
            Parent root = loader.load();
            CuentasController controller = loader.getController();
            controller.initAtributos(cuentas);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
