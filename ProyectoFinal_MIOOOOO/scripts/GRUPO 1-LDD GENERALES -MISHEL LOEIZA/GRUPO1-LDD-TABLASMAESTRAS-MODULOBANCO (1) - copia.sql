
CREATE TABLE `banco` (
  `id_banco` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `sede` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `tipo_cuenta` (
  `id_tipo_cuenta` int(11) NOT NULL,
  `tipo_cuenta` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



CREATE TABLE `tipo_operacion_bancaria` (
  `id_tipo_operacion` int(11) NOT NULL,
  `tipo_operacion` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `tipo_pago` (
  `id_tipo_pago` int(11) NOT NULL,
  `tipo_pago` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `tasas_cambio_diario` (
  `id_tasa_cambio_diario` int(11) NOT NULL,
  `valor_promedio_dia` decimal(10,2) DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

	CREATE TABLE `tipo_moneda` (
  `id_tipo_moneda` int(11) NOT NULL,
  `tipo_moneda` varchar(5) NOT NULL,
  `id_tasa_cambio_diario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




