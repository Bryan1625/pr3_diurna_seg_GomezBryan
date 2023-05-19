package co.edu.uniquindio.market_place.controllers;

import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.model.Login;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.services.ILoginService;

public class LoginViewController {
	
	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;
	
	public LoginViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketPlace = modelFactoryController.getMarketPlace();
	}

	public boolean login(String usuario, String contrasenia) throws UsuarioException {
		return modelFactoryController.login(usuario, contrasenia);
	}
	
	public Vendedor obtenerUsuarioLogin(String usuario){
		return modelFactoryController.obtenerVendedorUsuario(usuario);
	}
}
