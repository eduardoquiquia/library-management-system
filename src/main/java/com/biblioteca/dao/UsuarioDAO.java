package com.biblioteca.dao;

import com.biblioteca.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int id);
    List<Usuario> listarUsuarios();
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
}