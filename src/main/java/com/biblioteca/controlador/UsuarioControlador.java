package com.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.servicio.UsuarioServicio;

public class UsuarioControlador {

    @FXML private TextField nombreField, apellidoField, emailField, telefonoField;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre, colApellido, colEmail, colTelefono;

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

    private void cargarUsuarios() {
        listaUsuarios.setAll(usuarioServicio.listarUsuarios());
        tablaUsuarios.setItems(listaUsuarios);
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
    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        emailField.clear();
        telefonoField.clear();
        tablaUsuarios.getSelectionModel().clearSelection();
    }
}
