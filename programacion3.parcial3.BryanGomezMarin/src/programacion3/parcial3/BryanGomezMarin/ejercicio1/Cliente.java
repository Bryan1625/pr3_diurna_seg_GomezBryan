package programacion3.parcial3.BryanGomezMarin.ejercicio1;

public abstract class Cliente extends Thread{

	private int consumo;
	private Tanque t;
	
	public Cliente(Tanque t, int consumo) {
		// TODO Auto-generated constructor stub
		this.t = t;
		this.consumo = consumo;
	}
	
	
	public void cargarGasolina(){
		
	}
	
	@Override
	public void run() {
		
	}


	public int getConsumo() {
		return consumo;
	}


	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}
	
	
	
}
