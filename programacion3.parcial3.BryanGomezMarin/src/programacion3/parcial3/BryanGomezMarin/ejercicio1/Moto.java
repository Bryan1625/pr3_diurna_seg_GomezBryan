package programacion3.parcial3.BryanGomezMarin.ejercicio1;

public class Moto extends Cliente{

	
	public Moto(Tanque t) {
		super(t,4);
	}
	
	@Override
	public void run() {
		cargarGasolina();
	}
	
	@Override
	public void cargarGasolina(){
		
	}
}
