package co.edu.uniquindio.model;

public class Deposito extends Transaccion {

    public Deposito() {
    }

    public Deposito(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void depositar(){

    }
}
