package ejercicio1;

public class Hilo2 extends Thread{
	
	long n;

	public Hilo2(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try{
				Thread.sleep(1500);
				n = n*n;
				System.out.println("el valor actual al cuadrado es: "+n);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
