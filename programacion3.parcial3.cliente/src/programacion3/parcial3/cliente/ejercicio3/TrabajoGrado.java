package programacion3.parcial3.cliente.ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class TrabajoGrado {     
	private String fecha;
	private String titulo;
	private String descripcion;
	private List<Autor>autores= new ArrayList<Autor>();
	
	public TrabajoGrado() {
		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	
}
