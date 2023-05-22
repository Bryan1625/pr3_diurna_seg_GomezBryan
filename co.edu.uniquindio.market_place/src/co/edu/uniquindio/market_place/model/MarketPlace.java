package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.exceptions.AdministradorException;
import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.services.IMarketPlaceService;

public class MarketPlace implements IMarketPlaceService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Vendedor> vendedores = new ArrayList<>();

	private Administrador admin;

	private Login login;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public MarketPlace() {
		login = new Login();
		admin = new Administrador();
	}

	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Administrador crearAdministrador(String nombre, String apellido, String cedula, String direccion,
			String usuario, String contrasenia) throws AdministradorException {
		Administrador nuevoAdmin = null;
		// 1. verificar si existe
		boolean adminExiste = verificarAdminExistente();
		if (adminExiste) {
			throw new AdministradorException("El Administrador con cedula: " + cedula + " no se puedo asignar");
		} else {
			nuevoAdmin = new Administrador();
			nuevoAdmin.setNombre(nombre);
			nuevoAdmin.setApellido(apellido);
			nuevoAdmin.setCedula(cedula);
			nuevoAdmin.setUsuario(usuario);
			nuevoAdmin.setContrasenia(contrasenia);
			setAdmin(nuevoAdmin);
		}
		return nuevoAdmin;
	}
	
	public void agregarComentario(Producto producto, Vendedor vendedor, String comentario){
		producto.getComentarios().add(vendedor.getUsuario()+": "+comentario);
	}

	private boolean verificarAdminExistente() {
		if (getAdmin() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario,
			String contrasenia) throws VendedorException {
		Vendedor nuevoVendedor = null;
		// 1. verificar si existe
		boolean vendedorExiste = verificarVendedorExistente(cedula);
		if (vendedorExiste) {
			throw new VendedorException("El vendedor con cedula: " + cedula + " ya existe");
		} else {
			nuevoVendedor = new Vendedor();
			nuevoVendedor.setNombre(nombre);
			nuevoVendedor.setApellido(apellido);
			nuevoVendedor.setCedula(cedula);
			nuevoVendedor.setDireccion(direccion);
			nuevoVendedor.setUsuario(usuario);
			nuevoVendedor.setContrasenia(contrasenia);
			getVendedores().add(nuevoVendedor);
		}
		return nuevoVendedor;
	}

	@Override
	public Boolean eliminarVendedor(String cedula) throws VendedorException {
		Vendedor vendedor = null;
		boolean flagExiste = false;
		vendedor = obtenerVendedor(cedula);
		if (vendedor == null)
			throw new VendedorException("El vendedor a eliminar no existe");
		else {
			getVendedores().remove(vendedor);
			flagExiste = true;
		}
		return flagExiste;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	@Override
	public boolean verificarVendedorExistente(String cedula) {
		Vendedor vendedor = null;
		vendedor = obtenerVendedor(cedula);
		if (vendedor == null)
			return false;
		else
			return true;
	}

	@Override
	public Vendedor obtenerVendedor(String cedula) {
		Vendedor vendedorEncontrado = null;
		for (Vendedor vendedor : getVendedores()) {
			if (vendedor.getCedula().equalsIgnoreCase(cedula)) {
				vendedorEncontrado = vendedor;
				break;
			}
		}
		return vendedorEncontrado;
	}

	@Override
	public ArrayList<Vendedor> obtenerVendedores() {
		// TODO Auto-generated method stub
		return getVendedores();
	}

	public ArrayList<Vendedor> buscarVendedores(String nombre, String apellido, String cedula, String direccion,
			String usuario, String contrasenia) {
		ArrayList<Vendedor> vendedoresEncontrados = new ArrayList<>();
		for (Vendedor vendedor : vendedores) {
			if (vendedor.getNombre().contains(nombre) || vendedor.getApellido().contains(apellido)
					|| vendedor.getCedula().contains(cedula) || vendedor.getDireccion().contains(direccion)
					|| vendedor.getUsuario().contains(usuario) || vendedor.getContrasenia().contains(contrasenia)) {
				vendedoresEncontrados.add(vendedor);
			}
		}
		if (vendedoresEncontrados.isEmpty()) {
			return vendedores;
		}
		return vendedoresEncontrados;
	}

	public ArrayList<Vendedor> buscarVendedorPerfil(String nombre, String cedula, String usuario) {
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
		for (Vendedor vendedor : this.vendedores) {
			if (vendedor.getNombre().equalsIgnoreCase(nombre.toLowerCase())
					|| vendedor.getCedula().contentEquals(cedula)
					|| vendedor.getUsuario().equalsIgnoreCase(usuario.toLowerCase())) {
				vendedores.add(vendedor);
			}
		}
		return vendedores;
	}

	public Vendedor buscarVendedorUsuario(String usuario) {
		for (Vendedor vendedor : vendedores) {
			if (vendedor.getUsuario().contentEquals(usuario)) {
				return vendedor;
			}
		}
		return null;
	}

	public boolean login(String usuario, String contrasenia, Persona persona) throws UsuarioException {
		return login.login(usuario, contrasenia, persona);
	}

}
