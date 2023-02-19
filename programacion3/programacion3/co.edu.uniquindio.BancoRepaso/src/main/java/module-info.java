module BancoRepaso {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.controllers to javafx.fxml;
    exports co.edu.uniquindio.controllers;
    opens co.edu.uniquindio.model to javafx.fxml;
    exports co.edu.uniquindio.model;


}
