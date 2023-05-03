package ejercicio2;

public class Tuberia {

	String palabra= "";
	
	public synchronized void escribir(String palabra){
		this.palabra += palabra;
	}
	
	public synchronized String leerYBorrar(){
		String palabra = this.palabra;
		this.palabra = "";
		return palabra;
	}

}
