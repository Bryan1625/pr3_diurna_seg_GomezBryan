package co.edu.uniquindio.banco.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;

public class CrudClienteViewController {

	ModelFactoryController modelFactoryController;
	
	public CrudClienteViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
	}

	public Cliente crearCliente(String nombre, String apellido, String cedula, String fechaNacimiento) {
		return modelFactoryController.crearCliente(nombre, apellido, cedula, fechaNacimiento);
	}

	public boolean eliminarCliente(String cedula) {
		return modelFactoryController.eliminarCliente(cedula);
	}

	public ArrayList<Cliente> obtenerClientes() {
		return modelFactoryController.obtenerClientes();
	}

	public boolean actualizarCliente(String cedulaActual, String nombre, String apellido, String cedula,
			String fechaNacimiento) {
		return modelFactoryController.actualizarCliente(cedulaActual, nombre, apellido, cedula, fechaNacimiento);
	}



}
