package practica;

public class miHilo2 extends Thread {

	private String vocales = "";
	private String palabra;
	
	public miHilo2(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {
		recorrerPalabra(palabra,0);
	}

	public String recorrerPalabra(String word, int posicionActual) {
		if (posicionActual == word.length()) {
			return getVocales();
		} else {
			if (isVocal(""+word.charAt(posicionActual))) {
				System.out.println("vocal: "+word.charAt(posicionActual));
				vocales += word.charAt(posicionActual);
			}
			
		}
		return recorrerPalabra(word,++posicionActual);
	}

	private boolean isVocal(String letra) {
		if (letra.equalsIgnoreCase("a") || letra.equalsIgnoreCase("e") || letra.equalsIgnoreCase("i")
				|| letra.equalsIgnoreCase("o") || letra.equalsIgnoreCase("u")) {
			return true;
		} else {
			return false;
		}
	}

	public String getVocales() {
		return vocales;
	}

	public void setVocales(String vocales) {
		this.vocales = vocales;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

}
