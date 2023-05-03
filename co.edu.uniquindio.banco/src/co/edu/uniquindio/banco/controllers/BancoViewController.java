package co.edu.uniquindio.banco.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.Aplicacion;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BancoViewController {
	
	@FXML
	private TextField txtNumeroCuenta;
	@FXML
	private TextField txtSaldoCuenta;
	@FXML
	private Button btnAgregarCuenta;
	@FXML
	private Button btnEliminarCuenta;
	@FXML
	private Button btnNuevaCuenta;
	@FXML
	private Button btnActualizarCuenta;
	@FXML
	private TableView<Cuenta> tableCuentas;
	@FXML
	private TableColumn<String, String> clNumeroCuenta;
	@FXML
	private TableColumn<String, String> clSaldoCuenta;


	@FXML
	private Button btnEliminarCliente;
	@FXML
	private Button btnNuevoCliente;
	@FXML
	private Button btnVerCliente;
	@FXML
	private Button btnActualizarCliente;
	@FXML
	private TableView<Cliente> tblTablaClientes;
	@FXML
	private TableColumn<String, String> tblColCedulaCliente;
	@FXML
	private TableColumn<String, String> tblColNombreCliente;
	@FXML
	private TableColumn<String, String> tblColApellidoCliente;
	@FXML
	private TableColumn<String, String> tblColFechaNacimientoCliente;
	@FXML
	private TextField txtNombreCliente;
	@FXML
	private TextField txtCedulaCliente;
	@FXML
	private TextField txtApellidoCliente;
	@FXML
	private TextField txtFechaNacimientoCliente;
	@FXML
	private Button btnBuscarCliente;
	@FXML
	private Button btnIngresarCliente;
	Cliente clienteSeleccionado;
	Cuenta cuentaSeleccionada;
	ObservableList<Cliente> clientes;
	ObservableList<Cuenta> cuentas;
	Aplicacion aplicacion;
	ModelFactoryController modelFactoryController;
	CrudClienteViewController crudClienteViewController;
	CrudEmpleadoViewController crudEmpleadoViewController;
	CrudCuentaViewController crudCuentaViewController;

	ObservableList<Empleado> listaEmpleadosData = FXCollections.observableArrayList();
	Empleado empleadoSeleccionado;

	ObservableList<Cliente> listaClientesData = FXCollections.observableArrayList();
    ObservableList<Cuenta> listaCuentasData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNombreEmpleado;

	@FXML
	private Button btnNuevoEmpleado;

	@FXML
	private TableColumn<Empleado, String> clNombreEmpleado;

	@FXML
	private TableColumn<Empleado, String> clApellidoEmpleado;

	@FXML
	private Button btnAgregarEmpleado;

	@FXML
	private TextField txtApellidoEmpleado;

	@FXML
	private TextField txtCedulaEmpleado;

	@FXML
	private TableColumn<Empleado, String> clFechaNacimientoEmpleado;

	@FXML
	private Button btnActualizarEmpleado;

	@FXML
	private Button btnEliminarEmpleado;

	@FXML
	private TextField txtFechaNacimientoEmpleado;

	@FXML
	private TableView<Empleado> tableEmpleados;

	@FXML
	private TableColumn<Empleado, String> clCedulaEmpleado;

	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		crudClienteViewController = new CrudClienteViewController(modelFactoryController);
		crudEmpleadoViewController = new CrudEmpleadoViewController(modelFactoryController);
		crudCuentaViewController = new CrudCuentaViewController(modelFactoryController);

		inicializarEmpleadoView();
		inicializarClienteView();
		inicializarCuentaView();
	}

	private void inicializarCuentaView() {
		this.clNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
	    this.clSaldoCuenta.setCellValueFactory(new PropertyValueFactory<>("saldo"));

	    tableCuentas.getItems().clear();
	    tableCuentas.setItems(getListaCuentasData());

	    tableCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

	        cuentaSeleccionada = newSelection;

	        mostrarInformacionCuenta(cuentaSeleccionada);

	    });
	}

	private void mostrarInformacionCuenta(Cuenta cuentaSeleccionada) {
		if (cuentaSeleccionada != null) {
			txtNumeroCuenta.setText(cuentaSeleccionada.getNumeroCuenta());
			txtSaldoCuenta.setText(String.valueOf(cuentaSeleccionada.getSaldo()));
		}
	}


	private void inicializarEmpleadoView() {

		this.clNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.clApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.clCedulaEmpleado.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.clFechaNacimientoEmpleado.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

		tableEmpleados.getItems().clear();
		tableEmpleados.setItems(getListaEmpleadosData());

		tableEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			empleadoSeleccionado = newSelection;

			mostrarInformacionEmpleado(empleadoSeleccionado);

		});

	}

	private void inicializarClienteView() {

		this.tblColNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.tblColApellidoCliente.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.tblColCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.tblColFechaNacimientoCliente.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

		tblTablaClientes.getItems().clear();
		tblTablaClientes.setItems(getListaClientesData());

		tblTablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			clienteSeleccionado = newSelection;

			mostrarInformacionCliente(clienteSeleccionado);

		});

	}
	
	public ObservableList<Cuenta> getListaCuentasData() {
	    listaCuentasData.addAll(crudCuentaViewController.obtenerCuentas());
	    return listaCuentasData;
	}
	

	private ObservableList<Cliente> getListaClientesData() {
		listaClientesData.addAll(crudClienteViewController.obtenerClientes());
		return listaClientesData;
	}

	private void mostrarInformacionCliente(Cliente clienteSeleccionado2) {
		if (clienteSeleccionado != null) {
			txtNombreCliente.setText(clienteSeleccionado.getNombre());
			txtApellidoCliente.setText(clienteSeleccionado.getApellido());
			txtCedulaCliente.setText(clienteSeleccionado.getCedula());
			txtFechaNacimientoCliente.setText(clienteSeleccionado.getFechaNacimiento());
		}
	}

	private void mostrarInformacionEmpleado(Empleado empleadoSeleccionado) {
		if (empleadoSeleccionado != null) {
			txtNombreEmpleado.setText(empleadoSeleccionado.getNombre());
			txtApellidoEmpleado.setText(empleadoSeleccionado.getApellido());
			txtCedulaEmpleado.setText(empleadoSeleccionado.getCedula());
			txtFechaNacimientoEmpleado.setText(empleadoSeleccionado.getFechaNacimiento());
		}
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ObservableList<Empleado> getListaEmpleadosData() {
		listaEmpleadosData.addAll(crudEmpleadoViewController.obtenerEmpleados());
		return listaEmpleadosData;
	}

	@FXML
	void nuevoEmpleadoAction(ActionEvent event) {
		limpiarCamposEmpleado();
	}

	@FXML
	void agregarEmpleadoAction(ActionEvent event) {

		crearEmpleado();
	}

	private void crearEmpleado() {

		// 1. Capturar los datos
		String nombre = txtNombreEmpleado.getText();
		String apellido = txtApellidoEmpleado.getText();
		String cedula = txtCedulaEmpleado.getText();
		String fechaNacimiento = txtFechaNacimientoEmpleado.getText();

		// 2. Validar la información
		if (datosValidos(nombre, apellido, cedula, fechaNacimiento) == true) {
			Empleado empleado = null;
			empleado = crudEmpleadoViewController.crearEmpleado(nombre, apellido, cedula, fechaNacimiento);
			if (empleado != null) {
				listaEmpleadosData.add(empleado);
				mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito",
						AlertType.INFORMATION);
				limpiarCamposEmpleado();
			} else {
				mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito",
						AlertType.ERROR);
			}
		} else {
			mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos",
					AlertType.ERROR);
		}

	}

	@FXML
	void eliminarEmpleadoAction(ActionEvent event) {

		eliminarEmpleado();
	}

	private void eliminarEmpleado() {

		boolean empleadoEliminado = false;

		if (empleadoSeleccionado != null) {

			if (mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?") == true) {

				empleadoEliminado = crudEmpleadoViewController.eliminarEmpleado(empleadoSeleccionado.getCedula());

				if (empleadoEliminado == true) {
					listaEmpleadosData.remove(empleadoSeleccionado);
					empleadoSeleccionado = null;

					tableEmpleados.getSelectionModel().clearSelection();
					limpiarCamposEmpleado();
					mostrarMensaje("Notificación empleado", "Empleado eliminado",
							"El empleado se ha eliminado con éxito", AlertType.INFORMATION);
				} else {
					mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no se puede eliminar",
							AlertType.ERROR);
				}
			}
		} else {
			mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "Seleccionado un empleado de la lista",
					AlertType.WARNING);
		}
	}

	@FXML
	void actualizarEmpleadoAction(ActionEvent event) {
		actualizarEmpleado();
	}

	private void actualizarEmpleado() {

		// 1. Capturar los datos
		String nombre = txtNombreEmpleado.getText();
		String apellido = txtApellidoEmpleado.getText();
		String cedula = txtCedulaEmpleado.getText();
		String fechaNacimiento = txtFechaNacimientoEmpleado.getText();
		boolean clienteActualizado = false;

		// 2. verificar el empleado seleccionado
		if (empleadoSeleccionado != null) {

			// 3. Validar la información
			if (datosValidos(nombre, apellido, cedula, fechaNacimiento) == true) {

				clienteActualizado = crudEmpleadoViewController.actualizarEmpleado(empleadoSeleccionado.getCedula(),
						nombre, apellido, cedula, fechaNacimiento);

				if (clienteActualizado == true) {
					tableEmpleados.refresh();
					mostrarMensaje("Notificación empleado", "Empleado actualizado",
							"El empleado se ha actualizado con éxito", AlertType.INFORMATION);
					limpiarCamposEmpleado();
				} else {
					mostrarMensaje("Notificación empleado", "Empleado no actualizado",
							"El empleado no se ha actualizado con éxito", AlertType.INFORMATION);
				}
			} else {
				mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos",
						AlertType.ERROR);
			}

		}
	}

	private void limpiarCamposEmpleado() {
		txtNombreEmpleado.setText("");
		txtApellidoEmpleado.setText("");
		txtCedulaEmpleado.setText("");
		txtFechaNacimientoEmpleado.setText("");
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

	private boolean datosValidos(String nombre, String apellido, String cedula, String fechaNacimiento) {

		String mensaje = "";

		if (nombre == null || nombre.equals(""))
			mensaje += "El nombre es invalido \n";

		if (apellido == null || apellido.equals(""))
			mensaje += "El apellido es invalido \n";

		if (cedula == null || cedula.equals(""))
			mensaje += "El documento es invalido \n";

		if (fechaNacimiento == null || fechaNacimiento.equals(""))
			mensaje += "La fecha de nacimiento es invalida \n";

		if (mensaje.equals("")) {
			return true;
		} else {
			mostrarMensaje("Notificación", "Datos invalidos", mensaje, AlertType.WARNING);
			return false;
		}
	}


	@FXML
	public void onIngresarClienteClick(ActionEvent actionEvent) {

		String nombre = txtNombreCliente.getText();
		String apellido = txtApellidoCliente.getText();
		String cedula = txtCedulaCliente.getText();
		String fechaNacimiento = txtFechaNacimientoCliente.getText();

		if (datosValidos(nombre, apellido, cedula, fechaNacimiento) == true) {
			Cliente cliente = null;
			cliente = crudClienteViewController.crearCliente(nombre, apellido, cedula, fechaNacimiento);
			if (cliente != null) {
				listaClientesData.add(cliente);
				mostrarMensaje("Notificación cliente", "Cliente creado", "El cliente se ha creado con éxito",
						AlertType.INFORMATION);
				limpiarCamposEmpleado();
			} else {
				mostrarMensaje("Notificación cliente", "Cliente no creado", "El cliente no se ha creado con éxito",
						AlertType.ERROR);
			}
		} else {
			mostrarMensaje("Notificación cliente", "Cliente no creado", "Los datos ingresados son invalidos",
					AlertType.ERROR);
		}

	}

	@FXML
	private void eliminarCliente() {

		boolean clienteEliminado = false;

		if (clienteSeleccionado != null) {

			if (mostrarMensajeConfirmacion("¿Estas seguro de elmininar al cliente?") == true) {

				clienteEliminado = crudClienteViewController.eliminarCliente(clienteSeleccionado.getCedula());

				if (clienteEliminado == true) {
					listaClientesData.remove(clienteSeleccionado);
					clienteSeleccionado = null;

					tblTablaClientes.getSelectionModel().clearSelection();
					limpiarCamposCliente();
					mostrarMensaje("Notificación cliente", "Cliente eliminado", "El cliente se ha eliminado con éxito",
							AlertType.INFORMATION);
				} else {
					mostrarMensaje("Notificación cliente", "cliente no eliminado", "El cliente no se puede eliminar",
							AlertType.ERROR);
				}
			}
		} else {
			mostrarMensaje("Notificación cliente", "cliente no seleccionado", "Seleccionado un cliente de la lista",
					AlertType.WARNING);
		}
	}

	private void limpiarCamposCliente() {
		txtNombreCliente.setText("");
		txtApellidoCliente.setText("");
		txtCedulaCliente.setText("");
		txtFechaNacimientoCliente.setText("");
		
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		txtCedulaCliente.setText("" + clienteSeleccionado.getCedula());
		txtNombreCliente.setText(clienteSeleccionado.getNombre());
		txtApellidoCliente.setText(clienteSeleccionado.getApellido());
	}

	public ObservableList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ObservableList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@FXML
	void onEliminarClienteClick (ActionEvent event){
		eliminarCliente();
	}
	
	@FXML
	void onNuevoClienteClick(){
		limpiarCamposCliente();
	}
	
	@FXML
	void onActualizarClienteClick(){
		actualizarCliente();
	}

	private void actualizarCliente() {

		// 1. Capturar los datos
		String nombre = txtNombreCliente.getText();
		String apellido = txtApellidoCliente.getText();
		String cedula = txtCedulaCliente.getText();
		String fechaNacimiento = txtFechaNacimientoCliente.getText();
		boolean clienteActualizado = false;

		// 2. verificar el empleado seleccionado
		if (clienteSeleccionado != null) {

			// 3. Validar la información
			if (datosValidos(nombre, apellido, cedula, fechaNacimiento) == true) {

				clienteActualizado = crudClienteViewController.actualizarCliente(clienteSeleccionado.getCedula(),
						nombre, apellido, cedula, fechaNacimiento);

				if (clienteActualizado == true) {
					tblTablaClientes.refresh();
					mostrarMensaje("Notificación cliente", "Cliente actualizado",
							"El cliente se ha actualizado con éxito", AlertType.INFORMATION);
					limpiarCamposEmpleado();
				} else {
					mostrarMensaje("Notificación cliente", "Cliente no actualizado",
							"El cliente no se ha actualizado con éxito", AlertType.INFORMATION);
				}
			} else {
				mostrarMensaje("Notificación cliente", "Cliente no creado", "Los datos ingresados son invalidos",
						AlertType.ERROR);
			}

		}
	}
	
	@FXML
	void actualizarCuentaAction(ActionEvent event) {
		// 1. Capturar los datos
	    String numeroCuenta = txtNumeroCuenta.getText();
	    String saldo = txtSaldoCuenta.getText();
	    boolean cuentaActualizada = false;

	    // 2. verificar la cuenta seleccionada
	    if (cuentaSeleccionada != null) {

	        // 3. Validar la información
	        if (datosValidos(numeroCuenta, saldo)) {

	            cuentaActualizada = crudCuentaViewController.actualizarCuenta(cuentaSeleccionada.getNumeroCuenta(),
	                    numeroCuenta, Double.parseDouble(saldo));

	            if (cuentaActualizada) {
	                tableCuentas.refresh();
	                mostrarMensaje("Notificación cuenta", "Cuenta actualizada",
	                        "La cuenta se ha actualizado con éxito", AlertType.INFORMATION);
	                limpiarCamposCuenta();
	            } else {
	                mostrarMensaje("Notificación cuenta", "Cuenta no actualizada",
	                        "La cuenta no se ha actualizado con éxito", AlertType.INFORMATION);
	            }
	        } else {
	            mostrarMensaje("Notificación cuenta", "Cuenta no actualizada", "Los datos ingresados son invalidos",
	                    AlertType.ERROR);
	        }

	    }
	}

	private boolean datosValidos(String numeroCuenta, String saldo) {
	    String mensaje = "";

	    if (numeroCuenta == null || numeroCuenta.equals("")) {
	        mensaje += "El número de cuenta es inválido\n";
	    }

	    try {
	        double saldoDouble = Double.parseDouble(saldo);

	        if (saldoDouble <= 0) {
	            mensaje += "El saldo debe ser mayor a cero\n";
	        }
	    } catch (NumberFormatException e) {
	        mensaje += "El saldo debe ser un número válido\n";
	    }

	    if (mensaje.equals("")) {
	        return true;
	    } else {
	        mostrarMensaje("Notificación", "Datos inválidos", mensaje, AlertType.WARNING);
	        return false;
	    }
	}


	private void limpiarCamposCuenta() {
		txtNumeroCuenta.setText("");
		txtSaldoCuenta.setText("");

		
	}

	@FXML
	void nuevaCuentaAction(ActionEvent event) {
		limpiarCamposCuenta();
	}

	@FXML
	void agregarCuentaAction(ActionEvent event) {
	    String numeroCuenta = txtNumeroCuenta.getText();
	    String saldo = txtSaldoCuenta.getText();

	    if (datosValidos(numeroCuenta, saldo)) {
	        Cuenta cuenta = null;
	        cuenta = crudCuentaViewController.crearCuenta(numeroCuenta, Double.parseDouble(saldo));
	        if (cuenta != null) {
	            listaCuentasData.add(cuenta);
	            mostrarMensaje("Notificación cuenta", "Cuenta creada", "La cuenta se ha creado con éxito", AlertType.INFORMATION);
	            limpiarCamposCuenta();
	        } else {
	            mostrarMensaje("Notificación cuenta", "Cuenta no creada", "La cuenta no se ha creado con éxito", AlertType.ERROR);
	        }
	    } else {
	        mostrarMensaje("Notificación cuenta", "Cuenta no creada", "Los datos ingresados son inválidos", AlertType.ERROR);
	    }
	}


	@FXML
	void eliminarCuentaAction(ActionEvent event) {
	    Cuenta cuentaSeleccionada = tableCuentas.getSelectionModel().getSelectedItem();
	    if (cuentaSeleccionada == null) {
	        mostrarMensaje("Notificación", "Cuenta no seleccionada", "Por favor seleccione una cuenta a eliminar", AlertType.WARNING);
	        return;
	    }

	    boolean eliminado = crudCuentaViewController.eliminarCuenta(cuentaSeleccionada.getNumeroCuenta());
	    if (eliminado) {
	        listaCuentasData.remove(cuentaSeleccionada);
	        mostrarMensaje("Notificación", "Cuenta eliminada", "La cuenta ha sido eliminada con éxito", AlertType.INFORMATION);
	        limpiarCamposCuenta();
	    } else {
	        mostrarMensaje("Notificación", "Error al eliminar cuenta", "Ha ocurrido un error al eliminar la cuenta", AlertType.ERROR);
	    }
	}

}
