package co.edu.uniquindio.banco.services;

import java.util.ArrayList;

import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.Empleado;

public interface IBancoService {

	
	public Empleado crearEmpleado(String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException;
	public boolean actualizarEmpleado(String cedulaActual,String nombre, String apellido, String cedula, String fechaNacimiento) throws EmpleadoException;
	public Boolean eliminarEmpleado(String cedula)throws EmpleadoException;
	public boolean  verificarEmpleadoExistente(String cedula);
	public Empleado obtenerEmpleado(String cedula);
	public ArrayList<Empleado> obtenerEmpleados();
	
}
