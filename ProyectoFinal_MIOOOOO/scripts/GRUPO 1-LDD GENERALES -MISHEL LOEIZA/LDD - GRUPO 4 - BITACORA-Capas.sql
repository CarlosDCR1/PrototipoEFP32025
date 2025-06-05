CREATE TABLE `bitacora` (
  `id_bitacora` int auto_increment,
  `id_usuario` int(11) NOT NULL,
  `id_aplicacion` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `accion` varchar(50) DEFAULT NULL,
  `nombre_pc` varchar(50) DEFAULT NULL,
  primary key (id_bitacora)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;