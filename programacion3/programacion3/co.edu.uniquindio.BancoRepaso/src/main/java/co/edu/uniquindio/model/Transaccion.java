package co.edu.uniquindio.model;

public abstract class Transaccion {

    private double valor;
    private String hora;
    private String fecha;
    private Estado estado;
    private Cliente dueno;
    private Empleado empleado;
    private Cuenta cuenta;

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

    public String getTipo(){
        return null;
    }

    public Cliente getDueno() {
        return dueno;
    }

    public void setDueno(Cliente dueno) {
        this.dueno = dueno;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
