package programacion3.parcial3.BryanGomezMarin.ejercicio1;

import java.util.ArrayList;

public class Estacion {

	int tanque = 1000;
	
	private boolean estaLleno = false;
	private boolean capacidadSuficiente = true;
	ArrayList<Cliente> clientes = new ArrayList<>();

	public void espera() {
	}

	public boolean isEstaCompleta() {
		return estaCompleta;
	}

	public synchronized void cargar() {

		while (capacidadSuficiente == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				;
			}
		}
		
		notify();
	}

	// M�todo para a�adir letras al buffer
	public synchronized void abastecer() {
		// Espera hasta que haya sitio para otra letra
		while (estaLleno == true) {
			try {
				wait(); // Se sale cuando estaLlena cambia a false
			} catch (InterruptedException e) {
				;
			}
		}
		verificarTanqueLleno();
		// A�ade una letra en el primer lugar disponible
		if (!estaLleno) {
			
			
			// Comprueba si el buffer est� lleno
			if (siguiente == 8)
				estaLleno = true;
			capacidadSuficiente = false;
		}
		notify();
	}

	public void verificarTanqueLleno() {
    	if(tanque > 980){
        estaLleno = true;
    	}
    }

	public void verificarCapacidadSuficiente(int necesidad){
    	if(tanque < necesidad){
    	capacidadSuficiente = false;
    	}
    }
}
