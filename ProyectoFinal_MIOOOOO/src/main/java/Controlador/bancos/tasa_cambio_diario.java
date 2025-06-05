package Controlador.bancos;

import java.time.LocalDateTime;


//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 
public  class  tasa_cambio_diario{
    private int id_tasa_cambio_diario;
    private float valor_promedio_dia;
    private java.time.LocalDateTime fecha_hora; // Utilizando el tipo de fecha y hora moderno de Java
//Se uso antes uno ya no apto
    //Costructor sin sobre cargar 
    public tasa_cambio_diario() {
    }

    //constructo sobre cargado 
    public tasa_cambio_diario(int id_tasa_cambio_diario, float valor_promedio_dia, LocalDateTime fecha_hora) {
        this.id_tasa_cambio_diario = id_tasa_cambio_diario;
        this.valor_promedio_dia = valor_promedio_dia;
        this.fecha_hora = fecha_hora;
    }
/*
    sirve para proporcionar una representación de cadena legible de un objeto de la clase
    
    La anotación @Override indica que este método está sobrescribiendo un método de una superclase (en este caso, el método toString() de la clase Object)
    */
    @Override
    public String toString() {
        return "tasa_historial_banco{" + "id_tasa_cambio_diario=" + id_tasa_cambio_diario + ", valor_promedio_dia=" + valor_promedio_dia + ", fecha_hora=" + fecha_hora + '}';
    }

    public int getId_tasa_cambio_diario() {
        return id_tasa_cambio_diario;
    }

    public void setId_tasa_cambio_diario(int id_tasa_cambio_diario) {
        this.id_tasa_cambio_diario = id_tasa_cambio_diario;
    }

    public float getValor_promedio_dia() {
        return valor_promedio_dia;
    }

    public void setValor_promedio_dia(float valor_promedio_dia) {
        this.valor_promedio_dia = valor_promedio_dia;
    }

    public java.time.LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(java.time.LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }


}

