package co.edu.uniquindio.banco.controllers;


import java.util.ArrayList;

import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;
import co.edu.uniquindio.banco.services.IModelFactoryService;
import javafx.collections.ObservableList;

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
	public Empleado obtenerEmpleado(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Empleado> obtenerEmpleados() {
//		getBanco().getListaEmpleados();
		return getBanco().obtenerEmpleados();
	}

	@Override
	public boolean actualizarEmpleado(String cedulaActual, String nombre, String apellido, String cedula,
			String fechaNacimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente crearCliente(String nombre, String cedula) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
