/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Controlador.seguridad.Derechosusuapl; 
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
public class DerechosusuaplDAO {

    private static final String SQL_SELECT = "SELECT id_derechosusuapl, username, nombre_aplicacion, der_insertar, der_editar, der_ eliminar, der_imprimir FROM derechosusuapl";
    private static final String SQL_INSERT = "INSERT INTO derechosusuapl(id_derechosusuapl, username, nombre_aplicacion, der_insertar, der_editar, der_ eliminar, der_imprimir) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE derechosusuapl SET username=?, nombre_aplicacion=?, der_insertar=?, der_editar=?, der_ eliminar=?, der_imprimir=? WHERE id_derechosusuapl = ?";
    private static final String SQL_DELETE = "DELETE FROM derechosusuapl WHERE id_derechosusuapl=?";
    private static final String SQL_QUERY = "SELECT id_derechosusuapl, username, nombre_aplicacion, der_insertar, der_editar, der_ eliminar, der_imprimir FROM derechosusuapl WHERE id_derechosusuapl = ?";

    public List<Derechosusuapl> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Derechosusuapl derechosusuapl = null;
        List<Derechosusuapl> derechosusuaples = new ArrayList<Derechosusuapl>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idDerechosusuapl = rs.getInt("id_derechosusuapl");
                String Username = rs.getString("username");
                String nombreAplicacion = rs.getString("nombre_aplicacion");
                String derInsertar = rs.getString("der_insertar");
                String derEditar = rs.getString("der_editar");
                String derEliminar = rs.getString("der_ eliminar");
                String derImprimir = rs.getString("der_imprimir");
                
                derechosusuapl = new Derechosusuapl();
                derechosusuapl.setId_derechosusuapl(idDerechosusuapl);
                derechosusuapl.setUsername(Username);
                derechosusuapl.setNombre_aplicacion(nombreAplicacion);
                derechosusuapl.setDer_insertar(derInsertar);
                derechosusuapl.setDer_editar(derEditar);
                derechosusuapl.setDer_eliminar(derEliminar);
                derechosusuapl.setDer_imprimir(derImprimir);
                
                derechosusuaples.add(derechosusuapl);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return derechosusuaples;
    }

    public int insert(Derechosusuapl derechosusuapl) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, derechosusuapl.getUsername());
            stmt.setString(2, derechosusuapl.getNombre_aplicacion());
            stmt.setString(3, derechosusuapl.getDer_insertar());
            stmt.setString(4, derechosusuapl.getDer_editar());
            stmt.setString(5, derechosusuapl.getDer_eliminar());
            stmt.setString(6, derechosusuapl.getDer_imprimir());

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

    public int update(Derechosusuapl derechosusuapl) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, derechosusuapl.getUsername());
            stmt.setString(2, derechosusuapl.getNombre_aplicacion());
            stmt.setString(3, derechosusuapl.getDer_insertar());
            stmt.setString(4, derechosusuapl.getDer_editar());
            stmt.setString(5, derechosusuapl.getDer_eliminar());
            stmt.setString(6, derechosusuapl.getDer_imprimir());
            stmt.setInt(7, derechosusuapl.getId_derechosusuapl());
            
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

    public int delete(Derechosusuapl derechosusuapl) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, derechosusuapl.getId_derechosusuapl());
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

    public Derechosusuapl query(Derechosusuapl derechosusuapl) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Derechosusuapl> derechosusuaples = new ArrayList<Derechosusuapl>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, derechosusuapl.getId_derechosusuapl());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idDerechosusuapl = rs.getInt("id_derechosusuapl");
                String Username = rs.getString("username");
                String nombreAplicacion = rs.getString("nombre_aplicacion");
                String derInsertar = rs.getString("der_insertar");
                String derEditar = rs.getString("der_editar");
                String derEliminar = rs.getString("der_ eliminar");
                String derImprimir = rs.getString("der_imprimir");
                
                derechosusuapl = new Derechosusuapl();
                derechosusuapl.setId_derechosusuapl(idDerechosusuapl);
                derechosusuapl.setUsername(Username);
                derechosusuapl.setNombre_aplicacion(nombreAplicacion);
                derechosusuapl.setDer_insertar(derInsertar);
                derechosusuapl.setDer_editar(derEditar);
                derechosusuapl.setDer_eliminar(derEliminar);
                derechosusuapl.setDer_imprimir(derImprimir);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return derechosusuapl; 
    }
        
}
