package co.edu.uniquindio.controllers;

import co.edu.uniquindio.model.Cliente;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class ClientesController {

    @FXML
    private Button btnEliminarCliente;
    @FXML private Button btnVerCliente;
    @FXML private TableView<Cliente> tblTablaClientes;
    @FXML private TableColumn<String,String> tblColCedulaCliente;
    @FXML private TableColumn<String,String> tblColNombreCliente;
    @FXML private TableColumn<String,String> tblColEmailCliente;
    @FXML private TextField txtFNombreCliente;
    @FXML private TextField txtFCedulaCliente;
    @FXML private TextField txtFEmailCliente;
    @FXML private TextField txtFApellidosCliente;
    @FXML private TextField txtFDireccionCliente;
    @FXML private Button btnBuscarCliente;
    @FXML private Button btnIngresarCliente;

    ObservableList<Cliente> clientes;

    private ArrayList<Cliente> clienteArrayList;

    @FXML
    public void initialize() {
        this.clientes = FXCollections.observableArrayList();
        this.tblColCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.tblColNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tblColEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void initAtributos(ObservableList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setClienteArrayList(ArrayList<Cliente> clientes){
        this.clienteArrayList = clientes;
    }

    public ArrayList<Cliente> getClienteArrayList() {
        return clienteArrayList;
    }

    @FXML
    public void onBuscarClienteClick(ActionEvent actionEvent) {
        String cedula = txtFCedulaCliente.getText();

        if (!cedula.isEmpty()) {
            for (Cliente cliente : clientes) {
                if (cliente.getCedula() == Integer.parseInt(cedula)) {
                    txtFNombreCliente.setText(cliente.getNombre());
                    txtFApellidosCliente.setText(cliente.getApellidos());
                    txtFEmailCliente.setText(cliente.getEmail());
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cliente no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("No se encontró ningún cliente con la cédula " + cedula);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cédula vacía");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese una cédula");
            alert.showAndWait();
        }
    }

    @FXML
    public void onIngresarClienteClick(ActionEvent actionEvent) {
        String nombre = txtFNombreCliente.getText();
        String apellidos = txtFApellidosCliente.getText();
        String cedulaString = txtFCedulaCliente.getText();
        String email = txtFEmailCliente.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || cedulaString.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Todos los campos son obligatorios", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (!StringUtils.isNumeric(cedulaString)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La cédula debe ser un número", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        int cedula = Integer.parseInt(cedulaString);
        Cliente nuevoCliente = new Cliente(nombre, apellidos, cedula, email);
        boolean clienteExistente = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula() == cedula) {
                clienteExistente = true;
            }
        }
        if (clienteExistente) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El cliente ya existe");
            alert.showAndWait();
        } else {
            clientes.add(nuevoCliente);
            tblTablaClientes.setItems(clientes);

            txtFNombreCliente.clear();
            txtFApellidosCliente.clear();
            txtFCedulaCliente.clear();
            txtFEmailCliente.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "El cliente ha sido agregado correctamente", ButtonType.OK);
            alert.showAndWait();
        }

    }


    @FXML
    public void onEliminarClienteClick(ActionEvent actionEvent) {
        Cliente clienteSeleccionado = tblTablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que quieres eliminar a este cliente?");
            alert.setContentText(clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellidos());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                clientes.remove(clienteSeleccionado);
                tblTablaClientes.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecciona un cliente");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un cliente de la lista.");
            alert.showAndWait();
        }
    }

    public void onVerClienteClick(ActionEvent actionEvent) throws IOException {
        Cliente cliente = tblTablaClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/clienteUI.fxml"));
                Parent root = loader.load();
                ClienteController clienteController = loader.getController();
                clienteController.setCliente(cliente);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Información del cliente");
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un cliente primero.");
            alert.showAndWait();
        }
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        txtFCedulaCliente.setText(""+clienteSeleccionado.getCedula());
        txtFNombreCliente.setText(clienteSeleccionado.getNombre());
        txtFApellidosCliente.setText(clienteSeleccionado.getApellidos());
        txtFEmailCliente.setText(clienteSeleccionado.getEmail());
        txtFDireccionCliente.setText(clienteSeleccionado.getDireccion());
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ObservableList<Cliente> clientes) {
        this.clientes = clientes;
    }



}
