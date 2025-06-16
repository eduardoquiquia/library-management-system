package com.biblioteca.dao;

import com.biblioteca.modelo.Libro;
import java.util.List;

public interface LibroDAO {
    void agregarLibro(Libro libro);
    Libro obtenerLibroPorId(int id);
    List<Libro> listarLibros();
    void actualizarLibro(Libro libro);
    void eliminarLibro(int id);
    List<Libro> buscarPorTitulo(String titulo);
}