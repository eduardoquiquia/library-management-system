package com.biblioteca.servicio;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.dao.impl.UsuarioDAOImpl;
import com.biblioteca.modelo.Usuario;

import java.util.List;

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public void registrarUsuario(Usuario usuario) {
        // Ejemplo de validación de lógica de negocio
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        usuarioDAO.agregarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
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
}
