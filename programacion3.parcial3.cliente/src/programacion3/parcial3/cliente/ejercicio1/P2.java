package programacion3.parcial3.cliente.ejercicio1;

public class P2 extends Thread{
	
	private Tuberia tuberia;
	private String alfabeto = "bcdfghjklmnpqrstvwxyz";
	boolean palabraIncompleta = true;

	public P2(Tuberia t) {
		// TODO Auto-generated constructor stub
		tuberia = t;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String c;

		// Mete 10 letras en la tuber�a
		while(palabraIncompleta)
		{
			c = ""+alfabeto.charAt( (int)(Math.random()*21 ) );
			tuberia.lanzar( c );
			// Imprime un registro con lo a�adido
			System.out.println( "Lanzado "+c+" a la tuberia por p2." );
			// Espera un poco antes de a�adir m�s letras
			try
			{
				sleep(1000);
			} 
			catch( InterruptedException e ) 
			{
				System.out.println(e);;
			}
		}
	}
	

	
}
