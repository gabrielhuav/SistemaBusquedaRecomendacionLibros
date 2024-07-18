-- Eliminar la base de datos "libros" (si existe)
DROP DATABASE IF EXISTS libros;

-- Crear la base de datos "libros" con codificación UTF-8
CREATE DATABASE libros CHARACTER SET utf8 COLLATE utf8_general_ci;
-- Creación de la tabla usuario

CREATE TABLE usuario (
  idUsuario INT(11) PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  apellidoP VARCHAR(64) NOT NULL,
  apellidoM VARCHAR(64) NOT NULL,
  edad INT(3) NOT NULL,
  genero VARCHAR(64) NOT NULL,
  correo VARCHAR(64) NOT NULL UNIQUE,
  userU VARCHAR(64) NOT NULL UNIQUE,
  passwordU VARCHAR(64) NOT NULL
);

-- Creación de la tabla recomendaciontema
CREATE TABLE recomendaciontema (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  idUsuario INT(11) NOT NULL,
  idLibro VARCHAR(120) NOT NULL,
  titulo VARCHAR(120) NOT NULL,
  idAutor VARCHAR(64) NOT NULL,
  nombreAutor VARCHAR(120) NOT NULL,
  tema VARCHAR(120) NOT NULL,
  imagen VARCHAR(120) NOT NULL,
  FechaRecomendacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla recomendacionautor
CREATE TABLE recomendacionautor (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  idUsuario INT(11) NOT NULL,
  idLibro VARCHAR(120) NOT NULL,
  titulo VARCHAR(4094) NOT NULL,
  idAutor VARCHAR(64) NOT NULL,
  nombreAutor VARCHAR(120) NOT NULL,
  imagen VARCHAR(120) NOT NULL,
  FechaRecomendacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla librosfavoritos
CREATE TABLE librosfavoritos (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  idUsuario INT(11) NOT NULL,
  idLibro VARCHAR(120) NOT NULL,
  titulo VARCHAR(120) NOT NULL,
  idAutor VARCHAR(64) NOT NULL,
  nombreAutor VARCHAR(120) NOT NULL,
  yearP VARCHAR(120) NOT NULL,
  imagen VARCHAR(120) NOT NULL,
  FechaRecomendacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla gustotemas
CREATE TABLE gustotemas (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  idUsuario VARCHAR(64) NOT NULL,
  idTema VARCHAR(64) NOT NULL,
  tema VARCHAR(64) NOT NULL
);

-- Creación de la tabla gustoautor
CREATE TABLE gustoautor (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  idUsuario INT(11) NOT NULL,
  idAutor VARCHAR(64) NOT NULL,
  nombreAutor VARCHAR(64) NOT NULL
);

-- Creación de la tabla autor
CREATE TABLE autor (
  id VARCHAR(64)PRIMARY KEY,
  nombre VARCHAR(64) NOT NULL,
  mejorLibro VARCHAR(120) NOT NULL,
  fechaNacimiento VARCHAR(64) NOT NULL,
  fechaFallecimiento VARCHAR(64) NOT NULL,
  biografia VARCHAR(4096) NOT NULL
);

-- Creación de la tabla temas
CREATE TABLE temas (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  clave VARCHAR(64) NOT NULL,
  traduccion VARCHAR(64) NOT NULL
);