/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.ventas_cxc;

import Controlador.ventas_cxc.Vendedores;
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
public class VendedoresDAO {

    private static final String SQL_SELECT = "SELECT id_vendedor, nombre_vendedor, apellido_vendedor, direccion_vendedor, telefono_vendedor, email_vendedor, comision, estatus_vendedor FROM vendedores";
    private static final String SQL_INSERT = "INSERT INTO vendedores(nombre_vendedor,apellido_vendedor,direccion_vendedor, telefono_vendedor, email_vendedor, comision, estatus_vendedor) VALUES(?, ? , ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE vendedores SET nombre_vendedor=?, apellido_vendedor=?, direccion_vendedor=?, telefono_vendedor=?, email_vendedor=?, comision=?, estatus_vendedor=? WHERE id_vendedor = ?"; //comodines son los =?
    private static final String SQL_DELETE = "DELETE FROM vendedores WHERE id_vendedor=?";
    private static final String SQL_QUERY = "SELECT id_vendedor, nombre_vendedor, apellido_vendedor, direccion_vendedor, telefono_vendedor, email_vendedor, comision, estatus_vendedor FROM vendedores WHERE id_vendedor = ?";

    public List<Vendedores> select() { //consultar
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vendedores vendedor = null; 
        List<Vendedores> vendedores = new ArrayList<Vendedores>(); //vendedores = alumnos

        try { //permite condicionar y captar un error
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idVendedor = rs.getInt("id_vendedor");
                String nombreVendedor = rs.getString("nombre_vendedor");
                String apellidoVendedor = rs.getString("apellido_vendedor");
                String direccionVendedor = rs.getString("direccion_vendedor");
                String telefonoVendedor = rs.getString("telefono_vendedor");
                String emailVendedor = rs.getString("email_vendedor");
                String cOmision = rs.getString("comision");
                String estatusVendedor = rs.getString("estatus_vendedor");
                
                vendedor = new Vendedores();
                vendedor.setId_vendedor(idVendedor);
                vendedor.setNombre_vendedor(nombreVendedor);
                vendedor.setApellido_vendedor(apellidoVendedor);
                vendedor.setDireccion_vendedor(direccionVendedor);
                vendedor.setTelefono_vendedor(telefonoVendedor);
                vendedor.setEmail_vendedor(emailVendedor);
                vendedor.setComision(cOmision);
                vendedor.setEstatus_vendedor(estatusVendedor);
                
                
                
                vendedores.add(vendedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return vendedores;
    }

    public int insert(Vendedores vendedores) { //insertar datos
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //stmt.setString(1, alumno.getCarnet_alumno());
            stmt.setString(1, vendedores.getNombre_vendedor());
            stmt.setString(2, vendedores.getApellido_vendedor());
            stmt.setString(3, vendedores.getDireccion_vendedor());
            stmt.setString(4, vendedores.getTelefono_vendedor());
            stmt.setString(5, vendedores.getEmail_vendedor());
            stmt.setString(6, vendedores.getComision());
            stmt.setString(7, vendedores.getEstatus_vendedor());

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

    public int update(Vendedores vendedores) { //actualizar
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, vendedores.getNombre_vendedor());
            stmt.setString(2, vendedores.getApellido_vendedor());
            stmt.setString(3, vendedores.getDireccion_vendedor());
            stmt.setString(4, vendedores.getTelefono_vendedor());
            stmt.setString(5, vendedores.getEmail_vendedor());
            stmt.setString(6, vendedores.getComision());
            stmt.setString(7, vendedores.getEstatus_vendedor());
            stmt.setInt(8, vendedores.getId_vendedor());
            
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

    public int delete(Vendedores vendedores) {//elimina
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, vendedores.getId_vendedor());
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

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Vendedores query(Vendedores vendedores) {    //Select enfocado
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendedores> vendedor = new ArrayList<Vendedores>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, vendedores.getId_vendedor());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idVendedor = rs.getInt("id_vendedor");
                String nombreVendedor = rs.getString("nombre_vendedor");
                String apellidoVendedor = rs.getString("apellido_vendedor");
                String direccionVendedor = rs.getString("direccion_vendedor");
                String telefonoVendedor = rs.getString("telefono_vendedor");
                String emailVendedor = rs.getString("email_vendedor");
                String cOmision = rs.getString("comision");
                String estatusVendedor = rs.getString("estatus_vendedor");
                
                
                vendedores = new Vendedores();
                vendedores.setId_vendedor(idVendedor);
                vendedores.setNombre_vendedor(nombreVendedor);
                vendedores.setApellido_vendedor(apellidoVendedor);
                vendedores.setDireccion_vendedor(direccionVendedor);
                vendedores.setTelefono_vendedor(telefonoVendedor);
                vendedores.setEmail_vendedor(emailVendedor);
                vendedores.setComision(cOmision);
                vendedores.setEstatus_vendedor(estatusVendedor);
                
                //con los set, enviamos los objetos
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
        return vendedores; //retorna el objeto unico
    }
        
}
