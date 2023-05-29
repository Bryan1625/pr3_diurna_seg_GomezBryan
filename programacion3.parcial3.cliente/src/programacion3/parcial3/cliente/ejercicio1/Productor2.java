package programacion3.parcial3.cliente.ejercicio1;

class Productor2 extends Thread {
    private Tuberia tuberia;
    private String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    public Productor2(Tuberia t) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
    }

    public void run() {
        char c;
        while(!tuberia.isEstaCompleta()) {
            c = alfabeto.charAt((int) (Math.random() * 26));
            if(verificarConsonante(c)) {
                tuberia.lanzar(c);
                System.out.println("Lanzado " + c + " a la tuberia.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
                ;
            }
        }
    }

    public boolean verificarConsonante(char c) {
        return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u';
    }
}