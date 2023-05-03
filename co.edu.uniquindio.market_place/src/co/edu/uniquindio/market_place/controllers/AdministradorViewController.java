package co.edu.uniquindio.market_place.controllers;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.controllers.ModelFactoryController;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Vendedor;

public class AdministradorViewController {

	
	ModelFactoryController modelFactoryController;
	MarketPlace marketPlace;


	public AdministradorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketPlace = modelFactoryController.getMarketPlace();
	}
	
	
	public MarketPlace getMarketPlace() {
		return marketPlace;
	}


	public void setMarketPlace(MarketPlace MarketPlace) {
		this.marketPlace = MarketPlace;
	}


	public ArrayList<Vendedor> obtenerVendedores() {
		return modelFactoryController.obtenerVendedores();
	}


	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String contrasenia) throws VendedorException, IOException {
		return modelFactoryController.crearVendedor(nombre, apellido, cedula, direccion, usuario, contrasenia);
	}


	public boolean eliminarVendedor(String cedula) throws VendedorException, IOException {
		return modelFactoryController.eliminarVendedor(cedula);
	}


	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula,String direccion, String usuario, String contrasenia) {
	    return modelFactoryController.actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion, usuario, contrasenia);
	}

	
	public ArrayList<Vendedor> buscarVendedores(String nombre, String apellido, String cedula, String direccion, String usuario, String contrasenia){
		return modelFactoryController.buscarVendedores(nombre, apellido, cedula, direccion, usuario, contrasenia);
	}


}
