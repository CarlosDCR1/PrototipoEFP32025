USE sisg2k25;
CREATE TABLE `inventario` (
  `id_inventario` int auto_increment,
  `nombre_inventario` varchar(50) NOT NULL,
  `existencias_totales_inventario` int(11) NOT NULL,
  `fecha_ultimacompra` datetime not null,
  `fecha_ultimaventa` datetime not null,
  `accion` varchar(50) DEFAULT NULL,
  `nombre_pc` varchar(50) DEFAULT NULL,  
  `estatus_inventarios` varchar(45) DEFAULT NULL,

  primary key (id_bitacora)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;