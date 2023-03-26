package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.model.Transaccion;
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

public class TransaccionesController {

    //Transacciones
    @FXML
    private TableView<Transaccion> tblTransacciones;
    @FXML private TableColumn<String,String> tblColTipoTransaccion;
    @FXML private TableColumn<String,String> tblColFechaTransaccion;
    @FXML private TableColumn<String,String> tblColHoraTransaccion;
    @FXML private TableColumn<String,String> tblColValorTransaccion;
    @FXML private TableColumn<String,String> tblColEstadoTransaccion;
    @FXML private TextField txtFTipoTransaccion;
    @FXML private TextField txtFFechaTransaccion;
    @FXML private TextField txtFValorTransaccion;
    @FXML private TextField txtFEstadoTransaccion;
    @FXML private Button btnVerTransaccion;
    @FXML private Button btnBuscarTransaccion;

    ObservableList<Transaccion> transacciones;

    @FXML
    public void initialize(){
        transacciones = FXCollections.observableArrayList();
        this.tblColEstadoTransaccion.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.tblColFechaTransaccion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.tblColHoraTransaccion.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.tblColValorTransaccion.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.tblColTipoTransaccion.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    public void onBuscarTransaccion(ActionEvent actionEvent) {
        String estado = txtFEstadoTransaccion.getText();
        String valor = txtFValorTransaccion.getText();
        String fecha = txtFFechaTransaccion.getText();
        String tipo = txtFTipoTransaccion.getText();
        ObservableList<Transaccion> transaccionesB = transacciones;
        for (Transaccion t : transaccionesB){
            if(!estado.isEmpty() && !t.getEstado().equals(estado)){
                transaccionesB.remove(t);
            }
            if(!tipo.isEmpty() && !t.getTipo().equals(tipo)){
                transaccionesB.remove(t);
            }
            if(!valor.isEmpty() && t.getValor() != (Double.parseDouble(valor))){
                transaccionesB.remove(t);
            }
            if(!fecha.isEmpty() && !t.getFecha().equals(fecha)){
                transaccionesB.remove(t);
            }
        }
        tblTransacciones.setItems(transaccionesB);
    }
    public void onVerTransaccion(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/verTransaccionUI.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Transaccion");
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
