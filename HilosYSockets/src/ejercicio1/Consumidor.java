package ejercicio1;

public class Consumidor extends Thread{

	private Tuberia tuberia;
	
	public Consumidor(Tuberia t) {
		// TODO Auto-generated constructor stub
		tuberia = t;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		char c;
		
		for (int i = 0; i < 10; i++) {
			c = tuberia.recoger();
			System.out.println("Recogido el caracter "+c);
			try{
				sleep((int)(Math.random()*2000));
			}catch(InterruptedException e){
				
			}
		}
	}
}
