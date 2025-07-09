package com.biblioteca.dao;

import com.biblioteca.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int id);
    Usuario obtenerUsuarioPorNombreApellido(String nombre, String apellido);
    Usuario obtenerUsuarioPorEmailYPassword(String email, String password);
    List<Usuario> listarUsuarios();
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
}
