package Controlador.bancos;

// Clase que representa el detalle de los movimientos bancarios
public class detalle_movimientos_bancarios {

    // Identificador único del detalle del movimiento
    private int idDetalle;
    // Identificador del movimiento bancario al que pertenece este detalle
    private int idMovimiento;
    // Identificador del tipo de operación (por ejemplo: depósito, retiro, etc.)
    private int idTipoOperacion;
    // Identificador del tipo de pago (por ejemplo: efectivo, transferencia, cheque, etc.)
    private int idTipoPago;
    // Monto asociado al detalle del movimiento
    private float Monto;
    // Descripción del detalle del movimiento
    private String Descripcion;

    // Método para representar el objeto como una cadena de texto
    @Override
    public String toString() {
        return "detalle_movimientos_bancarios{" + "idDetalle=" + idDetalle + ", idMovimiento=" + idMovimiento + ", idTipoOperacion=" + idTipoOperacion + ", idTipoPago=" + idTipoPago + ", Monto=" + Monto + ", Descripcion=" + Descripcion + '}';
    }

    // Métodos getter y setter para cada atributo

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(int idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    // Constructor vacío
    public detalle_movimientos_bancarios() {
    }

    // Constructor con todos los atributos
    public detalle_movimientos_bancarios(int idDetalle, int idMovimiento, int idTipoOperacion, int idTipoPago, float Monto, String Descripcion) {
        this.idDetalle = idDetalle;
        this.idMovimiento = idMovimiento;
        this.idTipoOperacion = idTipoOperacion;
        this.idTipoPago = idTipoPago;
        this.Monto = Monto;
        this.Descripcion = Descripcion;
    }
}
