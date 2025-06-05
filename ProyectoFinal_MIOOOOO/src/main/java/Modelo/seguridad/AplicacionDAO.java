/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;
//---------------Victor Omar Gomez Carrascosa 9959-23-10733-----------------------------------------
import Controlador.seguridad.Aplicacion;
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
public class AplicacionDAO {

    private static final String SQL_SELECT = "SELECT pkid, idTipoBodega , nombre, direccion, estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega(pkid,idTipoBodega, nombre,direccion,estado) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET  idTipoBodega=?, nombre=?, direccion=?,estado=? WHERE pkid = ?";
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE pkid=?";
    private static final String SQL_QUERY = "SELECT pkid, idTipoBodega, nombre, direccion, estado FROM bodega WHERE pkid = ?";

    public List<Aplicacion> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aplicacion aplicacion = null;
        List<Aplicacion> list_aplicaciones = new ArrayList<Aplicacion>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String pkId = rs.getString("pkid");
                String idTipoBodega = rs.getString("idTipoBodega");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String estatus_aplicacion = rs.getString("estado");
                
                aplicacion = new Aplicacion();
                aplicacion.setPkId(pkId);
                aplicacion.setIdTipoBodega(idTipoBodega);
                aplicacion.setNombre(nombre);
                aplicacion.setDireccion(direccion);
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

    public int insert(Aplicacion aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, aplicacion.getPkId());
            stmt.setString(2, aplicacion.getIdTipoBodega());
            stmt.setString(3, aplicacion.getNombre());
            stmt.setString(4, aplicacion.getDireccion());
            stmt.setString(5, aplicacion.getEstatus_aplicacion());
         


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

    public int update(Aplicacion aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, aplicacion.getIdTipoBodega());
            stmt.setString(2, aplicacion.getNombre());
            stmt.setString(3, aplicacion.getDireccion());
            stmt.setString(4, aplicacion.getEstatus_aplicacion());
            //comodin del where
            stmt.setString(5,aplicacion.getPkId());
           

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

    public int delete(Aplicacion aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, aplicacion.getPkId());
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
    public Aplicacion query(Aplicacion aplicacion) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aplicacion> list_aplicacion = new ArrayList<Aplicacion>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, aplicacion.getPkId());
            rs = stmt.executeQuery();
            while (rs.next()) {
             String pkId = rs.getString("pkid");
             String idTipoBodega = rs.getString("idTipoBodega");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String estatus_aplicacion = rs.getString("estado");
                
                aplicacion = new Aplicacion();
                aplicacion.setPkId(pkId);
                aplicacion.setIdTipoBodega(idTipoBodega);
                aplicacion.setNombre(nombre);
                aplicacion.setDireccion(direccion);
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