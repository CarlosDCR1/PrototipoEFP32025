CREATE TABLE permisos_usuario (
   id_usuario int NOT NULL,
   puede_mantenimiento tinyint(1) NOT NULL,
   puede_procesos tinyint(1) NOT NULL,
   puede_eliminar tinyint(1) DEFAULT '0',
   puede_registrar tinyint(1) DEFAULT '0',
   puede_modificar tinyint(1) DEFAULT '0',
   PRIMARY KEY (id_usuario),
   CONSTRAINT permisos_usuario_ibfk_1 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

//CAMBIAR POR ID QUE ESTEN EN SU BASE

 INSERT INTO permisos_usuario (
  id_usuario, puede_mantenimiento, puede_procesos, puede_eliminar, puede_registrar, puede_modificar
) VALUES
  (2034, 1, 1, 1, 1, 1),
  (2038, 1, 0, 0, 1, 0),
  (211, 0, 1, 0, 0, 1);