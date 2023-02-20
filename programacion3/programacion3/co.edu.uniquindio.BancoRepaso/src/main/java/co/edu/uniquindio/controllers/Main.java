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

    //Clase clientes
    @FXML private Button btnEliminarCliente;
    @FXML private Button btnVerCliente;
    @FXML private TableView<Cliente> tblTablaClientes;
    @FXML private TableColumn tblColCedulaCliente;
    @FXML private TableColumn tblColNombreCliente;
    @FXML private TableColumn tblColEmailCliente;
    @FXML private TextField txtFNombreCliente;
    @FXML private TextField txtFCedulaCliente;
    @FXML private TextField txtFEmailCliente;
    @FXML private TextField txtFApellidosCliente;
    @FXML private TextField txtFDireccionCliente;
    @FXML private Button btnBuscarCliente;
    @FXML private Button btnIngresarCliente;

    //Cuentas
    @FXML private TextField txtFNombreCliente2;
    @FXML private TextField txtFCedulaCliente2;
    @FXML private TextField txtFNumeroCuenta;
    @FXML private Button btnBuscarCuenta;
    @FXML private TableView tblCuentas;
    @FXML private TableColumn tblColNumeroCuenta;
    @FXML private TableColumn tblColSaldo;
    private Cliente propietario;

    //Empleados
    @FXML private TableView tblEmpleados;
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
    @FXML private TableView tblTransacciones;
    @FXML private TableColumn tblColTipoTransaccion;
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

    ObservableList<Cliente> clientes;
    ObservableList<Empleado> empleados;
    ObservableList<Transaccion> transacciones;
    ObservableList<Cuenta> cuentas;

    @FXML
    public void initialize() {
    }

    public void onVerListaEmpleadosClick(ActionEvent actionEvent) throws IOException {
        empleados = FXCollections.observableArrayList();
        tblColCedulaEmpleado.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        tblColNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/empleadosUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onVerListaClientesClick(ActionEvent actionEvent) throws IOException {
        clientes = FXCollections.observableArrayList();
        tblColCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        tblColNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblColEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/clientesUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onVerListaTransaccionesClick(ActionEvent actionEvent) throws IOException {
        transacciones = FXCollections.observableArrayList();
        tblColValorTransaccion.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblColHoraTransaccion.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tblColFechaTransaccion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblColEstadoTransaccion.setCellValueFactory(new PropertyValueFactory<>("estado"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/transaccionesUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onVerListaCuentasClick(ActionEvent actionEvent) throws IOException {
        cuentas = FXCollections.observableArrayList();
        tblColNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        tblColSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/cuentasUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onBuscarClienteClick(ActionEvent actionEvent) {
        String cedula = txtFCedulaCliente.getText().trim();

        if (!cedula.isEmpty()) {
            for (Cliente cliente : clientes) {
                if (cliente.getCedula().contains(cedula)) {
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
        String cedula = txtFCedulaCliente.getText();
        String email = txtFEmailCliente.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Todos los campos son obligatorios", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El cliente ya existe", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }


        Cliente nuevoCliente = new Cliente(nombre, apellidos, cedula, email);
        clientes.add(nuevoCliente);

        txtFNombreCliente.clear();
        txtFApellidosCliente.clear();
        txtFCedulaCliente.clear();
        txtFEmailCliente.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "El cliente ha sido agregado correctamente", ButtonType.OK);
        alert.showAndWait();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/clienteUI.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            ClienteController clienteController = loader.getController();
            clienteController.setCliente(cliente);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Información del cliente");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un cliente primero.");
            alert.showAndWait();
        }
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

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        txtFCedulaCliente.setText(clienteSeleccionado.getCedula());
        txtFNombreCliente.setText(clienteSeleccionado.getNombre());
        txtFApellidosCliente.setText(clienteSeleccionado.getApellidos());
        txtFEmailCliente.setText(clienteSeleccionado.getEmail());
        txtFDireccionCliente.setText(clienteSeleccionado.getDireccion());
    }

}
