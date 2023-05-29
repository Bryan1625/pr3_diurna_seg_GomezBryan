package programacion3.parcial3.cliente.ejercicio1;

public  class Ejecucion {

    public static void main(String args[]) {
        Tuberia t = new Tuberia();
        Productor1 p = new Productor1(t);
        Productor2 p2 = new Productor2(t);
        Consumidor c = new Consumidor(t);

        p.start();
        p2.start();
        c.start();
    }
}