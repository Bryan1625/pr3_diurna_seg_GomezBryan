package co.edu.uniquindio.banco.model;

public class Deposito extends Transaccion {



    private final String tipo = "Deposito";
    public Deposito() {
    }

    public Deposito(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void depositar(){

    }
    @Override
    public String getTipo() {
        return tipo;
    }
}
