CREATE TABLE IF NOT EXISTS compra_cpp(
    no_compra INT AUTO_INCREMENT,
    nombre_usuario VARCHAR(60),
    apellido_usuario VARCHAR(60),
    id_proveedor INT,
    producto VARCHAR(60),
    cantidad INT,
    precio INT,
    saldo_anterior INT,
    plazo INT,
    total INT,
    PRIMARY KEY (no_compra),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)) 
ENGINE = INNODB CHARACTER SET = LATIN1;
