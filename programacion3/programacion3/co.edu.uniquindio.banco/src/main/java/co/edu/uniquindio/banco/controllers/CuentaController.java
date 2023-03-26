package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.Cliente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CuentaController {

    public TextField txtFNumeroCuenta3;
    public TextField txtFNombrePropietario;
    public TextField txtFSaldo;
    public Button btnCrearCuenta;

    @FXML
    private Button btnRealizarTransaccion;
    @FXML private TextField txtFNumeroCuenta1;
    @FXML private TextField txtFSaldoCuenta;
    @FXML private Button btnVerDueno;

    private Cuenta cuenta;

    private Cliente cliente;

    ObservableList<Cuenta> cuentas;

    @FXML
    public void initialize(){

    }

    public void onRealizarTransaccionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/transaccionUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TransaccionController controller = loader.getController();
            controller.initAtributos(cliente,cuenta);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/clienteUI.fxml"));
                Parent root = loader.load();
                ClienteController clienteController = loader.getController();
                clienteController.setCliente(cliente, cuentas);
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
    public void setCliente2(Cliente cliente) {
        this.cliente = cliente;
        txtFNombrePropietario.setText(cliente.getNombre());
        txtFSaldo.setText("0");
    }

    public void setCuenta(Cuenta cuenta, ObservableList<Cuenta> cuentas) {
        this.cuenta = cuenta;
        txtFNumeroCuenta1.setText(this.cuenta.getNumeroCuenta());
        txtFSaldoCuenta.setText(String.valueOf(this.cuenta.getSaldo()));
        this.cuentas = cuentas;
    }

    public void setCuenta2(Cuenta cuenta, ObservableList<Cuenta> cuentas) {
        this.cuenta = cuenta;
        this.cuentas = cuentas;
    }

    public void onCrearCuentaClick(ActionEvent actionEvent) {
        if(cliente.getCuenta() != null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "El cliente ya tiene una cuenta", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        String numeroCuenta = txtFNumeroCuenta3.getText();

        for (Cuenta cuentaExistente : cuentas) {
            if (cuentaExistente.getNumeroCuenta().equals(numeroCuenta)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El número de cuenta ya existe", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }

        this.cuenta.setNumeroCuenta(numeroCuenta);
        this.cuenta.setSaldo(0);

        this.cliente.setCuenta(cuenta);

        this.cuentas.add(cuenta);

        txtFNumeroCuenta3.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "La cuenta ha sido creada correctamente", ButtonType.OK);
        alert.showAndWait();
    }

}

