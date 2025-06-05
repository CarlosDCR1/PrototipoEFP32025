package Modelo.seguridad;

import Controlador.seguridad.AsignacionPerfil; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AsingnacionPerfilDAO {

    private static final String SQL_SELECT = "SELECT * FROM usuario_perfil";
   // private static final String SQL_INSERT = "INSERT INTO usuario_perfil (id_asignacion,id_usuario,id_perfil,fecha_asignacion) VALUES (?,?,?,?)";
  //  private static final String SQL_UPDATE = "UPDATE usuario_perfil SET id_asignacion=?, id_usuario=?,id_perfil=?,fecha_asignacion=? WHERE id_asignacion =?";
  //  private static final String SQL_DELETE = "DELETE FROM usuario_perfil WHERE id_asignacion =? ";
   // private static final String SQL_QUERY = "SELECT *FROM usuario_perfil WHERE id_asignacion =?";

    public List<AsignacionPerfil> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsignacionPerfil asignacionPerfil = null;
        List<AsignacionPerfil> asignacionPerfiles = new ArrayList<AsignacionPerfil>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int id_usuario = rs.getInt("id_usuario");
                int id_perfil = rs.getInt("id_perfil");
               
                
                
                asignacionPerfil = new AsignacionPerfil();
                
                asignacionPerfil.setId_usuario(id_usuario);
                asignacionPerfil.setId_perfil(id_perfil);
                
                
                
                asignacionPerfiles.add(asignacionPerfil);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return asignacionPerfiles;
    }
/*
    public int insert(AsignacionPerfil asignacionPerfil) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, String.valueOf(asignacionPerfil.getId_usuario()));
            stmt.setString(2, String.valueOf(asignacionPerfil.getId_perfil()));
           


           

            System.out.println("ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }*/
/*
    public int update(AsignacionPerfil asignacionPerfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
           stmt.setString(1, String.valueOf(asignacionPerfil.getId_usuario()));
            stmt.setString(2, String.valueOf(asignacionPerfil.getId_perfil()));
            stmt.setTimestamp(3, asignacionPerfil.getFecha_Asignacion());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
*/
    /*
    public int delete(AsignacionPerfil asignacionPerfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, asignacionPerfil.getId_perfil());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }*/

    /*
    public AsignacionPerfil query(AsignacionPerfil asignacionPerfil) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AsignacionPerfil> asignacionPerfiles = new ArrayList<AsignacionPerfil>();
        int rows = 0;
 try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            rs = stmt.executeQuery();
            while (rs.next()) {
            
                int id_usuario = rs.getInt("id_usuario");
                int id_perfil = rs.getInt("id_perfil");
               
                
                
                asignacionPerfil = new AsignacionPerfil();
                asignacionPerfil.setId_perfil(id_usuario);
                asignacionPerfil.setId_perfil(id_perfil);
                
                
            
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return asignacionPerfil; 
    }
      */  
}

