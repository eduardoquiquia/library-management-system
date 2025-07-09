package com.biblioteca.servicio;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.dao.impl.UsuarioDAOImpl;
import com.biblioteca.modelo.Usuario;

import java.util.List;

public class UsuarioServicio {
    private final UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        usuarioDAO.agregarUsuario(usuario);
    }

    public Usuario buscarPorNombreYApellido(String nombre, String apellido) {
        return usuarioDAO.obtenerUsuarioPorNombreApellido(nombre, apellido);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    public Usuario login(String email, String password) {
        return usuarioDAO.obtenerUsuarioPorEmailYPassword(email, password);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public void cambiarPassword(int idUsuario, String nuevoPassword) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuario.setPassword(nuevoPassword);
            usuarioDAO.actualizarUsuario(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    public void cambiarTipoUsuario(int idUsuario, String nuevoTipo) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuario.setTipoUsuario(nuevoTipo);
            usuarioDAO.actualizarUsuario(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}
