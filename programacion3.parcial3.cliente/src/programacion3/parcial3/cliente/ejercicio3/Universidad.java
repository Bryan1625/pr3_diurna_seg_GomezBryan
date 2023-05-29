package programacion3.parcial3.cliente.ejercicio3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Universidad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Autor>autores= new ArrayList<Autor>();
	List<TrabajoGrado>trabajosGrado= new ArrayList<TrabajoGrado>();
	
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public List<TrabajoGrado> getTrabajosGrado() {
		return trabajosGrado;
	}
	public void setTrabajosGrado(List<TrabajoGrado> trabajosGrado) {
		this.trabajosGrado = trabajosGrado;
	}
	
	
}
