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
public class Aplicacion1 {

    private int id_aplicacion;
    private String nombre_aplicacion;
    private String estatus_aplicacion;
    
    
    public Aplicacion1(int id_aplicacion, String nombre_aplicacion, String estatus_aplicacion) {
        this.id_aplicacion = id_aplicacion;
        this.nombre_aplicacion = nombre_aplicacion;
        this.estatus_aplicacion = estatus_aplicacion;
    }

    public int getId_aplicacion() {
        return id_aplicacion;
    }

    public String getNombre_aplicacion() {
        return nombre_aplicacion;
    }

    public String getEstatus_aplicacion() {
        return estatus_aplicacion;
    }

    public void setId_aplicacion (int id_aplicacion) {
        this.id_aplicacion = id_aplicacion;
    }
    
    public void setNombre_aplicacion(String nombre_aplicacion) {
        this.nombre_aplicacion = nombre_aplicacion;
    }

    public void setEstatus_aplicacion(String estatus_aplicacion) {
        this.estatus_aplicacion = estatus_aplicacion;
    }

    @Override
    public String toString() {
        return "Aplicacion{" + "id_aplicacion=" + id_aplicacion + ", nombre_aplicacion=" + nombre_aplicacion + ", estatus_aplicacion=" + estatus_aplicacion + '}';
    }
    

    public Aplicacion1() { //no contiene nada
    }
    
    
    
}
