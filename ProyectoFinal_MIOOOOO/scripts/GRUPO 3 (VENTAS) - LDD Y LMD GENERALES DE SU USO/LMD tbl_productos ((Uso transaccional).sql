-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2025 a las 20:19:04
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
-- Estructura de tabla para la tabla `tbl_productos`
--

CREATE TABLE `tbl_productos` (
  `proCodigo` int(11) NOT NULL,
  `linCodigo` int(11) NOT NULL,
  `marCodigo` int(11) NOT NULL,
  `proNombre` varchar(60) DEFAULT NULL,
  `proPrecios` double DEFAULT NULL,
  `proExistencias` int(11) DEFAULT NULL,
  `proEstatus` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `tbl_productos`
--

INSERT INTO `tbl_productos` (`proCodigo`, `linCodigo`, `marCodigo`, `proNombre`, `proPrecios`, `proExistencias`, `proEstatus`) VALUES
(2, 12, 11, 'Pastel fresas con crema', 150, 10, '1'),
(10, 10, 10, 'Herramienta truper', 106, 5, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_productos`
--
ALTER TABLE `tbl_productos`
  ADD PRIMARY KEY (`proCodigo`),
  ADD KEY `linCodigo` (`linCodigo`),
  ADD KEY `marCodigo` (`marCodigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_productos`
--
ALTER TABLE `tbl_productos`
  MODIFY `proCodigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_productos`
--
ALTER TABLE `tbl_productos`
  ADD CONSTRAINT `tbl_productos_ibfk_1` FOREIGN KEY (`linCodigo`) REFERENCES `tbl_lineas` (`linCodigo`),
  ADD CONSTRAINT `tbl_productos_ibfk_2` FOREIGN KEY (`marCodigo`) REFERENCES `tbl_marcas` (`marCodigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
