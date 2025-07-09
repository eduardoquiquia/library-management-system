package com.biblioteca.modelo;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioPublicacion;
    private int stock;

    public Libro() {}

    public Libro(int idLibro, String titulo, String autor, String editorial, int anioPublicacion, int stock) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.stock = stock;
    }

    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) {
        int currentYear = java.time.Year.now().getValue();
        if (anioPublicacion < 1000 || anioPublicacion > currentYear) {
            throw new IllegalArgumentException("El año de publicación es inválido.");
        }
        this.anioPublicacion = anioPublicacion;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }
}
