package co.edu.uniquindio.market_place.threads;

public class Solicitud implements Runnable{

	private String peticion;
	private Object[] parametros;
	private Metodos m;
	
	

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

	public Metodos getM() {
		return m;
	}

	public void setM(Metodos m) {
		this.m = m;
	}

}
