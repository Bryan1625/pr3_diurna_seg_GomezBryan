package co.edu.uniquindio.market_place.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.market_place.MainApp;
import co.edu.uniquindio.market_place.exceptions.AdministradorException;
import co.edu.uniquindio.market_place.exceptions.ProductoException;
import co.edu.uniquindio.market_place.exceptions.PublicacionException;
import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.exceptions.VendedorException;
import co.edu.uniquindio.market_place.model.Administrador;
import co.edu.uniquindio.market_place.model.Estado;
import co.edu.uniquindio.market_place.model.Persona;
import co.edu.uniquindio.market_place.model.Producto;
import co.edu.uniquindio.market_place.model.Vendedor;
import co.edu.uniquindio.market_place.services.IMarketPlaceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class MarketPlaceViewController implements IMarketPlaceService {

	MainApp aplicacion;
	ModelFactoryController modelFactoryController;
	VendedorViewController vendedorViewController;
	AdministradorViewController administradorViewController;
	LoginViewController loginViewController;

	private String sesion;
	private Persona usuario;
	private Vendedor vendedorLogin;

	Vendedor vendedorSeleccionado;

	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

	ObservableList<Vendedor> listaProductosData = FXCollections.observableArrayList();

	/*
	 * login
	 */

	@FXML
	private TextField txtFieldContrasenia;

	@FXML
	private TextField txtFieldUsuario;

	/*
	 * administrador
	 */

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

	/*
	 * perfil
	 */

	@FXML
	private TableView<Producto> productosPerfilTable;

	@FXML
	private TableColumn<Producto, String> nombreProductosPerfilColumn;

	@FXML
	private TableColumn<Producto, String> fechaProductosPerfilColumn;

	@FXML
	private TableColumn<Producto, String> estadoProductosPerfilColumn;

	@FXML
	private TableColumn<Producto, String> precioProductosPerfilColumn;

	@FXML
	private TableColumn<Producto, String> categoriaProductosPerfilColumn;

	@FXML
	private TableColumn<Producto, Integer> likesProductosPerfilColumn;

	@FXML
	private TextField nombreProductoPerfilTextField;

	@FXML
	private TextField precioProductoPerfilTextField;

	@FXML
	private TextField categoriaProductoPerfilTextField;

	@FXML
	private ComboBox<Estado> estadoProductoPerfilComboBox;

	@FXML
	private ImageView imagenProductoImageView;

	@FXML
	private TextArea descripcionProductoPerfilTextArea;

	@FXML
	private Button agregarProductoButton;

	@FXML
	private TableView<Vendedor> vendedoresPerfilTable;

	@FXML
	private TableColumn<Vendedor, String> nombreVendedorColumn;

	@FXML
	private TableColumn<Vendedor, String> cedulaVendedorColumn;

	@FXML
	private TableColumn<Vendedor, String> usuarioVendedorColumn;

	@FXML
	private TableView<Vendedor> amigosPerfilTable;

	@FXML
	private TableColumn<Vendedor, String> nombreAmigoColumn;

	@FXML
	private TableColumn<Vendedor, String> cedulaAmigoColumn;

	@FXML
	private TableColumn<Vendedor, String> usuarioAmigoColumn;

	@FXML
	private TextField nombreVendedorPerfilTextField;

	@FXML
	private TextField cedulaVendedorPerfilTextField;

	@FXML
	private TextField usuarioVendedorPerfilTextField;

	@FXML
	private Button agregarAmigoButton;

	@FXML
	private Button eliminarProductoButton;

	@FXML
	private Button subirImagenButton;

	@FXML
	private Button eliminarAmigoButton;

	@FXML
	private Button descargarEstadisticasPerfilBtn;

	@FXML
	private Button buscarVendedorPerfilBtn;

	@FXML
	private TextArea comentariosPerfilTextArea;

	/*
	 * vendedores
	 */

	@FXML
	private TableView<Producto> productosVendedor1Table;

	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor1Column;

	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor1Column;

	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor1Column;

	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor1Column;

	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor1Column;

	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor1Column;

	@FXML
	private TableView<Vendedor> amigosVendedor1Table;

	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor1Column;

	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor1Column;

	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor1Column;

	@FXML
	private TextArea comentarVendedor1TextArea;

	@FXML
	private TextArea comentariosVendedor1TextArea;

	@FXML
	private TextField nombreVendedor1TextField;

	@FXML
	private TextField usuarioVendedor1TextField;
	@FXML
	private TableView<Producto> productosVendedor2Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor2Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor2Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor2Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor2Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor2Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor2Column;

	@FXML
	private TableView<Vendedor> amigosVendedor2Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor2Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor2Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor2Column;

	@FXML
	private TextArea comentarVendedor2TextArea;
	@FXML
	private TextArea comentariosVendedor2TextArea;
	@FXML
	private TextField nombreVendedor2TextField;
	@FXML
	private TextField usuarioVendedor2TextField;

	@FXML
	private TableView<Producto> productosVendedor3Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor3Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor3Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor3Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor3Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor3Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor3Column;

	@FXML
	private TableView<Vendedor> amigosVendedor3Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor3Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor3Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor3Column;

	@FXML
	private TextArea comentarVendedor3TextArea;
	@FXML
	private TextArea comentariosVendedor3TextArea;
	@FXML
	private TextField nombreVendedor3TextField;
	@FXML
	private TextField usuarioVendedor3TextField;

	@FXML
	private TableView<Producto> productosVendedor4Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor4Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor4Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor4Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor4Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor4Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor4Column;

	@FXML
	private TableView<Vendedor> amigosVendedor4Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor4Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor4Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor4Column;

	@FXML
	private TextArea comentarVendedor4TextArea;
	@FXML
	private TextArea comentariosVendedor4TextArea;
	@FXML
	private TextField nombreVendedor4TextField;
	@FXML
	private TextField usuarioVendedor4TextField;

	// Vendedor 5
	@FXML
	private TableView<Producto> productosVendedor5Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor5Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor5Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor5Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor5Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor5Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor5Column;

	@FXML
	private TableView<Vendedor> amigosVendedor5Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor5Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor5Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor5Column;

	@FXML
	private TextArea comentarVendedor5TextArea;
	@FXML
	private TextArea comentariosVendedor5TextArea;
	@FXML
	private TextField nombreVendedor5TextField;
	@FXML
	private TextField usuarioVendedor5TextField;

	@FXML
	private TableView<Producto> productosVendedor6Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor6Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor6Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor6Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor6Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor6Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor6Column;

	@FXML
	private TableView<Vendedor> amigosVendedor6Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor6Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor6Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor6Column;

	@FXML
	private TextArea comentarVendedor6TextArea;
	@FXML
	private TextArea comentariosVendedor6TextArea;
	@FXML
	private TextField nombreVendedor6TextField;
	@FXML
	private TextField usuarioVendedor6TextField;

	@FXML
	private TableView<Producto> productosVendedor7Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor7Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor7Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor7Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor7Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor7Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor7Column;

	@FXML
	private TableView<Vendedor> amigosVendedor7Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor7Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor7Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor7Column;

	@FXML
	private TextArea comentarVendedor7TextArea;
	@FXML
	private TextArea comentariosVendedor7TextArea;
	@FXML
	private TextField nombreVendedor7TextField;
	@FXML
	private TextField usuarioVendedor7TextField;

	@FXML
	private TableView<Producto> productosVendedor8Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor8Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor8Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor8Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor8Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor8Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor8Column;

	@FXML
	private TableView<Vendedor> amigosVendedor8Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor8Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor8Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor8Column;

	@FXML
	private TextArea comentarVendedor8TextArea;
	@FXML
	private TextArea comentariosVendedor8TextArea;
	@FXML
	private TextField nombreVendedor8TextField;
	@FXML
	private TextField usuarioVendedor8TextField;

	@FXML
	private TableView<Producto> productosVendedor9Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor9Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor9Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor9Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor9Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor9Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor9Column;

	@FXML
	private TableView<Vendedor> amigosVendedor9Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor9Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor9Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor9Column;

	@FXML
	private TextArea comentarVendedor9TextArea;
	@FXML
	private TextArea comentariosVendedor9TextArea;
	@FXML
	private TextField nombreVendedor9TextField;
	@FXML
	private TextField usuarioVendedor9TextField;

	@FXML
	private TableView<Producto> productosVendedor10Table;
	@FXML
	private TableColumn<Producto, String> nombreProductosVendedor10Column;
	@FXML
	private TableColumn<Producto, LocalDateTime> fechaProductosVendedor10Column;
	@FXML
	private TableColumn<Producto, String> estadoProductosVendedor10Column;
	@FXML
	private TableColumn<Producto, Double> precioProductosVendedor10Column;
	@FXML
	private TableColumn<Producto, String> categoriaProductosVendedor10Column;
	@FXML
	private TableColumn<Producto, Integer> likesProductosVendedor10Column;

	@FXML
	private TableView<Vendedor> amigosVendedor10Table;
	@FXML
	private TableColumn<Vendedor, String> nombreAmigosVendedor10Column;
	@FXML
	private TableColumn<Vendedor, String> cedulaAmigosVendedor10Column;
	@FXML
	private TableColumn<Vendedor, String> usuarioAmigosVendedor10Column;

	@FXML
	private TextArea comentarVendedor10TextArea;
	@FXML
	private TextArea comentariosVendedor10TextArea;
	@FXML
	private TextField nombreVendedor10TextField;
	@FXML
	private TextField usuarioVendedor10TextField;

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
		inicializarPerfilView();
		inicializarVendedoresView();
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

	private void inicializarPerfilView() {

	}

	private void inicializarVendedoresView() {
		// TODO Auto-generated method stub
		estadoProductoPerfilComboBox.getItems().addAll(Estado.values());

	}

	public void setAplicacion(MainApp aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ObservableList<Vendedor> getListaVendedoresData() {
		listaVendedoresData.addAll(administradorViewController.obtenerVendedores());
		return listaVendedoresData;
	}

	@FXML
	void nuevoVendedorAction() {
		txtFieldNombreVendedorAdmin.setText("");
		txtFieldApellidoVendedorAdmin.setText("");
		txtFieldCedulaVendedorAdmin.setText("");
		txtFieldDireccionVendedorAdmin.setText("");
		txtFieldUsuarioVendedorAdmin.setText("");
		txtFieldContraseniaVendedorAdmin.setText("");
	}

	@FXML
	void agregarVendedorAction() throws VendedorException, IOException {

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
	
	public void setVendedorLogin(Vendedor vendedor){
		this.vendedorLogin = vendedor;
	}

	@FXML
	void eliminarVendedorAction() throws VendedorException, IOException {

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
			mostrarMensaje("Notificación login", "no se pudo iniciar sesion",
					"Los campos de texto no pueden estar vacios", AlertType.ERROR);
		} else {

			boolean resultado = login(usuario, contrasenia);

			if (resultado) {
				mostrarMensaje("Inicio de Sesion", "Se ha iniciado sesion", "Bienvenido " + usuario,
						AlertType.INFORMATION);
				this.setUsuario(modelFactoryController.getMarketPlace().getLogin().getPersona());
				setVendedorLogin(loginViewController.obtenerUsuarioLogin(usuario));
				txtFieldUsuario.setText("");
				txtFieldContrasenia.setText("");
				validarSesion();
			} else {
				mostrarMensaje("Notificación login", "no se pudo iniciar sesion",
						"El usuario o la contraseia son incorrectos", AlertType.ERROR);
			}
		}

	}

	public void validarSesion() {
		if (usuario instanceof Vendedor) {
			sesion = "vendedor";
		} else if (usuario instanceof Administrador) {
			sesion = "admin";
		} else {
			mostrarMensaje("Notificacion de sesion", "No se pudo validar la sesion",
					"hubo un error y la persona que inicio sesion no es reconocidad como vendedor no como administrador",
					AlertType.ERROR);
		}
	}

	@FXML
	void actualizarVendedorAction() {
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
	private void buscarVendedorAction() {
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

	@FXML
	public void agregarProductoClick() {
		agregarProducto();
	}

	public void agregarProducto() {

		String nombre = nombreProductoPerfilTextField.getText();
		Double precio = Double.parseDouble(precioProductoPerfilTextField.getText());
		String descripcion = descripcionProductoPerfilTextArea.getText();
		Image imagen = imagenProductoImageView.getImage();
		String categoria = categoriaProductoPerfilTextField.getText();
		Estado estado = estadoProductoPerfilComboBox.getValue();

		Producto p = new Producto(nombre, precio, descripcion, imagen, categoria, estado);

		try {
			vendedorViewController.publicarProducto(p);
		} catch (PublicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		limpiarCamposProductoPerfil();
	}

	public void limpiarCamposProductoPerfil() {
		nombreProductoPerfilTextField.clear();
		precioProductoPerfilTextField.clear();
		descripcionProductoPerfilTextArea.clear();
		imagenProductoImageView.setImage(null);
		categoriaProductoPerfilTextField.clear();
		estadoProductoPerfilComboBox.getSelectionModel().select(Estado.cancelado);
	}

	@FXML
	public void eliminarProductoClick() {
		eliminarProducto();
	}

	public void eliminarProducto() {
		boolean productoEliminado = false;
		Producto productoSeleccionado = productosPerfilTable.getSelectionModel().getSelectedItem();

		if (productoSeleccionado != null) {
			if (mostrarMensajeConfirmacion("¿Estás seguro de eliminar el producto?")) {
				try {
					productoEliminado = vendedorViewController.eliminarProducto(productoSeleccionado);

					if (productoEliminado) {
						listaProductosData.remove(productoSeleccionado);
						productoSeleccionado = null;

						productosPerfilTable.getSelectionModel().clearSelection();
						limpiarCamposProductoPerfil();
						mostrarMensaje("Notificación producto", "Producto eliminado",
								"El producto se ha eliminado con éxito", AlertType.INFORMATION);
					} else {
						mostrarMensaje("Notificación producto", "Producto no eliminado",
								"El producto no se puede eliminar", AlertType.ERROR);
					}
				} catch (ProductoException e) {
					// Manejar la excepción en caso de que ocurra
					mostrarMensaje("Error", "Error al eliminar producto",
							"Ocurrió un error al eliminar el producto: " + e.getMessage(), AlertType.ERROR);
				}
			}
		} else {
			mostrarMensaje("Notificación producto", "Producto no seleccionado", "Selecciona un producto de la lista",
					AlertType.WARNING);
		}
	}

	@FXML
	public void subirImagenClick() {
		seleccionarImagen();
	}

	public void seleccionarImagen() {
	    // Crear un objeto FileChooser
	    FileChooser fileChooser = new FileChooser();

	    // Configurar el filtro de extensión para mostrar solo archivos de imagen
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Archivos de imagen", "*.jpg", "*.jpeg", "*.png"));

	    // Mostrar el cuadro de diálogo de selección de archivo
	    Stage stage = new Stage();
	    File archivoSeleccionado = fileChooser.showOpenDialog(stage);

	    // Verificar si se seleccionó un archivo
	    if (archivoSeleccionado != null) {
	        // Convertir el archivo seleccionado a un objeto Path
	        Path rutaArchivo = Paths.get(archivoSeleccionado.toURI());

	        // Crear un objeto Image con la ruta del archivo seleccionado
	        Image imagen = new Image(rutaArchivo.toUri().toString());

	        // Asignar la imagen al ImageView
	        imagenProductoImageView.setImage(imagen);
	    }
	}


	@FXML
	public void agregarAmigoClick() {
		vendedorViewController.agregarAmigo(vendedorLogin, vendedoresPerfilTable.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void eliminarAmigoClick() {
		vendedorViewController.eliminarAmigo(amigosPerfilTable.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void descargarEstadisticasPerfilClick() {

	}

	@FXML
	public void buscarVendedorPerfilClick() {

	}

	@FXML
	private void likeVendedor1Click() {
		vendedorViewController.agregarLike(productosVendedor1Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor1Click() {

	}

	@FXML
	private void agregarAmigoVendedor1Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor1Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor1Click() {
		vendedorViewController.agregarComentario(productosVendedor1Table.getSelectionModel().getSelectedItem(),
				comentarVendedor1TextArea.getText());
	}

	@FXML
	private void likeVendedor2Click() {
		vendedorViewController.agregarLike(productosVendedor2Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor2Click() {

	}

	@FXML
	private void agregarAmigoVendedor2Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor2Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor2Click() {
		vendedorViewController.agregarComentario(productosVendedor2Table.getSelectionModel().getSelectedItem(),
				comentarVendedor2TextArea.getText());
	}

	@FXML
	private void likeVendedor3Click() {
		vendedorViewController.agregarLike(productosVendedor3Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor3Click() {

	}

	@FXML
	private void agregarAmigoVendedor3Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor3Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor3Click() {
		vendedorViewController.agregarComentario(productosVendedor3Table.getSelectionModel().getSelectedItem(),
				comentarVendedor3TextArea.getText());
	}

	// Métodos para el vendedor 4
	@FXML
	private void likeVendedor4Click() {
		vendedorViewController.agregarLike(productosVendedor4Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor4Click() {

	}

	@FXML
	private void agregarAmigoVendedor4Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor4Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor4Click() {
		vendedorViewController.agregarComentario(productosVendedor4Table.getSelectionModel().getSelectedItem(),
				comentarVendedor4TextArea.getText());
	}

	@FXML
	private void likeVendedor5Click() {
		vendedorViewController.agregarLike(productosVendedor5Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor5Click() {

	}

	@FXML
	private void agregarAmigoVendedor5Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor5Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor5Click() {
		vendedorViewController.agregarComentario(productosVendedor5Table.getSelectionModel().getSelectedItem(),
				comentarVendedor5TextArea.getText());
	}

	// Métodos para el vendedor 6
	@FXML
	private void likeVendedor6Click() {
		vendedorViewController.agregarLike(productosVendedor6Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor6Click() {

	}

	@FXML
	private void agregarAmigoVendedor6Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor6Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor6Click() {
		vendedorViewController.agregarComentario(productosVendedor6Table.getSelectionModel().getSelectedItem(),
				comentarVendedor6TextArea.getText());
	}

	// Métodos para el vendedor 7
	@FXML
	private void likeVendedor7Click() {
		vendedorViewController.agregarLike(productosVendedor7Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor7Click() {

	}

	@FXML
	private void agregarAmigoVendedor7Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor7Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor7Click() {
		vendedorViewController.agregarComentario(productosVendedor7Table.getSelectionModel().getSelectedItem(),
				comentarVendedor7TextArea.getText());
	}

	@FXML
	private void likeVendedor8Click() {
		vendedorViewController.agregarLike(productosVendedor8Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor8Click() {

	}

	@FXML
	private void agregarAmigoVendedor8Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor8Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor8Click() {
		vendedorViewController.agregarComentario(productosVendedor8Table.getSelectionModel().getSelectedItem(),
				comentarVendedor8TextArea.getText());
	}

	// Métodos para el vendedor 9
	@FXML
	private void likeVendedor9Click() {
		vendedorViewController.agregarLike(productosVendedor9Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor9Click() {

	}

	@FXML
	private void agregarAmigoVendedor9Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor9Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor9Click() {
		vendedorViewController.agregarComentario(productosVendedor9Table.getSelectionModel().getSelectedItem(),
				comentarVendedor9TextArea.getText());
	}

	// Métodos para el vendedor 10
	@FXML
	private void likeVendedor10Click() {
		vendedorViewController.agregarLike(productosVendedor10Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor10Click() {

	}

	@FXML
	private void agregarAmigoVendedor10Click() {
		vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor10Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void comentarVendedor10Click() {
		vendedorViewController.agregarComentario(productosVendedor10Table.getSelectionModel().getSelectedItem(),
				comentarVendedor10TextArea.getText());
	}

}
