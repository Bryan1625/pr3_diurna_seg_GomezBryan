package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.services.IVendedorService;

public class Vendedor extends Persona implements IVendedorService, Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int max_contactos = 10;
	private ArrayList<Producto> productos = new ArrayList<>();
	private ArrayList<Vendedor> amigos = new ArrayList<>();
	private ArrayList<Vendedor> contactos = new ArrayList<>();
	private ArrayList<Mensaje> listaMensajesOrigen = new ArrayList<>();
	private ArrayList<Mensaje> listaMensajesDestino = new ArrayList<>();
	
	public Vendedor() {
		
	}

	public boolean agregarAmigo(Vendedor vendedor){
		if(amigos.size()<max_contactos){
			amigos.add(vendedor);
			return true;
		}
		return false;
	}

	public ArrayList<Vendedor> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Vendedor> amigos) {
		this.amigos = amigos;
	}
	
	public ArrayList<Producto> gerProductos(){
		return this.productos;
	}
	
	public void agregarProducto(Producto producto){
		this.productos.add(producto);
	}


	public ArrayList<Vendedor> getContactos() {
		return contactos;
	}


	public void setContactos(ArrayList<Vendedor> contactos) {
		this.contactos = contactos;
	}


	public int getMax_contactos() {
		return max_contactos;
	}


	public ArrayList<Mensaje> getListaMensajesOrigen() {
		return listaMensajesOrigen;
	}


	public void setListaMensajesOrigen(ArrayList<Mensaje> listaMensajesOrigen) {
		this.listaMensajesOrigen = listaMensajesOrigen;
	}


	public ArrayList<Mensaje> getListaMensajesDestino() {
		return listaMensajesDestino;
	}


	public void setListaMensajesDestino(ArrayList<Mensaje> listaMensajesDestino) {
		this.listaMensajesDestino = listaMensajesDestino;
	}


	@Override
	public Boolean enviarMensaje(String mensaje) throws MensajeException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean publicarProducto(Producto producto) throws PublicacionException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean eliminarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean actualizarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
