/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






package Modelo.seguridad;


import Controlador.seguridad.RelPerfUsu;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class RelPerfUsuDAO {

    private static final String SQL_SELECT = "SELECT id_usuario, id_perfil FROM relperfusu";
    private static final String SQL_INSERT = "INSERT INTO relperfusu(id_usuario, id_perfil) VALUES(?,?)";
    //private static final String SQL_UPDATE = "UPDATE relperfusu SET consultar_rpa=? WHERE id_usuario=?,id_perfil=?";
    private static final String SQL_DELETE = "DELETE FROM relperfusu WHERE id_usuario = ? AND id_perfil = ?";
    private static final String SQL_DELETE_BY_USER = "DELETE FROM relperfusu WHERE id_usuario = ?";
    private static final String SQL_QUERY = "SELECT id_usuario, id_perfil FROM relperfusu WHERE id_usuario= ?,id_perfil=?";

    public List<RelPerfUsu> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RelPerfUsu relPerfUsu = null;
        List<RelPerfUsu> list_relPerfUsu = new ArrayList<RelPerfUsu>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                int id_perfil = rs.getInt("id_perfil");
                
                relPerfUsu = new RelPerfUsu();
                relPerfUsu.setUsuario_codigo(id_usuario);
                relPerfUsu.setPerfil_codigo(id_perfil);
                
              
                list_relPerfUsu.add(relPerfUsu);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_relPerfUsu;
    }

    public int insert(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, relPerfUsu.getUsuario_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
         
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }


    public int delete(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, relPerfUsu.getUsuario_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    // Nuevo método para eliminar todas las relaciones de un usuario
    public int deleteByUserId(int usuarioId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_USER);
            stmt.setInt(1, usuarioId);

            System.out.println("Ejecutando query: " + SQL_DELETE_BY_USER);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

// Método para realizar una consulta específica
    public RelPerfUsu query(RelPerfUsu relPerfUsu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, relPerfUsu.getUsuario_codigo());
            stmt.setInt(2, relPerfUsu.getPerfil_codigo());
            rs = stmt.executeQuery();

            if (rs.next()) {
                relPerfUsu.setUsuario_codigo(rs.getInt("id_usuario"));
                relPerfUsu.setPerfil_codigo(rs.getInt("id_perfil"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return relPerfUsu;
    }
}