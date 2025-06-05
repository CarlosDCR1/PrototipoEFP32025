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
    public class Proveedor {

        int id_proveedor;
        String nombre_proveedor;
        String direccion_proveedor;
        String telefono_proveedor;
        String email_proveedor;
        String fecha_registro;
        int saldo_proveedor;
        int estatus_proveedor;
        int plazo_limite;

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public void setEmail_proveedor(String email_proveedor) {
        this.email_proveedor = email_proveedor;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setSaldo_proveedor(int saldo_proveedor) {
        this.saldo_proveedor = saldo_proveedor;
    }

    public void setEstatus_proveedor(int estatus_proveedor) {
        this.estatus_proveedor = estatus_proveedor;
    }

    public void setPlazo_limite(int plazo_limite) {
        this.plazo_limite = plazo_limite;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public String getEmail_proveedor() {
        return email_proveedor;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public int getSaldo_proveedor() {
        return saldo_proveedor;
    }

    public int getEstatus_proveedor() {
        return estatus_proveedor;
    }

    public int getPlazo_limite() {
        return plazo_limite;
    }
        


        public Proveedor(int id_proveedor, String nombre_proveedor, String direccion_proveedor, String telefono_proveedor, String email_proveedor, int saldo_proveedor, int estatus_proveedor, String fecha_registro, int plazo_limite) {
            this.id_proveedor = id_proveedor;
            this.nombre_proveedor = nombre_proveedor;
            this.direccion_proveedor = direccion_proveedor;
            this.telefono_proveedor = telefono_proveedor;
            this.email_proveedor = email_proveedor;
            this.saldo_proveedor = saldo_proveedor;
            this.estatus_proveedor = estatus_proveedor;
            this.fecha_registro = fecha_registro;
            this.plazo_limite = plazo_limite;
            
            
        }


        @Override
        public String toString() {
            return "Proveedor{" + "id_proveedor=" + id_proveedor + ", nombre_proveedor=" + nombre_proveedor + ", direccion_proveedor=" + direccion_proveedor + ", telefono_proveedor=" + telefono_proveedor + ", email_proveedor=" + email_proveedor + ", saldo_proveedor=" + saldo_proveedor + ", estatus_proveedor=" + estatus_proveedor + ", fecha_registro=" + fecha_registro + ",plazo_limite=" + plazo_limite+'}';
        }
            

   
        
        public Proveedor() {
        }

    }
    