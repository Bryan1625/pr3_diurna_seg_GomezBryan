package co.edu.uniquindio.market_place.threads;

public class Solicitud implements Runnable{

	private String peticion;
	private Object[] parametros;
	private Metodos m;
	
	public Solicitud(String peticion, Object[] parametros, Metodos m) {
		// TODO Auto-generated constructor stub
		this.peticion = peticion;
		this.parametros = parametros;
		this.m = m;
	}
	
	public Solicitud(String peticion, Metodos m) {
		this.peticion = peticion;
		this.m = m;
	}

	@Override
	public void run() {
		
	}
	
	public String getPeticion() {
		return peticion;
	}
	public void setPeticion(String peticion) {
		this.peticion = peticion;
	}
	public Object[] getParametros() {
		return parametros;
	}
	public void setParametros(Object[] parametros) {
		this.parametros = parametros;
	}
}
