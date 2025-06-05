CREATE TABLE IF NOT EXISTS perfiles(
    id_perfil INT(10) NOT NULL,
    nombre_perfil VARCHAR(85),
    estatus_perfil VARCHAR (1),
  	PRIMARY KEY (id_perfil)
) ENGINE = INNODB CHARSET =latin1;

CREATE TABLE IF NOT EXISTS relusuapl(
  id_relusuapl int(10) NOT NULL,
  id_aplicacion int(5) NOT NULL,
  id_usuario int(11) NOT NULL,
  der_insertar varchar(1) NOT NULL,
  der_editar varchar(1) NOT NULL,
  der_eliminar varchar(1) NOT NULL,
  der_imprimir varchar(1) NOT NULL,
  fecha varchar(50) NOT NULL,
    PRIMARY KEY (id_relusuapl),
    FOREIGN KEY (id_aplicacion) references aplicacion(id_aplicacion)
) ENGINE = INNODB CHARSET =latin1;