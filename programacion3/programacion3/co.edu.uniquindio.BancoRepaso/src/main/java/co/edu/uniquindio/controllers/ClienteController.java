package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteController {

    //Clase Cliente
    @FXML
    private TextField txtFNombreCliente1;
    @FXML private TextField txtFApellidosCliente1;
    @FXML private TextField txtFCedulaCliente1;
    @FXML private TextField txtFEmailCliente1;
    @FXML private TextField txtFDireccionCliente1;
    @FXML private Button btnVerCuenta;
    @FXML private Button btnAgregarCuenta;

    private Cliente cliente;

    @FXML
    public void initialize(){
        txtFNombreCliente1.setText(cliente.getNombre());
        txtFApellidosCliente1.setText(cliente.getApellidos());
        txtFCedulaCliente1.setText(cliente.getCedula());
        txtFEmailCliente1.setText(cliente.getEmail());
        txtFDireccionCliente1.setText(cliente.getDireccion());
    }


    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void onVerCuentaClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/cuentaUI.fxml"));
            Parent root = loader.load();
            CuentaController cuentaController = loader.getController();
            cuentaController.setCliente(cliente);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cuenta del cliente " + cliente.getNombre());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAgregarCuentaClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/agregarCuentaUI.fxml"));
            Parent root = loader.load();
            ClientesController clientesController = loader.getController();
            clientesController.setClienteSeleccionado(cliente);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar cuenta al cliente " + cliente.getNombre() + " " + cliente.getApellidos());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
