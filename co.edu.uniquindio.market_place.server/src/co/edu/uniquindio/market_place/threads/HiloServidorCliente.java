package co.edu.uniquindio.market_place.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.market_place.Server;
import co.edu.uniquindio.market_place.model.MarketPlace;
import co.edu.uniquindio.market_place.persistence.Persistencia;

public class HiloServidorCliente extends Thread{

	Server miServidor;
    
    DataInputStream flujoEntradaComunicacion=null;
    DataOutputStream flujoSalidaComunicacion=null;
    ObjectOutputStream flujoSalidaObjeto=null;
    ObjectInputStream flujoEntradaObjeto=null;
    DataOutputStream dos;
    byte[] byteArray;
    int in;
    File localFileServer;
    int opcion=0;
    
    MarketPlace marketPlace;
    
   
   
    
    public HiloServidorCliente()
    {
    }
    
	
	public void inicializarConexion(DataInputStream flujoEntradaComunicacion,DataOutputStream flujoSalidadComunicacion, ObjectOutputStream flujoSalidaObjeto,
			ObjectInputStream flujoEntradaObjeto, Server servidor) {
		
       this.miServidor=servidor;
		this.flujoEntradaComunicacion = flujoEntradaComunicacion;
		this.flujoSalidaComunicacion = flujoSalidadComunicacion;
		this.flujoSalidaObjeto = flujoSalidaObjeto;
		this.flujoEntradaObjeto = flujoEntradaObjeto;

	}
    
    public void run()
    {

   	 try
   	 {
   		 opcion=flujoEntradaComunicacion.readInt();
   		 System.out.println(opcion);
   		 
   		 switch(opcion)
   		 {
   		 case 1://Solicitud de enviar el objeto  de persistencia

   			 try {
   				 enviarInformacionPersistencia();
   			 } catch (Exception e) {
   				 // TODO Auto-generated catch block
   				 e.printStackTrace();
   			 }

   			 break;
   		 case 2:////Solicitud de guardar el objeto  de persistencia
   			 guardarInformacionPersistencia();
   			 break;

   		 case 3: // 
   			 registrarAccionesDelSistema();
   			 break;
   		 
   		 case 4:
   			comprobarCambiosModelo();
   			 break;
   		 case 5:
   			 break;
   		 }
   		 
   	 }catch (Exception e) {
   		 // TODO: handle exception
   	 }

    }
    
    private void comprobarCambiosModelo() {
		try {
			marketPlace = (MarketPlace) flujoEntradaObjeto.readObject();
			if(marketPlace.equals(Persistencia.cargarRecursomarketPlaceXML())){
				flujoSalidaComunicacion.writeBoolean(false);
			}else{
				flujoSalidaComunicacion.writeBoolean(true);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void enviarInformacionPersistencia() throws IOException,Exception {

   	 marketPlace = Persistencia.cargarRecursomarketPlaceXML();

   	 if(marketPlace == null)
   		 return;

   	 System.out.println("Se envio la información del banco");
   	 flujoSalidaObjeto.writeObject(marketPlace);
    }
    
    
    private void guardarInformacionPersistencia() throws IOException,Exception {

   	 marketPlace =  (MarketPlace) flujoEntradaObjeto.readObject();
   	 Persistencia.guardarRecursomarketPlaceXML(marketPlace);
   	 System.out.println("Se guardo la información del banco");
    }
	
	
	
	
	
	 private void registrarAccionesDelSistema() {
	        // Lógica para la opción 1
	        System.out.println("registrar acciones del sistema");

	        try {
	            // Leer los parámetros enviados por el cliente
	            String mensaje = flujoEntradaComunicacion.readUTF();
	            int nivel = flujoEntradaComunicacion.readInt();
	            String accion = flujoEntradaComunicacion.readUTF();

	            // Procesar los datos y realizar las acciones necesarias
	            // Por ejemplo, puedes llamar a un método en ModelFactoryController
	            Persistencia.guardarRegistroLog(mensaje, nivel, accion);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
