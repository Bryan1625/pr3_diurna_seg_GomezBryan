package co.edu.uniquindio.model;

import java.util.ArrayList;

public class Cuenta {

    private String numeroCuenta;
    private double saldo;
    private ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
    private Cliente propietario;

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public Cuenta() {
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
