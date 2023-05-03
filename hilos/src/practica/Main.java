package practica;

public class Main {

	public static void main(String[] args) {
		ejecutarHilos();
	}

	private static void ejecutarHilos() {
		
		String palabra = "otorrinolaringologia";
		miHilo hilo1 = new miHilo();
		miHilo2 hilo2 = new miHilo2(palabra);
		hilo1.start();
		hilo2.start();

		try{
			hilo2.join();
			hilo1.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("la vocales de la palabra: "+palabra+" son: "+hilo2.getVocales());
		System.out.println("la suma de los resultados de la tabla del 2 fue: "+hilo1.getSuma());
	}

}
