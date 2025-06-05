/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.compras_cxp;

import Controlador.compras_cxp.*;
import Controlador.compras_cxp.Proveedor;
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ProveedorDAO {

    private static final String SQL_SELECT = "SELECT id_proveedor, nombre_proveedor, direccion_proveedor, telefono_proveedor, email_proveedor, saldo_proveedor, estatus_proveedor, fecha_registro, plazo_limite   FROM proveedor";
    private static final String SQL_INSERT = "INSERT INTO proveedor (id_proveedor, nombre_proveedor, direccion_proveedor, telefono_proveedor, email_proveedor, saldo_proveedor, estatus_proveedor, fecha_registro, plazo_limite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE proveedor SET nombre_proveedor=?, direccion_proveedor=?, telefono_proveedor=?, email_proveedor=?, saldo_proveedor=?, estatus_proveedor=?, fecha_registro=?, plazo_limite=? WHERE id_proveedor=? ";
    private static final String SQL_DELETE = "DELETE FROM proveedor WHERE id_proveedor=?";
    private static final String SQL_QUERY = "SELECT id_proveedor, nombre_proveedor, direccion_proveedor, telefono_proveedor, email_proveedor, saldo_proveedor, estatus_proveedor, fecha_registro, plazo_limite FROM proveedor WHERE id_proveedor=?";

    public List<Proveedor> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Proveedor> list_proveedores = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                proveedor.setEmail_proveedor(rs.getString("email_proveedor"));
                proveedor.setSaldo_proveedor(rs.getInt("saldo_proveedor"));
                proveedor.setEstatus_proveedor(rs.getInt("estatus_proveedor"));
                proveedor.setFecha_registro(rs.getString("fecha_registro"));
                proveedor.setPlazo_limite(rs.getInt("plazo_limite"));

                list_proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_proveedores;
    }

    public int insert(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, proveedor.getId_proveedor());
            stmt.setString(2, proveedor.getNombre_proveedor());
            stmt.setString(3, proveedor.getDireccion_proveedor());
            stmt.setString(4, proveedor.getTelefono_proveedor());
            stmt.setString(5, proveedor.getEmail_proveedor());
            stmt.setInt(6, proveedor.getSaldo_proveedor());
            stmt.setInt(7, proveedor.getEstatus_proveedor());
            stmt.setString(8, proveedor.getFecha_registro());
            stmt.setInt(9, proveedor.getPlazo_limite());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
                stmt.setString(1, proveedor.getNombre_proveedor());
            stmt.setString(2, proveedor.getDireccion_proveedor());
            stmt.setString(3, proveedor.getTelefono_proveedor());
            stmt.setString(4, proveedor.getEmail_proveedor());
            stmt.setInt(5, proveedor.getSaldo_proveedor());
            stmt.setInt(6, proveedor.getEstatus_proveedor());
            stmt.setString(7, proveedor.getFecha_registro());
            stmt.setInt(8, proveedor.getPlazo_limite());          // ✅ ahora en la posición correcta
            stmt.setInt(9, proveedor.getId_proveedor()); 

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, proveedor.getId_proveedor());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Proveedor query(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, proveedor.getId_proveedor());
            rs = stmt.executeQuery();
            if (rs.next()) {
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                proveedor.setEmail_proveedor(rs.getString("email_proveedor"));
                proveedor.setSaldo_proveedor(rs.getInt("saldo_proveedor"));
                proveedor.setEstatus_proveedor(rs.getInt("estatus_proveedor"));
                proveedor.setFecha_registro(rs.getString("fecha_registro"));
                proveedor.setPlazo_limite(rs.getInt("plazo_limite"));
                
            }

            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return proveedor;
        
    }
     
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/ReporteProveedores_cxp/"+ "Reporte.Proveedor.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
