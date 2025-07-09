package com.biblioteca.modelo;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private int idUsuario;
    private int idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado; // Nuevo campo

    public Prestamo() {}

    public Prestamo(int idPrestamo, int idUsuario, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estado) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (estado == null || (!estado.equals("Prestado") && !estado.equals("Devuelto"))) {
            throw new IllegalArgumentException("El estado debe ser 'Prestado' o 'Devuelto'.");
        }
        this.estado = estado;
    }
}
