package co.edu.uniquindio.market_place.services;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.model.Producto;

public interface IVendedorService {

	public Boolean enviarMensaje(String mensaje) throws MensajeException;
	public Boolean publicarProducto(Producto producto) throws PublicacionException;
	public Boolean eliminarProducto(Producto producto) throws ProductoException;
	public Boolean actualizarProducto(Producto producto) throws ProductoException;
	
}
