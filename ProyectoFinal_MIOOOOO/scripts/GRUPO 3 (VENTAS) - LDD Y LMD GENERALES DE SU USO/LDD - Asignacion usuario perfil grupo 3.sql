-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-04-2025 a las 00:24:17
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
-- Base de datos: `seguridadumg`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relperfapl`
--

CREATE TABLE `relperfapl` (
  `aplicacion_codigo` int(5) NOT NULL,
  `perfil_codigo` int(5) NOT NULL,
  `consultar_rpa` varchar(1) DEFAULT NULL,
  `actualizar_rpa` varchar(1) DEFAULT NULL,
  `eliminar_rpa` varchar(1) DEFAULT NULL,
  `imprimir_rpa` varchar(1) DEFAULT NULL,
  `insertar_rpa` varchar(1) DEFAULT NULL,
  `fecha_rpa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `relperfapl`
--
ALTER TABLE `relperfapl`
  ADD PRIMARY KEY (`aplicacion_codigo`,`perfil_codigo`),
  ADD KEY `perfil_codigo` (`perfil_codigo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `relperfapl`
--
ALTER TABLE `relperfapl`
  ADD CONSTRAINT `relperfapl_ibfk_1` FOREIGN KEY (`aplicacion_codigo`) REFERENCES `aplicacion` (`id_aplicacion`),
  ADD CONSTRAINT `relperfapl_ibfk_2` FOREIGN KEY (`perfil_codigo`) REFERENCES `perfiles` (`id_perfil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
