package persistence;

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

import co.edu.uniquindio.metodos.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.metodos.model.metodos;
import ejercicio2parte2.Metodos;
import co.edu.uniquindio.metodos.model.estudiante;
import model.Estudiante;
import model.Programa;
import co.edu.uniquindio.metodos.model.Empleado;
import co.edu.uniquindio.metodos.model.Usuario;



public class Persistencia {

	public static final String RUTA_ARCHIVO_estudiantes = "src/resources/archivoestudiantes.txt";
	public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/resources/log.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_metodos_BINARIO = "src/resources/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_METODOS_XML = "src/resources/programas.xml";
	public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/resources/estudiantes.txt";
	public static final String RUTA_ARCHIVO_PROGRAMAS = "src/resources/programas.txt";
//	C:\td\persistencia

	
	
	public static void cargarDatosArchivos(Metodos metodos) throws FileNotFoundException, IOException {
		
		
		//cargar archivo de estudiantes
		ArrayList<Programa> programasCargados = cargarProgramas();
		
		if(programasCargados.size() > 0)
			metodos.getProgramas().addAll(programasCargados);

		
		//cargar archivos empleados
		ArrayList<Empleado> empleadosCargados = cargarEmpleados();
		
		if(empleadosCargados.size() > 0)
			metodos.getListaEmpleados().addAll(empleadosCargados);
		
		//cargar archivo objetos
		
		//cargar archivo empleados
		
		//cargar archivo prestamo
		
	}
	
	
	




	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarEstudiantes(ArrayList<Estudiante> listaestudiantes) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Estudiante estudiante:listaestudiantes) 
		{
			contenido += estudiante.getCodigo() + ";"
		            + estudiante.getNombre() + ";"
		            + estudiante.getNota1() + ";"
		            + estudiante.getNota2() + ";"
		            + estudiante.getNota3() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_estudiantes, contenido, false);
		
	}
	
	
	public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Empleado empleado:listaEmpleados) 
		{
			contenido+= empleado.getNombre()+","+empleado.getApellido()+","+empleado.getCedula()+","+empleado.getFechaNacimiento()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
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
	public static ArrayList<Estudiante> cargarEstudiantes() throws FileNotFoundException, IOException 
	{
		ArrayList<Estudiante> estudiantes =new ArrayList<Estudiante>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_estudiantes);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
			Estudiante estudiante = new Estudiante();
			estudiante.setCodigo(Integer.parseInt(linea.split(";")[0]));
			estudiante.setNombre(linea.split(";")[1]);
			estudiante.setNota1(Double.parseDouble(linea.split(";")[2]));
			estudiante.setNota2(Double.parseDouble(linea.split(";")[3]));
			estudiante.setNota3(Double.parseDouble(linea.split(";")[4]));
			estudiantes.add(estudiante);
		}
		return estudiantes;
	}
	
	
	public static ArrayList<Programa> cargarProgramas() throws FileNotFoundException, IOException {
	    ArrayList<Programa> programas = new ArrayList<Programa>();
	    ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PROGRAMAS);
	    String linea = "";

	    for (int i = 0; i < contenido.size(); i++) {
	        linea = contenido.get(i);
	        Programa programa = new Programa();
	        programa.setCodigo(linea.split(";")[0]);
	        programa.setNombre(linea.split(";")[1]);
	        programa.setModalidad(linea.split(";")[2]);
	        programas.add(programa);
	    }

	    return programas;
	}

	
	
	public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
		ArrayList<Empleado> empleados =new ArrayList<Empleado>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Empleado empleado = new Empleado(); 
			empleado.setNombre(linea.split(",")[0]);
			empleado.setApellido(linea.split(",")[1]);
			empleado.setCedula(linea.split(",")[2]);
			empleado.setFechaNacimiento(linea.split(",")[3]);
			empleados.add(empleado);
		}
		return empleados;
	}
	
	


	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
		
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new UsuarioExcepcion("Usuario no existe");
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
			usuario.setUsuario(linea.split(",")[0]);
			usuario.setContrasenia(linea.split(",")[1]);
			
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
	
	public static void guardarObjetos(ArrayList<Estudiante> listaestudiantes, String ruta) throws IOException  {
		String contenido = "";
		
		for (Estudiante estudianteAux : listaestudiantes) {
		    contenido += estudianteAux.getCodigo() + ";"
		            + estudianteAux.getNombre() + ";"
		            + estudianteAux.getNota1() + ";"
		            + estudianteAux.getNota2() + ";"
		            + estudianteAux.getNota3() + "\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}


	
	
	
	//------------------------------------SERIALIZACIÓN  y XML
	
	
	public static metodos cargarRecursometodosBinario() {
		
		metodos metodos = null;
		
		try {
			metodos = (metodos)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_metodos_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metodos;
	}
	
	public static void guardarRecursometodosBinario(Metodos metodos) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_metodos_BINARIO, metodos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Metodos cargarRecursoMetodosXML() {
		
		Metodos metodos = null;
		
		try {
			metodos = (Metodos)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_METODOS_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metodos;

	}

	
	
	public static void guardarRecursoMetodosXML(Metodos metodos) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_METODOS_XML, metodos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	
	



}
