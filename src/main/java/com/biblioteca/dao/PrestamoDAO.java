package com.biblioteca.dao;

import com.biblioteca.modelo.Prestamo;
import java.util.List;

public interface PrestamoDAO {
    void registrarPrestamo(Prestamo prestamo);
    Prestamo obtenerPrestamoPorId(int id);
    List<Prestamo> listarPrestamos();
    void actualizarPrestamo(Prestamo prestamo);
    void eliminarPrestamo(int id);
    List<Prestamo> listarPrestamosPorUsuario(int idUsuario);
}
