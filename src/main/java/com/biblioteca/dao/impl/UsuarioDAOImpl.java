package com.biblioteca.dao.impl;

import com.biblioteca.conexion.ConexionBD;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefono());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setIdUsuario(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = crearUsuarioDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioPorNombreApellido(String nombre, String apellido) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE nombre = ? AND apellido = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = crearUsuarioDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioPorEmailYPassword(String email, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE email = ? AND password = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = crearUsuarioDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = crearUsuarioDesdeResultSet(rs);
                lista.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre = ?, apellido = ?, email = ?, telefono = ?, password = ?, tipo = ? WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, usuario.getTipoUsuario());
            stmt.setInt(7, usuario.getIdUsuario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("tipo"),
                rs.getString("telefono")
        );
    }
}
