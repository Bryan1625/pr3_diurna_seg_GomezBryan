package co.edu.uniquindio.market_place.model;

import java.io.Serializable;

public abstract class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre="";
	private String apellido="";
	private String cedula="";
	private String direccion="";
	private String usuario="";
	private String contrasenia="";
	
	
	
	
	public Persona(String nombre, String apellido, String cedula, String direccion, String usuario,
			String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Persona() {
		
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
