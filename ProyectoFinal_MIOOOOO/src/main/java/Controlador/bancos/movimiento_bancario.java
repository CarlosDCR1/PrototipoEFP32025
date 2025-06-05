package Controlador.bancos;

//Ruddyard Castro 9959-23-1409

import java.time.LocalDateTime;

// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 

public class movimiento_bancario {

    private int id_movimiento_bancario;
    private int id_cuenta; // 
    private LocalDateTime fecha;
    private String tipoSaldo;
    private float monto;
    private float saldoActualizado;

 //Costructor sin sobre cargar 
    public movimiento_bancario() {
    }
//constructo sobre cargado 
    public movimiento_bancario(int id_movimiento_bancario, int id_cuenta, LocalDateTime fecha, String tipoSaldo, float monto, float saldoActualizado) {
        this.id_movimiento_bancario = id_movimiento_bancario;
        this.id_cuenta = id_cuenta;
        this.fecha = fecha;
        this.tipoSaldo = tipoSaldo;
        this.monto = monto;
        this.saldoActualizado = saldoActualizado;
    }

    public int getId_movimiento_bancario() {
        return id_movimiento_bancario;
    }

    public void setId_movimiento_bancario(int id_movimiento_bancario) {
        this.id_movimiento_bancario = id_movimiento_bancario;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    public float getSaldoActualizado() {
        return saldoActualizado;
    }

    public void setSaldoActualizado(float saldoActualizado) {
        this.saldoActualizado = saldoActualizado;
    }
/*
    sirve para proporcionar una representación de cadena legible de un objeto de la clase
    
    La anotación @Override indica que este método está sobrescribiendo un método de una superclase (en este caso, el método toString() de la clase Object)
    */
    @Override
    public String toString() {
        return "movimiento_bancario{" + "id_movimiento_bancario=" + id_movimiento_bancario + ", id_cuenta=" + id_cuenta + ", fecha=" + fecha + ", tipoSaldo=" + tipoSaldo + ", monto=" + monto + ", saldoActualizado=" + saldoActualizado + '}';
    }
    
}
