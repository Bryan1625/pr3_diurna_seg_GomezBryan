package programacion3.parcial3.cliente.ejercicio1;

import java.util.ArrayList;

public class Consumidor extends Thread {
    private Tuberia tuberia;
    private char[] palabra = {'e', 'l', 'e', 'c', 't', 'r', 'o', 'e', 'n', 'c', 'e', 'f', 'a', 'l', 'o', 'g', 'r', 'a', 'f', 'i', 's', 't', 'a'};
    private char[] palabraLlenar = new char[palabra.length];
    private ArrayList<Character> sobrantes = new ArrayList<>();

    public Consumidor(Tuberia t) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
    }

    public void run() {
        char c;
        boolean flag = false;
        while(!flag) {
            c = tuberia.recoger();
            Verificacion v = verificarLetraEnPalabra(c);
            if(v.isPresente()) {
                agregarLetra(c, v.getIndice());
                System.out.println("Recogido el caracter " + c);
            } else {
                sobrantes.add(c);
            }
            flag = verificarPalabraCompleta();
            if(flag)
                tuberia.verficarPalabraCompleta();
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                ;
            }
        }
        System.out.println(""+new String(palabraLlenar));
        System.out.println(sobrantes.toString());
    }

    private boolean verificarPalabraCompleta() {
        int c = 0;
        for (int i = 0; i < palabraLlenar.length; i++) {
            if((int)palabraLlenar[i] != 0)
                c++;
        }
        return c == palabraLlenar.length;
    }

    private void agregarLetra(char c, int indice) {
        palabraLlenar[indice] = c;
    }

    public Verificacion verificarLetraEnPalabra(char c) {
        for (int i = 0; i<palabra.length; i++) {
            if(c == palabra[i]){
                palabra[i] = ' ';
                return new Verificacion(true, i);
            }
        }
        return new Verificacion(false, -1);
    }
}