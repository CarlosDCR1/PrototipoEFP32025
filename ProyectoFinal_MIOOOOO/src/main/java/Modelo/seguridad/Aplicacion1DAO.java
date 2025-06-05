/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;
//---------------Victor Omar Gomez Carrascosa 9959-23-10733-----------------------------------------
import Controlador.seguridad.Aplicacion1;
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
public class Aplicacion1DAO {

    private static final String SQL_SELECT = "SELECT id_aplicacion, nombre_aplicacion, estatus_aplicacion FROM aplicacion";
    private static final String SQL_INSERT = "INSERT INTO aplicacion(id_aplicacion,nombre_aplicacion, estatus_aplicacion) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE aplicacion SET  nombre_aplicacion=?, estatus_aplicacion=? WHERE id_aplicacion = ?";
    private static final String SQL_DELETE = "DELETE FROM aplicacion WHERE id_aplicacion=?";
    private static final String SQL_QUERY = "SELECT id_aplicacion, nombre_aplicacion, estatus_aplicacion FROM aplicacion WHERE id_aplicacion = ?";

    public List<Aplicacion1> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aplicacion1 aplicacion = null;
        List<Aplicacion1> list_aplicaciones = new ArrayList<Aplicacion1>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_aplicacion = rs.getInt("id_aplicacion");
                String nombre_aplicacion = rs.getString("nombre_aplicacion");
                String estatus_aplicacion = rs.getString("estatus_aplicacion");
                
                aplicacion = new Aplicacion1();
                aplicacion.setId_aplicacion(id_aplicacion);
                aplicacion.setNombre_aplicacion(nombre_aplicacion);
                aplicacion.setEstatus_aplicacion(estatus_aplicacion);
              
                
                list_aplicaciones.add(aplicacion);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_aplicaciones;
    }

    public int insert(Aplicacion1 aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getId_aplicacion());
            stmt.setString(2, aplicacion.getNombre_aplicacion());
            stmt.setString(3, aplicacion.getEstatus_aplicacion());
         


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

    public int update(Aplicacion1 aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, aplicacion.getNombre_aplicacion());
            stmt.setString(2, aplicacion.getEstatus_aplicacion());
            //comodin del where
            stmt.setInt(3,aplicacion.getId_aplicacion());
           

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Aplicacion1 aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getId_aplicacion());
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

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Aplicacion1 query(Aplicacion1 aplicacion) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aplicacion1> list_aplicacion = new ArrayList<Aplicacion1>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, aplicacion.getId_aplicacion());
            rs = stmt.executeQuery();
            while (rs.next()) {
             int id_aplicacion = rs.getInt("id_aplicacion");
                String nombre_aplicacion = rs.getString("nombre_aplicacion");
                String estatus_aplicacion = rs.getString("estatus_aplicacion");
                
                aplicacion = new Aplicacion1();
                aplicacion.setId_aplicacion(id_aplicacion);
                aplicacion.setNombre_aplicacion(nombre_aplicacion);
                aplicacion.setEstatus_aplicacion(estatus_aplicacion);
              
                
               
                
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return vendedores;  // Si se utiliza un ArrayList
        return aplicacion;
    }
            
}