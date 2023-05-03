package ejercicio3;

public class Metodos {

	private static String palabra;
	private static int numero;
	private String vocales;
	private int s3;
	int s2;

	public int s4(int i, int j) {
		return i * j;
	}

	public void s3() {
		recorrerPalabra(palabra, 0);
		s3 = vocales.length();
	}

	public void s2() {
		s2 = sumaRecursiva(numero);
		System.out.println(s2);
	}

	public void s1(int n, String p) {
		palabra = p;
		numero = n;
	}

	public static int sumaRecursiva(int n) {
	    if (n == 0) {
	        return 0;
	    } else {
	        return n + sumaRecursiva(n - 1);
	    }
	}

	public static boolean isVocal(String letra) {
		if (letra.equalsIgnoreCase("a") || letra.equalsIgnoreCase("e") || letra.equalsIgnoreCase("i")
				|| letra.equalsIgnoreCase("o") || letra.equalsIgnoreCase("u")) {
			return true;
		} else {
			return false;
		}
	}

	public String recorrerPalabra(String word, int posicionActual) {
		if (posicionActual == word.length()) {
			return vocales;
		} else {
			if (isVocal("" + word.charAt(posicionActual))) {
				System.out.println("vocal: " + word.charAt(posicionActual));
				vocales += word.charAt(posicionActual);
			}

		}
		return recorrerPalabra(word, ++posicionActual);
	}

	public static String getPalabra() {
		return palabra;
	}

	public static void setPalabra(String palabra) {
		Metodos.palabra = palabra;
	}

	public static int getNumero() {
		return numero;
	}

	public static void setNumero(int numero) {
		Metodos.numero = numero;
	}

	public String getVocales() {
		return vocales;
	}

	public void setVocales(String vocales) {
		this.vocales = vocales;
	}

	public int getS3() {
		return s3;
	}

	public void setS3(int s3) {
		this.s3 = s3;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}
}
