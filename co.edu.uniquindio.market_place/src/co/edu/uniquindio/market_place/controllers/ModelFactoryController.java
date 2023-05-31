package co.edu.uniquindio.market_place.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.uniquindio.market_place.Hilo_servicioComprobacion;
import co.edu.uniquindio.market_place.exceptions.MensajeException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Mensaje;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.persistence.Persistencia;
import co.edu.uniquindio.market_place.services.IModelFactoryService;

public class ModelFactoryController implements IModelFactoryService, Runnable {

	MarketPlace marketPlace;
	private boolean bloqueo = false;

	Thread hiloServicio1_guardarXML;
	Thread hiloServicio2_guardarLog;
	Thread hiloServicio3_cargarXML;

	String mensaje = "";
	int nivel = 0;
	String accion = "";

	private Socket socketComunicacion = null;// para la comunicacion (entrada y
												// salida)
	private Socket socketTransferenciaObjeto = null;// permite recibir el
													// archivo
	private DataOutputStream flujoSalidaComunicacion = null;
	private DataInputStream flujoEntradaComunicacion;

	
	private DataInputStream flujoEntradaComunicacionHilo = null;
	private ObjectOutputStream flujoSalidaModeloHilo = null;
	private Socket socketHilo = null;

	byte[] receivedData;
	int in;
	BufferedInputStream flujoEntradaArchivo;
	BufferedOutputStream flujoSalidadLocalArchivo;

	String filename = "";
	String rutaArchivoLocal = "src/resourcesServidor/";
	ObjectInputStream flujoEntradaObjeto;
	ObjectOutputStream flujoSalidaObjeto;

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

		// 1. inicializar datos y luego guardarlo en archivos
		// inicializarSalvarDatos();

		// 2. Cargar los datos de los archivos
		// cargarDatosDesdeArchivos();

		// 3. Guardar y Cargar el recurso serializable binario
		// guardarResourceBinario();
		// cargarResourceBinario();

		// 4. Guardar y Cargar el recurso serializable XML
		// guardarResourceXML();
		// cargarResourceXMLHilos();

		// Siempre se debe verificar si la raiz del recurso es null

		// if (marketPlace == null) {
		// inicializarDatos();
		// // guardarResourceXML();
		// }
		// metodos = new Metodos(this);

		// registrarAccionesSistemaHilos("Ejecucion de la aplicacion", 1,
		// "Ejecucion");

		System.out.println("llamado....");

		try {
			crearConexion();

			solicitarInformacionPersistencia();
			leerObjetoPersistenciaTransferido();

//			iniciarServicioComprobacion();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void iniciarServicioComprobacion() {
		Hilo_servicioComprobacion hiloComprobacion = new Hilo_servicioComprobacion(this, 
				flujoEntradaComunicacionHilo, socketHilo, flujoSalidaModeloHilo);
		hiloComprobacion.start();

	}

