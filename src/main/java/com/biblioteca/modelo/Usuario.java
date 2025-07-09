package com.biblioteca.modelo;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String tipoUsuario;
    private String telefono;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String apellido, String email, String password, String tipoUsuario, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre solo debe contener letras.");
        }
        this.nombre = nombre;
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El apellido solo debe contener letras.");
        }
        this.apellido = apellido;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Correo inválido.");
        }
        if (!email.toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("El correo debe terminar en @gmail.com");
        }
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        this.password = password;
    }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) {
        if (tipoUsuario == null || (!tipoUsuario.equals("Administrador") && !tipoUsuario.equals("Lector"))) {
            throw new IllegalArgumentException("El tipo de usuario debe ser 'Administrador' o 'Lector'.");
        }
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        if (!telefono.matches("\\d{9}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener exactamente 9 dígitos.");
        }
        this.telefono = telefono;
    }
}
