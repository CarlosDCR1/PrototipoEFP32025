/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.seguridad;
//----------------Evelyn Sofia Andrade Luna 9959-23-1224-------------------------
/**
 *
 * @author visitante
 */
public class RelPerfApl {
 
    int aplicacion_codigo;
    int perfil_codigo;
    String consultar_rpa;
    String actualizar_rpa;
    String eliminar_rpa;
    String imprimir_rpa;
    String insertar_rpa;
    String fecha_rpa;

    public int getAplicacion_codigo() {
        return aplicacion_codigo;
    }

    public int getPerfil_codigo() {
        return perfil_codigo;
    }

    public String getConsultar_rpa() {
        return consultar_rpa;
    }

    public String getActualizar_rpa() {
        return actualizar_rpa;
    }

    public String getEliminar_rpa() {
        return eliminar_rpa;
    }

    public String getImprimir_rpa() {
        return imprimir_rpa;
    }

    public String getInsertar_rpa() {
        return insertar_rpa;
    }

    public String getFecha_rpa() {
        return fecha_rpa;
    }

    public void setAplicacion_codigo(int aplicacion_codigo) {
        this.aplicacion_codigo = aplicacion_codigo;
    }

    public void setPerfil_codigo(int perfil_codigo) {
        this.perfil_codigo = perfil_codigo;
    }

    public void setConsultar_rpa(String consultar_rpa) {
        this.consultar_rpa = consultar_rpa;
    }

    public void setActualizar_rpa(String actualizar_rpa) {
        this.actualizar_rpa = actualizar_rpa;
    }

    public void setEliminar_rpa(String eliminar_rpa) {
        this.eliminar_rpa = eliminar_rpa;
    }

    public void setImprimir_rpa(String imprimir_rpa) {
        this.imprimir_rpa = imprimir_rpa;
    }

    public void setInsertar_rpa(String insertar_rpa) {
        this.insertar_rpa = insertar_rpa;
    }

    public void setFecha_rpa(String fecha_rpa) {
        this.fecha_rpa = fecha_rpa;
    }

    public RelPerfApl(int aplicacion_codigo, int perfil_codigo, String consultar_rpa, String actualizar_rpa, String eliminar_rpa, String imprimir_rpa, String insertar_rpa, String fecha_rpa) {
        this.aplicacion_codigo = aplicacion_codigo;
        this.perfil_codigo = perfil_codigo;
        this.consultar_rpa = consultar_rpa;
        this.actualizar_rpa = actualizar_rpa;
        this.eliminar_rpa = eliminar_rpa;
        this.imprimir_rpa = imprimir_rpa;
        this.insertar_rpa = insertar_rpa;
        this.fecha_rpa = fecha_rpa;
    }

    @Override
    public String toString() {
        return "RelPerfApl{" + "aplicacion_codigo=" + aplicacion_codigo + ", perfil_codigo=" + perfil_codigo + ", consultar_rpa=" + consultar_rpa + ", actualizar_rpa=" + actualizar_rpa + ", eliminar_rpa=" + eliminar_rpa + ", imprimir_rpa=" + imprimir_rpa + ", insertar_rpa=" + insertar_rpa + ", fecha_rpa=" + fecha_rpa + '}';
    }

    
    
    public RelPerfApl() { //no contiene nada
    }
    
    

}
