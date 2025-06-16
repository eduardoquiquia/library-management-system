package com.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class MainControlador {

    @FXML
    private void abrirUsuarios() {
        abrirVentana("/com/biblioteca/vistas/UsuarioVista.fxml", "Gestión de Usuarios");
    }

    @FXML
    private void abrirLibros() {
        abrirVentana("/com/biblioteca/vistas/LibroVista.fxml", "Gestión de Libros");
    }

    @FXML
    private void abrirPrestamos() {
        abrirVentana("/com/biblioteca/vistas/PrestamoVista.fxml", "Gestión de Préstamos");
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
