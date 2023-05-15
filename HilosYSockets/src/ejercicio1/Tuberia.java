package ejercicio1;

public class Tuberia {

	private char buffer[] = new char[6];

	private int siguiente = 0;

	// Flags para saber el estado del buffer 
	private boolean estaLlena = false;
    private boolean estaVacia = true;

	// Método para retirar letras del buffer 
    public synchronized char recoger()
	{ // No se puede consumir si el buffer está vacío
    	while( estaVacia==true){
    		try{
    			wait();
    		}catch(InterruptedException e){
    			
    		}
    	}
    	siguiente--;
    	
    	if(siguiente == 0){
    		estaVacia = true;    	}
    	estaLlena = false;
    	notify();
    	
    	return (buffer[siguiente]);
	}

    public synchronized void lanzar(char c){
    	while(estaLlena==true){
    		try{
    			wait();
    		}catch(InterruptedException e){
    			
    		}
    	}
    	buffer[siguiente]=c;
    	siguiente++;
    	if(siguiente ==6){
    		estaLlena=true;
    	}
    	estaVacia=false;
    	notify();
    }
}
