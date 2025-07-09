package com.biblioteca.servicio;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.dao.impl.LibroDAOImpl;
import com.biblioteca.modelo.Libro;

import java.util.List;

public class LibroServicio {
    private final LibroDAO libroDAO;

    public LibroServicio() {
        this.libroDAO = new LibroDAOImpl();
    }

    public void registrarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título del libro es obligatorio");
        }
        libroDAO.agregarLibro(libro);
    }

    public Libro buscarLibroPorId(int id) {
        return libroDAO.obtenerLibroPorId(id);
    }

    public List<Libro> listarLibros() {
        return libroDAO.listarLibros();
    }

    public Libro buscarLibrosPorTitulo(String titulo) {
        return libroDAO.buscarPorTitulo(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libroDAO.buscarPorAutor(autor);
    }

    public List<Libro> buscarLibrosPorEditorial(String editorial) {
        return libroDAO.buscarPorEditorial(editorial);
    }

    public void actualizarLibro(Libro libro) {
        libroDAO.actualizarLibro(libro);
    }

    public void eliminarLibro(int id) {
        libroDAO.eliminarLibro(id);
    }
}
