package co.edu.uniquindio.market_place;

import co.edu.uniquindio.market_place.controllers.MarketPlaceViewController;
import co.edu.uniquindio.market_place.controllers.ModelFactoryController;
import co.edu.uniquindio.market_place.persistence.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    // Cargar la interfaz desde el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MarketPlaceView.fxml"));
	    Parent root = loader.load();
	    
	    // Crear una escena y establecerla en el escenario
	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    MarketPlaceViewController market = loader.getController();
	    ModelFactoryController m = market.getModelFactoryController();
	    // Mostrar el escenario
	    primaryStage.show();
	    
	    primaryStage.setOnCloseRequest(e -> {
            // Método para guardar el modelo XML antes de cerrar
            Persistencia.guardarRecursomarketPlaceXML(m.getMarketPlace());
        });
	}


}
