package programacion3.parcial3.BryanGomezMarin.ejercicio1;

public class CamionCisterna extends Thread{

	int capacidad = 100;
	Estacion estacion;
	
	public void abastecer(){
		estacion.abastecer();
	}
}
