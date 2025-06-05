-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2025 a las 20:17:49
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
-- Estructura de tabla para la tabla `tbl_lineas`
--

CREATE TABLE `tbl_lineas` (
  `linCodigo` int(11) NOT NULL,
  `linNombre` varchar(60) DEFAULT NULL,
  `linPrecios` double DEFAULT NULL,
  `linEstatus` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `tbl_lineas`
--

INSERT INTO `tbl_lineas` (`linCodigo`, `linNombre`, `linPrecios`, `linEstatus`) VALUES
(10, 'honda', 50000, '1'),
(12, 'Pasteles', 150, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_lineas`
--
ALTER TABLE `tbl_lineas`
  ADD PRIMARY KEY (`linCodigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_lineas`
--
ALTER TABLE `tbl_lineas`
  MODIFY `linCodigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
