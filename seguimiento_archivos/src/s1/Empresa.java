package s1;

import java.util.ArrayList;

public class Empresa {

	private ArrayList<Cliente> listaClientes = new ArrayList<>();
	
	
	public Empresa() {
	
	}


	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
}
