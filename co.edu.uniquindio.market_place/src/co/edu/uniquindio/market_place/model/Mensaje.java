package co.edu.uniquindio.market_place.model;

import java.io.Serializable;

public class Mensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje="";
	
	public Mensaje() {
		// TODO Auto-generated constructor stub
	}
	
	public Mensaje(String mensaje) {
		// TODO Auto-generated constructor stub
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
