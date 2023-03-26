package co.edu.uniquindio.banco.services;

import java.util.ArrayList;

import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;

public interface IModelFactoryService {

	
	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String fechaNacimiento);
	public Boolean eliminarEmpleado(String cedula);
	public Empleado obtenerEmpleado(String cedula);
	public ArrayList<Empleado> obtenerEmpleados();
	boolean actualizarEmpleado(String cedulaActual, String nombre, String apellido, String cedula, String fechaNacimiento);
	
	public Cliente crearCliente(String nombre, String cedula);
}
