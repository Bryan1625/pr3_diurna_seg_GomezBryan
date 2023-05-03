package co.edu.uniquindio.market_place.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Mensaje> listaMensajes = new ArrayList<>();

	public ArrayList<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

}
