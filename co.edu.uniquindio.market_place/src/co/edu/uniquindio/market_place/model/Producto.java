package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;



public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre ="";
	private Double precio = 0.0;
	private String descripcion="";
	private String rutaImagen;
	private String categoria="";
	private Estado estado=Estado.publicado;
	private ArrayList<Vendedor> likes=new ArrayList<Vendedor>();
	private int numeroLikes;
	private ArrayList<String> comentarios = new ArrayList<String>();
	private String fecha;
	
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Producto(String nombre, Double precio, String descripcion, String imagen, String categoria, Estado estado) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.setRutaImagen(imagen);
		this.categoria = categoria;
		this.estado = estado;
		this.fecha = LocalDate.now().toString();
	}

	public void agregarLike(Vendedor vendedor){
		if(buscarLike(vendedor)){
			likes.remove(vendedor);
		}
		likes.add(vendedor);
	}
	
	public boolean buscarLike(Vendedor vendedor){
		if(likes.contains(vendedor)){
			return true;
		}
		return false;
	}

	public ArrayList<Vendedor> getLikes() {
		return likes;
	}


	public void setLikes(ArrayList<Vendedor> likes) {
		this.likes = likes;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Producto() {
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<String> comentarios) {
		this.comentarios = comentarios;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getNumeroLikes() {
		return likes.size();
	}

	public void setNumeroLikes(int numeroLikes) {
		this.numeroLikes = numeroLikes;
	}



}
