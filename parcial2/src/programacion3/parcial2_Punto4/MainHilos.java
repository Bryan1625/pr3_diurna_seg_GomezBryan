package programacion3.parcial2_Punto4;

public class MainHilos {

	public static void main(String[] args) {
		ejecucionHilos();

	}

	private static void ejecucionHilos() {
		Hilo1 hilo1 = new Hilo1();
		Hilo2 hilo2 = new Hilo2();

		hilo1.start();
		try {
			hilo1.join();
			hilo2.start();
			hilo2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		int a = hilo1.num_Mayor;
		double b = hilo2.prom_Matriz;
		double c = a*b;
		System.out.println("La multiplicacion es: "+c);
		
	}

}
