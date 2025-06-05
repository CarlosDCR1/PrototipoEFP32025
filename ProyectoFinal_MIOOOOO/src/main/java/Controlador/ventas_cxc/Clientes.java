/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.ventas_cxc;


/**
 *
 * @author visitante
 */
//Victor 
public class Clientes {
 
    int id_cliente;
    String nombre_cliente;
    String apellido_cliente;
    String direccion_cliente;
    String telefono_cliente;
    String emaill_cliente;
    double limite_credito_CLE;
    int dias_credito_CLE;
    String estatus_cliente;
    Double saldo_actual_CLE;

    @Override
    public String toString() {
        return "Clientes{" + "id_cliente=" + id_cliente + ", nombre_cliente=" + nombre_cliente + ", apellido_cliente=" + apellido_cliente + ", direccion_cliente=" + direccion_cliente + ", telefono_cliente=" + telefono_cliente + ", emaill_cliente=" + emaill_cliente + ", limite_credito_CLE=" + limite_credito_CLE + ", dias_credito_CLE=" + dias_credito_CLE + ", estatus_cliente=" + estatus_cliente + ", saldo_actual_CLE=" + saldo_actual_CLE + '}';
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getEmaill_cliente() {
        return emaill_cliente;
    }

    public void setEmaill_cliente(String emaill_cliente) {
        this.emaill_cliente = emaill_cliente;
    }

    public double getLimite_credito_CLE() {
        return limite_credito_CLE;
    }

    public void setLimite_credito_CLE(double limite_credito_CLE) {
        this.limite_credito_CLE = limite_credito_CLE;
    }

    public int getDias_credito_CLE() {
        return dias_credito_CLE;
    }

    public void setDias_credito_CLE(int dias_credito_CLE) {
        this.dias_credito_CLE = dias_credito_CLE;
    }

    public String getEstatus_cliente() {
        return estatus_cliente;
    }

    public void setEstatus_cliente(String estatus_cliente) {
        this.estatus_cliente = estatus_cliente;
    }

    public double getSaldo_actual_CLE() {
        return saldo_actual_CLE;
    }

    public void setSaldo_actual_CLE(double saldo_actual_CLE) {
        this.saldo_actual_CLE = saldo_actual_CLE;
    }

    public Clientes(int id_cliente, String nombre_cliente, String apellido_cliente, String direccion_cliente, String telefono_cliente, String emaill_cliente, double limite_credito_CLE, int dias_credito_CLE, String estatus_cliente, double saldo_actual_CLE) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.direccion_cliente = direccion_cliente;
        this.telefono_cliente = telefono_cliente;
        this.emaill_cliente = emaill_cliente;
        this.limite_credito_CLE = limite_credito_CLE;
        this.dias_credito_CLE = dias_credito_CLE;
        this.estatus_cliente = estatus_cliente;
        this.saldo_actual_CLE = saldo_actual_CLE;
    }

    public Clientes() {
    }

}
