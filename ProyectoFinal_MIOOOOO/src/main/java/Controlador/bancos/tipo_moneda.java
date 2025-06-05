package Controlador.bancos;

// MISHEL LOEIZA 9959-23-3457
// Nombres de los getter, setter y variables igual al nombre en base de datos
// Nombre del archivo Java como se llama la tabla 
// Se usó encapsulate field para getter y setter

public class tipo_moneda {
    private int id_tipo_moneda;
    private String tipo_moneda;
    private int id_tasa_cambio_diario;
    private double valor_tasa_cambio;
    
    public tipo_moneda() {
    }

    public tipo_moneda(int id_tipo_moneda, String tipo_moneda, int id_tasa_cambio_diario) {
        this.id_tipo_moneda = id_tipo_moneda;
        this.tipo_moneda = tipo_moneda;
        this.id_tasa_cambio_diario = id_tasa_cambio_diario;
    }

    public int getId_tipo_moneda() {
        return id_tipo_moneda;
    }

    public void setId_tipo_moneda(int id_tipo_moneda) {
        this.id_tipo_moneda = id_tipo_moneda;
    }

    public String getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(String tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public int getId_tasa_cambio_diario() {
        return id_tasa_cambio_diario;
    }

    public void setId_tasa_cambio_diario(int id_tasa_cambio_diario) {
        this.id_tasa_cambio_diario = id_tasa_cambio_diario;
    }

    @Override
    public String toString() {
        return "tipo_moneda{" + "id_tipo_moneda=" + id_tipo_moneda + ", tipo_moneda=" + tipo_moneda + ", id_tasa_cambio_diario=" + id_tasa_cambio_diario + '}';
    }

    public double getValor_tasa_cambio() {
        return valor_tasa_cambio;
    }

    public void setValor_tasa_cambio(double valor_tasa_cambio) {
        this.valor_tasa_cambio = valor_tasa_cambio;
    }
}

