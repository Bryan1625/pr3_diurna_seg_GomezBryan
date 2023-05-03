package ejercicio3;

public class T2 extends Thread{
	
	Metodos m;
	String n;
	
	public T2(Metodos m) {
		this.m = m;
	}

	@Override
	public void run() {
		m.s3();
		n = m.getVocales();
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}
}
