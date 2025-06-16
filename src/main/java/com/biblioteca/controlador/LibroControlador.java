package com.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Libro;
import com.biblioteca.servicio.LibroServicio;

public class LibroControlador {

    @FXML private TextField tituloField, autorField, editorialField, anioField;
    @FXML private CheckBox disponibleCheckBox;
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId, colAnio;
    @FXML private TableColumn<Libro, String> colTitulo, colAutor, colEditorial;
    @FXML private TableColumn<Libro, Boolean> colDisponible;

    private final LibroServicio libroServicio = new LibroServicio();
    private final ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdLibro()).asObject());
        colTitulo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTitulo()));
        colAutor.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAutor()));
        colEditorial.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEditorial()));
        colAnio.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getAnioPublicacion()).asObject());
        colDisponible.setCellValueFactory(cell -> new javafx.beans.property.SimpleBooleanProperty(cell.getValue().isDisponible()).asObject());

        cargarLibros();
    }

    private void cargarLibros() {
        listaLibros.setAll(libroServicio.listarLibros());
        tablaLibros.setItems(listaLibros);
    }

    @FXML
    private void agregarLibro() {
        try {
            Libro libro = new Libro();
            libro.setTitulo(tituloField.getText());
            libro.setAutor(autorField.getText());
            libro.setEditorial(editorialField.getText());
            libro.setAnioPublicacion(Integer.parseInt(anioField.getText()));
            libro.setDisponible(disponibleCheckBox.isSelected());

            libroServicio.registrarLibro(libro);
            cargarLibros();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El año debe ser un número entero");
        }
    }

    @FXML
    private void actualizarLibro() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                seleccionado.setTitulo(tituloField.getText());
                seleccionado.setAutor(autorField.getText());
                seleccionado.setEditorial(editorialField.getText());
                seleccionado.setAnioPublicacion(Integer.parseInt(anioField.getText()));
                seleccionado.setDisponible(disponibleCheckBox.isSelected());

                libroServicio.actualizarLibro(seleccionado);
                cargarLibros();
                limpiarCampos();
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El año debe ser un número entero");
            }
        }
    }

    @FXML
    private void eliminarLibro() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            libroServicio.eliminarLibro(seleccionado.getIdLibro());
            cargarLibros();
            limpiarCampos();
        }
    }

    @FXML
    private void limpiarCampos() {
        tituloField.clear();
        autorField.clear();
        editorialField.clear();
        anioField.clear();
        disponibleCheckBox.setSelected(false);
        tablaLibros.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
