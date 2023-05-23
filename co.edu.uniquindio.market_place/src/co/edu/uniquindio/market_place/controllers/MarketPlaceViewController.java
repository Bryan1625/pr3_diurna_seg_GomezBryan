package co.edu.uniquindio.market_place.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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

	Producto productoSeleccionadoPerfil;
	Vendedor vendedorSeleccionadoPerfil;
	Vendedor amigoSeleccionadoPerfil;

	String rutaImagenPerfil;

	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

	ObservableList<Producto> listaProductosPerfilData = FXCollections.observableArrayList();

	ObservableList<Vendedor> listaAmigosPerfilData = FXCollections.observableArrayList();

	ObservableList<Vendedor> listaAmigosVendedor1Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor1Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor1;
	Vendedor vendedorSeleccionadoVendedor1;
	ObservableList<Vendedor> listaAmigosVendedor2Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor2Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor2;
	Vendedor vendedorSeleccionadoVendedor2;
	ObservableList<Vendedor> listaAmigosVendedor3Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor3Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor3;
	Vendedor vendedorSeleccionadoVendedor3;
	ObservableList<Vendedor> listaAmigosVendedor4Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor4Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor4;
	Vendedor vendedorSeleccionadoVendedor4;
	ObservableList<Vendedor> listaAmigosVendedor5Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor5Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor5;
	Vendedor vendedorSeleccionadoVendedor5;
	ObservableList<Vendedor> listaAmigosVendedor6Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor6Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor6;
	Vendedor vendedorSeleccionadoVendedor6;
	ObservableList<Vendedor> listaAmigosVendedor7Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor7Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor7;
	Vendedor vendedorSeleccionadoVendedor7;
	ObservableList<Vendedor> listaAmigosVendedor8Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor8Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor8;
	Vendedor vendedorSeleccionadoVendedor8;
	ObservableList<Vendedor> listaAmigosVendedor9Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor9Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor9;
	Vendedor vendedorSeleccionadoVendedor9;
	ObservableList<Vendedor> listaAmigosVendedor10Data = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosVendedor10Data = FXCollections.observableArrayList();
	Producto productoSeleccionadoVendedor10;
	Vendedor vendedorSeleccionadoVendedor10;

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
	private ImageView imagenProductoVendedor1;

	@FXML
	private TextArea descripcionProductoVendedor1TextArea;

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
	private ImageView imagenProductoVendedor2;

	@FXML
	private TextArea descripcionProductoVendedor2TextArea;

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
	private ImageView imagenProductoVendedor3;

	@FXML
	private TextArea descripcionProductoVendedor3TextArea;

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
	@FXML
	private ImageView imagenProductoVendedor4;

	@FXML
	private TextArea descripcionProductoVendedor4TextArea;

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
	private ImageView imagenProductoVendedor5;

	@FXML
	private TextArea descripcionProductoVendedor5TextArea;

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
	private ImageView imagenProductoVendedor6;

	@FXML
	private TextArea descripcionProductoVendedor6TextArea;

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
	private ImageView imagenProductoVendedor7;

	@FXML
	private TextArea descripcionProductoVendedor7TextArea;

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
	private ImageView imagenProductoVendedor8;

	@FXML
	private TextArea descripcionProductoVendedor8TextArea;

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
	private ImageView imagenProductoVendedor9;

	@FXML
	private TextArea descripcionProductoVendedor9TextArea;

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

	@FXML
	private ImageView imagenProductoVendedor10;

	@FXML
	private TextArea descripcionProductoVendedor10TextArea;

	public MarketPlaceViewController() {

	}

	@Override
	public Administrador crearAdministrador(String nombre, String apellido, String cedula, String direccion,
			String usuario, String contrasenia) throws AdministradorException {

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
		inicializarVendedoresView();
	}

	private void inicializarAdministradorView() {
		this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		tblVendedores.setItems(getListaVendedoresData());

		tblVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			vendedorSeleccionado = newSelection;
			mostrarInformacionvendedor(vendedorSeleccionado);
		});
	}

	private void mostrarInformacionProductoPerfil(Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			nombreProductoPerfilTextField.setText(productoSeleccionado.getNombre());
			precioProductoPerfilTextField.setText("" + productoSeleccionado.getPrecio());
			categoriaProductoPerfilTextField.setText(productoSeleccionado.getCategoria());
			estadoProductoPerfilComboBox.setValue(productoSeleccionado.getEstado());
			descripcionProductoPerfilTextArea.setText(productoSeleccionado.getDescripcion());
			imagenProductoImageView.setImage(new Image(productoSeleccionado.getRutaImagen()));
			String comentarios = getComentariosProducto(productoSeleccionado);
			if (comentarios != null && comentarios != "") {
				comentariosPerfilTextArea.setText(comentarios);
			}else{
				comentariosPerfilTextArea.clear();
			}
		}
	}

	private void mostrarInformacionAmigosPerfil(Vendedor amigoSeleccionado) {
		if (amigoSeleccionado != null) {
			nombreAmigoColumn.setText(amigoSeleccionado.getNombre());
			cedulaAmigoColumn.setText(amigoSeleccionado.getCedula());
			usuarioAmigoColumn.setText(amigoSeleccionado.getUsuario());
		}
	}

	private void mostrarInformacionVendedoresPerfil(Vendedor vendedorSeleccionado) {
		if (vendedorSeleccionado != null) {
			nombreVendedorPerfilTextField.setText(vendedorSeleccionado.getNombre());
			cedulaVendedorPerfilTextField.setText(vendedorSeleccionado.getCedula());
			usuarioVendedorPerfilTextField.setText(vendedorSeleccionado.getUsuario());
		}
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

	public String getComentariosProducto(Producto producto) {
		String comentarios = vendedorViewController.obtenerComentarios(producto);
		if (comentarios != "") {
			return comentarios;
		} else {
			return null;
		}
	}

	private void inicializarPerfilView() {
		nombreProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		fechaProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		estadoProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
		precioProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
		categoriaProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		likesProductosPerfilColumn.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

		productosPerfilTable.setItems(getListaProductosPerfilData());

		productosPerfilTable.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					productoSeleccionadoPerfil = newSelection;
					mostrarInformacionProductoPerfil(productoSeleccionadoPerfil);
				});

		nombreVendedorColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		cedulaVendedorColumn.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		usuarioVendedorColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

		vendedoresPerfilTable.setItems(tblVendedores.getItems());

		vendedoresPerfilTable.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					vendedorSeleccionadoPerfil = newSelection;
					mostrarInformacionVendedoresPerfil(vendedorSeleccionadoPerfil);
				});

		nombreAmigoColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		cedulaAmigoColumn.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		usuarioAmigoColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		amigosPerfilTable.setItems(getListaAmigosPerfilData());

		amigosPerfilTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			amigoSeleccionadoPerfil = newSelection;
			mostrarInformacionAmigosPerfil(amigoSeleccionadoPerfil);
		});

		if (vendedorLogin.getAmigos().size() > 0) {
			inicializarVendedor1();
		}
		if(vendedorLogin.getAmigos().size() > 1){
			inicializarVendedor2();
		}
		if(vendedorLogin.getAmigos().size() > 2){
			inicializarVendedor3();
		}
		if(vendedorLogin.getAmigos().size() > 3){
			inicializarVendedor4();
		}
		if(vendedorLogin.getAmigos().size() > 4){
			inicializarVendedor5();
		}
		if(vendedorLogin.getAmigos().size() > 5){
			inicializarVendedor6();
		}
		if(vendedorLogin.getAmigos().size() > 6){
			inicializarVendedor7();
		}
		if(vendedorLogin.getAmigos().size() > 7){
			inicializarVendedor8();
		}
		if(vendedorLogin.getAmigos().size() > 8){
			inicializarVendedor9();
		}
		if(vendedorLogin.getAmigos().size() > 9){
			inicializarVendedor10();
		}
	}

	public void inicializarVendedor1() {
		if (vendedorLogin.getAmigos().get(0) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(0);
			nombreVendedor1TextField.setText(vendedor.getNombre());
			usuarioVendedor1TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor1Table.setItems(getListaAmigosVendedor1Data());

			amigosVendedor1Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor1 = newSelection;
					});

			nombreProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor1Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor1Table.setItems(getProductosVendedor1());

			productosVendedor1Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor1 = newSelection;
						mostrarInformacionProductoVendedor1(productoSeleccionadoVendedor1);
					});
		}
	}
	
	public void inicializarVendedor2() {
		if (vendedorLogin.getAmigos().get(1) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(1);
			nombreVendedor2TextField.setText(vendedor.getNombre());
			usuarioVendedor2TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor2Table.setItems(getListaAmigosVendedor2Data());

			amigosVendedor2Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor2 = newSelection;
					});

			nombreProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor2Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor2Table.setItems(getProductosVendedor2());

			productosVendedor2Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor2 = newSelection;
						mostrarInformacionProductoVendedor2(productoSeleccionadoVendedor2);
					});
		}
	}
	
	public void inicializarVendedor3() {
		if (vendedorLogin.getAmigos().size() >= 3 && vendedorLogin.getAmigos().get(2) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(2);
			nombreVendedor3TextField.setText(vendedor.getNombre());
			usuarioVendedor3TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor3Table.setItems(getListaAmigosVendedor3Data());

			amigosVendedor3Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor3 = newSelection;
					});

			nombreProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor3Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor3Table.setItems(getProductosVendedor3());

			productosVendedor3Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor3 = newSelection;
						mostrarInformacionProductoVendedor3(productoSeleccionadoVendedor3);
					});
		}
	}

	public void inicializarVendedor4() {
		if (vendedorLogin.getAmigos().size() >= 4 && vendedorLogin.getAmigos().get(3) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(3);
			nombreVendedor4TextField.setText(vendedor.getNombre());
			usuarioVendedor4TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor4Table.setItems(getListaAmigosVendedor4Data());

			amigosVendedor4Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor4 = newSelection;
					});

			nombreProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor4Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor4Table.setItems(getProductosVendedor4());

			productosVendedor4Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor4 = newSelection;
						mostrarInformacionProductoVendedor4(productoSeleccionadoVendedor4);
					});
		}
	}
	
	public void inicializarVendedor5() {
		if (vendedorLogin.getAmigos().size() >= 5 && vendedorLogin.getAmigos().get(4) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(4);
			nombreVendedor5TextField.setText(vendedor.getNombre());
			usuarioVendedor5TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor5Table.setItems(getListaAmigosVendedor5Data());

			amigosVendedor5Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor5 = newSelection;
					});

			nombreProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor5Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor5Table.setItems(getProductosVendedor5());

			productosVendedor5Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor5 = newSelection;
						mostrarInformacionProductoVendedor5(productoSeleccionadoVendedor5);
					});
		}
	}


	public void inicializarVendedor6() {
		if (vendedorLogin.getAmigos().size() >= 6 && vendedorLogin.getAmigos().get(5) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(5);
			nombreVendedor6TextField.setText(vendedor.getNombre());
			usuarioVendedor6TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor6Table.setItems(getListaAmigosVendedor6Data());

			amigosVendedor6Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor6 = newSelection;
					});

			nombreProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor6Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor6Table.setItems(getProductosVendedor6());

			productosVendedor6Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor6 = newSelection;
						mostrarInformacionProductoVendedor6(productoSeleccionadoVendedor6);
					});
		}
	}
	
	public void inicializarVendedor7() {
		if (vendedorLogin.getAmigos().size() >= 7 && vendedorLogin.getAmigos().get(6) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(6);
			nombreVendedor7TextField.setText(vendedor.getNombre());
			usuarioVendedor7TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor7Table.setItems(getListaAmigosVendedor7Data());

			amigosVendedor7Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor7 = newSelection;
					});

			nombreProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor7Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor7Table.setItems(getProductosVendedor7());

			productosVendedor7Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor7 = newSelection;
						mostrarInformacionProductoVendedor7(productoSeleccionadoVendedor7);
					});
		}
	}
	
	public void inicializarVendedor8() {
		if (vendedorLogin.getAmigos().size() >= 8 && vendedorLogin.getAmigos().get(7) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(7);
			nombreVendedor8TextField.setText(vendedor.getNombre());
			usuarioVendedor8TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor8Table.setItems(getListaAmigosVendedor8Data());

			amigosVendedor8Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor8 = newSelection;
					});

			nombreProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor8Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor8Table.setItems(getProductosVendedor8());

			productosVendedor8Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor8 = newSelection;
						mostrarInformacionProductoVendedor8(productoSeleccionadoVendedor8);
					});
		}
	}

	public void inicializarVendedor9() {
		if (vendedorLogin.getAmigos().size() >= 9 && vendedorLogin.getAmigos().get(8) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(8);
			nombreVendedor9TextField.setText(vendedor.getNombre());
			usuarioVendedor9TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor9Table.setItems(getListaAmigosVendedor9Data());

			amigosVendedor9Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor9 = newSelection;
					});

			nombreProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor9Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor9Table.setItems(getProductosVendedor9());

			productosVendedor9Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor9 = newSelection;
						mostrarInformacionProductoVendedor9(productoSeleccionadoVendedor9);
					});
		}
	}

	public void inicializarVendedor10() {
		if (vendedorLogin.getAmigos().size() >= 10 && vendedorLogin.getAmigos().get(9) != null) {
			Vendedor vendedor = vendedorLogin.getAmigos().get(9);
			nombreVendedor10TextField.setText(vendedor.getNombre());
			usuarioVendedor10TextField.setText(vendedor.getUsuario());

			nombreAmigosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			cedulaAmigosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			usuarioAmigosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("usuario"));
			amigosVendedor10Table.setItems(getListaAmigosVendedor10Data());

			amigosVendedor10Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						vendedorSeleccionadoVendedor10 = newSelection;
					});

			nombreProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			fechaProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			estadoProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("estado"));
			precioProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("precio"));
			categoriaProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			likesProductosVendedor10Column.setCellValueFactory(new PropertyValueFactory<>("numeroLikes"));

			productosVendedor10Table.setItems(getProductosVendedor10());

			productosVendedor10Table.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						productoSeleccionadoVendedor10 = newSelection;
						mostrarInformacionProductoVendedor10(productoSeleccionadoVendedor10);
					});
		}
	}

	private void mostrarInformacionProductoVendedor2(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor2TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor2TextArea.clear();
		}
		imagenProductoVendedor2.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor2TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor3(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor3TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor3TextArea.clear();
		}
		imagenProductoVendedor3.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor3TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor4(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor4TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor4TextArea.clear();
		}
		imagenProductoVendedor4.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor4TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor5(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor5TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor5TextArea.clear();
		}
		imagenProductoVendedor5.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor5TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor6(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor6TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor6TextArea.clear();
		}
		imagenProductoVendedor6.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor6TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor7(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor7TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor7TextArea.clear();
		}
		imagenProductoVendedor7.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor7TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor8(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor8TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor8TextArea.clear();
		}
		imagenProductoVendedor8.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor8TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor9(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor9TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor9TextArea.clear();
		}
		imagenProductoVendedor9.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor9TextArea.setText(productoSeleccionado.getDescripcion());
	}
	
	private void mostrarInformacionProductoVendedor10(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor10TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor10TextArea.clear();
		}
		imagenProductoVendedor10.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor10TextArea.setText(productoSeleccionado.getDescripcion());
	}

	private ObservableList<Producto> getProductosVendedor2() {
		listaProductosVendedor2Data.addAll(vendedorLogin.getAmigos().get(1).getProductos());
		return listaProductosVendedor2Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor2Data() {
		listaAmigosVendedor2Data.addAll(vendedorLogin.getAmigos().get(1).getAmigos());
		return listaAmigosVendedor2Data;
	}
	
	private ObservableList<Producto> getProductosVendedor3() {
		listaProductosVendedor3Data.addAll(vendedorLogin.getAmigos().get(2).getProductos());
		return listaProductosVendedor3Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor3Data() {
		listaAmigosVendedor3Data.addAll(vendedorLogin.getAmigos().get(2).getAmigos());
		return listaAmigosVendedor3Data;
	}
	
	private ObservableList<Producto> getProductosVendedor4() {
		listaProductosVendedor4Data.addAll(vendedorLogin.getAmigos().get(3).getProductos());
		return listaProductosVendedor4Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor4Data() {
		listaAmigosVendedor4Data.addAll(vendedorLogin.getAmigos().get(3).getAmigos());
		return listaAmigosVendedor4Data;
	}
	
	private ObservableList<Producto> getProductosVendedor5() {
		listaProductosVendedor5Data.addAll(vendedorLogin.getAmigos().get(4).getProductos());
		return listaProductosVendedor5Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor5Data() {
		listaAmigosVendedor5Data.addAll(vendedorLogin.getAmigos().get(4).getAmigos());
		return listaAmigosVendedor5Data;
	}
	
	private ObservableList<Producto> getProductosVendedor6() {
		listaProductosVendedor6Data.addAll(vendedorLogin.getAmigos().get(5).getProductos());
		return listaProductosVendedor6Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor6Data() {
		listaAmigosVendedor2Data.addAll(vendedorLogin.getAmigos().get(5).getAmigos());
		return listaAmigosVendedor2Data;
	}
	
	private ObservableList<Producto> getProductosVendedor7() {
		listaProductosVendedor7Data.addAll(vendedorLogin.getAmigos().get(6).getProductos());
		return listaProductosVendedor7Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor7Data() {
		listaAmigosVendedor7Data.addAll(vendedorLogin.getAmigos().get(6).getAmigos());
		return listaAmigosVendedor7Data;
	}
	
	private ObservableList<Producto> getProductosVendedor8() {
		listaProductosVendedor8Data.addAll(vendedorLogin.getAmigos().get(7).getProductos());
		return listaProductosVendedor8Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor8Data() {
		listaAmigosVendedor8Data.addAll(vendedorLogin.getAmigos().get(7).getAmigos());
		return listaAmigosVendedor8Data;
	}
	
	private ObservableList<Producto> getProductosVendedor9() {
		listaProductosVendedor9Data.addAll(vendedorLogin.getAmigos().get(8).getProductos());
		return listaProductosVendedor9Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor9Data() {
		listaAmigosVendedor9Data.addAll(vendedorLogin.getAmigos().get(8).getAmigos());
		return listaAmigosVendedor9Data;
	}
	
	private ObservableList<Producto> getProductosVendedor10() {
		listaProductosVendedor10Data.addAll(vendedorLogin.getAmigos().get(9).getProductos());
		return listaProductosVendedor10Data;
	}

	private ObservableList<Vendedor> getListaAmigosVendedor10Data() {
		listaAmigosVendedor10Data.addAll(vendedorLogin.getAmigos().get(9).getAmigos());
		return listaAmigosVendedor10Data;
	}
	

	public void mostrarInformacionProductoVendedor1(Producto productoSeleccionado) {
		String comentarios = getComentariosProducto(productoSeleccionado);
		if (comentarios != "") {
			comentariosVendedor1TextArea.setText(productoSeleccionado.obtenerComentarios());
		}else{
			comentariosVendedor1TextArea.clear();
		}
		imagenProductoVendedor1.setImage(new Image(productoSeleccionado.getRutaImagen()));
		descripcionProductoVendedor1TextArea.setText(productoSeleccionado.getDescripcion());
	}

	private ObservableList<Vendedor> getListaAmigosVendedor1Data() {
		listaAmigosVendedor1Data.addAll(vendedorLogin.getAmigos().get(0).getAmigos());
		return listaAmigosVendedor1Data;
	}

	public ObservableList<Producto> getProductosVendedor1() {
		listaProductosVendedor1Data.addAll(vendedorLogin.getAmigos().get(0).getProductos());
		return listaProductosVendedor1Data;
	}

	private void inicializarVendedoresView() {

		estadoProductoPerfilComboBox.getItems().addAll(Estado.values());

	}

	public void setAplicacion(MainApp aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ObservableList<Vendedor> getListaVendedoresData() {
		listaVendedoresData.addAll(administradorViewController.obtenerVendedores());
		return listaVendedoresData;
	}

	public ObservableList<Producto> getListaProductosPerfilData() {
		listaProductosPerfilData.addAll(vendedorLogin.getProductos());
		return listaProductosPerfilData;
	}

	public ObservableList<Vendedor> getListaAmigosPerfilData() {
		listaAmigosPerfilData.addAll(vendedorLogin.getAmigos());
		return listaAmigosPerfilData;
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
	void agregarVendedorAction() {

		try {
			crearVendedor();
		} catch (VendedorException | IOException e) {
			e.printStackTrace();
		}
	}

	private void crearVendedor() throws VendedorException, IOException {

		String nombre = txtFieldNombreVendedorAdmin.getText();
		String apellido = txtFieldApellidoVendedorAdmin.getText();
		String cedula = txtFieldCedulaVendedorAdmin.getText();
		String direccion = txtFieldDireccionVendedorAdmin.getText();
		String usuario = txtFieldUsuarioVendedorAdmin.getText();
		String contrasenia = txtFieldContraseniaVendedorAdmin.getText();

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

	public void setVendedorLogin(Vendedor vendedor) {
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
				inicializarPerfilView();
				vendedorViewController.setVendedor(vendedorLogin);
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

		String nombre = txtFieldNombreVendedorAdmin.getText();
		String apellido = txtFieldApellidoVendedorAdmin.getText();
		String cedula = txtFieldCedulaVendedorAdmin.getText();
		String direccion = txtFieldDireccionVendedorAdmin.getText();
		String usuario = txtFieldUsuarioVendedorAdmin.getText();
		String contrasenia = txtFieldContraseniaVendedorAdmin.getText();
		boolean vendedorActualizado = false;

		if (vendedorSeleccionado != null) {

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

		return null;
	}

	@Override
	public Boolean eliminarVendedor(String cedula) throws VendedorException {

		return null;
	}

	@Override
	public boolean verificarVendedorExistente(String cedula) {

		return false;
	}

	@Override
	public Vendedor obtenerVendedor(String cedula) {

		return null;
	}

	@Override
	public ArrayList<Vendedor> obtenerVendedores() {

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
		String nombre = nombreProductoPerfilTextField.getText().trim();
		String precioText = precioProductoPerfilTextField.getText().trim();
		String descripcion = descripcionProductoPerfilTextArea.getText().trim();
		String imagen = rutaImagenPerfil;
		String categoria = categoriaProductoPerfilTextField.getText().trim();
		Estado estado = estadoProductoPerfilComboBox.getValue();
		if (!nombre.isEmpty() && !precioText.isEmpty() && !descripcion.isEmpty() && imagen != null
				&& !categoria.isEmpty() && estado != null) {
			try {
				Double precio = Double.parseDouble(precioText);
				Producto p = new Producto(nombre, precio, descripcion, imagen, categoria, estado);
				vendedorViewController.publicarProducto(p);
				listaProductosPerfilData.add(p);
				limpiarCamposProductoPerfil();
			} catch (NumberFormatException e) {
				mostrarMensaje("Error", "Formato de numero incorrecto", "El numero ingresado no es un numero valido",
						AlertType.ERROR);
			} catch (PublicacionException e) {
				e.printStackTrace();
			}
		} else {
			mostrarMensaje("No se pudo publicar el producto", "Campo vacio", "Todo los campos son obligatorios",
					AlertType.ERROR);
		}
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
		if (mostrarMensajeConfirmacion("¿Esta seguro que desea eliminar el producto seleccionado?")) {
			eliminarProducto();
		}
	}

	public void eliminarProducto() {
		boolean productoEliminado = false;
		Producto productoSeleccionado = productosPerfilTable.getSelectionModel().getSelectedItem();

		if (productoSeleccionado != null) {
			if (mostrarMensajeConfirmacion("¿Estás seguro de eliminar el producto?")) {
				try {
					productoEliminado = vendedorViewController.eliminarProducto(productoSeleccionado);

					if (productoEliminado) {
						listaProductosPerfilData.remove(productoSeleccionado);
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
		rutaImagenPerfil = seleccionarImagen();
	}

	public String seleccionarImagen() {
		String ruta = "";
		// Crear un objeto FileChooser
		FileChooser fileChooser = new FileChooser();

		// Configurar el filtro de extensión para mostrar solo archivos de
		// imagen
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
			try {
				ruta = archivoSeleccionado.toURI().toURL().toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ruta;
	}

	@FXML
	public void agregarAmigoClick() {
		Vendedor amigoSeleccionado = vendedoresPerfilTable.getSelectionModel().getSelectedItem();

		if (amigoSeleccionado != null) {
			if (!amigoSeleccionado.equals(vendedorLogin)) {
				if (vendedorViewController.agregarAmigo(vendedorLogin, amigoSeleccionado)) {
					listaAmigosPerfilData.add(amigoSeleccionado);
				} else {
					mostrarMensaje("error", "no se pudo agregar amigo",
							"El vendedor seleccionado ya está en tu lista de amigos", AlertType.ERROR);
				}
			} else {
				mostrarMensaje("Error", "No se pudo agregar amigo", "No puedes agregarte a ti mismo como amigo.",
						AlertType.ERROR);
			}
		} else {
			mostrarMensaje("Error", "No se pudo agregar amigo", "No has seleccionado ningún vendedor.",
					AlertType.ERROR);
		}
	}

	@FXML
	public void eliminarAmigoClick() {
		if (mostrarMensajeConfirmacion("¿Seguro que quiere eliminar el amigo seleccionado?")) {
			eliminarAmigoPerfil();
		}
	}

	public void eliminarAmigoPerfil() {
		if (vendedorViewController.eliminarAmigo(amigosPerfilTable.getSelectionModel().getSelectedItem())) {
			mostrarMensaje("amigo eliminado", "accion eliminar amigo", "se ha eliminado el amigo con exito",
					AlertType.INFORMATION);
		}
		listaAmigosPerfilData.remove(amigosPerfilTable.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void descargarEstadisticasPerfilClick() {

	}

	@FXML
	public void buscarVendedorPerfilClick() {
		String nombre = nombreVendedorPerfilTextField.getText().trim();
		String cedula = cedulaVendedorPerfilTextField.getText().trim();
		String usuario = usuarioVendedorPerfilTextField.getText().trim();

		if (!nombre.isEmpty() || !cedula.isEmpty() || !usuario.isEmpty()) {
			ObservableList<Vendedor> vendedoresEncontrados = FXCollections.observableArrayList();
			vendedoresEncontrados.setAll(buscarVendedorPerfil(nombre, cedula, usuario));
			if (!vendedoresEncontrados.isEmpty()) {
				vendedoresPerfilTable.setItems(vendedoresEncontrados);
			} else {
				mostrarMensaje("Información", "Búsqueda sin resultados",
						"No se encontró ningún vendedor con los criterios de búsqueda especificados.",
						AlertType.INFORMATION);
			}
		} else {
			vendedoresPerfilTable.setItems(listaVendedoresData);
			mostrarMensaje("Error", "Búsqueda incompleta",
					"Debes completar al menos uno de los campos para realizar la búsqueda.", AlertType.ERROR);
		}
	}

	public ArrayList<Vendedor> buscarVendedorPerfil(String nombre, String cedula, String usuario) {
		return vendedorViewController.buscarVendedorPerfil(nombre, cedula, usuario);
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
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor1Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor1Click() {
		vendedorViewController.agregarComentario(productosVendedor1Table.getSelectionModel().getSelectedItem(),
				comentarVendedor1TextArea.getText());
		actualizarComentariosVendedor1(productosVendedor1Table.getSelectionModel().getSelectedItem());
	}
	
	public void actualizarComentariosVendedor1(Producto producto){
		comentariosVendedor1TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor2(Producto producto){
		comentariosVendedor2TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor3(Producto producto){
		comentariosVendedor3TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor4(Producto producto){
		comentariosVendedor4TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor5(Producto producto){
		comentariosVendedor5TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor6(Producto producto){
		comentariosVendedor6TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor7(Producto producto){
		comentariosVendedor7TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor8(Producto producto){
		comentariosVendedor8TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor9(Producto producto){
		comentariosVendedor9TextArea.setText(producto.obtenerComentarios());
	}
	public void actualizarComentariosVendedor10(Producto producto){
		comentariosVendedor10TextArea.setText(producto.obtenerComentarios());
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
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor2Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor2Click() {
		vendedorViewController.agregarComentario(productosVendedor2Table.getSelectionModel().getSelectedItem(),
				comentarVendedor2TextArea.getText());
		actualizarComentariosVendedor2(productosVendedor2Table.getSelectionModel().getSelectedItem());
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
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor3Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor3Click() {
		vendedorViewController.agregarComentario(productosVendedor3Table.getSelectionModel().getSelectedItem(),
				comentarVendedor3TextArea.getText());
		actualizarComentariosVendedor3(productosVendedor3Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void likeVendedor4Click() {
		vendedorViewController.agregarLike(productosVendedor4Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor4Click() {

	}

	@FXML
	private void agregarAmigoVendedor4Click() {
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor4Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor4Click() {
		vendedorViewController.agregarComentario(productosVendedor4Table.getSelectionModel().getSelectedItem(),
				comentarVendedor4TextArea.getText());
		actualizarComentariosVendedor4(productosVendedor4Table.getSelectionModel().getSelectedItem());
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
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor5Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor5Click() {
		vendedorViewController.agregarComentario(productosVendedor5Table.getSelectionModel().getSelectedItem(),
				comentarVendedor5TextArea.getText());
		actualizarComentariosVendedor5(productosVendedor5Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void likeVendedor6Click() {
		vendedorViewController.agregarLike(productosVendedor6Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor6Click() {

	}

	@FXML
	private void agregarAmigoVendedor6Click() {
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor6Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor6Click() {
		vendedorViewController.agregarComentario(productosVendedor6Table.getSelectionModel().getSelectedItem(),
				comentarVendedor6TextArea.getText());
		actualizarComentariosVendedor6(productosVendedor6Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void likeVendedor7Click() {
		vendedorViewController.agregarLike(productosVendedor7Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor7Click() {

	}

	@FXML
	private void agregarAmigoVendedor7Click() {
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor7Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor7Click() {
		vendedorViewController.agregarComentario(productosVendedor7Table.getSelectionModel().getSelectedItem(),
				comentarVendedor7TextArea.getText());
		actualizarComentariosVendedor7(productosVendedor7Table.getSelectionModel().getSelectedItem());
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
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor8Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor8Click() {
		vendedorViewController.agregarComentario(productosVendedor8Table.getSelectionModel().getSelectedItem(),
				comentarVendedor8TextArea.getText());
		actualizarComentariosVendedor8(productosVendedor8Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void likeVendedor9Click() {
		vendedorViewController.agregarLike(productosVendedor9Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor9Click() {

	}

	@FXML
	private void agregarAmigoVendedor9Click() {
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor9Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor9Click() {
		vendedorViewController.agregarComentario(productosVendedor9Table.getSelectionModel().getSelectedItem(),
				comentarVendedor9TextArea.getText());
		actualizarComentariosVendedor9(productosVendedor9Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void likeVendedor10Click() {
		vendedorViewController.agregarLike(productosVendedor10Table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void descargarEstadisticasVendedor10Click() {

	}

	@FXML
	private void agregarAmigoVendedor10Click() {
		if(vendedorViewController.agregarAmigo(vendedorLogin, amigosVendedor10Table.getSelectionModel().getSelectedItem())){
			mostrarMensaje("Éxito", "Amigo agregado", "El amigo ha sido agregado con éxito.", AlertType.INFORMATION);
		}
		mostrarMensaje("Error", "Error al agregar amigo", "No se pudo agregar el amigo.", AlertType.ERROR);
	}

	@FXML
	private void comentarVendedor10Click() {
		vendedorViewController.agregarComentario(productosVendedor10Table.getSelectionModel().getSelectedItem(),
				comentarVendedor10TextArea.getText());
		actualizarComentariosVendedor10(productosVendedor10Table.getSelectionModel().getSelectedItem());
	}

}
