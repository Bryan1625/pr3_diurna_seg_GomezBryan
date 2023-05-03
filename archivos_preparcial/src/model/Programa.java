package model;

import java.io.Serializable;

public class Programa implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
    private String nombre;
    private String modalidad;

    public Programa(String codigo, String nombre, String modalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modalidad = modalidad;
    }

    public Programa() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", modalidad='" + modalidad + '\'' +
                '}';
    }

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
}

