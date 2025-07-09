package com.biblioteca.controlador.Lector;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import com.biblioteca.modelo.Libro;
import com.biblioteca.servicio.LibroServicio;

public class LibroControlador {

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
        configurarEventosTabla();
    }

    private void configurarEventosTabla() {
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        });
    }

    @FXML
    private void cargarLibros() {
        listaLibros.setAll(libroServicio.listarLibros());
        tablaLibros.setItems(listaLibros);
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
        buscarIdField.clear();
        buscarTituloField.clear();
        buscarAutorField.clear();
        buscarEditorialField.clear();
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
