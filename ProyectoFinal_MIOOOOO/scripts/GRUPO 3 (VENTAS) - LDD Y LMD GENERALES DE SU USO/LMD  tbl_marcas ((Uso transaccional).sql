-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2025 a las 20:17:34
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sig2k25`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_marcas`
--

CREATE TABLE `tbl_marcas` (
  `marCodigo` int(11) NOT NULL,
  `marNombre` varchar(60) DEFAULT NULL,
  `marExistencias` int(11) DEFAULT NULL,
  `marPrecios` double DEFAULT NULL,
  `marEstatus` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `tbl_marcas`
--

INSERT INTO `tbl_marcas` (`marCodigo`, `marNombre`, `marExistencias`, `marPrecios`, `marEstatus`) VALUES
(10, 'Nissan', 3, 206, '1'),
(11, 'canalitos delicias', 10, 12.5, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_marcas`
--
ALTER TABLE `tbl_marcas`
  ADD PRIMARY KEY (`marCodigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_marcas`
--
ALTER TABLE `tbl_marcas`
  MODIFY `marCodigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
