package ejercicio2;

public class Hilo1 extends Thread {

	String palabra;
	Tuberia tuberia;
	boolean flag;

	public Hilo1(String palabra, Tuberia tuberia) {
		this.palabra = palabra;
		this.tuberia = tuberia;
		flag = true;
	}

	@Override
	public void run() {
		while (flag) {
			try {
				sleep(100);
				tuberia.escribir(palabra);
			} catch (InterruptedException e) {
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

	public String getPalabra() {
		return palabra;
	}

}
