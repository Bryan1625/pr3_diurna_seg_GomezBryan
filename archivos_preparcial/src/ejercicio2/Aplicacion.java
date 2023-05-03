package ejercicio2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacion extends Application{

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    // Cargar la interfaz desde el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/estudiantesView.fxml"));
	    Parent root = loader.load();
	    
	    // Crear una escena y establecerla en el escenario
	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    
	    // Mostrar el escenario
	    primaryStage.show();
	}
}
