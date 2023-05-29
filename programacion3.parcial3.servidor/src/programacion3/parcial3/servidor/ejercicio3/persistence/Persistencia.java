package programacion3.parcial3.servidor.ejercicio3.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import programacion3.parcial3.servidor.ejercicio3.Autor;
import programacion3.parcial3.servidor.ejercicio3.TrabajoGrado;
import programacion3.parcial3.servidor.ejercicio3.Universidad;
public class Persistencia {
	public static final String RUTA_ARCHIVO_AUTORES = "src\\archivos\\autores.txt";
	public static final String RUTA_ARCHIVO_TRABAJOS_GRADO = "src\\archivos\\trabajosGrado.txt";

	public static void cargarDatosTrabajoGrado(Universidad universidad) throws FileNotFoundException, IOException {
		
		ArrayList<TrabajoGrado> trabajosGradoCargados = cargarTrabajosGrado();
		
		if(trabajosGradoCargados.size() > 0)
			universidad.getTrabajosGrado().addAll(trabajosGradoCargados);
	}
	
	public static void cargarDatosAutores(Universidad universidad) throws FileNotFoundException, IOException {
		
		ArrayList<Autor> autoresCargados = cargarAutores();
		
		if(autoresCargados.size() > 0)
			universidad.getAutores().addAll(autoresCargados);
	}
	
	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarTrabajosGrado(List<TrabajoGrado> listaTrabajosGado) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(TrabajoGrado trabajo:listaTrabajosGado) 
		{
			contenido+= trabajo.getTitulo()+"@"+trabajo.getFecha()+"@"+trabajo.getDescripcion()+"@";
			ArrayList<Autor>autores= (ArrayList<Autor>) trabajo.getAutores();
			for(Autor autor: autores) {
				contenido+=autor.getNombre()+"@"+autor.getApellido()+"@"+autor.getCedula()+"@"+autor.getPrograma()+"@"+autor.getTitulo()+"@";
			}
			contenido+="\n";
			
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_TRABAJOS_GRADO , contenido, false);
		
	}
	
	
	public static void guardarAutores(List<Autor> listaAutores) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Autor autor:listaAutores) 
		{
			contenido+= autor.getNombre()+"@"+autor.getApellido()+"@"+autor.getCedula()+"@"+autor.getPrograma()+"@"+autor.getTitulo()+"@"+"\n" ;
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_AUTORES, contenido, false);
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
	public static ArrayList<TrabajoGrado> cargarTrabajosGrado() throws FileNotFoundException, IOException 
	{
		ArrayList<TrabajoGrado> trabajosGrado =new ArrayList<TrabajoGrado>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_TRABAJOS_GRADO);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			TrabajoGrado trabajoGrado = new TrabajoGrado();
			trabajoGrado.setTitulo(linea.split("@")[0]);
			trabajoGrado.setFecha(linea.split("@")[1]);
			trabajoGrado.setDescripcion(linea.split("@")[2]);
			Autor autor= new Autor();
			autor.setNombre(linea.split("@")[3]);
			autor.setApellido(linea.split("@")[4]);
			autor.setCedula(linea.split("@")[5]);
			autor.setPrograma(linea.split("@")[6]);
			autor.setTitulo(linea.split("@")[7]);
			trabajoGrado.getAutores().add(autor);
			autor.setNombre(linea.split("@")[8]);
			autor.setApellido(linea.split("@")[9]);
			autor.setCedula(linea.split("@")[10]);
			autor.setPrograma(linea.split("@")[11]);
			autor.setTitulo(linea.split("@")[12]);
			trabajoGrado.getAutores().add(autor);
			trabajosGrado.add(trabajoGrado);

		}
		return trabajosGrado;
	}
	
	public static ArrayList<Autor> cargarAutores() throws FileNotFoundException, IOException 
	{
		ArrayList<Autor> autores =new ArrayList<Autor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_AUTORES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Autor autor = new Autor();
			autor.setNombre(linea.split("@")[0]);
			autor.setApellido(linea.split("@")[1]);
			autor.setCedula(linea.split("@")[2]);
			autor.setPrograma(linea.split("@")[3]);
			autor.setTitulo(linea.split("@")[4]);
			autores.add(autor);
		}
		return autores;
	}

	
}
