package co.edu.uniquindio.market_place.controllers;

import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Mensaje;
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

	public VendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketPlace = modelFactoryController.getMarketPlace();
		vendedor = modelFactoryController.getMarketPlace().buscarVendedorUsuario(modelFactoryController.getMarketPlace().getLogin().getUsuario());
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

}
