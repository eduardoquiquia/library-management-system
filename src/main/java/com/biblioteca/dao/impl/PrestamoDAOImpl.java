package com.biblioteca.dao.impl;

import com.biblioteca.conexion.ConexionBD;
import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.modelo.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public void registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamo (id_usuario, id_libro, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setString(5, prestamo.getEstado());

            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                prestamo.setIdPrestamo(generatedKeys.getInt(1)); // AQUÍ SE USA
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prestamo obtenerPrestamoPorId(int id) {
        Prestamo prestamo = null;
        String sql = "SELECT * FROM Prestamo WHERE id_prestamo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prestamo = crearPrestamoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo prestamo = crearPrestamoDesdeResultSet(rs);
                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        String sql = "UPDATE Prestamo SET id_usuario = ?, id_libro = ?, fecha_prestamo = ?, fecha_devolucion = ?, estado = ? WHERE id_prestamo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setString(5, prestamo.getEstado());
            stmt.setInt(6, prestamo.getIdPrestamo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPrestamo(int id) {
        String sql = "DELETE FROM Prestamo WHERE id_prestamo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> listarPrestamosPorUsuario(int idUsuario) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = crearPrestamoDesdeResultSet(rs);
                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    private Prestamo crearPrestamoDesdeResultSet(ResultSet rs) throws SQLException {
        return new Prestamo(
                rs.getInt("id_prestamo"),
                rs.getInt("id_usuario"),
                rs.getInt("id_libro"),
                rs.getDate("fecha_prestamo").toLocalDate(),
                rs.getDate("fecha_devolucion").toLocalDate(),
                rs.getString("estado")
        );
    }
}
