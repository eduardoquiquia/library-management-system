package com.biblioteca.util;

import com.biblioteca.modelo.Usuario;

public class Sesion {
    private static Usuario usuarioActual;

    public static void establecerUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario obtenerUsuario() {
        return usuarioActual;
    }
}