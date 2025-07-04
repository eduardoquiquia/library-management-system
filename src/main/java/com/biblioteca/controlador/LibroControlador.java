package com.biblioteca.controlador;

import com.biblioteca.util.ReporteUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Libro;
import com.biblioteca.servicio.LibroServicio;

import java.util.HashMap;
import java.util.Map;

public class LibroControlador {

    @FXML private TextField tituloField, autorField, editorialField, anioField;
    @FXML private CheckBox disponibleCheckBox;
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId, colAnio;
    @FXML private TableColumn<Libro, String> colTitulo, colAutor, colEditorial;
    @FXML private TableColumn<Libro, Boolean> colDisponible;
    @FXML private TextField buscarTituloField;
    @FXML private TextField buscarIdField;

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

    public void cargarLibros() {
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
    private void buscarPorTitulo() {
        String titulo = buscarTituloField.getText();
        if (titulo != null && !titulo.isEmpty()) {
            listaLibros.setAll(libroServicio.buscarLibrosPorTitulo(titulo));
        } else {
            cargarLibros();
        }
    }

    @FXML
    private void buscarPorId() {
        try {
            int id = Integer.parseInt(buscarIdField.getText());
            Libro libro = libroServicio.buscarLibroPorId(id);
            if (libro != null) {
                listaLibros.setAll(libro);
            } else {
                mostrarAlerta("No encontrado", "No existe un libro con ese ID");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingresa un ID válido");
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

    @FXML
    private void verTodosLibros() {
        ReporteUtil.mostrarReporte("/com/biblioteca/reportes/Libros.jasper", null);
    }

    @FXML
    private void verLibroSeleccionado() {
        Libro libro = tablaLibros.getSelectionModel().getSelectedItem();
        if (libro != null) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id_libro", libro.getIdLibro());
            ReporteUtil.mostrarReporte("/com/biblioteca/reportes/LibroPorId.jasper", parametros);
        } else {
            mostrarAlerta("Selecciona un libro para generar el reporte.", "aña");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
