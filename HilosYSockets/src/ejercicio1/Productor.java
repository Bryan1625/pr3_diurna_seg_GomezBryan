package ejercicio1;

public class Productor extends Thread{

	private Tuberia tuberia;
	private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Productor(Tuberia t) {
		// TODO Auto-generated constructor stub
		tuberia = t;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		char c;
		
		for (int i = 0; i < 10; i++) {
			c = alfabeto.charAt((int)(Math.random()*26));
			tuberia.lanzar(c);
			System.out.println("Lanzado "+c+" a la tuberia.");
			try{
				sleep((int)(Math.random()*100));
			}catch(InterruptedException e){
				
			}
		}
	}
}
