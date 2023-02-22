package co.edu.uniquindio.model;

public class Solicitud extends Transaccion{

    private final String tipo = "Solicitud";
    public Solicitud() {
    }

    public Solicitud(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void solicitar(){

    }

    @Override
    public String getTipo() {
        return tipo;
    }
}
