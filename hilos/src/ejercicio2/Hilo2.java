package ejercicio2;

public class Hilo2 extends Thread{
	String palabra;
	Tuberia tuberia;
	boolean flag;

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public Hilo2(Tuberia tuberia) {
		this.tuberia = tuberia;
		flag = true;
	}
	
	@Override
	public void run() {
		while(flag){
			try{
				sleep(400);
				String palabra = tuberia.leerYBorrar();
				if(palabra!=""){
				System.out.println(palabra);
				}
			}catch(InterruptedException e) {
                e.printStackTrace();
                }
			
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
