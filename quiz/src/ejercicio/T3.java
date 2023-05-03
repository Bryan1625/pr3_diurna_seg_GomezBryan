package ejercicio;

public class T3 extends Thread{
	
	Metodos m;
	int n;
	String palabra;
	
	public T3(Metodos m, String palabra) {
		this.m = m;
		this.palabra = palabra;
	}
	
	@Override
	public void run() {
		n = m.s3(palabra);
	}

	public Metodos getM() {
		return m;
	}

	public void setM(Metodos m) {
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

}
