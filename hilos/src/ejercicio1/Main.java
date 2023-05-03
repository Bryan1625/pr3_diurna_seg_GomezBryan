package ejercicio1;

import java.util.ArrayList;

public class Main {
	

	public static void main(String[] args) {
		ArrayList<Integer> arreglo = new ArrayList<>();
		arreglo.add(5);
		arreglo.add(4);
		arreglo.add(8);
		arreglo.add(10);
		arreglo.add(4);
		arreglo.add(7);
		Hilo1 hilo1 = new Hilo1(arreglo);
		Hilo2 hilo2 = new Hilo2(2);
		Hilo3 hilo3 = new Hilo3("hilo 3");
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
}
