-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-04-2025 a las 01:23:11
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
-- Base de datos: `ventas_cuentas_por_cobrar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL,
  `nombre_vendedor` varchar(65) DEFAULT NULL,
  `apellido_vendedor` varchar(65) DEFAULT NULL,
  `direccion_vendedor` varchar(80) DEFAULT NULL,
  `telefono_vendedor` varchar(50) DEFAULT NULL,
  `email_vendedor` varchar(80) DEFAULT NULL,
  `comision` varchar(80) DEFAULT NULL,
  `estatus_vendedor` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`id_vendedor`, `nombre_vendedor`, `apellido_vendedor`, `direccion_vendedor`, `telefono_vendedor`, `email_vendedor`, `comision`, `estatus_vendedor`) VALUES
(1, 'Paola', 'Espinoza', 'Canalitos Delicias', '52721772', 'eandradel2@gmail.com', '2.9%', '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`id_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
