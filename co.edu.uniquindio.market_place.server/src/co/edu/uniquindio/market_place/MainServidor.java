package co.edu.uniquindio.market_place;

import java.io.IOException;

public class MainServidor {
    
	static Server miServidor;
	
	
	 public static void main(String args[]) throws IOException
	   {                
	     
		 miServidor=new Server();
		 
		 try {
			miServidor.runServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   }
}

