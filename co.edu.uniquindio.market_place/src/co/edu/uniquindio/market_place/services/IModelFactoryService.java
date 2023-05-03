package co.edu.uniquindio.market_place.services;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Vendedor;

public interface IModelFactoryService {
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String contraseña) throws VendedorException, IOException;
	public Boolean eliminarVendedor(String cedula)throws VendedorException, IOException;
	public Vendedor obtenerVendedor(String cedula);
	public ArrayList<Vendedor> obtenerVendedores();
	
}
