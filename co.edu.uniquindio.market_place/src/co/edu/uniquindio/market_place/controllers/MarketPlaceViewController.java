package co.edu.uniquindio.market_place.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import co.edu.uniquindio.market_place.MainApp;
import co.edu.uniquindio.market_place.exceptions.AdministradorException;
import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Administrador;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.services.IMarketPlaceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

public class MarketPlaceViewController implements IMarketPlaceService {

	MainApp aplicacion;
	ModelFactoryController modelFactoryController;
	VendedorViewController vendedorViewController;
	AdministradorViewController administradorViewController;
	LoginViewController loginViewController;
	
	private String sesion;
	private Persona usuario;

	Vendedor vendedorSeleccionado;

	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

	@FXML
	private TextField txtFieldContrasenia;

	@FXML
	private TextField txtFieldUsuario;

	@FXML
	private TextField txtFieldNombreVendedorAdmin;

	@FXML
	private TextField txtFieldApellidoVendedorAdmin;

	@FXML
	private TextField txtFieldCedulaVendedorAdmin;

	@FXML
	private TextField txtFieldDireccionVendedorAdmin;

	@FXML
	private TextField txtFieldUsuarioVendedorAdmin;

	@FXML
	private TableView<Vendedor> tblVendedores;

	@FXML
	private TableColumn<Vendedor, String> colNombre;

	@FXML
	private TableColumn<Vendedor, String> colApellido;

	@FXML
	private TableColumn<Vendedor, String> colCedula;

	@FXML
	private TableColumn<Vendedor, String> colDireccion;

	@FXML
	private TableColumn<Vendedor, String> colUsuario;

	@FXML
	private TextField txtFieldContraseniaVendedorAdmin;

	public MarketPlaceViewController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Administrador crearAdministrador(String nombre, String apellido, String cedula, String direccion,
			String usuario, String contrasenia) throws AdministradorException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setListaVendedoresData(ObservableList<Vendedor> listaVendedoresData) {
		this.listaVendedoresData = listaVendedoresData;
	}

	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		vendedorViewController = new VendedorViewController(modelFactoryController);
		administradorViewController = new AdministradorViewController(modelFactoryController);
		loginViewController = new LoginViewController(modelFactoryController);

