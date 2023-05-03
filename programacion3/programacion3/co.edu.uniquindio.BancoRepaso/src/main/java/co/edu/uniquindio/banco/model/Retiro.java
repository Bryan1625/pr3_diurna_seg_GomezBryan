package co.edu.uniquindio.banco.model;

public class Retiro extends Transaccion{



    private final String tipo= "Retiro";
    public Retiro() {
    }

    public Retiro(double valor, String hora, String fecha, Estado estado) {
        super(valor, hora, fecha, estado);
    }

    public void retirar(){
    }

    @Override
    public String getTipo() {
        return tipo;
    }
}
