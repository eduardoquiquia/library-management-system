package com.biblioteca.controlador.Lector;

import com.biblioteca.modelo.Usuario;
import com.biblioteca.util.Sesion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.servicio.PrestamoServicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class PrestamoControlador {

    @FXML private TableView<Prestamo> tablaPrestamos;
    @FXML private TableColumn<Prestamo, Integer> colId, colUsuarioId, colLibroId;
    @FXML private TableColumn<Prestamo, LocalDate> colFechaPrestamo, colFechaDevolucion;
    @FXML private TextField txtBuscarFecha;
    @FXML private Button btnBuscar, btnLimpiar;
    @FXML private Label lblResultados;

    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();
    private final ObservableList<Prestamo> listaPrestamosFiltrados = FXCollections.observableArrayList();
    private List<Prestamo> prestamosOriginales;

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdPrestamo()).asObject());
        colUsuarioId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdUsuario()).asObject());
        colLibroId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIdLibro()).asObject());
        colFechaPrestamo.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getFechaPrestamo()));
        colFechaDevolucion.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getFechaDevolucion()));

        // Configurar formato de fecha para las columnas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colFechaPrestamo.setCellFactory(column -> new TableCell<Prestamo, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(formatter));
                }
            }
        });

        colFechaDevolucion.setCellFactory(column -> new TableCell<Prestamo, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(formatter));
                }
            }
        });

        // Obtener el usuario actual desde la clase Sesion
        Usuario lector = Sesion.obtenerUsuario();

        if (lector != null && lector.getTipoUsuario().equals("Lector")) {
            // Cargar solo los préstamos del lector
            prestamosOriginales = prestamoServicio.listarPrestamosPorUsuario(lector.getIdUsuario());
            listaPrestamos.setAll(prestamosOriginales);
            tablaPrestamos.setItems(listaPrestamos);
            actualizarEtiquetaResultados(prestamosOriginales.size());
        } else {
            // En caso de que no se haya iniciado sesión correctamente, muestra vacío
            prestamosOriginales = List.of();
            listaPrestamos.clear();
            tablaPrestamos.setItems(listaPrestamos);
            actualizarEtiquetaResultados(0);
        }

        // Configurar placeholder para el campo de búsqueda
        txtBuscarFecha.setPromptText("dd/MM/yyyy");
    }

    @FXML
    private void buscarPorFecha() {
        String fechaTexto = txtBuscarFecha.getText().trim();

        if (fechaTexto.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingresa una fecha para buscar.");
            return;
        }

        try {
            // Parsear la fecha en formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaBuscada = LocalDate.parse(fechaTexto, formatter);

            // Filtrar préstamos por fecha de préstamo
            List<Prestamo> prestamosFiltrados = prestamosOriginales.stream()
                    .filter(prestamo -> prestamo.getFechaPrestamo().equals(fechaBuscada))
                    .collect(Collectors.toList());

            // Actualizar la tabla con los resultados filtrados
            listaPrestamosFiltrados.setAll(prestamosFiltrados);
            tablaPrestamos.setItems(listaPrestamosFiltrados);

            // Actualizar etiqueta de resultados
            actualizarEtiquetaResultados(prestamosFiltrados.size());

            if (prestamosFiltrados.isEmpty()) {
                mostrarInformacion("Búsqueda", "No se encontraron préstamos para la fecha: " + fechaTexto);
            }

        } catch (DateTimeParseException e) {
            mostrarAlerta("Error de formato", "El formato de fecha debe ser dd/MM/yyyy (ejemplo: 09/09/2025)");
        }
    }

    @FXML
    private void limpiarBusqueda() {
        txtBuscarFecha.clear();
        tablaPrestamos.setItems(listaPrestamos);
        actualizarEtiquetaResultados(prestamosOriginales.size());
    }

    @FXML
    private void buscarPorFechaDevolucion() {
        String fechaTexto = txtBuscarFecha.getText().trim();

        if (fechaTexto.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingresa una fecha para buscar.");
            return;
        }

        try {
            // Parsear la fecha en formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaBuscada = LocalDate.parse(fechaTexto, formatter);

            // Filtrar préstamos por fecha de devolución
            List<Prestamo> prestamosFiltrados = prestamosOriginales.stream()
                    .filter(prestamo -> prestamo.getFechaDevolucion() != null &&
                            prestamo.getFechaDevolucion().equals(fechaBuscada))
                    .collect(Collectors.toList());

            // Actualizar la tabla con los resultados filtrados
            listaPrestamosFiltrados.setAll(prestamosFiltrados);
            tablaPrestamos.setItems(listaPrestamosFiltrados);

            // Actualizar etiqueta de resultados
            actualizarEtiquetaResultados(prestamosFiltrados.size());

            if (prestamosFiltrados.isEmpty()) {
                mostrarInformacion("Búsqueda", "No se encontraron préstamos con fecha de devolución: " + fechaTexto);
            }

        } catch (DateTimeParseException e) {
            mostrarAlerta("Error de formato", "El formato de fecha debe ser dd/MM/yyyy (ejemplo: 09/09/2025)");
        }
    }

    public void cargarPrestamos() {
        Usuario lector = Sesion.obtenerUsuario();
        if (lector != null && lector.getTipoUsuario().equals("Lector")) {
            prestamosOriginales = prestamoServicio.listarPrestamosPorUsuario(lector.getIdUsuario());
            listaPrestamos.setAll(prestamosOriginales);
            tablaPrestamos.setItems(listaPrestamos);
            actualizarEtiquetaResultados(prestamosOriginales.size());
        }
    }

    private void actualizarEtiquetaResultados(int cantidad) {
        if (lblResultados != null) {
            lblResultados.setText("Mostrando " + cantidad + " préstamo(s)");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
