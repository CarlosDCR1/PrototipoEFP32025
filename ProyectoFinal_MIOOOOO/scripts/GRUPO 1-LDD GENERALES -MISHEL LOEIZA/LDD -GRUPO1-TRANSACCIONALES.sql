CREATE TABLE `cuentas_bancarias` (
  `id_cuenta` int(11) NOT NULL,
  `id_banco` int(11) NOT NULL,
  `id_tipo_cuenta` int(11) NOT NULL,
  `id_tipo_moneda` int(11) NOT NULL,
  `saldo` decimal(15,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `movimientos_bancarios` (
  `id_movimiento_bancario` int(11) NOT NULL,
  `id_cuenta` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo_saldo` varchar(8) DEFAULT NULL COMMENT 'Acreedor o Deudor',
  `monto` decimal(10,2) DEFAULT NULL COMMENT 'Monto del movimiento',
  `saldo_actualizado` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `detalle_movimientos_bancarios` (
  `id_detalle` int(11) NOT NULL,
  `id_movimiento_bancario` int(11) NOT NULL,
  `id_tipo_operacion` int(11) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `monto` decimal(15,2) NOT NULL CHECK (`monto` > 0),
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


CREATE TABLE `conciliacion_bancaria` (
  `id_conciliacion` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `id_movimiento_bancario` int(11) NOT NULL,
  `fecha` date NOT NULL DEFAULT curdate(),
  `saldo` decimal(15,2) NOT NULL DEFAULT 0.00,
  `saldo_actualizado` decimal(10,2) NOT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT 'CONCILIADO o PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


ALTER TABLE `cuentas_bancarias`
  ADD PRIMARY KEY (`id_cuenta`),
  ADD KEY `id_banco` (`id_banco`),
  ADD KEY `id_tipo_cuenta` (`id_tipo_cuenta`),
  ADD KEY `id_tipo_moneda` (`id_tipo_moneda`);


ALTER TABLE `movimientos_bancarios`
  ADD PRIMARY KEY (`id_movimiento_bancario`),
  ADD KEY `id_cuenta` (`id_cuenta`);


  ALTER TABLE `detalle_movimientos_bancarios`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_movimiento_bancario` (`id_movimiento_bancario`),
  ADD KEY `id_tipo_operacion` (`id_tipo_operacion`),
  ADD KEY `id_tipo_pago` (`id_tipo_pago`);


ALTER TABLE `conciliacion_bancaria`
  ADD PRIMARY KEY (`id_conciliacion`),
  ADD KEY `id_cuenta` (`id_cuenta`),
  ADD KEY `id_movimiento_bancario` (`id_movimiento_bancario`);

