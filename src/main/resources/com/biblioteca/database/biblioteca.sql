-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS BibliotecaDB;
USE BibliotecaDB;

-- Tabla de Usuarios (Administradores y lectores)
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL DEFAULT 'usuario123',
    tipo ENUM('Administrador', 'Lector') NOT NULL DEFAULT 'Lector',
    telefono VARCHAR(20)
);

-- Tabla de Libros
CREATE TABLE Libro (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    editorial VARCHAR(100),
    anio_publicacion INT,
    stock INT NOT NULL DEFAULT 1
);

-- Tabla de Préstamos
CREATE TABLE Prestamo (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    estado ENUM('Prestado', 'Devuelto') DEFAULT 'Prestado',
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_libro) REFERENCES Libro(id_libro)
);

-- Datos de prueba para Usuarios
INSERT INTO Usuario (nombre, apellido, email, password, tipo, telefono) VALUES
('Juan', 'Pérez', 'juan.perez@gmail.com', 'hashed_pass_123', 'Administrador', '987654321'),
('María', 'Gómez', 'maria.gomez@gmail.com', 'hashed_pass_456', 'Lector', '912345678'),
('Luis', 'Ramírez', 'luis.ramirez@gmail.com', 'hashed_pass_789', 'Lector', '956789012'),
('Ana', 'Torres', 'ana.torres@gmail.com', 'hashed_pass_321', 'Administrador', '924681357'),
('Carlos', 'Mendoza', 'carlos.mendoza@gmail.com', 'hashed_pass_654', 'Lector', '998877665'),
('Lucía', 'Fernández', 'lucia.fernandez@gmail.com', 'hashed_pass_111', 'Lector', '981223344'),
('Pedro', 'López', 'pedro.lopez@gmail.com', 'hashed_pass_222', 'Administrador', '955112233'),
('Sofía', 'Castillo', 'sofia.castillo@gmail.com', 'hashed_pass_333', 'Lector', '984561234'),
('Diego', 'Vargas', 'diego.vargas@gmail.com', 'hashed_pass_444', 'Lector', '912008800'),
('Carmen', 'Ruiz', 'carmen.ruiz@gmail.com', 'hashed_pass_555', 'Administrador', '923456789'),
('José', 'Herrera', 'jose.herrera@gmail.com', 'hashed_pass_666', 'Lector', '934567890'),
('Elena', 'Morales', 'elena.morales@gmail.com', 'hashed_pass_777', 'Lector', '956789034'),
('Raúl', 'Sánchez', 'raul.sanchez@gmail.com', 'hashed_pass_888', 'Lector', '975312468'),
('Natalia', 'Ortega', 'natalia.ortega@gmail.com', 'hashed_pass_999', 'Administrador', '901234567'),
('Miguel', 'Cruz', 'miguel.cruz@gmail.com', 'hashed_pass_101', 'Lector', '902345678'),
('Camila', 'Núñez', 'camila.nunez@gmail.com', 'hashed_pass_102', 'Lector', '903456789'),
('Jorge', 'Silva', 'jorge.silva@gmail.com', 'hashed_pass_103', 'Administrador', '913456789'),
('Isabel', 'Paredes', 'isabel.paredes@gmail.com', 'hashed_pass_104', 'Lector', '923456123'),
('Álvaro', 'Delgado', 'alvaro.delgado@gmail.com', 'hashed_pass_105', 'Lector', '933456987'),
('Rosa', 'Campos', 'rosa.campos@gmail.com', 'hashed_pass_106', 'Administrador', '943456789');


-- Datos de prueba para Libros
INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, stock) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'Editorial Sudamericana', 1967, 5),
('1984', 'George Orwell', 'Secker & Warburg', 1949, 3),
('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943, 10),
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 1605, 2),
('La sombra del viento', 'Carlos Ruiz Zafón', 'Planeta', 2001, 4),
('Rayuela', 'Julio Cortázar', 'Editorial Sudamericana', 1963, 6),
('Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 1953, 5),
('Crónica de una muerte anunciada', 'Gabriel García Márquez', 'Editorial Oveja Negra', 1981, 7),
('El amor en los tiempos del cólera', 'Gabriel García Márquez', 'Editorial Oveja Negra', 1985, 6),
('Ensayo sobre la ceguera', 'José Saramago', 'Santillana', 1995, 4),
('Los juegos del hambre', 'Suzanne Collins', 'Scholastic Press', 2008, 8),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Bloomsbury', 1997, 10),
('Orgullo y prejuicio', 'Jane Austen', 'T. Egerton', 1813, 3),
('Matar a un ruiseñor', 'Harper Lee', 'J. B. Lippincott & Co.', 1960, 2),
('El alquimista', 'Paulo Coelho', 'HarperTorch', 1988, 6),
('Los hombres me explican cosas', 'Rebecca Solnit', 'Editorial Capitán Swing', 2014, 4),
('Sapiens: De animales a dioses', 'Yuval Noah Harari', 'Debate', 2011, 9),
('Breves respuestas a las grandes preguntas', 'Stephen Hawking', 'Editorial Crítica', 2018, 5),
('El código Da Vinci', 'Dan Brown', 'Doubleday', 2003, 7),
('La chica del tren', 'Paula Hawkins', 'Planeta', 2015, 5);
