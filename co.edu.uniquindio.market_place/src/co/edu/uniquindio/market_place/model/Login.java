package co.edu.uniquindio.market_place.model;

import java.io.Serializable;

import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.services.ILoginService;

public class Login implements ILoginService, Serializable {

	private static final long serialVersionUID = 1L;
	private Persona persona = new Vendedor();

	public Login() {

	}

	@Override
	public boolean login(String usuario, String contrasenia, Persona persona) throws UsuarioException {
		boolean login = false;
		if (usuario.equals(persona.getUsuario()) && contrasenia.equals(persona.getContrasenia())) {
			login = true;
			this.persona = persona;
		}
		return login;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsuario() {
		return persona.getUsuario();
	}

	public void setUsuario(String usuario) {
		persona.setUsuario(usuario);
	}

	public String getContrasenia() {
		return persona.getContrasenia();
	}

	public void setContrasenia(String contrasenia) {
		persona.setContrasenia(contrasenia);
	}

}
