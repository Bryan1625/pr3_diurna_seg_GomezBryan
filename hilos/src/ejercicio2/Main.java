package ejercicio2;

public class Main {

    public static void main(String[] args) {
        Tuberia t = new Tuberia();
        Hilo1 h1 = new Hilo1("hola", t);
        Hilo2 h2 = new Hilo2(t);
        long tac = System.currentTimeMillis() + 20000;
        while (System.currentTimeMillis() < tac) {
            ejecutar20Segundos();
        }
    }

    public static void ejecutar20Segundos() {
        Tuberia t = new Tuberia();
        Hilo1 h1 = new Hilo1("hola", t);
        Hilo2 h2 = new Hilo2(t);
        h1.start();
        h2.start();
        try {
            Thread.sleep(1000);
            h1.setFlag(false);
            h1.join();
            h2.setFlag(false);
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
