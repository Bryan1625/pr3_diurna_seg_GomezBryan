package programacion3.parcial3.cliente.ejercicio2;


public class MainCliente {

	public static void main(String[] args) {
		String frase="Alliva Ramon y no maravilla";
		String cadena="Uniquindio";
		Cliente appCliente = new Cliente(frase,"localhost",8081,cadena);
		System.out.println("Iniciando cliente\n");
		appCliente.iniciarCliente();
	}
}
