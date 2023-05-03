module BancoRepaso {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires commons.lang3;

    opens co.edu.uniquindio.banco.controllers to javafx.fxml;
    exports co.edu.uniquindio.banco.controllers;
    opens co.edu.uniquindio.banco.model to javafx.fxml;
    exports co.edu.uniquindio.banco.model;
    exports co.edu.uniquindio.banco;


}
