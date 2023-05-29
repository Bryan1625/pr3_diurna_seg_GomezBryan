package programacion3.parcial3.cliente.ejercicio3;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainCliente {
	static Universidad universidad;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Cliente cliente = new Cliente(8081);
		cliente.iniciarCliente();
		
		
	}

}
