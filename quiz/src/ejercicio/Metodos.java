package ejercicio;

public class Metodos {
	
	int s1;
	int s2;
	int s3;
	String palabra;

	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getS1() {
		return s1;
	}

	public void setS1(int s1) {
		this.s1 = s1;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}

	public int s1(int s1) {
		this.s1 = s1;
		this.s1 = sumaRecursiva(s1);
		return this.s1;
	}
	
	public int s2(int s2){
		this.s2 = s2;
		this.s2 = calcularFactorial(s2);
		return this.s2;
		
	}
	
	public int s3(String palabra){
		String s3;
		s3 = palabra;
		this.s3 = contarConsonantes(s3);
		return this.s3;
	}
	
	public int s4(){
		return s1 * s2;
	}


	public int getS3() {
		return s3;
	}

	public void setS3(int s3) {
		this.s3 = s3;
	}

	public static int sumaRecursiva(int n) {
	    if (n == 0) {
	        return 0;
	    } else {
	        return n + sumaRecursiva(n - 1);
	    }
	}

	
	public int calcularFactorial(int n){
	    int a = 1;
	    for (int i = 1; i <= n; i++) {
	        a = a * i;
	    }
	    return a;
	}

	
	public int contarConsonantes(String palabra){
		int consonantes = 0;
		for (int i = 0; i < palabra.length(); i++) {
			if(!isVocal(""+palabra.charAt(i))){
				consonantes++;
			}
		}
		return consonantes;
	}
	
	public boolean isVocal(String letra) {
		if (letra.equalsIgnoreCase("a") || letra.equalsIgnoreCase("e") || letra.equalsIgnoreCase("i")
				|| letra.equalsIgnoreCase("o") || letra.equalsIgnoreCase("u")) {
			return true;
		} else {
			return false;
		}
	}
}
