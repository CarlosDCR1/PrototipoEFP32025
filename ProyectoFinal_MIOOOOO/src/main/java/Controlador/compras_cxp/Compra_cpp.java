/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package Controlador.compras_cxp;

    import java.time.LocalDateTime;

    /**
     *
     * @author visitante
     */
    public class Compra_cpp {

        int no_compra;
        String nombre_usuario;
        String apellido_usuario;
        int id_proveedor;
        String producto;
        int cantidad;
        int precio;
        int saldo_anterior;
        int plazo;
        int total;

    public Compra_cpp(int no_compra, String nombre_usuario, String apellido_usuario, int id_proveedor, String producto, int cantidad, int precio, int saldo_anterior, int plazo, int total) {
        this.no_compra = no_compra;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.id_proveedor = id_proveedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.saldo_anterior = saldo_anterior;
        this.plazo = plazo;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compra_cpp{" + "no_compra=" + no_compra + ", nombre_usuario=" + nombre_usuario + ", apellido_usuario=" + apellido_usuario + ", id_proveedor=" + id_proveedor + ", producto=" + producto + ", cantidad=" + cantidad + ", precio=" + precio + ", saldo_anterior=" + saldo_anterior + ", plazo=" + plazo + ", total=" + total + '}';
    }

    public int getNo_compra() {
        return no_compra;
    }

    public void setNo_compra(int no_compra) {
        this.no_compra = no_compra;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(int saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
       

        
        public Compra_cpp() {
        }

    }
    