package ejercicio;

public class Main {
	
	public static void main(String[] args) {
		Metodos m = new Metodos();
		
		T1 t1 = new T1(m, 10);
		T2 t2 = new T2(m,6);
		T3 t3 = new T3(m, "Electroencefalografista");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int s4 = m.s4();
		
		System.out.println("s1: "+t1.getN());
		System.out.println("s2: "+t2.getN());
		System.out.println("s3: "+t3.getN());
		System.out.println("s4: "+s4);
	}

}
