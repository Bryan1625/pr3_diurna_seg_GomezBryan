package co.edu.uniquindio.market_place.persistence;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.market_place.exceptions.UsuarioException;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.model.Usuario;
import co.edu.uniquindio.market_place.model.Vendedor;



public class Persistencia {

	public static final String RUTA_ARCHIVO_VENDEDORES = "src/resources/archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "C://td//persistencia//log//marketPlaceLog.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "src/resources/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_XML = "src/resources/model.xml";
//	C:\td\persistencia

	
	
	public static void cargarDatosArchivos(MarketPlace marketPlace) throws FileNotFoundException, IOException {
		
		
		//cargar archivo de vendedores
		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();
		
		if(vendedoresCargados.size() > 0)
			marketPlace.getVendedores().addAll(vendedoresCargados);

		
	}
	
	
	




	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarvendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Vendedor vendedor:listaVendedores) 
		{
			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellido()+"@@"+vendedor.getCedula()+"@@"+vendedor.getDireccion()
		     +"@@"+vendedor.getUsuario()+"@@"+vendedor.getContrasenia()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
		
	}
	
	public static void guardarUsuarios(ArrayList<Vendedor> listaVendedores) throws IOException {
	    String contenido = "";
	    
	    for (Vendedor vendedor : listaVendedores) {
	        contenido += vendedor.getUsuario() + "@@" + vendedor.getContrasenia() + "\n";
	    }
	    
	    ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}

	
	
	
//	----------------------LOADS------------------------
	
	/**
	 * 
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Vendedor> cargarVendedores() throws FileNotFoundException, IOException 
	{
		ArrayList<Vendedor> vendedores =new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@@")[0]);
			vendedor.setApellido(linea.split("@@")[1]);
			vendedor.setCedula(linea.split("@@")[2]);
			vendedor.setDireccion(linea.split("@@")[3]);
			vendedor.setUsuario(linea.split("@@")[4]);
			vendedor.setContrasenia(linea.split("@@")[5]);
			vendedores.add(vendedor);
		}
		return vendedores;
	}
	


	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioException {
		
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new UsuarioException("Usuario no existe");
		}
		
	}
	
	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
	{
		ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			Usuario usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(linea.split("@@")[0]);
			usuario.setContrasenia(linea.split("@@")[1]);
			
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	
//	----------------------SAVES------------------------
	
	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	
	public static void guardarObjetos(ArrayList<Vendedor> listavendedores, String ruta) throws IOException  {
		String contenido = "";
		
		for(Vendedor vendedorAux:listavendedores) {
			contenido+= vendedorAux.getNombre()+"@@"+vendedorAux.getApellido()+"@@"+vendedorAux.getCedula()+vendedorAux.getDireccion()
					     +"@@"+vendedorAux.getUsuario()+"@@"+vendedorAux.getContrasenia()+"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}


	public static void guardarObjeto(ArrayList<Vendedor> listavendedores, String ruta) throws IOException  {
		String contenido = "";
		
		Vendedor vendedorAux = listavendedores.get(listavendedores.size()-1);
			contenido+= vendedorAux.getNombre()+"@@"+vendedorAux.getApellido()+"@@"+vendedorAux.getCedula()+vendedorAux.getDireccion()
					     +"@@"+vendedorAux.getUsuario()+"@@"+vendedorAux.getContrasenia()+"\n";
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}

	
	
	
	//------------------------------------SERIALIZACIÓN  y XML
	
	
	public static MarketPlace cargarRecursoMarketPlaceBinario() {
		
		MarketPlace marketPlace = null;
		
		try {
			marketPlace = (MarketPlace)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketPlace;
	}
	
	public static void guardarRecursomarketPlaceBinario(MarketPlace marketPlace) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, marketPlace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static MarketPlace cargarRecursomarketPlaceXML() {
		
		MarketPlace marketPlace = null;
		
		try {
			marketPlace = (MarketPlace)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketPlace;

	}

	
	
	public static void guardarRecursomarketPlaceXML(MarketPlace marketPlace) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, marketPlace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	
	



}
