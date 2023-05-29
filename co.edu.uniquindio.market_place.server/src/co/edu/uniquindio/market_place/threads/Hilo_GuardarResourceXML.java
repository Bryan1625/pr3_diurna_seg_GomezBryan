package co.edu.uniquindio.market_place.threads;

public class Hilo_GuardarResourceXML extends Solicitud{

	Metodos m;
	

	
	@Override
	public void run() {
		m.guardarResourceXML();
	}
}
