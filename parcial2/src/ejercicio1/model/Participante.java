package ejercicio1.model;

import java.io.Serializable;

public class Participante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private String cedula;
	private String direccion;
	private Afiliacion afiliacion;
	
	

	public Participante(String nombre, String apellidos, String cedula, String direccion, Afiliacion afiliacion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.direccion = direccion;
		this.afiliacion = afiliacion;
	}
	

	public Participante(){
		
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Afiliacion getAfiliacion() {
		return afiliacion;
	}


	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	

}
