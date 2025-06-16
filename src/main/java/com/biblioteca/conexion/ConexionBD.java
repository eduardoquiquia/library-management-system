package com.biblioteca.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/BibliotecaDB";
    private static final String USUARIO = "root"; // Cambiar si es necesario
    private static final String CONTRASENA = "eduardo40";  // Cambiar si tiene contraseña

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
