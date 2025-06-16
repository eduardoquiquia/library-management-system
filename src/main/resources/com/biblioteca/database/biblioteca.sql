-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS BibliotecaDB;
USE BibliotecaDB;

-- Tabla de Usuarios (lectores)
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla de Libros
CREATE TABLE Libro (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    editorial VARCHAR(100),
    anio_publicacion INT,
    disponible BOOLEAN DEFAULT TRUE
);

-- Tabla de Préstamos
CREATE TABLE Prestamo (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

-- Datos de ejemplo: Usuarios
INSERT INTO Usuario (nombre, apellido, email, telefono) VALUES
('Ana', 'Ramírez', 'ana.ramirez@email.com', '123456789'),
('Carlos', 'López', 'carlos.lopez@email.com', '987654321');

-- Datos de ejemplo: Libros
INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, disponible) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967, TRUE),
('1984', 'George Orwell', 'Secker & Warburg', 1949, TRUE),
('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943, TRUE);

-- Datos de ejemplo: Préstamos
INSERT INTO Prestamo (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES
(1, 1, '2025-06-10', NULL),
(2, 3, '2025-06-12', NULL);
