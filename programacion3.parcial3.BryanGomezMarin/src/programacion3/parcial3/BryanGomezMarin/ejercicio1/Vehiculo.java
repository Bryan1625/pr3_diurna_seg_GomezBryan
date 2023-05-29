package programacion3.parcial3.BryanGomezMarin.ejercicio1;

public class Vehiculo extends Cliente{
	
	public Vehiculo(Tanque t) {
		super(t, 10);
	}
	
	@Override
	public void run() {
		cargarGasolina();
	}
	
	@Override
	public void cargarGasolina(){
		
	}
	
	
}
