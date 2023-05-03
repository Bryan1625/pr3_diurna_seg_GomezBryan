package ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ejercicio1.model.Afiliacion;
import ejercicio1.model.Club;
import ejercicio1.model.Participante;
import ejercicio1.model.Tipo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import persistence.Persistencia;

public class Main {
	
	static Club club;

	public static void main(String[] args) {
		club = new Club();
		club.setAfiliaciones(new ArrayList<Afiliacion>());
		inicializarDatos();
		guardarArchivos();
		guardarBinario();
		guardarXML();
	}
	
	public static void guardarArchivos(){
		try {
			Persistencia.guardarAmigos(club.getAmigos());
			Persistencia.guardarAfiliaciones(club.getAmigos());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void guardarBinario(){
		try {
			club.setAmigos(Persistencia.cargarAmigos());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Persistencia.guardarRecursoClubBinario(club);
	}
	
	public static void guardarXML(){
		try {
			club.setAmigos(Persistencia.cargarAmigos());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Persistencia.guardarRecursoClubXML(club);
	}
	
	
	public static void inicializarDatos(){
		Participante p= new Participante(); 
		p.setNombre("juan");
		p.setApellidos("perez");
		p.setCedula("1234");
		p.setDireccion("armenia");
		p.setAfiliacion(new Afiliacion(Tipo.amigos,"567",""+LocalDateTime.now(),p,"activo"));
		club.agregarAmigo(p);
		
		p= new Participante(); 
		p.setNombre("maria");
		p.setApellidos("martinez");
		p.setCedula("529");
		p.setDireccion("montenegro");
		p.setAfiliacion(new Afiliacion(Tipo.universidad,"567",""+LocalDateTime.now(),p,"inactivo"));
		club.agregarAmigo(p);
		
		p= new Participante(); 
		p.setNombre("laura");
		p.setApellidos("torres");
		p.setCedula("903");
		p.setDireccion("pereira");
		p.setAfiliacion(new Afiliacion(Tipo.trabajo,"567",""+LocalDateTime.now(),p,"activo"));
		club.agregarAmigo(p);
	}

}
