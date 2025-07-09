package com.biblioteca.controlador.Administrador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Libro;
import com.biblioteca.servicio.LibroServicio;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibroControlador {

    @FXML private TextField tituloField, autorField, editorialField, anioField, stockField;
    @FXML private TextField buscarTituloField, buscarAutorField, buscarEditorialField, buscarIdField;
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId, colAnio, colStock;
    @FXML private TableColumn<Libro, String> colTitulo, colAutor, colEditorial;

    private final LibroServicio libroServicio = new LibroServicio();
    private final ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("idLibro"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anioPublicacion"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        cargarLibros();
    }

    @FXML
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
            libro.setStock(Integer.parseInt(stockField.getText()));

            libroServicio.registrarLibro(libro);
            cargarLibros();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El año y el stock deben ser números enteros.");
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
                seleccionado.setStock(Integer.parseInt(stockField.getText()));

                libroServicio.actualizarLibro(seleccionado);
                cargarLibros();
                limpiarCampos();
            } catch (NumberFormatException e) {
                mostrarAlerta("Error de Formato", "El año y el stock deben ser números enteros.");
            }
        } else {
            mostrarAlerta("Selección Requerida", "Selecciona un libro para actualizar.");
        }
    }

    @FXML
    private void eliminarLibro() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            libroServicio.eliminarLibro(seleccionado.getIdLibro());
            cargarLibros();
            limpiarCampos();
        } else {
            mostrarAlerta("Selección Requerida", "Selecciona un libro para eliminar.");
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
    private void buscarPorAutor() {
        String autor = buscarAutorField.getText();
        if (autor != null && !autor.isEmpty()) {
            listaLibros.setAll(libroServicio.buscarLibrosPorAutor(autor));
        } else {
            cargarLibros();
        }
    }

    @FXML
    private void buscarPorEditorial() {
        String editorial = buscarEditorialField.getText();
        if (editorial != null && !editorial.isEmpty()) {
            listaLibros.setAll(libroServicio.buscarLibrosPorEditorial(editorial));
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
                mostrarAlerta("No encontrado", "No existe un libro con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Ingresa un ID válido.");
        }
    }

    @FXML
    private void limpiarCampos() {
        tituloField.clear();
        autorField.clear();
        editorialField.clear();
        anioField.clear();
        stockField.clear();
        buscarIdField.clear();
        buscarTituloField.clear();
        tablaLibros.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
