/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo.ventas_cxc;

import Controlador.ventas_cxc.Clientes;
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
public class ClientesDAO {

    private static final String SQL_SELECT = "SELECT id_cliente, nombre_cliente, apellido_cliente,direccion_cliente,telefono_cliente,email_cliente,limite_credito,dias_credito,estatus_cliente,saldo_actual FROM clientes";
    private static final String SQL_INSERT = "INSERT INTO clientes(nombre_cliente, apellido_cliente,direccion_cliente,telefono_cliente,email_cliente,limite_credito,dias_credito,estatus_cliente,saldo_actual) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE clientes SET nombre_cliente=?, apellido_cliente=?, direccion_cliente=?, telefono_cliente=?, email_cliente=?, limite_credito=?, dias_credito=?, estatus_cliente=?, saldo_actual=? WHERE id_cliente=?";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente=?";
    private static final String SQL_QUERY = "SELECT id_cliente, nombre_cliente, apellido_cliente,direccion_cliente,telefono_cliente,email_cliente,limite_credito,dias_credito,estatus_cliente,saldo_actual FROM clientes WHERE id_cliente= ?";

    public List<Clientes> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes clientes = null;
        List<Clientes> list_clientes = new ArrayList<Clientes>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String nombre_cliente = rs.getString("nombre_cliente");
                String apellido_cliente = rs.getString("apellido_cliente");
                String direccion_cliente = rs.getString("direccion_cliente");
                String telefono_cliente = rs.getString("telefono_cliente");
                String email_cliente = rs.getString("email_cliente");
                double limite_creditoCLE = rs.getDouble("limite_credito");
                int dias_credito = rs.getInt("dias_credito");
                String estatus_cliente = rs.getString("estatus_cliente");
                Double saldo_actual = rs.getDouble("saldo_actual");
                
                clientes = new Clientes();
                clientes.setId_cliente(id_cliente);
                clientes.setNombre_cliente(nombre_cliente);
                clientes.setApellido_cliente(apellido_cliente);
                clientes.setDireccion_cliente(direccion_cliente);
                clientes.setTelefono_cliente(telefono_cliente);
                clientes.setEmaill_cliente(email_cliente);
                clientes.setLimite_credito_CLE(limite_creditoCLE);
                 clientes.setDias_credito_CLE(dias_credito);
                clientes.setEstatus_cliente(estatus_cliente);
                clientes.setSaldo_actual_CLE(saldo_actual);
              
                
                list_clientes.add(clientes);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_clientes;
    }

    public int insert(Clientes clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, clientes.getNombre_cliente());
            stmt.setString(2, clientes.getApellido_cliente());
            stmt.setString(3, clientes.getDireccion_cliente());
            stmt.setString(4, clientes.getTelefono_cliente());
            stmt.setString(5, clientes.getEmaill_cliente());
            stmt.setDouble(6, clientes.getLimite_credito_CLE());
            stmt.setInt(7, clientes.getDias_credito_CLE());
            stmt.setString(8, clientes.getEstatus_cliente());
            stmt.setDouble(9, clientes.getSaldo_actual_CLE());


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

    public int update(Clientes clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
             stmt.setString(1, clientes.getNombre_cliente());
            stmt.setString(2, clientes.getApellido_cliente());
            stmt.setString(3, clientes.getDireccion_cliente());
            stmt.setString(4, clientes.getTelefono_cliente());
            stmt.setString(5, clientes.getEmaill_cliente());
            stmt.setDouble(6, clientes.getLimite_credito_CLE());
            stmt.setInt(7, clientes.getDias_credito_CLE());
            stmt.setString(8, clientes.getEstatus_cliente());
            stmt.setDouble(9, clientes.getSaldo_actual_CLE());   
            stmt.setDouble(10, clientes.getId_cliente());   

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

    public int delete(Clientes clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, clientes.getId_cliente()); 
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
    public Clientes query(Clientes clientes) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> list_clientes = new ArrayList<Clientes>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, clientes.getId_cliente());
          
            rs = stmt.executeQuery();
            while (rs.next()) {
             int id_cliente = rs.getInt("id_cliente");
                String nombre_cliente = rs.getString("nombre_cliente");
                String apellido_cliente = rs.getString("apellido_cliente");
                String direccion_cliente = rs.getString("direccion_cliente");
                String telefono_cliente = rs.getString("telefono_cliente");
                String email_cliente = rs.getString("email_cliente");
                double limite_creditoCLE = rs.getDouble("limite_credito");
                int dias_credito = rs.getInt("dias_credito");
                String estatus_cliente = rs.getString("estatus_cliente");
                Double saldo_actual = rs.getDouble("saldo_actual");
                
                clientes = new Clientes();
                clientes.setId_cliente(id_cliente);
                clientes.setNombre_cliente(nombre_cliente);
                clientes.setApellido_cliente(apellido_cliente);
                clientes.setDireccion_cliente(direccion_cliente);
                clientes.setTelefono_cliente(telefono_cliente);
                clientes.setEmaill_cliente(email_cliente);
                clientes.setLimite_credito_CLE(limite_creditoCLE);
                 clientes.setDias_credito_CLE(dias_credito);
                clientes.setEstatus_cliente(estatus_cliente);
                clientes.setSaldo_actual_CLE(saldo_actual);
              
                
               
                
                
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

       
        return clientes;
    }
    
    public Clientes getById(int idCliente) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Clientes cliente = null;
    
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCliente);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            cliente = new Clientes();
            cliente.setId_cliente(rs.getInt("id_cliente"));
            cliente.setNombre_cliente(rs.getString("nombre_cliente"));
            cliente.setApellido_cliente(rs.getString("apellido_cliente"));
            cliente.setLimite_credito_CLE(rs.getDouble("limite_credito"));
            cliente.setDias_credito_CLE(rs.getInt("dias_credito"));// Asegúrate que este campo existe
            cliente.setSaldo_actual_CLE(rs.getDouble("saldo_actual")); // Asegúrate que este campo existe
            
        }
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return cliente;
}
    
    
            
}