package com.biblioteca.dao.impl;

import com.biblioteca.conexion.ConexionBD;
import com.biblioteca.dao.LibroDAO;
import com.biblioteca.modelo.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public void agregarLibro(Libro libro) {
        String query = "INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, disponible) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnioPublicacion());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                libro.setIdLibro(generatedKeys.getInt(1)); // AQUÍ SE USA
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Libro obtenerLibroPorId(int id) {
        Libro libro = null;
        String query = "SELECT * FROM Libro WHERE id_libro = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_publicacion"),
                        rs.getBoolean("disponible")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM Libro";
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_publicacion"),
                        rs.getBoolean("disponible")
                );
                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public void actualizarLibro(Libro libro) {
        String query = "UPDATE Libro SET titulo = ?, autor = ?, editorial = ?, anio_publicacion = ?, disponible = ? WHERE id_libro = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnioPublicacion());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setInt(6, libro.getIdLibro());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(int id) {
        String query = "DELETE FROM Libro WHERE id_libro = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM Libro WHERE titulo LIKE ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_publicacion"),
                        rs.getBoolean("disponible")
                );
                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
