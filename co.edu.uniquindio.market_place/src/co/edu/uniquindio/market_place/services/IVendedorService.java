package co.edu.uniquindio.market_place.services;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.model.Mensaje;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.model.Vendedor;

public interface IVendedorService {

	public void enviarMensaje(Vendedor vendedor, Mensaje mensaje) throws MensajeException;
	public void publicarProducto(Producto producto) throws PublicacionException;
	public Boolean eliminarProducto(Producto producto) throws ProductoException;
	public Boolean actualizarProducto(Producto producto) throws ProductoException;
	
}
