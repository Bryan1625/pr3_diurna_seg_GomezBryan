package ejercicio1;

import java.util.ArrayList;

public class Hilo1 extends Thread{
	
	ArrayList<Integer> arreglo;

	public Hilo1(ArrayList<Integer> arreglo) {
		this.arreglo = arreglo;
	}

	@Override
	public void run() {
		for (int i = 0; i < arreglo.size(); i++) {
			try{
				Thread.sleep(1000);
				System.out.println("valor del arreglo numero "+(i+1)+"= "+arreglo.get(i));
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
