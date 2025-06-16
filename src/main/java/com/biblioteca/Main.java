package com.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el FXML usando la ruta correcta (desde el classpath)
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/biblioteca/vistas/Main.fxml"));

        // Crea la escena con el tamaño deseado (ajusta 800x600 según necesites)
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Configura el título y muestra la ventana
        stage.setTitle("Sistema de Biblioteca");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}