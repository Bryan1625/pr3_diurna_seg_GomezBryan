package model;

public class Personaje {
    private String nombre;
    private String paisNacimiento;
    private int edad;
    private String codigoSerie;

    public Personaje(String nombre, String paisNacimiento, int edad, String codigoSerie) {
        this.nombre = nombre;
        this.paisNacimiento = paisNacimiento;
        this.edad = edad;
        this.codigoSerie = codigoSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }
}
