package co.edu.uniquindio.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Main {

    //Clase clientes
    public Button btnEliminarCliente;
    public Button btnVerCliente;
    public TableView tblTablaClientes;
    public TableColumn tblColCedulaCliente;
    public TableColumn tblColNombreCliente;
    public TableColumn tblColEmailCliente;
    public TextField txtFNombreCliente;
    public TextField txtFCedulaCliente;
    public TextField txtFEmailCliente;
    public TextField txtFApellidosCliente;
    public TextField txtFDireccionCliente;
    public Button btnBuscarCliente;
    public Button btnIngresarCliente;

    //Clase Cliente
    public TextField txtFNombreCliente1;
    public TextField txtFApellidosCliente1;
    public TextField txtFCedulaCliente1;
    public TextField txtFEmailCliente1;
    public TextField txtFDireccionCliente1;
    public Button btnVerCuenta;
    public Button btnAgregarCuenta;

    //Cuentas
    public TextField txtFNombreCliente2;
    public TextField txtFCedulaCliente2;
    public TextField txtFNumeroCuenta;
    public Button btnBuscarCuenta;

    //Cuenta
    public Button btnRealizarTransaccion;
    public TextField txtFNumeroCuenta1;
    public TextField txtFSaldoCuenta;
    public Button txtFVerDueno;

    //Empleados
    public TableView tblEmpleados;
    public TableColumn tblColCedulaEmpleado;
    public TableColumn tblColNombreEmpleado;
    public TextField txtFNombreEmpleado;
    public TextField txtFCedulaEmpleado;
    public TextField txtFApellidosEmpleado;
    public TextField txtFDireccionEmpleado;
    public Button btnIngresarEmpleado;
    public Button btnBuscarEmpleado;
    public Button btnEliminarEmpleado;
    public Button btnVerEmpleado;

    //Empleado
    public TextField txtFNombreEmpleado1;
    public TextField txtFApellidosEmpleado1;
    public TextField txtFCedulaEmpleado1;
    public TextField txtFDireccionEmpleado1;

    //Transacciones
    public TableView tblTransacciones;
    public TableColumn tblColTipoTransaccion;
    public TableColumn tblColFechaTransaccion;
    public TableColumn tblColHoraTransaccion;
    public TableColumn tblColValorTransaccion;
    public TableColumn tblColEstadoTransaccion;
    public TextField txtFTipoTransaccion;
    public TextField txtFFechaTransaccion;
    public TextField txtFValorTransaccion;
    public TextField txtFEstadoTransaccion;
    public Button btnBuscarTransaccion;
    public Button btnVerTransaccion;

    //Transaccion
    public ComboBox cbTipoTransaccion;
    public TextField txtValorTransaccion;
    public Button btnRealizarTransaccion1;
    
    //VerTransaccion
    public TextField txtFValorTransaccion2;
    public TextField txtFHoraTransaccion1;
    public TextField txtFFechaTransaccion1;
    public TextField txtFEstadoTransaccion1;
    
    //Banco
    public Button btnVerListaEmpleados;
    public Button btnVerListaClientes;
    public Button btnVerListaTransacciones;
    public Button btnVerListaCuentas;
}
