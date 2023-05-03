package co.edu.uniquindio.banco.model;

import java.util.ArrayList;

public class Banco {

    private final String nombre = "UniBanco";
    private ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    public Banco(ArrayList<Empleado> listaEmpleados, ArrayList<Cliente> listaClientes, ArrayList<Cuenta> listaCuentas) {
        this.listaEmpleados = listaEmpleados;
        this.listaClientes = listaClientes;
        this.listaCuentas = listaCuentas;
    }

    public Banco() {
    }

    public ArrayList<Empleado> getEmpleados() {
        return listaEmpleados;
    }

    public void setEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<Cliente> getClientes() {
        return listaClientes;
    }

    public void setClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cuenta> getCuentas() {
        return listaCuentas;
    }

    public void setCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
}
