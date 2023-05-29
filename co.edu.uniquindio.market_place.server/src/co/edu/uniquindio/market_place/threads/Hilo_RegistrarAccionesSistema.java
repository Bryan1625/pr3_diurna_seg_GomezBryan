package co.edu.uniquindio.market_place.threads;

public class Hilo_RegistrarAccionesSistema extends Solicitud{
	
	Metodos m;
	String mensaje;
	int nivel;
	String accion;


	@Override
	public void run() {
		m.registrarAccionesSistema(mensaje, nivel, accion);
	}
}
