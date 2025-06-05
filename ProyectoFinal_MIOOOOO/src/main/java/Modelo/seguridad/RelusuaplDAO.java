/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Controlador.seguridad.Relusuapl; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class RelusuaplDAO {
    //Modificacion de fecha en RelusuaplDAO Hecho por Kathia Contreras
    private static final String SQL_SELECT = "SELECT id_relusuapl, id_aplicacion, id_usuario, der_insertar, der_editar, der_eliminar, der_imprimir, fecha FROM relusuapl";
    private static final String SQL_INSERT = "INSERT INTO relusuapl(id_relusuapl, id_aplicacion, id_usuario, der_insertar, der_editar, der_eliminar, der_imprimir,fecha) VALUES(?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_UPDATE = "UPDATE relusuapl SET id_aplicacion=?, id_usuario=?, der_insertar=?, der_editar=?, der_eliminar=?, der_imprimir=?, fecha =? WHERE id_relusuapl = ?";
    private static final String SQL_DELETE = "DELETE FROM relusuapl WHERE id_relusuapl=?";
    private static final String SQL_QUERY = "SELECT id_relusuapl, id_aplicacion, id_usuario, der_insertar, der_editar, der_eliminar, der_imprimir, fecha FROM relusuapl WHERE id_relusuapl = ?";

    public List<Relusuapl> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Relusuapl relusuapl = null;
        List<Relusuapl> relusuaples = new ArrayList<Relusuapl>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idDerechosusuapl = rs.getInt("id_relusuapl");
                int idAplicacion = rs.getInt("id_aplicacion");
                int idUsuario = rs.getInt("id_usuario");
                String derInsertar = rs.getString("der_insertar");
                String derEditar = rs.getString("der_editar");
                String derEliminar = rs.getString("der_eliminar");
                String derImprimir = rs.getString("der_imprimir");
                String fechaRelUsuApl = rs.getString("fecha");
                relusuapl = new Relusuapl();
                relusuapl.setId_relusuapl(idDerechosusuapl);
                relusuapl.setId_aplicacion(idAplicacion);
                relusuapl.setId_usuario(idUsuario);
                relusuapl.setDer_insertar(derInsertar);
                relusuapl.setDer_editar(derEditar);
                relusuapl.setDer_eliminar(derEliminar);
                relusuapl.setDer_imprimir(derImprimir);
                relusuapl.setFecha_relusuapl(fechaRelUsuApl);
                
                relusuaples.add(relusuapl);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return relusuaples;
    }

    public int insert(Relusuapl relusuapl) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, relusuapl.getId_relusuapl());
            stmt.setInt(2, relusuapl.getId_aplicacion());
            stmt.setInt(3, relusuapl.getId_usuario());
            stmt.setString(4, relusuapl.getDer_insertar());
            stmt.setString(5, relusuapl.getDer_editar());
            stmt.setString(6, relusuapl.getDer_eliminar());
            stmt.setString(7, relusuapl.getDer_imprimir());
            stmt.setString(8, relusuapl.getFecha_relusuapl());
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
    }

    public int update(Relusuapl relusuapl) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setInt(1, relusuapl.getId_aplicacion());
            stmt.setInt(2, relusuapl.getId_usuario());
            stmt.setString(3, relusuapl.getDer_insertar());
            stmt.setString(4, relusuapl.getDer_editar());
            stmt.setString(5, relusuapl.getDer_eliminar());
            stmt.setString(6, relusuapl.getDer_imprimir());
            stmt.setString(7, relusuapl.getFecha_relusuapl());
            
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

    public int delete(Relusuapl relusuapl) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, relusuapl.getId_relusuapl());
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

    public Relusuapl query(Relusuapl relusuapl) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Relusuapl> relusuaples = new ArrayList<Relusuapl>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, relusuapl.getId_relusuapl());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idDerechosusuapl = rs.getInt("id_relusuapl");
                int idAplicacion = rs.getInt("id_aplicacion");
                int idUsuario = rs.getInt("id_usuario");
                String derInsertar = rs.getString("der_insertar");
                String derEditar = rs.getString("der_editar");
                String derEliminar = rs.getString("der_eliminar");
                String derImprimir = rs.getString("der_imprimir");
                String fechaRelUsuApl = rs.getString("fecha");
                relusuapl = new Relusuapl();
                relusuapl.setId_relusuapl(idDerechosusuapl);
                relusuapl.setId_aplicacion(idAplicacion);
                relusuapl.setId_usuario(idUsuario);
                relusuapl.setDer_insertar(derInsertar);
                relusuapl.setDer_editar(derEditar);
                relusuapl.setDer_eliminar(derEliminar);
                relusuapl.setDer_imprimir(derImprimir);
                relusuapl.setFecha_relusuapl(fechaRelUsuApl);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return relusuapl; 
    }
        
}
