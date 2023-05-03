package ejercicio;

public class T2 extends Thread{
	
	Metodos m;
	int n;
	
	public T2(Metodos m, int n) {
		this.m = m;
		this.n = n;
	}
	
	@Override
	public void run() {
		n = m.s2(n);
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

}
