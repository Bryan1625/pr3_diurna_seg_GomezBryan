package co.edu.uniquindio.model;

import java.util.ArrayList;

public class Empleado extends Persona {

    private ArrayList<Transaccion>  listaTransacciones = new ArrayList<>();

    public Empleado(){
    }

    public Empleado(String nombre, String apellidos, String cedula, String direccion) {
        super(nombre, apellidos, cedula, direccion);
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
}
