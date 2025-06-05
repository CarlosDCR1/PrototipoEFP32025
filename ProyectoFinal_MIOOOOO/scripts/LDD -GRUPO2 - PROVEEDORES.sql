CREATE TABLE IF NOT EXISTS proveedor (
    id_proveedor INT AUTO_INCREMENT,
    nombre_proveedor VARCHAR(60),
    direccion_proveedor VARCHAR(100),
    telefono_proveedor VARCHAR(20),
    email_proveedor VARCHAR(100),
    saldo_proveedor INT,
    estatus_proveedor INT,
    fecha_registro VARCHAR(20),
    plazo_limite INT,
    saldo_pendiente INT,
    PRIMARY KEY (id_proveedor)
)ENGINE=InnoDB CHARACTER SET = latin1;