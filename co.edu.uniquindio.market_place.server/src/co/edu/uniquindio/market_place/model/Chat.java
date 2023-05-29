package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Chat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Mensaje> listaMensajes = new ArrayList<>();
	private String id = "";

	public ArrayList<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getChat(){
		return listaMensajes.stream().map(Mensaje::getMensaje).collect(Collectors.joining("\n"));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
