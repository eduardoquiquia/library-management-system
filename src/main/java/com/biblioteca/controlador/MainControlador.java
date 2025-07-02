package com.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.Optional;

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

    @FXML
    private void salirAplicacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Salida");
        alert.setHeaderText("¿Está seguro que desea salir?");
        alert.setContentText("Se cerrará la aplicación.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Hace la ventana modal
            stage.setResizable(false); // Evita redimensionar
            stage.centerOnScreen(); // Centra la ventana
            stage.show();

        } catch (IOException e) {
            mostrarError("Error al abrir ventana",
                    "No se pudo cargar la vista: " + titulo,
                    e.getMessage());
        } catch (Exception e) {
            mostrarError("Error inesperado",
                    "Ocurrió un error inesperado al abrir: " + titulo,
                    e.getMessage());
        }
    }

    private void mostrarError(String titulo, String header, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
