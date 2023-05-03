package co.edu.uniquindio.market_place.model;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrador() {
		
	}
	
	public Administrador(String nombre, String apellido, String cedula, String direccion, String usuario, String contrasenia){
		super(nombre, apellido, cedula, direccion, usuario, contrasenia);
	}
	
}
