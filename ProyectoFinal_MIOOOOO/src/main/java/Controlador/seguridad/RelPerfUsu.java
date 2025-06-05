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
public class RelPerfUsu {
 
    int id_usuario;
    int id_perfil;
    

    @Override
    public String toString() {
        return "RelPerfApl{" + "id_usuario=" + id_usuario + ", id_perfil=" + id_perfil + '}';
    }
    
        public int getUsuario_codigo() {
        return id_usuario;
    }

    public int getPerfil_codigo() {
        return id_perfil;
    }

    public void setUsuario_codigo(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setPerfil_codigo(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public RelPerfUsu(int id_usuario, int id_perfil) {
        this.id_usuario = id_usuario;
        this.id_perfil = id_perfil;
    }
    
    public RelPerfUsu() { //no contiene nada
    }
    
    

}