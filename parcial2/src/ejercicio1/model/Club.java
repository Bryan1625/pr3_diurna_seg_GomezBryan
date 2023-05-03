package ejercicio1.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Club implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Participante> amigos;
	private ArrayList<Afiliacion> afiliaciones;
	
	public Club(ArrayList<Participante> amigos){
		this.amigos = amigos;
	}
	
	public Club(){
		this.amigos = new ArrayList<Participante>();
	}
	
	public void agregarAmigo(Participante amigo){
		amigos.add(amigo);
	}
	
	public void guardarAmigos(){
		
	}
	
	public Participante buscarAmigo(String codigo){
		for (int i = 0; i < amigos.size(); i++) {
			if(amigos.get(i).getCedula().equals(codigo)){
				return amigos.get(i);
			}
		}
		
		return null;
	}


	public ArrayList<Participante> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Participante> amigos) {
		this.amigos = amigos;
	}

	public ArrayList<Afiliacion> getAfiliaciones() {
		return afiliaciones;
	}

	public void setAfiliaciones(ArrayList<Afiliacion> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}
}
