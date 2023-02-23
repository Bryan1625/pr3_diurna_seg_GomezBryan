package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;

import static co.edu.uniquindio.model.Estado.*;

public class TransaccionController {

    //Transaccion
    @FXML
    private ComboBox<String> cbTipoTransaccion;
    @FXML private TextField txtValorTransaccion;
    @FXML private Button btnRealizarTransaccion1;

    //VerTransaccion
    @FXML private TextField txtFValorTransaccion2;
    @FXML private TextField txtFHoraTransaccion1;
    @FXML private TextField txtFFechaTransaccion1;
    @FXML private TextField txtFEstadoTransaccion1;

    private Cuenta cuenta;
    private Cliente cliente;
    private Empleado empleado;
    private Transaccion transaccion;
    private ObservableList<String> tipos;

    @FXML
    public void initialize(){
        tipos = FXCollections.observableArrayList();
        tipos.add("Solicitud");
        tipos.add("Deposito");
        tipos.add("Retiro");
        cbTipoTransaccion.setItems(tipos);

    }

    public void initAtributos(Cliente cliente, Cuenta cuenta){
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    public void onRealizarTransaccion1Click(ActionEvent actionEvent) {

        if (cbTipoTransaccion.getValue().equals("Solicitud")) {
            transaccion = new Solicitud(Double.parseDouble(txtValorTransaccion.getText()), LocalDate.now().toString(), LocalTime.now().toString(), Exitosa);
            cuenta.setSaldo(cuenta.getSaldo() + transaccion.getValor());
        } else if (cbTipoTransaccion.getValue().equals("Deposito")) {
            transaccion = new Deposito(Double.parseDouble(txtValorTransaccion.getText()), LocalDate.now().toString(), LocalTime.now().toString(), Exitosa);
            cuenta.setSaldo(cuenta.getSaldo() + transaccion.getValor());
        }else if (cbTipoTransaccion.getValue().equals("Retiro")) {
            if (cuenta.getSaldo()>= Double.parseDouble(txtValorTransaccion.getText())) {
                transaccion = new Retiro(Double.parseDouble(txtValorTransaccion.getText()), LocalDate.now().toString(), LocalTime.now().toString(), Exitosa);
                cuenta.setSaldo(cuenta.getSaldo()-transaccion.getValor());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de transacción");
                alert.setHeaderText(null);
                alert.setContentText("El saldo actual de la cuenta es insuficiente para realizar el retiro.");
                alert.showAndWait();
                transaccion = new Solicitud(Double.parseDouble(txtValorTransaccion.getText()), LocalDate.now().toString(), LocalTime.now().toString(), SinFondos);
            }
        }
    }

}
