package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Cuenta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CuentasController {

    @FXML
    private TextField txtFNombreCliente2;
    @FXML private TextField txtFCedulaCliente2;
    @FXML private TextField txtFNumeroCuenta;
    @FXML private Button btnBuscarCuenta;
    @FXML private TableView<Cuenta> tblCuentas;
    @FXML private TableColumn tblColNumeroCuenta;
    @FXML private TableColumn tblColSaldo;
    private Cliente propietario;
    ObservableList<Cuenta> cuentas;

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
