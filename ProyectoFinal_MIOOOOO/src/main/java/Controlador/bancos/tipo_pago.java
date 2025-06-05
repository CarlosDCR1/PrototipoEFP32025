package Controlador.bancos;

// Ruddyard Castro 9959-23-1409
// Los nombres de los getters, setters y variables coinciden con los nombres de los campos en la base de datos.
// El nombre del archivo Java coincide con el nombre de la tabla correspondiente.
// Se utilizó la funcionalidad "Encapsulate Field" para generar automáticamente los métodos get y set.

/**
 * Clase modelo que representa la entidad `tipo_pago`.
 * Esta clase se utiliza para mapear los datos de la tabla tipo_pago en la base de datos.
 */
public class tipo_pago {

    // Atributos privados que representan las columnas de la tabla tipo_pago
    private int idTipoPago;     // Identificador único del tipo de pago
    private String tipoPago;    // Nombre o descripción del tipo de pago
    private String Status;      // Estado del tipo de pago (activo, inactivo, etc.)

    // Constructor vacío: permite crear una instancia sin inicializar atributos
    public tipo_pago() {
    }

    // Constructor con parámetros: permite crear una instancia con todos los atributos definidos
    public tipo_pago(int idTipoPago, String tipoPago, String Status) {
        this.idTipoPago = idTipoPago;
        this.tipoPago = tipoPago;
        this.Status = Status;
    }

    // Método toString: devuelve una representación en texto del objeto
    @Override
    public String toString() {
        return "tipo_pago{" + "idTipoPago=" + idTipoPago + ", tipoPago=" + tipoPago + ", Status=" + Status + '}';
    }

    // Métodos getter y setter para acceder y modificar los atributos

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
