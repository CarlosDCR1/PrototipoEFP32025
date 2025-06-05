/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.ventas_cxc;

/**
 *
 * @author melen
 */
//Cristian Sipac
public class Ventascxc {
    int no_factura;
    String no_venta;
    int id_vendedor;
    String nombre_cliente;
    String apellido_cliente;
    int pro_codigo;
    int cantidad;
    double proPrecios;
    double saldo_actual;
    String proNombre;
    int dias_credito;
    double total;
    String fecha_venta;

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public String getNo_venta() {
        return no_venta;
    }

    public void setNo_venta(String no_venta) {
        this.no_venta = no_venta;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public int getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(int pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getProPrecios() {
        return proPrecios;
    }

    public void setProPrecios(double proPrecios) {
        this.proPrecios = proPrecios;
    }

    public double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public int getDias_credito() {
        return dias_credito;
    }

    public void setDias_credito(int dias_credito) {
        this.dias_credito = dias_credito;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Ventascxc(int no_factura, String no_venta, int id_vendedor, String nombre_cliente, String apellido_cliente, int pro_codigo, int cantidad, double proPrecios, double saldo_actual, String proNombre, int dias_credito, double total, String precio_producto, String fecha_venta) {
        this.no_factura = no_factura;
        this.no_venta = no_venta;
        this.id_vendedor = id_vendedor;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.pro_codigo = pro_codigo;
        this.cantidad = cantidad;
        this.proPrecios = proPrecios;
        this.saldo_actual = saldo_actual;
        this.proNombre = proNombre;
        this.dias_credito = dias_credito;
        this.total = total;
        this.fecha_venta = fecha_venta;
    }

    @Override
    public String toString() {
        return "Ventascxc{" + "no_factura=" + no_factura + ", no_venta=" + no_venta + ", id_vendedor=" + id_vendedor + ", nombre_cliente=" + nombre_cliente + ", apellido_cliente=" + apellido_cliente + ", pro_codigo=" + pro_codigo + ", cantidad=" + cantidad + ", proPrecios=" + proPrecios + ", saldo_actual=" + saldo_actual + ", proNombre=" + proNombre + ", dias_credito=" + dias_credito + ", total=" + total + ", precio_producto=" + ", fecha_venta=" + fecha_venta + '}';
    }

    

    public Ventascxc()  {
    }

    }