package ejercicio1;

public class Hilo3 extends Thread{
	
	String nombre;

	public Hilo3(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			try{
				Thread.sleep(500);
				System.out.println("el nombre del hilo es: "+nombre+", vez numero: "+(i+1));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
