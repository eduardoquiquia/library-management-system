package com.biblioteca.controlador;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.servicio.UsuarioServicio;
import com.biblioteca.util.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginControlador {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Debes ingresar tu email y contraseña.");
            return;
        }

        Usuario usuario = usuarioServicio.login(email, password);

        if (usuario != null) {
            Sesion.establecerUsuario(usuario);

            abrirPantallaPorTipo(usuario.getTipoUsuario());
            cerrarVentanaActual();
        } else {
            mostrarAlerta("Credenciales incorrectas.");
        }
    }

    private void abrirPantallaPorTipo(String tipoUsuario) {
        try {
            String vista = tipoUsuario.equals("Administrador")
                    ? "/com/biblioteca/vistas/Administrador/Main.fxml"
                    : "/com/biblioteca/vistas/Lector/Main.fxml";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Bienvenido, " + tipoUsuario);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("No se pudo abrir la ventana del " + tipoUsuario);
        }
    }

    private void cerrarVentanaActual() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
