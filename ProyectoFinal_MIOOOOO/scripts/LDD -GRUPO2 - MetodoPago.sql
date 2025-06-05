CREATE TABLE IF NOT EXISTS metodo_pago(
id_metodo_pago INT AUTO_INCREMENT,
nombre_metodo_pago VARCHAR(60),
estatus_metodo_pago INT,
PRIMARY KEY (id_metodo_pago)
)ENGINE=InnoDB CHARACTER SET = latin1;
