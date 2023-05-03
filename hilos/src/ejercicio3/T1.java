package ejercicio3;

public class T1 extends Thread{
	
	Metodos m;
	int n;
	
	public T1(Metodos m) {
		this.m = new Metodos();
	}
	
	@Override
	public void run() {
		m.s2();
		n = m.getS2();
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
}
