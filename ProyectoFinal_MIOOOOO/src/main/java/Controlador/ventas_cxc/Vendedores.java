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
public class Vendedores {
    //Sofia

    int id_vendedor;
    String nombre_vendedor;
    String apellido_vendedor;
    String direccion_vendedor;
    String telefono_vendedor;
    String email_vendedor;
    String comision;
    String estatus_vendedor;

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getApellido_vendedor() {
        return apellido_vendedor;
    }

    public void setApellido_vendedor(String apellido_vendedor) {
        this.apellido_vendedor = apellido_vendedor;
    }

    public String getDireccion_vendedor() {
        return direccion_vendedor;
    }

    public void setDireccion_vendedor(String direccion_vendedor) {
        this.direccion_vendedor = direccion_vendedor;
    }

    public String getTelefono_vendedor() {
        return telefono_vendedor;
    }

    public void setTelefono_vendedor(String telefono_vendedor) {
        this.telefono_vendedor = telefono_vendedor;
    }

    public String getEmail_vendedor() {
        return email_vendedor;
    }

    public void setEmail_vendedor(String email_vendedor) {
        this.email_vendedor = email_vendedor;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getEstatus_vendedor() {
        return estatus_vendedor;
    }

    public void setEstatus_vendedor(String estatus_vendedor) {
        this.estatus_vendedor = estatus_vendedor;
    }

    public Vendedores(int id_vendedor, String nombre_vendedor, String apellido_vendedor, String direccion_vendedor, String telefono_vendedor, String email_vendedor, String comision, String estatus_vendedor) {
        this.id_vendedor = id_vendedor;
        this.nombre_vendedor = nombre_vendedor;
        this.apellido_vendedor = apellido_vendedor;
        this.direccion_vendedor = direccion_vendedor;
        this.telefono_vendedor = telefono_vendedor;
        this.email_vendedor = email_vendedor;
        this.comision = comision;
        this.estatus_vendedor = estatus_vendedor;
    }

    @Override
    public String toString() {
        return "Vendedores{" + "id_vendedor=" + id_vendedor + ", nombre_vendedor=" + nombre_vendedor + ", apellido_vendedor=" + apellido_vendedor + ", direccion_vendedor=" + direccion_vendedor + ", telefono_vendedor=" + telefono_vendedor + ", email_vendedor=" + email_vendedor + ", comision=" + comision + ", estatus_vendedor=" + estatus_vendedor + '}';
    }
    
    public Vendedores() { //sin nada, sin parametros
    }
    
    

}
