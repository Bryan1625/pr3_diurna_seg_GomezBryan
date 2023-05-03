package archivoRecursivo;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		System.out.println("archivo ubicado en: " + findFile("hola.txt", "C://Users//Bryan/Documents/btw/recursivo").getAbsolutePath());
	}
	
	public static File findFile(String fileName, String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            return null; // El directorio no existe o no es un directorio válido
        }
        
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    File nestedFile = findFile(fileName, file.getAbsolutePath()); // Llamada recursiva
                    if (nestedFile != null) {
                        return nestedFile;
                    }
                } else if (file.getName().equals(fileName)) {
                    return file; // Archivo encontrado
                }
            }
        }
        
        return null; // No se encontró el archivo en este directorio ni en sus subdirectorios
    }
}
