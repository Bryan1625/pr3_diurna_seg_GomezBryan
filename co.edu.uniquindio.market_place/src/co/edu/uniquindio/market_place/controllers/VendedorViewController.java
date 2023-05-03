package co.edu.uniquindio.market_place.controllers;


import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.services.IVendedorService;

public class VendedorViewController implements IVendedorService{
	
	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;

	public VendedorViewController() {
		// TODO Auto-generated constructor stub
	}
	
	public VendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketPlace = modelFactoryController.getMarketPlace();
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
