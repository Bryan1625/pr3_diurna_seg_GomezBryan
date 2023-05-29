package co.edu.uniquindio.market_place.services;

import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.model.Login;
import co.edu.uniquindio.market_place.model.Persona;

public interface ILoginService {

	public boolean login(String usuario, String contrasenia, Persona persona) throws UsuarioException;
}
