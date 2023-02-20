package co.edu.uniquindio.model;

public abstract class Persona {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;

    public Persona(String nombre, String apellidos, String cedula, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public Persona(String nombre, String apellidos, String cedula) {
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.nombre= nombre +" "+ apellidos;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
