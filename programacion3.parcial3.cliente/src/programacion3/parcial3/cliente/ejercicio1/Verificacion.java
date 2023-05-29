package programacion3.parcial3.cliente.ejercicio1;

public class Verificacion {
    private boolean presente;
    private int indice;

    public Verificacion(boolean presente, int indice) {
        this.presente = presente;
        this.indice = indice;
    }

    public boolean isPresente() {
        return presente;
    }

    public int getIndice() {
        return indice;
    }
}
