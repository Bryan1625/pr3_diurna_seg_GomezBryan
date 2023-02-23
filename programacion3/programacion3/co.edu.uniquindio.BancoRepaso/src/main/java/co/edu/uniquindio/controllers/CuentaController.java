package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Cuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CuentaController {

    //Cuenta
    @FXML
    private Button btnRealizarTransaccion;
    @FXML private TextField txtFNumeroCuenta1;
    @FXML private TextField txtFSaldoCuenta;
    @FXML private Button btnVerDueno;

    private Cuenta cuenta;

    private Cliente cliente;

    @FXML
    public void initialize(){

    }

    public void onRealizarTransaccionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/transaccionUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TransaccionController controller = loader.getController();
            controller.setCuenta(this.cuenta);
            Stage stage = (Stage) btnRealizarTransaccion.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void onVerDuenoClick(ActionEvent actionEvent) {
        if (cliente != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/clienteUI.fxml"));
                Parent root = loader.load();
                ClienteController clienteController = loader.getController();
                clienteController.setCliente(cliente);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Información del cliente");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha seleccionado un cliente");
            alert.showAndWait();
        }
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        txtFNumeroCuenta1.setText(cuenta.getNumeroCuenta());
        txtFSaldoCuenta.setText(String.valueOf(cuenta.getSaldo()));
    }
}

