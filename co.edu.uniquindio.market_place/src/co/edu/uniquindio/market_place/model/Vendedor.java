package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.services.IVendedorService;

public class Vendedor extends Persona implements IVendedorService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int max_contactos = 10;
	private ArrayList<Producto> productos = new ArrayList<>();
	private ArrayList<Vendedor> amigos = new ArrayList<>();
	private ArrayList<Vendedor> contactos = new ArrayList<>();
	private ArrayList<Chat> chat = new ArrayList<Chat>();
	private ArrayList<Mensaje> listaMensajesOrigen = new ArrayList<>();
	private ArrayList<Mensaje> listaMensajesDestino = new ArrayList<>();

	public Vendedor() {

	}

	public boolean agregarAmigo(Vendedor vendedor){
		if(amigos.size()<max_contactos && vendedor.getAmigos().size()<max_contactos){
			if(!amigos.contains(vendedor)){
			amigos.add(vendedor);
			vendedor.getAmigos().add(this);
			return true;
			}
		}
		return false;
	}

	public ArrayList<Vendedor> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Vendedor> amigos) {
		this.amigos = amigos;
	}

	public ArrayList<Producto> gerProductos() {
		return this.productos;
	}

	public void agregarProducto(Producto producto) {
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
	public void enviarMensaje(Vendedor vendedor, Mensaje mensaje) throws MensajeException {
		listaMensajesOrigen.add(mensaje);
		vendedor.getListaMensajesDestino().add(mensaje);
	}

	@Override
	public void publicarProducto(Producto producto) throws PublicacionException {
		// TODO Auto-generated method stub
		productos.add(producto);
	}

	@Override
	public Boolean eliminarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		if (productos.contains(producto)) {
			productos.remove(producto);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Boolean actualizarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Chat> getChat() {
		return chat;
	}

	public void setChat(ArrayList<Chat> chat) {
		this.chat = chat;
	}

	public boolean eliminarAmigo(Vendedor vendedor2) {
		// TODO Auto-generated method stub
		if (amigos.contains(vendedor2)) {
			amigos.remove(vendedor2);
			vendedor2.getAmigos().remove(this);
			return true;
		}
		return false;
	}

}
