package com.biblioteca.controlador.Administrador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.servicio.UsuarioServicio;

public class UsuarioControlador {

    @FXML private TextField nombreField, apellidoField, emailField, telefonoField, buscarIdField;
    @FXML private TextField nombreBuscarField, apellidoBuscarField;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre, colApellido, colEmail, colTelefono;
    @FXML private PasswordField nuevoPasswordField;
    @FXML private TextField nuevoTipoUsuarioField;

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdUsuario()).asObject());
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colApellido.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getApellido()));
        colEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        colTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));

        cargarUsuarios();
    }

    public void cargarUsuarios() {
        listaUsuarios.setAll(usuarioServicio.listarUsuarios());
        tablaUsuarios.setItems(listaUsuarios);
    }

    @FXML
    private void buscarPorNombreYApellido() {
        String nombre = nombreBuscarField.getText();
        String apellido = apellidoBuscarField.getText();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Debes ingresar nombre y apellido.");
            return;
        }

        Usuario usuario = usuarioServicio.buscarPorNombreYApellido(nombre, apellido);
        if (usuario != null) {
            listaUsuarios.clear();
            listaUsuarios.add(usuario);
        } else {
            mostrarAlerta("No se encontró un usuario con ese nombre y apellido.");
        }
    }

    @FXML
    private void buscarUsuarioPorId() {
        try {
            int id = Integer.parseInt(buscarIdField.getText());
            Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);

            if (usuario != null) {
                listaUsuarios.clear();
                listaUsuarios.add(usuario);
            } else {
                mostrarAlerta("No se encontró un usuario con el ID especificado");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El ID debe ser un número válido");
        }
    }

    @FXML
    private void agregarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreField.getText());
        usuario.setApellido(apellidoField.getText());
        usuario.setEmail(emailField.getText());
        usuario.setTelefono(telefonoField.getText());

        usuarioServicio.registrarUsuario(usuario);
        cargarUsuarios();
        limpiarCampos();
    }

    @FXML
    private void actualizarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombre(nombreField.getText());
            seleccionado.setApellido(apellidoField.getText());
            seleccionado.setEmail(emailField.getText());
            seleccionado.setTelefono(telefonoField.getText());

            usuarioServicio.actualizarUsuario(seleccionado);
            cargarUsuarios();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            usuarioServicio.eliminarUsuario(seleccionado.getIdUsuario());
            cargarUsuarios();
            limpiarCampos();
        }
    }

    @FXML
    private void cambiarPassword() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String nuevoPassword = nuevoPasswordField.getText();
            if (!nuevoPassword.isEmpty()) {
                usuarioServicio.cambiarPassword(seleccionado.getIdUsuario(), nuevoPassword);
                mostrarAlerta("Contraseña actualizada correctamente.");
                nuevoPasswordField.clear();
            } else {
                mostrarAlerta("Ingresa la nueva contraseña.");
            }
        } else {
            mostrarAlerta("Selecciona un usuario para cambiar la contraseña.");
        }
    }

    @FXML
    private void cambiarTipoUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String nuevoTipo = nuevoTipoUsuarioField.getText();
            if (!nuevoTipo.isEmpty()) {
                usuarioServicio.cambiarTipoUsuario(seleccionado.getIdUsuario(), nuevoTipo);
                mostrarAlerta("Tipo de usuario actualizado correctamente.");
                cargarUsuarios();
                nuevoTipoUsuarioField.clear();
            } else {
                mostrarAlerta("Ingresa el nuevo tipo de usuario.");
            }
        } else {
            mostrarAlerta("Selecciona un usuario para cambiar su tipo.");
        }
    }

    @FXML
    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        emailField.clear();
        telefonoField.clear();
        tablaUsuarios.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
