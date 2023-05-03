package ejercicio1.model;

import java.io.Serializable;

public class Afiliacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipo tipo;
	private String codigo;
	private String horaAfiliacion;
	private Participante amigo;
	private String estado;
	
	
	public Afiliacion(Tipo tipo, String codigo, String horaAfiliacion, Participante amigo, String estado) {
		this.tipo = tipo;
		this.codigo = codigo;
		this.horaAfiliacion = horaAfiliacion;
		this.amigo = amigo;
		this.estado = estado;
	}

	public Afiliacion(){
		
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getHoraAfiliacion() {
		return horaAfiliacion;
	}
	public void setHoraAfiliacion(String horaAfiliacion) {
		this.horaAfiliacion = horaAfiliacion;
	}
	public Participante getAmigo() {
		return amigo;
	}
	public void setAmigo(Participante amigo) {
		this.amigo = amigo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
