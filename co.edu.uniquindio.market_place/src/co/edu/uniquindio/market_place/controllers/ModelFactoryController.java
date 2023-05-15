package co.edu.uniquindio.market_place.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import co.edu.uniquindio.market_place.persistence.*;
import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Administrador;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Mensaje;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.persistence.Persistencia;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.services.IModelFactoryService;
import co.edu.uniquindio.market_place.threads.Hilo_GuardarResourceXML;
import co.edu.uniquindio.market_place.threads.Hilo_RegistrarAccionesSistema;

public class ModelFactoryController implements IModelFactoryService {

	MarketPlace marketPlace;

	/*
	 * Singleton
	 */
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser
		// protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {

//		 1. inicializar datos y luego guardarlo en archivos
//		 inicializarSalvarDatos();

		// 2. Cargar los datos de los archivos
//		 cargarDatosDesdeArchivos();

		// 3. Guardar y Cargar el recurso serializable binario
//		 guardarResourceBinario();
//		 cargarResourceBinario();

		// 4. Guardar y Cargar el recurso serializable XML
//		 guardarResourceXML();
		 cargarResourceXML();

		// Siempre se debe verificar si la raiz del recurso es null

//		if (marketPlace == null) {
//			inicializarDatos();
//			// guardarResourceXML();
//		}

