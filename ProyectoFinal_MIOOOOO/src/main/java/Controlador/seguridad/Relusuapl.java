/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.seguridad;

/**
 *
 * @author visitante
 */
public class Relusuapl {
    
    int id_relusuapl;
    int id_aplicacion;
    int id_usuario;
    String der_insertar;
    String der_editar;
    String der_eliminar;
    String der_imprimir;
    //Modificacion de fecha en Relusuapl Hecho por Kathia Contreras
    String Fecha_relusuapl;

    public Relusuapl(int id_relusuapl, int id_aplicacion, int id_usuario, String der_insertar, String der_editar, String der_eliminar, String der_imprimir, String Fecha_relusuapl) {
        this.id_relusuapl = id_relusuapl;
        this.id_aplicacion = id_aplicacion;
        this.id_usuario = id_usuario;
        this.der_insertar = der_insertar;
        this.der_editar = der_editar;
        this.der_eliminar = der_eliminar;
        this.der_imprimir = der_imprimir;
        this.Fecha_relusuapl = Fecha_relusuapl;
    }

    @Override
    public String toString() {
        return "Relusuapl{" + "id_relusuapl=" + id_relusuapl + ", id_aplicacion=" + id_aplicacion + ", id_usuario=" + id_usuario + ", der_insertar=" + der_insertar + ", der_editar=" + der_editar + ", der_eliminar=" + der_eliminar + ", der_imprimir=" + der_imprimir + ", Fecha_relusuapl=" + Fecha_relusuapl + '}';
    }

    public int getId_relusuapl() {
        return id_relusuapl;
    }

    public void setId_relusuapl(int id_relusuapl) {
        this.id_relusuapl = id_relusuapl;
    }

    public int getId_aplicacion() {
        return id_aplicacion;
    }

    public void setId_aplicacion(int id_aplicacion) {
        this.id_aplicacion = id_aplicacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDer_insertar() {
        return der_insertar;
    }

    public void setDer_insertar(String der_insertar) {
        this.der_insertar = der_insertar;
    }

    public String getDer_editar() {
        return der_editar;
    }

    public void setDer_editar(String der_editar) {
        this.der_editar = der_editar;
    }

    public String getDer_eliminar() {
        return der_eliminar;
    }

    public void setDer_eliminar(String der_eliminar) {
        this.der_eliminar = der_eliminar;
    }

    public String getDer_imprimir() {
        return der_imprimir;
    }

    public void setDer_imprimir(String der_imprimir) {
        this.der_imprimir = der_imprimir;
    }

    public String getFecha_relusuapl() {
        return Fecha_relusuapl;
    }

    public void setFecha_relusuapl(String Fecha_relusuapl) {
        this.Fecha_relusuapl = Fecha_relusuapl;
    }
    
        public Relusuapl() {
    }
   
}
