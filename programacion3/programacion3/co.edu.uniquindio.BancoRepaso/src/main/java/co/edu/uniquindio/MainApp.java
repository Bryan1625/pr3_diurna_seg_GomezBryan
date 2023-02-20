package co.edu.uniquindio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co.edu.uniquindio/bancoUI.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("UniBanco");
        stage.setScene(scene);
        stage.show();
    }


}
