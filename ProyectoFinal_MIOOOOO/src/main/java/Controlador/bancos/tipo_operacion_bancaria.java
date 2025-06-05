package Controlador.bancos;

// Ruddyard Castro 9959-23-1409
// Nota: Los nombres de los getters, setters y variables coinciden con los nombres de los campos en la base de datos.
// El nombre de la clase Java coincide con el nombre de la tabla en la base de datos.
// Se utilizó la funcionalidad "Encapsulate Field" para generar los métodos get y set.

/**
 * Clase modelo que representa la entidad `tipo_operacion_bancaria`.
 * Esta clase se utiliza para mapear los datos de la tabla correspondiente en la base de datos.
 */
public class tipo_operacion_bancaria {

    // Atributos privados que representan las columnas de la tabla
    private int id_tipo_operacion;     // Identificador único del tipo de operación
    private String tipo_operacion;     // Nombre o tipo de la operación bancaria
    private String descripcion;        // Descripción adicional del tipo de operación

    // Constructor vacío: necesario para instanciación sin parámetros
    public tipo_operacion_bancaria() {
    }

    // Constructor con parámetros: permite crear un objeto con todos los atributos definidos
    public tipo_operacion_bancaria(int id_tipo_operacion, String tipo_operacion, String descripcion) {
        this.id_tipo_operacion = id_tipo_operacion;
        this.tipo_operacion = tipo_operacion;
        this.descripcion = descripcion;
    }

    // Método toString: devuelve una representación en texto del objeto
    @Override
    public String toString() {
        return "tipo_operacion_bancaria{" + "id_tipo_operacion=" + id_tipo_operacion + ", tipo_operacion=" + tipo_operacion + ", descripcion=" + descripcion + '}';
    }

    // Métodos getter y setter para acceder y modificar los atributos

    public int getId_tipo_operacion() {
        return id_tipo_operacion;
    }

    public void setId_tipo_operacion(int id_tipo_operacion) {
        this.id_tipo_operacion = id_tipo_operacion;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
