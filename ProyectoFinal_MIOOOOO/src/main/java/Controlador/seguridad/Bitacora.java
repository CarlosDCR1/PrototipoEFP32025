/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.seguridad;
import Modelo.seguridad.BitacoraDAO;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author c2307
 */
public class Bitacora {
    private int idBitacora;
    private int idUsuario;
    private int idAplicacion;
    private String Fecha;
    private String Ip;
    private String Accion;
    private String nombrePc;

    @Override
    public String toString() {
        return "Bitacora{" + "idBitacora=" + idBitacora + ", idUsuario=" + idUsuario + ", idAplicacion=" + idAplicacion + ", Fecha=" + Fecha +  ", Ip=" + Ip + ", Accion=" + Accion + ", nombrePc=" + nombrePc + '}';
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }
    
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }
    
    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }
    
    public String getNombrePc() {
        return nombrePc;
    }

    public void setNombrePc(String nombrePc) {
        this.nombrePc = nombrePc;
    }

    public Bitacora(int idBitacora, int idUsuario, int idAplicacion, String Fecha, String Ip, String Accion, String nombrePc) {
        this.idBitacora = idBitacora;
        this.idUsuario = idUsuario;
        this.idAplicacion = idAplicacion;
        this.Fecha = Fecha;
        this.Ip = Ip;
        this.Accion = Accion;
        this.nombrePc = nombrePc;
    }
    
    public Bitacora() {
    }
    public int setIngresarBitacora(int codigoUsuario, int codigoAplicacion, String accion)
    {
        BitacoraDAO daoBitacora = new BitacoraDAO();
        return daoBitacora.registrarAccionEnBitacora(codigoUsuario, codigoAplicacion, accion);
    }
    public List<Bitacora> getListadoBitacora(String primeraFecha, String segundaFecha) throws ParseException
    {
        BitacoraDAO daoBitacora = new BitacoraDAO();
        List<Bitacora> listadoBitacora = daoBitacora.query(primeraFecha, segundaFecha);
        return listadoBitacora;
    } 
}