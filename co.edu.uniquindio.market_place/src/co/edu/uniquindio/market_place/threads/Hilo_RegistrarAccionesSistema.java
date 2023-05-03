package co.edu.uniquindio.market_place.threads;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;

public class Hilo_RegistrarAccionesSistema extends Thread{
	
	Metodos m;
	String mensaje;
	int nivel;
	String accion;

	public Hilo_RegistrarAccionesSistema(ModelFactoryController model,String mensaje, int nivel, String accion) {
		// TODO Auto-generated constructor stub
		m = new Metodos(model);
		this.mensaje = mensaje;
		this.nivel = nivel;
		this.accion = accion;
	}
	
	@Override
	public void run() {
		m.registrarAccionesSistema(mensaje, nivel, accion);
	}
}
