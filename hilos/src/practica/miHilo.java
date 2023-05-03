package practica;

public class miHilo extends Thread{
	
	private int suma=0;

	
	//tabla de multiplicar de 2 (1-10), sumar los valores, guardar en variable global
	
	@Override
	public void run(){
		try{
		for (int i = 1; i <= 10; i++) {
			int num = 2*i;
			System.out.println("2*"+i+"= "+num);
			Thread.sleep(500);
			setSuma(getSuma() + num);
		}}catch(Exception e){
			e.printStackTrace();
		}
	}


	public int getSuma() {
		return suma;
	}


	public void setSuma(int suma) {
		this.suma = suma;
	}
}
