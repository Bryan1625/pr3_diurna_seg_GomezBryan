package co.edu.uniquindio.model;

public abstract class Transaccion {

    private double valor;
    private String hora;
    private String fecha;
    private Estado estado;

    public Transaccion() {
    }

    public Transaccion(double valor, String hora, String fecha, Estado estado) {
        this.valor = valor;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
