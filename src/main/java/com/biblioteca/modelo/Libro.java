package com.biblioteca.modelo;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private int publicacion;
    private boolean disponible;

    public Libro() {}

    public Libro(int idLibro, String titulo, String autor, String editorial, int publicacion, boolean disponible) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.publicacion = publicacion;
        this.disponible = disponible;
    }

    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnioPublicacion() { return publicacion; }
    public void setAnioPublicacion(int anioPublicacion) { publicacion = anioPublicacion; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}

