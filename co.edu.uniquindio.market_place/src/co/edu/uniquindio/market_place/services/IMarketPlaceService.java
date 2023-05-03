package co.edu.uniquindio.market_place.services;

import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.AdministradorException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Administrador;
import co.edu.uniquindio.market_place.model.Vendedor;

public interface IMarketPlaceService {

	public Administrador crearAdministrador(String nombre, String apellido, String cedula, String direccion, String usuario, String contraseña) throws AdministradorException;
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String contraseña) throws VendedorException;
	public Boolean eliminarVendedor(String cedula) throws VendedorException;
	public boolean  verificarVendedorExistente(String cedula);
	public Vendedor obtenerVendedor(String cedula);
	public ArrayList<Vendedor> obtenerVendedores();
}