		inicializarAdministradorView();
		inicializarVendedorView();
	}

	private void inicializarAdministradorView() {
		this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		// Cargar la lista de vendedores

		tblVendedores.setItems(getListaVendedoresData());

		tblVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			vendedorSeleccionado = newSelection;
			mostrarInformacionvendedor(vendedorSeleccionado);
		});
	}

	private void mostrarInformacionvendedor(Vendedor vendedorSeleccionado) {
		if (vendedorSeleccionado != null) {
			txtFieldNombreVendedorAdmin.setText(vendedorSeleccionado.getNombre());
			txtFieldApellidoVendedorAdmin.setText(vendedorSeleccionado.getApellido());
			txtFieldCedulaVendedorAdmin.setText(vendedorSeleccionado.getCedula());
			txtFieldDireccionVendedorAdmin.setText(vendedorSeleccionado.getDireccion());
			txtFieldUsuarioVendedorAdmin.setText(vendedorSeleccionado.getUsuario());
			txtFieldContraseniaVendedorAdmin.setText(vendedorSeleccionado.getContrasenia());
		}
	}

	private void inicializarVendedorView() {
		// TODO Auto-generated method stub

	}

	public void setAplicacion(MainApp aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ObservableList<Vendedor> getListaVendedoresData() {
		listaVendedoresData.addAll(administradorViewController.obtenerVendedores());
		return listaVendedoresData;
	}

	@FXML
	void nuevoVendedorAction(ActionEvent event) {
		txtFieldNombreVendedorAdmin.setText("");
		txtFieldApellidoVendedorAdmin.setText("");
		txtFieldCedulaVendedorAdmin.setText("");
		txtFieldDireccionVendedorAdmin.setText("");
		txtFieldUsuarioVendedorAdmin.setText("");
		txtFieldContraseniaVendedorAdmin.setText("");
	}

	@FXML
	void agregarVendedorAction(ActionEvent event) throws VendedorException, IOException {

		crearVendedor();
	}

	private void crearVendedor() throws VendedorException, IOException {

		// 1. Capturar los datos
		String nombre = txtFieldNombreVendedorAdmin.getText();
		String apellido = txtFieldApellidoVendedorAdmin.getText();
		String cedula = txtFieldCedulaVendedorAdmin.getText();
		String direccion = txtFieldDireccionVendedorAdmin.getText();
		String usuario = txtFieldUsuarioVendedorAdmin.getText();
		String contrasenia = txtFieldContraseniaVendedorAdmin.getText();

		// 2. Validar la información
		if (datosValidos(nombre, apellido, cedula, direccion, usuario, contrasenia) == true) {
			Vendedor vendedor = null;
			vendedor = administradorViewController.crearVendedor(nombre, apellido, cedula, direccion, usuario,
					contrasenia);
			if (vendedor != null) {
				listaVendedoresData.add(vendedor);
				mostrarMensaje("Notificación vendedor", "vendedor creado", "El vendedor se ha creado con éxito",
						AlertType.INFORMATION);
				limpiarCamposVendedor();
			} else {
				mostrarMensaje("Notificación vendedor", "vendedor no creado", "El vendedor no se ha creado con éxito",
						AlertType.INFORMATION);
			}
		} else {
			mostrarMensaje("Notificación vendedor", "vendedor no creado", "Los datos ingresados son invalidos",
					AlertType.ERROR);
		}

	}

	@FXML
	void eliminarVendedorAction(ActionEvent event) throws VendedorException, IOException {

		eliminarVendedor();
	}

	private void eliminarVendedor() throws VendedorException, IOException {

		boolean vendedorEliminado = false;

		if (vendedorSeleccionado != null) {

			if (mostrarMensajeConfirmacion("¿Estas seguro de elmininar al vendedor?") == true) {

				vendedorEliminado = administradorViewController.eliminarVendedor(vendedorSeleccionado.getCedula());

				if (vendedorEliminado == true) {
					listaVendedoresData.remove(vendedorSeleccionado);
					vendedorSeleccionado = null;

					tblVendedores.getSelectionModel().clearSelection();
					limpiarCamposVendedor();
					mostrarMensaje("Notificación vendedor", "vendedor eliminado",
							"El vendedor se ha eliminado con éxito", AlertType.INFORMATION);
				} else {
					mostrarMensaje("Notificación vendedor", "vendedor no eliminado", "El vendedor no se puede eliminar",
							AlertType.ERROR);
				}
			}
		} else {
			mostrarMensaje("Notificación vendedor", "vendedor no seleccionado", "Seleccionado un vendedor de la lista",
					AlertType.WARNING);
		}
	}

	@FXML
	void ingresarAction() throws UsuarioException {
		String usuario = txtFieldUsuario.getText();
		String contrasenia = txtFieldContrasenia.getText();

		if (usuario.isEmpty() || contrasenia.isEmpty()) {
			mostrarMensaje("Notificación login", "no se pudo iniciar sesion", "Los campos de texto no pueden estar vacios",
					AlertType.ERROR);
		} else {
			
			boolean resultado = login(usuario, contrasenia);

			if (resultado) {
				mostrarMensaje("Inicio de Sesion", "Se ha iniciado sesion", "Bienvenido "+usuario, AlertType.INFORMATION);
				this.setUsuario(modelFactoryController.getMarketPlace().getLogin().getPersona());
				txtFieldUsuario.setText("");
				txtFieldContrasenia.setText("");
				validarSesion();
			} else {
				mostrarMensaje("Notificación login", "no se pudo iniciar sesion", "El usuario o la contraseia son incorrectos",
						AlertType.ERROR);
			}
		}

	}
	
	public void validarSesion(){
		if(usuario instanceof Vendedor){
			sesion = "vendedor";
		}else if(usuario instanceof Administrador){
			sesion = "admin";
		}else{
			mostrarMensaje("Notificacion de sesion", "No se pudo validar la sesion", "hubo un error y la persona que inicio sesion no es reconocidad como vendedor no como administrador", AlertType.ERROR);
		}
	}

	@FXML
	void actualizarVendedorAction(ActionEvent event) {
		actualizarVendedor();
	}

	private void actualizarVendedor() {

		// 1. Capturar los datos
		String nombre = txtFieldNombreVendedorAdmin.getText();
		String apellido = txtFieldApellidoVendedorAdmin.getText();
		String cedula = txtFieldCedulaVendedorAdmin.getText();
		String direccion = txtFieldDireccionVendedorAdmin.getText();
		String usuario = txtFieldUsuarioVendedorAdmin.getText();
		String contrasenia = txtFieldContraseniaVendedorAdmin.getText();
		boolean vendedorActualizado = false;

		// 2. verificar el vendedor seleccionado
		if (vendedorSeleccionado != null) {

			// 3. Validar la información
			if (datosValidos(nombre, apellido, cedula, direccion, usuario, contrasenia) == true) {

				vendedorActualizado = administradorViewController.actualizarVendedor(vendedorSeleccionado.getCedula(),
						nombre, apellido, cedula, direccion, usuario, contrasenia);

				if (vendedorActualizado == true) {
					tblVendedores.refresh();
					mostrarMensaje("Notificación vendedor", "vendedor actualizado",
							"El vendedor se ha actualizado con éxito", AlertType.INFORMATION);
					limpiarCamposVendedor();
				} else {
					mostrarMensaje("Notificación vendedor", "vendedor no actualizado",
							"El vendedor no se ha actualizado con éxito", AlertType.INFORMATION);
				}
			} else {
				mostrarMensaje("Notificación vendedor", "vendedor no creado", "Los datos ingresados son invalidos",
						AlertType.ERROR);
			}

		}
	}

	private void limpiarCamposVendedor() {
		txtFieldNombreVendedorAdmin.setText("");
		txtFieldApellidoVendedorAdmin.setText("");
		txtFieldCedulaVendedorAdmin.setText("");
		txtFieldDireccionVendedorAdmin.setText("");
		txtFieldUsuarioVendedorAdmin.setText("");
		txtFieldContraseniaVendedorAdmin.setText("");
	}

	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	private boolean datosValidos(String nombre, String apellido, String cedula, String direccion, String usuario,
			String contrasenia) {
		String mensaje = "";

		if (nombre == null || nombre.equals(""))
			mensaje += "El nombre es inválido \n";

		if (apellido == null || apellido.equals(""))
			mensaje += "El apellido es inválido \n";

		if (cedula == null || cedula.equals(""))
			mensaje += "La cédula es inválida \n";

		if (direccion == null || direccion.equals(""))
			mensaje += "La dirección es inválida \n";

		if (usuario == null || usuario.equals(""))
			mensaje += "El usuario es inválido \n";

		if (contrasenia == null || contrasenia.equals(""))
			mensaje += "La contraseña es inválida \n";

		if (mensaje.equals("")) {
			return true;
		} else {
			mostrarMensaje("Notificación vendedor", "Datos inválidos", mensaje, AlertType.WARNING);
			return false;
		}
	}

	@FXML
	private void buscarVendedorAction(ActionEvent event) {
		String nombre = txtFieldNombreVendedorAdmin.getText();
		String apellido = txtFieldApellidoVendedorAdmin.getText();
		String cedula = txtFieldCedulaVendedorAdmin.getText();
		String direccion = txtFieldDireccionVendedorAdmin.getText();
		String usuario = txtFieldUsuarioVendedorAdmin.getText();
		String contrasenia = txtFieldContraseniaVendedorAdmin.getText();

		ArrayList<Vendedor> vendedoresEncontrados = administradorViewController.buscarVendedores(nombre, apellido,
				cedula, direccion, usuario, contrasenia);

		ObservableList<Vendedor> listaVendedores = FXCollections.observableArrayList(vendedoresEncontrados);

		colNombre.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nombre"));
		colApellido.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("apellido"));

		tblVendedores.setItems(listaVendedores);
	}

	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String usuario,
			String contraseña) throws VendedorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminarVendedor(String cedula) throws VendedorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarVendedorExistente(String cedula) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vendedor obtenerVendedor(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vendedor> obtenerVendedores() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean login(String usuario, String contrasenia) throws UsuarioException {
		return loginViewController.login(usuario, contrasenia);
	}

	public Persona getUsuario() {
		return usuario;
	}

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

}
