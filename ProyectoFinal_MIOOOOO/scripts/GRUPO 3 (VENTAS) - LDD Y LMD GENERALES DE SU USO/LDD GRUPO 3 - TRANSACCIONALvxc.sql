-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2025 a las 16:58:01
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
-- Estructura de tabla para la tabla `transaccionalvxc`
--

CREATE TABLE `transaccionalvxc` (
  `no_factura` int(11) NOT NULL,
  `no_venta` varchar(8) NOT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `nombre_cliente` varchar(60) DEFAULT NULL,
  `apellido_cliente` varchar(60) DEFAULT NULL,
  `proCodigo` int(11) DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `proPrecios` double(60,2) DEFAULT NULL,
  `saldo_actual` decimal(12,2) DEFAULT NULL,
  `proNombre` varchar(60) DEFAULT NULL,
  `dias_credito` int(11) DEFAULT NULL,
  `total` decimal(12,2) DEFAULT NULL,
  `fecha_venta` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `transaccionalvxc`
--

INSERT INTO `transaccionalvxc` (`no_factura`, `no_venta`, `id_vendedor`, `nombre_cliente`, `apellido_cliente`, `proCodigo`, `cantidad`, `proPrecios`, `saldo_actual`, `proNombre`, `dias_credito`, `total`, `fecha_venta`) VALUES
(6, '93', 3, 'Mailo', 'Melendez', 3034, 1, 10.00, 200.00, 'UNIDAD MANGO', 12, 210.00, '2025-05-22 08:48:38'),
(8, '99', 3, 'Cristian', 'Sipac', 2026, 1, 500.00, 250.00, ' PUYASO RES IMPORTADO EMPACADO', 12, 750.00, '2025-05-22 08:49:00'),
(14, '88', 3, 'Pingu', 'Calderon', 3032, 2, 20.00, 0.00, 'LIBRA DE FRESAS', 7, 40.00, '2025-05-22 08:50:44');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `transaccionalvxc`
--
ALTER TABLE `transaccionalvxc`
  ADD PRIMARY KEY (`no_factura`,`no_venta`),
  ADD KEY `id_vendedor` (`id_vendedor`),
  ADD KEY `proCodigo` (`proCodigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
