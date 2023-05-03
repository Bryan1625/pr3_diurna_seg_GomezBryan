package s1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Empresa empresa = new Empresa();
		ArrayList<Cliente> clientes = new ArrayList<>();
				clientes.add(new Cliente("juan","gonzalez","18","123456"));
				clientes.add(new Cliente("juan","gonzalez","18","123456"));
				clientes.add(new Cliente("juan","gonzalez","18","123456"));
				empresa.setListaClientes(clientes);
				try {
		            FileWriter writer = new FileWriter("clientes.txt");
		            
		            for (Cliente cliente : empresa.getListaClientes()) {
		                String linea = String.format("%s;%s;%d;%d\n", cliente.getNombre(), cliente.getApellido(),
		                                             cliente.getEdad(), cliente.getCedula());
		                writer.write(linea);
		            }
		            
		            writer.close();
		        } catch (IOException e) {
		            System.out.println("Error al guardar los datos en el archivo");
		            e.printStackTrace();
		        }
		
		
	}
	
	
	
}
