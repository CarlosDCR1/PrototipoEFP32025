/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.inventarios;



/**
 *
 * @author visitante
 */
public class productos {
 
    int     proCodigo;
    int linCodigo;
    int marCodigo;
    String proNombre;
    Double proPrecio;
    int proExistencias;
    String proEstatus;

    @Override
    public String toString() {
        return "productos{" + "proCodigo=" + proCodigo + ", linCodigo=" + linCodigo + ", marCodigo=" + marCodigo + ", proNombre=" + proNombre + ", proPrecio=" + proPrecio + ", proExistencias=" + proExistencias + ", proEstatus=" + proEstatus + '}';
    }

    public int getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(int proCodigo) {
        this.proCodigo = proCodigo;
    }

    public int getLinCodigo() {
        return linCodigo;
    }

    public void setLinCodigo(int linCodigo) {
        this.linCodigo = linCodigo;
    }

    public int getMarCodigo() {
        return marCodigo;
    }

    public void setMarCodigo(int marCodigo) {
        this.marCodigo = marCodigo;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public Double getProPrecio() {
        return proPrecio;
    }

    public void setProPrecio(Double proPrecio) {
        this.proPrecio = proPrecio;
    }

    public int getProExistencias() {
        return proExistencias;
    }

    public void setProExistencias(int proExistencias) {
        this.proExistencias = proExistencias;
    }

    public String getProEstatus() {
        return proEstatus;
    }

    public void setProEstatus(String proEstatus) {
        this.proEstatus = proEstatus;
    }

    public productos(int proCodigo, int linCodigo, int marCodigo, String proNombre, Double proPrecio, int proExistencias, String proEstatus) {
        this.proCodigo = proCodigo;
        this.linCodigo = linCodigo;
        this.marCodigo = marCodigo;
        this.proNombre = proNombre;
        this.proPrecio = proPrecio;
        this.proExistencias = proExistencias;
        this.proEstatus = proEstatus;
    }

    public productos() {
    }
    
 
}
   