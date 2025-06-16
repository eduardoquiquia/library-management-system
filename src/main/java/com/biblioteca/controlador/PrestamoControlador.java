package com.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.servicio.PrestamoServicio;

import java.time.LocalDate;

public class PrestamoControlador {

    @FXML private TextField usuarioIdField, libroIdField;
    @FXML private DatePicker fechaPrestamoPicker, fechaDevolucionPicker;
    @FXML private TableView<Prestamo> tablaPrestamos;
    @FXML private TableColumn<Prestamo, Integer> colId, colUsuarioId, colLibroId;
    @FXML private TableColumn<Prestamo, LocalDate> colFechaPrestamo, colFechaDevolucion;

    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdPrestamo()).asObject());
        colUsuarioId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdUsuario()).asObject());
        colLibroId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdLibro()).asObject());
        colFechaPrestamo.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getFechaPrestamo()));
        colFechaDevolucion.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getFechaDevolucion()));

        cargarPrestamos();
    }

    private void cargarPrestamos() {
        listaPrestamos.setAll(prestamoServicio.listarPrestamos());
        tablaPrestamos.setItems(listaPrestamos);
    }

    @FXML
    private void registrarPrestamo() {
        try {
            int idUsuario = Integer.parseInt(usuarioIdField.getText());
            int idLibro = Integer.parseInt(libroIdField.getText());
            LocalDate fechaPrestamo = fechaPrestamoPicker.getValue();
            LocalDate fechaDevolucion = fechaDevolucionPicker.getValue();

            Prestamo prestamo = new Prestamo();
            prestamo.setIdUsuario(idUsuario);
            prestamo.setIdLibro(idLibro);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);

            prestamoServicio.registrarPrestamo(prestamo);
            cargarPrestamos();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Los IDs deben ser numéricos");
        }
    }

    @FXML
    private void actualizarPrestamo() {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                seleccionado.setIdUsuario(Integer.parseInt(usuarioIdField.getText()));
                seleccionado.setIdLibro(Integer.parseInt(libroIdField.getText()));
                seleccionado.setFechaPrestamo(fechaPrestamoPicker.getValue());
                seleccionado.setFechaDevolucion(fechaDevolucionPicker.getValue());

                prestamoServicio.actualizarPrestamo(seleccionado);
                cargarPrestamos();
                limpiarCampos();
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Los IDs deben ser numéricos");
            }
        }
    }

    @FXML
    private void eliminarPrestamo() {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            prestamoServicio.eliminarPrestamo(seleccionado.getIdPrestamo());
            cargarPrestamos();
            limpiarCampos();
        }
    }

    @FXML
    private void limpiarCampos() {
        usuarioIdField.clear();
        libroIdField.clear();
        fechaPrestamoPicker.setValue(null);
        fechaDevolucionPicker.setValue(null);
        tablaPrestamos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