	public void crearConexion() throws IOException, Exception {
		try {
			socketComunicacion = new Socket("localhost", 8081); // socket para
																// enviar datos
																// primitivos
			socketTransferenciaObjeto = new Socket("localhost", 8082);// sokcet
																		// para
																		// enviar
																		// y
																		// recibir
																		// objeto

			flujoEntradaComunicacion = new DataInputStream(socketComunicacion.getInputStream());
			flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());

			flujoEntradaObjeto = new ObjectInputStream(socketTransferenciaObjeto.getInputStream());
			flujoSalidaObjeto = new ObjectOutputStream(socketTransferenciaObjeto.getOutputStream());

		} catch (IOException e) {
			throw new Exception("\tError en el servidor");

		}
	}

	private void solicitarInformacionPersistencia() throws IOException {
		flujoSalidaComunicacion.writeInt(1);
		flujoSalidaComunicacion.close();
	}

	private void solicitarGuardarPersistencia() throws IOException {

		flujoSalidaComunicacion.writeInt(2);
		flujoSalidaComunicacion.close();
	}

	private void enviarObjetoPersistenciaTransferido() throws IOException, ClassNotFoundException {

		flujoSalidaObjeto.writeObject(marketPlace);
		System.out.println("objeto enviado");
		flujoSalidaObjeto.close();
	}

	private void leerObjetoPersistenciaTransferido() throws IOException, ClassNotFoundException {

		marketPlace = (MarketPlace) flujoEntradaObjeto.readObject();
		System.out.println("objeto recibido");
		flujoEntradaObjeto.close();
	}

	private void cargarResourceXML() {
		hiloServicio3_cargarXML = new Thread(this);
		hiloServicio3_cargarXML.start();
	}

	public void guardarResourceXML() {

		hiloServicio1_guardarXML = new Thread(this);
		hiloServicio1_guardarXML.start();
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
			// e.printStackTrace();
			registrarAccionesSistemaHilos("No se pudo inicializar los datos", 3, "inicializar salvar datos");
		}

	}

	private void cargarDatosDesdeArchivos() {
		this.marketPlace = new MarketPlace();
		try {
			ArrayList<Vendedor> listavendedores = new ArrayList<Vendedor>();
			listavendedores = Persistencia.cargarVendedores();
			getMarketPlace().getVendedores().addAll(listavendedores);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("Error --> " + e.getMessage(), 3, "inicializar datos desde archivo");
			registrarAccionesSistemaHilos("No se pudo cargar datos desde archivo", 3,
					"inicializar datos desde archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("Error --> " + e.getMessage(), 3, "inicializar datos desde archivo");
			registrarAccionesSistemaHilos("No se pudo cargar datos desde archivo", 3,
					"inicializar datos desde archivo");
		}

	}

	public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
		Persistencia.guardarRegistroLog(mensaje, nivel, accion);
	}

	public void registrarAccionesSistemaHilos(String mensaje, int nivel, String accion) {
		this.mensaje = mensaje;
		this.nivel = nivel;
		this.accion = accion;

		hiloServicio2_guardarLog = new Thread(this);
		hiloServicio2_guardarLog.start();
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
			bloqueo();
			vendedor = getMarketPlace().crearVendedor(nombre, apellido, cedula, direccion, usuario, contrasenia);
			registrarAccionesSistemaHilos("Vendedor creado por el usuario: " + marketPlace.getLogin().getUsuario(), 1,
					"Agregar Vendedor");
			guardarResourceXML();
			desbloqueo();
		} catch (VendedorException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("Error ---> " + e.getMessage(), 3, "Agregar Vendedor");
		}

		return vendedor;
	}

	@Override
	public Boolean eliminarVendedor(String cedula) throws VendedorException, IOException {
		boolean flagExiste = false;
		try {
			bloqueo();
			flagExiste = getMarketPlace().eliminarVendedor(cedula);
			guardarResourceXML();
			desbloqueo();
			registrarAccionesSistemaHilos("vendedor con cedula " + cedula + " eliminado por el usuario: "
					+ marketPlace.getLogin().getUsuario(), 1, "eliminar vendedor");

		} catch (VendedorException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("Error ---> " + e.getMessage(), 3, "Eliminar Vendedor");
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

	public ArrayList<Vendedor> buscarVendedores(String nombre, String apellido, String cedula, String direccion,
			String usuario, String contrasenia) {
		return marketPlace.buscarVendedores(nombre, apellido, cedula, direccion, usuario, contrasenia);
	}

	public boolean login(String usuario, String contrasenia) throws UsuarioException {
		Persona persona;
		for (Vendedor vendedor : marketPlace.getVendedores()) {
			if (vendedor.getUsuario().equals(usuario)) {
				if (vendedor.getContrasenia().equals(contrasenia)) {
					persona = vendedor;
					marketPlace.getLogin().setPersona(persona);
					registrarAccionesSistemaHilos("se ha iniciado sesion con el usuario: " + persona.getUsuario(), 1,
							"login");
					return marketPlace.login(usuario, contrasenia, persona);
				}
			} else if (marketPlace.getAdmin().getUsuario().equals(usuario)
					&& marketPlace.getAdmin().getContrasenia().equals(contrasenia)) {
				marketPlace.getLogin().setPersona(marketPlace.getAdmin());
				return true;
			}
		}
		return false;
	}

	public Persona obtenerUsuario() {
		return marketPlace.getLogin().getPersona();
	}

	public void enviarMensaje(Vendedor emisor, Vendedor receptor, Mensaje mensaje) {
		try {
			emisor.enviarMensaje(receptor, mensaje);
		} catch (MensajeException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("error" + e.getMessage(), 3, "enviar mensaje");
		}
	}

	public void publicarProducto(Vendedor vendedor, Producto producto) throws PublicacionException {
		try {
			bloqueo();
			vendedor.publicarProducto(producto);
			guardarResourceXML();
			desbloqueo();
		} catch (PublicacionException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("error" + e.getMessage(), 3, "publicar producto");
		}
	}

	public boolean eliminarProducto(Vendedor vendedor, Producto producto) {
		try {
			bloqueo();
			if (vendedor.eliminarProducto(producto)) {
				guardarResourceXML();
				desbloqueo();
				return true;
			}
			desbloqueo();
		} catch (ProductoException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			registrarAccionesSistemaHilos("error" + e.getMessage(), 3, "eliminar producto");
		}
		return false;
	}

	public boolean agregarAmigo(Vendedor usuario, Vendedor vendedor2) {
		bloqueo();
		if (usuario.agregarAmigo(vendedor2)) {
			guardarResourceXML();
			desbloqueo();
			return true;
		} else {
			desbloqueo();
			registrarAccionesSistemaHilos("error al agregar un amigo", 2, "agregar amigo");
			return false;
		}

	}

	public boolean eliminarAmigo(Vendedor vendedor1, Vendedor vendedor2) {
		// TODO Auto-generated method stub
		bloqueo();
		if (vendedor1.eliminarAmigo(vendedor2)) {
			guardarResourceXML();
			desbloqueo();
			return true;
		} else {
			desbloqueo();
			return false;
		}
	}

	public ArrayList<Vendedor> buscarVendedorPerfil(String nombre, String cedula, String usuario) {
		return marketPlace.buscarVendedorPerfil(nombre, cedula, usuario);
	}

	public void agregarLike(Vendedor vendedor, Producto selectedItem) {
		// TODO Auto-generated method stub
		selectedItem.agregarLike(vendedor);
	}

	public void agregarComentario(Vendedor vendedor, Producto producto, String comentario) {
		// TODO Auto-generated method stub
		bloqueo();
		marketPlace.agregarComentario(producto, vendedor, comentario);
		guardarResourceXML();
		desbloqueo();
	}

	public String obtenerComentarios(Producto producto) {
		return producto.obtenerComentarios();
	}

	public Vendedor obtenerVendedorUsuario(String usuario) {
		// TODO Auto-generated method stub
		return marketPlace.buscarVendedorUsuario(usuario);
	}

	public synchronized void bloqueo() {
		while (bloqueo) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		bloqueo = true;
	}

	public synchronized void desbloqueo() {
		bloqueo = false;
		notifyAll();
	}

	private void solicitarGuardarRegistroLog() {
		try {
			flujoSalidaComunicacion.writeInt(3);
			flujoSalidaComunicacion.writeUTF(mensaje);
			flujoSalidaComunicacion.writeInt(nivel);
			flujoSalidaComunicacion.writeUTF(accion);
			flujoSalidaComunicacion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		Thread hiloEjecucion = Thread.currentThread();

		// Semaforo

		if (hiloEjecucion == hiloServicio1_guardarXML) {
			try {
				crearConexion();

				solicitarGuardarPersistencia();
				enviarObjetoPersistenciaTransferido();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("Ejecuto hilo servicio 1");
			// Persistencia.guardarRecursoBancoXML(banco);
		}

		if (hiloEjecucion == hiloServicio2_guardarLog) {
			System.out.println("Ejecuto hilo servicio 2");
			try {
				crearConexion();
				solicitarGuardarRegistroLog();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (hiloEjecucion == hiloServicio3_cargarXML) {
			try {
				crearConexion();
				solicitarCargarXML();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void solicitarCargarXML() {
		try {
			flujoSalidaComunicacion.writeInt(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
