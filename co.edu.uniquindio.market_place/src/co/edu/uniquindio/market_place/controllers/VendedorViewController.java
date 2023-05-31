package co.edu.uniquindio.market_place.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Mensaje;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.services.IVendedorService;

public class VendedorViewController implements IVendedorService {

	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;
	Vendedor vendedor;

	public VendedorViewController() {
		// TODO Auto-generated constructor stub
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public VendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketPlace = modelFactoryController.getMarketPlace();
		vendedor = modelFactoryController.getMarketPlace()
				.buscarVendedorUsuario(modelFactoryController.getMarketPlace().getLogin().getUsuario());
	}

	@Override
	public void enviarMensaje(Vendedor vendedor, Mensaje mensaje) throws MensajeException {
		// TODO Auto-generated method stub
		modelFactoryController.enviarMensaje(this.vendedor, vendedor, mensaje);
	}

	@Override
	public void publicarProducto(Producto producto) throws PublicacionException {
		// TODO Auto-generated method stub
		modelFactoryController.publicarProducto(vendedor, producto);
	}

	@Override
	public Boolean eliminarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		return modelFactoryController.eliminarProducto(vendedor, producto);
	}

	@Override
	public Boolean actualizarProducto(Producto producto) throws ProductoException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean agregarAmigo(Vendedor usuario, Vendedor vendedor2) {
		return modelFactoryController.agregarAmigo(usuario, vendedor2);
	}

	public ArrayList<Vendedor> buscarVendedorPerfil(String nombre, String cedula, String usuario) {
		return modelFactoryController.buscarVendedorPerfil(nombre, cedula, usuario);
	}

	public boolean eliminarAmigo(Vendedor vendedor) {
		return modelFactoryController.eliminarAmigo(this.vendedor, vendedor);
	}

	public void agregarLike(Producto selectedItem) {
		// TODO Auto-generated method stub
		modelFactoryController.agregarLike(vendedor, selectedItem);
	}

	public void agregarComentario(Producto producto, String comentario) {
		// TODO Auto-generated method stub
		modelFactoryController.agregarComentario(vendedor, producto, comentario);
	}

	public String obtenerComentarios(Producto producto) {
		return modelFactoryController.obtenerComentarios(producto);
	}

	public ArrayList<Vendedor> obtenerVendedores() {
		return modelFactoryController.obtenerVendedores();
	}
}
