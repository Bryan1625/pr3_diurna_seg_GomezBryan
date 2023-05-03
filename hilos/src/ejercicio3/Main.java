package ejercicio3;

public class Main {

	public static void main(String[] args) {
		Metodos m = new Metodos();
		String p = "otorrinolaringologia";
		int n = 5;
		m.s1(n, p);
		T1 t1 = new T1(m);
		T2 t2 = new T2(m);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int h = m.s4(t1.getN(), t2.getN().length());
		System.out.println("s2: "+t1.getN());
		System.out.println("s3: "+m.getS3());
		System.out.println("s4: "+h);
	}
	
	public static int sumaRecursiva(int n){
	    if(n <= 0){
	        return 0;
	    } else if(n == 1){
	        return 1;
	    } else {
	    	System.out.println(n);
	        return n + sumaRecursiva(n-1);
	    }
	}

}
