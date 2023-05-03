package co.edu.uniquindio.banco.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.banco.model.Cuenta;

public class CrudCuentaViewController {

	ModelFactoryController modelFactoryController;
	
	
	public CrudCuentaViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
	}


	public ArrayList<Cuenta> obtenerCuentas() {
		return modelFactoryController.obtenerCuentas();
	}


	public boolean actualizarCuenta(String numeroCuentaActual, String numeroCuenta, double saldo) {
		return modelFactoryController.actualizarCuenta(numeroCuentaActual, saldo);
	}


	public Cuenta crearCuenta(String numeroCuenta, double saldo) {
		return modelFactoryController.crearCuenta(numeroCuenta, saldo);
	}


	public boolean eliminarCuenta(String numeroCuenta) {
		return modelFactoryController.eliminarCuenta(numeroCuenta);
	}

}
