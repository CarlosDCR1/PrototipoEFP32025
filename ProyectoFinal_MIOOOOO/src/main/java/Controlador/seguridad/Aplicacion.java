/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.seguridad;
//----------------Evelyn Sofia Andrade Luna 9959-23-1224-------------------------
/**
 *
 * @author cdavi
 */
public class Aplicacion {

    public Aplicacion(String pkId, String idTipoBodega, String nombre, String direccion, String estatus_aplicacion) {
        this.pkId = pkId;
        this.idTipoBodega = idTipoBodega;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estatus_aplicacion = estatus_aplicacion;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getIdTipoBodega() {
        return idTipoBodega;
    }

    public void setIdTipoBodega(String idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstatus_aplicacion() {
        return estatus_aplicacion;
    }

    public void setEstatus_aplicacion(String estatus_aplicacion) {
        this.estatus_aplicacion = estatus_aplicacion;
    }

    @Override
    public String toString() {
        return "Bodega{" + "pkid=" + pkId + ", idTipoBodega=" + idTipoBodega + ", nombre=" + nombre + ", direccion=" + direccion + ", estado=" + estatus_aplicacion + '}';
    }

    private String pkId;
    private String idTipoBodega;
    private String nombre;
    private String direccion;
    private String estatus_aplicacion;
    
    
    public Aplicacion() { //no contiene nada
    }
    
    
    
}
