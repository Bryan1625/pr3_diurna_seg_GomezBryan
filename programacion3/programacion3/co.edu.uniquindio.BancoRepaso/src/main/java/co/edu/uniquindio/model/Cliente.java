package co.edu.uniquindio.model;

import java.util.ArrayList;

public class Cliente extends Persona{

    private String email;
    private ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
    private Cuenta cuenta;

    public Cliente(String nombre, String apellidos, String cedula, String direccion, String email) {
        super(nombre, apellidos, cedula, direccion);
        this.email = email;
    }

    public Cliente() {
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void anadirTransaccion(Transaccion transaccion){
        this.listaTransacciones.add(transaccion);
    }

    public void eliminarTransaccion(Transaccion transaccion){
        this.listaTransacciones.remove(transaccion);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
