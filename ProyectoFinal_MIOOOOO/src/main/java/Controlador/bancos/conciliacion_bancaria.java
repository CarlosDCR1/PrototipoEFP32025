package Controlador.bancos;

// Mishel

import java.time.LocalDateTime;

// Modelo para tabla conciliacion_bancaria
public class conciliacion_bancaria {
    // Identificador único de la conciliación bancaria
    private int id_conciliacion;
    // Identificador de la cuenta bancaria asociada
    private int id_cuenta;
    // Identificador del movimiento bancario relacionado
    private int id_movimiento_bancario;
    // Fecha en que se realizó la conciliación
    private LocalDateTime fecha;
    // Saldo registrado antes de la conciliación
    private float saldo;
    // Saldo actualizado después de la conciliación
    private float saldo_actualizado;
    // Estado de la conciliación (por ejemplo: pendiente, conciliado, etc.)
    private String status;

    // Constructor vacío
    public conciliacion_bancaria() {
    }

    // Constructor con todos los atributos
    public conciliacion_bancaria(int id_conciliacion, int id_cuenta, int id_movimiento_bancario, LocalDateTime fecha, float saldo, float saldo_actualizado, String status) {
        this.id_conciliacion = id_conciliacion;
        this.id_cuenta = id_cuenta;
        this.id_movimiento_bancario = id_movimiento_bancario;
        this.fecha = fecha;
        this.saldo = saldo;
        this.saldo_actualizado = saldo_actualizado;
        this.status = status;
    }

    // Métodos getter y setter para cada atributo

    public int getId_conciliacion() {
        return id_conciliacion;
    }

    public void setId_conciliacion(int id_conciliacion) {
        this.id_conciliacion = id_conciliacion;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    
    public int getId_movimiento_bancario() {
        return id_movimiento_bancario;
    }

    public void setId_movimiento_bancario(int id_movimiento_bancario) {
        this.id_movimiento_bancario = id_movimiento_bancario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo_actualizado() {
        return saldo_actualizado;
    }

    public void setSaldo_actualizado(float saldo_actualizado) {
        this.saldo_actualizado = saldo_actualizado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Representación en forma de cadena del objeto conciliacion_bancaria
    @Override
    public String toString() {
        return "conciliacion_bancaria{" + "id_conciliacion=" + id_conciliacion + ", id_cuenta=" + id_cuenta + ", id_movimiento_bancario=" + id_movimiento_bancario + ", fecha=" + fecha + ", saldo=" + saldo + ", saldo_actualizado=" + saldo_actualizado + ", status=" + status + '}';
    }
}
