package com.biblioteca.servicio;

import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.dao.impl.PrestamoDAOImpl;
import com.biblioteca.modelo.Prestamo;

import java.util.List;

public class PrestamoServicio {
    private final PrestamoDAO prestamoDAO;

    public PrestamoServicio() {
        this.prestamoDAO = new PrestamoDAOImpl();
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamoDAO.registrarPrestamo(prestamo);
    }

    public Prestamo buscarPrestamoPorId(int id) {
        return prestamoDAO.obtenerPrestamoPorId(id);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.listarPrestamos();
    }

    public List<Prestamo> listarPrestamosPorUsuario(int idUsuario) {
        return prestamoDAO.listarPrestamosPorUsuario(idUsuario);
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        prestamoDAO.actualizarPrestamo(prestamo);
    }

    public void eliminarPrestamo(int id) {
        prestamoDAO.eliminarPrestamo(id);
    }
}
