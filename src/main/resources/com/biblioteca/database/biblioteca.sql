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

-- Ejemplos de Usuarios
INSERT INTO Usuario (nombre, apellido, email, telefono) VALUES
('Juan', 'Pérez', 'juan.perez@example.com', '987654321'),
('María', 'García', 'maria.garcia@example.com', '986543210'),
('Luis', 'Ramírez', 'luis.ramirez@example.com', '985432109'),
('Ana', 'Torres', 'ana.torres@example.com', '984321098'),
('Carlos', 'Fernández', 'carlos.fernandez@example.com', '983210987'),
('Lucía', 'Martínez', 'lucia.martinez@example.com', '982109876'),
('Jorge', 'López', 'jorge.lopez@example.com', '981098765'),
('Elena', 'Mendoza', 'elena.mendoza@example.com', '980987654'),
('Pedro', 'Sánchez', 'pedro.sanchez@example.com', '979876543'),
('Laura', 'Castro', 'laura.castro@example.com', '978765432'),
('Diego', 'Rojas', 'diego.rojas@example.com', '977654321'),
('Rosa', 'Delgado', 'rosa.delgado@example.com', '976543210'),
('Andrés', 'Gómez', 'andres.gomez@example.com', '975432109'),
('Patricia', 'Herrera', 'patricia.herrera@example.com', '974321098'),
('Miguel', 'Ortega', 'miguel.ortega@example.com', '973210987'),
('Diana', 'Vargas', 'diana.vargas@example.com', '972109876'),
('Raúl', 'Morales', 'raul.morales@example.com', '971098765'),
('Natalia', 'Cruz', 'natalia.cruz@example.com', '970987654'),
('Alberto', 'Pineda', 'alberto.pineda@example.com', '969876543'),
('Camila', 'Salazar', 'camila.salazar@example.com', '968765432'),
('Sergio', 'Jiménez', 'sergio.jimenez@example.com', '967654321'),
('Valeria', 'Flores', 'valeria.flores@example.com', '966543210'),
('Esteban', 'Navarro', 'esteban.navarro@example.com', '965432109'),
('Isabel', 'Luna', 'isabel.luna@example.com', '964321098'),
('Tomás', 'Silva', 'tomas.silva@example.com', '963210987'),
('Marta', 'Peña', 'marta.pena@example.com', '962109876'),
('Ricardo', 'Reyes', 'ricardo.reyes@example.com', '961098765'),
('Fernanda', 'Aguilar', 'fernanda.aguilar@example.com', '960987654'),
('Pablo', 'Ibarra', 'pablo.ibarra@example.com', '959876543'),
('Andrea', 'Campos', 'andrea.campos@example.com', '958765432');

-- Ejemplo de libros
INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, disponible) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967, TRUE),
('El amor en los tiempos del cólera', 'Gabriel García Márquez', 'Oveja Negra', 1985, TRUE),
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Francisco de Robles', 1605, TRUE),
('La sombra del viento', 'Carlos Ruiz Zafón', 'Planeta', 2001, TRUE),
('1984', 'George Orwell', 'Secker & Warburg', 1949, TRUE),
('Rebelión en la granja', 'George Orwell', 'Secker & Warburg', 1945, TRUE),
('Crónica de una muerte anunciada', 'Gabriel García Márquez', 'Oveja Negra', 1981, TRUE),
('Ficciones', 'Jorge Luis Borges', 'Sur', 1944, TRUE),
('Rayuela', 'Julio Cortázar', 'Sudamericana', 1963, TRUE),
('Pedro Páramo', 'Juan Rulfo', 'FCE', 1955, TRUE),
('La ciudad y los perros', 'Mario Vargas Llosa', 'Seix Barral', 1963, TRUE),
('Conversación en La Catedral', 'Mario Vargas Llosa', 'Seix Barral', 1969, TRUE),
('La casa verde', 'Mario Vargas Llosa', 'Seix Barral', 1966, TRUE),
('Los detectives salvajes', 'Roberto Bolaño', 'Anagrama', 1998, TRUE),
('2666', 'Roberto Bolaño', 'Anagrama', 2004, TRUE),
('El túnel', 'Ernesto Sabato', 'Sur', 1948, TRUE),
('Sobre héroes y tumbas', 'Ernesto Sabato', 'Losada', 1961, TRUE),
('El Aleph', 'Jorge Luis Borges', 'Losada', 1949, TRUE),
('Ensayo sobre la ceguera', 'José Saramago', 'Caminho', 1995, TRUE),
('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943, TRUE),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Bloomsbury', 1997, TRUE),
('Harry Potter y la cámara secreta', 'J.K. Rowling', 'Bloomsbury', 1998, TRUE),
('El nombre del viento', 'Patrick Rothfuss', 'DAW Books', 2007, TRUE),
('La ladrona de libros', 'Markus Zusak', 'Picador', 2005, TRUE),
('Orgullo y prejuicio', 'Jane Austen', 'T. Egerton', 1813, TRUE),
('Matar a un ruiseñor', 'Harper Lee', 'J. B. Lippincott & Co.', 1960, TRUE),
('Drácula', 'Bram Stoker', 'Archibald Constable', 1897, TRUE),
('Frankenstein', 'Mary Shelley', 'Lackington, Hughes', 1818, TRUE),
('El retrato de Dorian Gray', 'Oscar Wilde', 'Ward, Lock & Co.', 1890, TRUE),
('El código Da Vinci', 'Dan Brown', 'Doubleday', 2003, TRUE);

-- Ejemplo de prestamos
INSERT INTO Prestamo (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES
(1, 5, '2025-06-01', '2025-06-15'),
(2, 3, '2025-06-02', NULL),
(3, 7, '2025-06-03', NULL),
(4, 10, '2025-06-05', '2025-06-19'),
(5, 1, '2025-06-06', '2025-06-20'),
(6, 8, '2025-06-07', NULL),
(7, 2, '2025-06-08', NULL),
(8, 12, '2025-06-10', '2025-06-24'),
(9, 15, '2025-06-11', NULL),
(10, 18, '2025-06-12', '2025-06-26'),
(11, 20, '2025-06-13', NULL),
(12, 25, '2025-06-14', NULL),
(13, 6, '2025-06-15', NULL),
(14, 9, '2025-06-16', NULL),
(15, 11, '2025-06-17', '2025-07-01'),
(16, 13, '2025-06-18', NULL),
(17, 14, '2025-06-19', NULL),
(18, 17, '2025-06-20', '2025-07-04'),
(19, 19, '2025-06-21', NULL),
(20, 22, '2025-06-22', NULL),
(21, 24, '2025-06-23', NULL),
(22, 26, '2025-06-24', '2025-07-08'),
(23, 28, '2025-06-25', NULL),
(24, 29, '2025-06-26', NULL),
(25, 30, '2025-06-27', NULL),
(26, 4, '2025-06-28', NULL),
(27, 16, '2025-06-29', NULL),
(28, 23, '2025-06-30', NULL),
(29, 27, '2025-07-01', NULL),
(30, 21, '2025-07-02', NULL);