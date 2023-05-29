package programacion3.parcial3.cliente.ejercicio1;

class Productor1 extends Thread {
    private Tuberia tuberia;
    private String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    public Productor1(Tuberia t) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
    }

    public void run() {
        char c;
        while(!tuberia.isEstaCompleta()) {
            c = alfabeto.charAt((int) (Math.random() * 26));
            if(verificarVocal(c)) {
                tuberia.lanzar(c);
                System.out.println("Lanzado " + c + " a la tuberia.");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
                ;
            }
        }
    }

    public boolean verificarVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}