		registrarAccionesSistemaHilos("Inicio de sesión", 1, "inicioSesión");

	}

	private void cargarResourceXML() {
		marketPlace = Persistencia.cargarRecursomarketPlaceXML();
	}

	public void guardarResourceXML() {
		Persistencia.guardarRecursomarketPlaceXML(marketPlace);
	}
	
	private void guardarResourceXMLHilos(){
		Hilo_GuardarResourceXML h = new Hilo_GuardarResourceXML(getInstance());
		h.start();
	}

	private void cargarResourceBinario() {
		marketPlace = Persistencia.cargarRecursoMarketPlaceBinario();
	}

	private void guardarResourceBinario() {
		Persistencia.guardarRecursomarketPlaceBinario(marketPlace);
	}

	private void inicializarSalvarDatos() {
		inicializarDatos();
		try {
			Persistencia.guardarvendedores(getMarketPlace().getVendedores());
			Persistencia.guardarUsuarios(getMarketPlace().getVendedores());
			registrarAccionesSistemaHilos("se inicializaron los datos en el archivo", 1, "incializar salvar datos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistemaHilos("No se pudo inicializar los datos", 3, "inicializar salvar datos");
		}

	}
	
	private void cargarDatosDesdeArchivos() {
		this.marketPlace = new MarketPlace();
		try {
			ArrayList<Vendedor> listavendedores =new ArrayList<Vendedor>();
			listavendedores = Persistencia.cargarVendedores();
			getMarketPlace().getVendedores().addAll(listavendedores);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistemaHilos("Error --> "+e.getMessage(),3,"inicializar datos desde archivo");
			registrarAccionesSistemaHilos("No se pudo cargar datos desde archivo", 3, "inicializar datos desde archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistemaHilos("Error --> "+e.getMessage(),3,"inicializar datos desde archivo");
			registrarAccionesSistemaHilos("No se pudo cargar datos desde archivo", 3, "inicializar datos desde archivo");
		}
		
	}
	

	public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
		Persistencia.guardarRegistroLog(mensaje, nivel, accion);
	}
	
	public void registrarAccionesSistemaHilos(String mensaje, int nivel, String accion){
		Hilo_RegistrarAccionesSistema h = new Hilo_RegistrarAccionesSistema(getInstance(), mensaje, nivel, accion);
		h.start();
	}
	
	
	private void inicializarDatos() {
		
		
		marketPlace = new MarketPlace();
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre("Daniel");
		vendedor.setApellido("arias");
		vendedor.setCedula("125454");
		vendedor.setDireccion("Armenia");
		vendedor.setUsuario("usuario_0");
		vendedor.setContrasenia("contrasenia1");

		marketPlace.getVendedores().add(vendedor);

		vendedor = new Vendedor();
		vendedor.setNombre("Juan");
		vendedor.setApellido("Perez");
		vendedor.setCedula("77787");
		vendedor.setDireccion("Pererira");
		vendedor.setUsuario("usuario_1");
		vendedor.setContrasenia("contrasenia2");
		marketPlace.getVendedores().add(vendedor);
		
		vendedor = new Vendedor();
		vendedor.setNombre("Alberto");
		vendedor.setApellido("Arias");
		vendedor.setCedula("12555");
		vendedor.setDireccion("Pererira");
		vendedor.setUsuario("usuario_2");
		vendedor.setContrasenia("contrasenia3");
		marketPlace.getVendedores().add(vendedor);
		
		registrarAccionesSistemaHilos("datos inicializados", 1, "inicializar los datos");
		
	}

	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	public void setmarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}


	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario,
			String contrasenia) throws VendedorException, IOException {
		Vendedor vendedor = null;
		try {
			vendedor = getMarketPlace().crearVendedor(nombre, apellido, cedula, direccion, usuario, contrasenia);
			registrarAccionesSistemaHilos("Vendedor creado por el usuario: "+marketPlace.getLogin().getUsuario(), 1, "Agregar Vendedor");
			guardarResourceXMLHilos();
		} catch (VendedorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			registrarAccionesSistemaHilos("Error ---> "+e.getMessage(), 3, "Agregar Vendedor");
		}
		
		return vendedor;
	}

	@Override
	public Boolean eliminarVendedor(String cedula) throws VendedorException, IOException {
		boolean flagExiste = false;
		try {
			flagExiste = getMarketPlace().eliminarVendedor(cedula);
			guardarResourceXMLHilos();
			registrarAccionesSistemaHilos("vendedor con cedula "+cedula+" eliminado por el usuario: "+marketPlace.getLogin().getUsuario(), 1, "eliminar vendedor");
			
		} catch (VendedorException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistemaHilos("Error ---> "+e.getMessage(), 3, "Eliminar Vendedor");
		}
		return flagExiste;
	}

	@Override
	public Vendedor obtenerVendedor(String cedula) {
		// TODO Auto-generated method stub
		return marketPlace.obtenerVendedor(cedula);
	}

	@Override
	public ArrayList<Vendedor> obtenerVendedores() {
		// TODO Auto-generated method stub
		return marketPlace.getVendedores();
	}

	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula,
			String direccion, String usuario, String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<Vendedor> buscarVendedores(String nombre, String apellido, String cedula, String direccion, String usuario, String contrasenia){
		return marketPlace.buscarVendedores(nombre, apellido, cedula, direccion, usuario, contrasenia);
	}
	
	public boolean login(String usuario, String contrasenia) throws UsuarioException{
		Persona persona;
		for(Vendedor vendedor: marketPlace.getVendedores()){
			if(vendedor.getUsuario().equals(usuario)){
				if(vendedor.getContrasenia().equals(contrasenia)){
					persona = vendedor;
					marketPlace.getLogin().setPersona(persona);;
					registrarAccionesSistemaHilos("se ha iniciado sesion con el usuario: "+persona.getUsuario(), 1, "login");
					return marketPlace.login(usuario, contrasenia, persona);
				}
			}
		}
		return false;
	}
	
	public Persona obtenerUsuario(){
		return marketPlace.getLogin().getPersona();
	}
	
	public void enviarMensaje(Vendedor enviar, Vendedor recibir, Mensaje mensaje){
		try {
			enviar.enviarMensaje(recibir, mensaje);
		} catch (MensajeException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistema("error" +e.getMessage(), 3, "enviar mensaje");
		}
	}
	
	public void publicarProducto(Vendedor vendedor, Producto producto){
		try {
			vendedor.publicarProducto(producto);
		} catch (PublicacionException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistema("error"+e.getMessage(), 3, "publicar producto");
		}
	}
	
	public boolean eliminarProducto(Vendedor vendedor, Producto producto){
		try {
			return vendedor.eliminarProducto(producto);
		} catch (ProductoException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			registrarAccionesSistema("error"+e.getMessage(), 3, "eliminar producto");
		}
		return false;
	}

}
