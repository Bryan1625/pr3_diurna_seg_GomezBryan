package co.edu.uniquindio.model;

public class Solicitud extends Transaccion{

    public Solicitud() {
    }

    public Solicitud(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void solicitar(){

    }
}
