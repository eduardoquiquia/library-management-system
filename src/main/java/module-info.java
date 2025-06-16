module com.biblioteca.sistemabiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;  // Esto soluciona el primer problema
    requires mysql.connector.j;  // Para el driver de MySQL

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.biblioteca to javafx.fxml;
    exports com.biblioteca;
    exports com.biblioteca.controlador;
    opens com.biblioteca.controlador to javafx.fxml;
}