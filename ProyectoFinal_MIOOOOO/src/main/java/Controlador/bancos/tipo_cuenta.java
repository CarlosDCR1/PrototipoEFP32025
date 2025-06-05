package Controlador.bancos;

//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 

public class tipo_cuenta {

    // Identificador único del tipo de cuenta
    private int id_tipo_cuenta;
    // Nombre del tipo de cuenta (por ejemplo: Ahorro, Corriente)
    private String tipo_cuenta;
    // Estado del tipo de cuenta (por ejemplo: activo = 1, inactivo = 0)
    private int status; 

    // Constructor vacío
    public tipo_cuenta() {
    }
// Constructor con todos los atributos
    public tipo_cuenta(int id_tipo_cuenta, String tipo_cuenta, int status) { // ← Modificado
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.status = status;
    }
// Métodos getter y setter para cada atributo
    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public int getStatus() { // ← Agregado
        return status;
    }

    public void setStatus(int estatus) { // ← Agregado
        this.status = estatus;
    }

    // Representación en forma de cadena del objeto tipo_cuenta
    @Override
    public String toString() {
        return "tipo_cuenta{" + "id_tipo_cuenta=" + id_tipo_cuenta + ", tipo_cuenta=" + tipo_cuenta + ", status=" + status + '}'; // ← Modificado
    }
}
