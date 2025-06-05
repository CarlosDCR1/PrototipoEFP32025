/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.compras_cxp;

/**
 *
 * @author visitante
 */

//Metododepago.java HECHO POR KATHIA CONTRERAS

public class Metododepago {
    int id_metodoPago;
    String nombreMetodoPago;
    String estatusMetodoPago;

    public Metododepago(int id_metodoPago, String nombreMetodoPago, String estatusMetodoPago) {
        this.id_metodoPago = id_metodoPago;
        this.nombreMetodoPago = nombreMetodoPago;
        this.estatusMetodoPago = estatusMetodoPago;
    }

    @Override
    public String toString() {
        return "Metododepago{" + "id_metodoPago=" + id_metodoPago + ", nombreMetodoPago=" + nombreMetodoPago + ", estatusMetodoPago=" + estatusMetodoPago + '}';
    }

    public void setId_metodoPago(int id_metodoPago) {
        this.id_metodoPago = id_metodoPago;
    }

    public void setNombreMetodoPago(String nombreMetodoPago) {
        this.nombreMetodoPago = nombreMetodoPago;
    }

    public void setEstatusMetodoPago(String estatusMetodoPago) {
        this.estatusMetodoPago = estatusMetodoPago;
    }

    public int getId_metodoPago() {
        return id_metodoPago;
    }

    public String getNombreMetodoPago() {
        return nombreMetodoPago;
    }

    public String getEstatusMetodoPago() {
        return estatusMetodoPago;
    }

    public Metododepago() {
    }

    
}