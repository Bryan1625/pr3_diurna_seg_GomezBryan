package co.edu.uniquindio.banco.controllers;


import java.util.ArrayList;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.CuentaException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Cuenta;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.services.IModelFactoryService;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ModelFactoryController implements IModelFactoryService{

	Banco banco;
	
	
	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		System.out.println("invocación clase singleton");
		inicializarDatos();
	}

	private void inicializarDatos() {

		banco = new Banco();
		
		
		Cliente cliente = new Cliente();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("125454");
		cliente.setDireccion("Armenia");
		cliente.setCorreo("uni1@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("Pedro");
		cliente.setApellido("Perez");
		cliente.setCedula("77787");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni2@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		cliente = new Cliente();
		cliente.setNombre("Alberto");
		cliente.setApellido("Arias");
		cliente.setCedula("12555");
		cliente.setDireccion("Pererira");
		cliente.setCorreo("uni3@");
		cliente.setFechaNacimiento("12454");
		cliente.setTelefono("125444");

		banco.getListaClientes().add(cliente);
		
		
		Empleado empleado = new Empleado();
		empleado.setNombre("juan");
		empleado.setApellido("arias");
		empleado.setCedula("125454");
		empleado.setFechaNacimiento("12454");
		banco.getListaEmpleados().add(empleado);
		
		
		empleado = new Empleado();
		empleado.setNombre("Ana");
		empleado.setApellido("alzate");
		empleado.setCedula("125454");
		empleado.setFechaNacimiento("12454");
		banco.getListaEmpleados().add(empleado);
		
		empleado = new Empleado();
		empleado.setNombre("Pedro");
		empleado.setApellido("perez");
		empleado.setCedula("125454");
		empleado.setFechaNacimiento("12454");
		banco.getListaEmpleados().add(empleado);
		
		Cuenta cuenta1 = new Cuenta();
		Cuenta cuenta2 = new Cuenta();
		Cuenta cuenta3 = new Cuenta();

		cuenta1.setNumeroCuenta("1234");
		cuenta1.setSaldo(1000.0);
		cuenta2.setNumeroCuenta("54325");
		cuenta2.setSaldo(5000.0);
		cuenta3.setNumeroCuenta("34563");
		cuenta3.setSaldo(10000.0);
		banco.getListaCuentas().add(cuenta1);
		banco.getListaCuentas().add(cuenta2);
		banco.getListaCuentas().add(cuenta3);

		
		System.out.println("Banco inicializado "+banco );
		
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String fechaNacimiento) {
		Empleado empleado = null;
		try {
			empleado = getBanco().crearEmpleado(nombre, apellido, cedula, fechaNacimiento);
		} catch (EmpleadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleado;
	}
	
	public Cuenta crearCuenta(String numeroCuenta, double saldo) {
	    if (!numeroCuenta.isEmpty() && saldo >= 0) {
	        Cuenta cuenta = new Cuenta(numeroCuenta, saldo);
	        return cuenta;
	    } else {
	        return null;
	    }
	}

	@Override
	public Boolean eliminarEmpleado(String cedula) {
		boolean flagExiste = false;
		try {
			flagExiste = getBanco().eliminarEmpleado(cedula);
		} catch (EmpleadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flagExiste;
	}



	@Override
	public ArrayList<Empleado> obtenerEmpleados() {
//		getBanco().getListaEmpleados();
		return getBanco().obtenerEmpleados();
	}



	@Override
	public Cliente crearCliente(String nombre, String apellido, String cedula, String fechaNacimiento) {
		Cliente cliente = null;
		try {
			cliente = getBanco().crearCliente(nombre, apellido, cedula, fechaNacimiento);
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}

	public boolean eliminarCliente(String cedula){
		boolean flagExiste = false;
		try {
			flagExiste = getBanco().eliminarCliente(cedula);
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flagExiste;
	}

	public ArrayList<Cliente> obtenerClientes() {
		return getBanco().obtenerClientes();
	}

	public ArrayList<Cuenta> obtenerCuentas() {
		return getBanco().obtenerCuentas();
	}
	
	public boolean actualizarCuenta(String numeroCuentaActual, double saldo) {
	    if (numeroCuentaActual == null || numeroCuentaActual.equals("") || saldo < 0) {
	        mostrarMensaje("Error", "Datos inválidos", "Por favor ingrese datos válidos.", AlertType.ERROR);
	        return false;
	    } else {
	        Cuenta cuenta = obtenerCuenta(numeroCuentaActual);
	        if (cuenta == null) {
	            mostrarMensaje("Error", "Cuenta no encontrada", "No se encontró ninguna cuenta con el número de cuenta proporcionado.", AlertType.ERROR);
	            return false;
	        } else {
	            cuenta.setSaldo(saldo);
	            return true;
	        }
	    }
	}

	public boolean actualizarCliente(String cedulaActual, String nombre, String apellido, String cedula, String fechaNacimiento) {
	    if (cedulaActual == null || cedulaActual.equals("") || nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || cedula == null || cedula.equals("") || fechaNacimiento == null || fechaNacimiento.equals("")) {
	        mostrarMensaje("Error", "Datos inválidos", "Por favor ingrese datos válidos.", AlertType.ERROR);
	        return false;
	    } else {
	        Cliente cliente = obtenerCliente(cedulaActual);
	        if (cliente == null) {
	            mostrarMensaje("Error", "Cliente no encontrado", "No se encontró ningún cliente con la cédula proporcionada.", AlertType.ERROR);
	            return false;
	        } else {
	            cliente.setNombre(nombre);
	            cliente.setApellido(apellido);
	            cliente.setCedula(cedula);
	            cliente.setFechaNacimiento(fechaNacimiento);
	            return true;
	        }
	    }
	}

	@Override
	public boolean actualizarEmpleado(String cedulaActual, String nombre, String apellido, String cedula, String fechaNacimiento) {
	    if (cedulaActual == null || cedulaActual.equals("") || nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || cedula == null || cedula.equals("") || fechaNacimiento == null || fechaNacimiento.equals("")) {
	        mostrarMensaje("Error", "Datos inválidos", "Por favor ingrese datos válidos.", AlertType.ERROR);
	        return false;
	    } else {
	        Empleado empleado = obtenerEmpleado(cedulaActual);
	        if (empleado == null) {
	            mostrarMensaje("Error", "Empleado no encontrado", "No se encontró ningún empleado con la cédula proporcionada.", AlertType.ERROR);
	            return false;
	        } else {
	            empleado.setNombre(nombre);
	            empleado.setApellido(apellido);
	            empleado.setCedula(cedula);
	            empleado.setFechaNacimiento(fechaNacimiento);
	            return true;
	        }
	    }
	}
	
	
	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}
	
	public Empleado obtenerEmpleado(String cedula) {
	    for (Empleado empleado : getBanco().getListaEmpleados()) {
	        if (empleado.getCedula().equals(cedula)) {
	            return empleado;
	        }
	    }
	    return null; // Si no se encontró el empleado
	}

	public Cliente obtenerCliente(String cedula) {
	    for (Cliente cliente : getBanco().getListaClientes()) {
	        if (cliente.getCedula().equals(cedula)) {
	            return cliente;
	        }
	    }
	    return null; // Si no se encontró el cliente
	}

	public Cuenta obtenerCuenta(String numeroCuenta) {
	        for (Cuenta cuenta : getBanco().getListaCuentas()) {
	            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
	                return cuenta;
	            }
	        }
	    return null; // Si no se encontró la cuenta
	}

	public boolean eliminarCuenta(String numeroCuenta){
	    boolean flagExiste = false;
	        flagExiste = getBanco().eliminarCuenta(numeroCuenta);
	        if(flagExiste){
	            // Si se eliminó correctamente, removemos la cuenta de la lista
	            for (Cuenta cuenta : getBanco().getListaCuentas()) {
	                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
	                    getBanco().getListaCuentas().remove(cuenta);
	                    break;
	                }
	            }
	        }
	    return flagExiste;
	}

	
}
