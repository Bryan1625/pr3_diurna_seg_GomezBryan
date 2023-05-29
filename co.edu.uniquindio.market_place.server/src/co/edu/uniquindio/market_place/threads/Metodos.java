package co.edu.uniquindio.market_place.threads;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.persistence.Persistencia;

public class Metodos {

	private ModelFactoryController m;

	public Metodos(ModelFactoryController m) {
		// TODO Auto-generated constructor stub
		this.m = m;
	}

	public void guardarResourceXML() {
		m.guardarResourceXML();
	}

	public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
		m.registrarAccionesSistema(mensaje, nivel, accion);
	}

	public MarketPlace cargarResourceXML() {
		return Persistencia.cargarRecursomarketPlaceXML();
	}
}
