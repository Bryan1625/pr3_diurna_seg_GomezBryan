package co.edu.uniquindio.model;

public class Retiro extends Transaccion{

    public Retiro() {
    }

    public Retiro(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void retirar(){
    }
}
