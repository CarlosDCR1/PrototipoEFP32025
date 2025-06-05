package Controlador.bancos;

//MISHEL LOEIZA 9959-23-3457
// Nombres de los getters, setters y variables igual al nombre en base de datos
// Nombre del archivo Java igual que la tabla
// Se usó encapsulate field para get y set

public class bancos {

    private int id_banco;
    private String nombre;
    private String sede;
    private String direccion;
    private String telefono;
    private String email;
    private String status;

    // Constructor vacío
    public bancos() {
    }

    // Constructor con los campos principales
    public bancos(int id_banco, String nombre, String sede, String direccion, String telefono, String email, String status) {
        this.id_banco = id_banco;
        this.nombre = nombre;
        this.sede = sede;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "bancos{" +
                "id_banco=" + id_banco +
                ", nombre='" + nombre + '\'' +
                ", sede='" + sede + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Getters y Setters
    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
