/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.compras_cxp;

import Controlador.compras_cxp.*;
import Controlador.compras_cxp.Compra_cpp;
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

public class Compras_cppDAO {

    private static final String SQL_SELECT = "SELECT no_compra, nombre_usuario, apellido_usuario, id_proveedor, producto, cantidad, precio, saldo_anterior, plazo, total   FROM compra_cpp";
    private static final String SQL_INSERT = "INSERT INTO compra_cpp (no_compra, nombre_usuario, apellido_usuario, id_proveedor, producto, cantidad, precio, saldo_anterior, plazo, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE compra_cpp SET nombre_usuario=?, apellido_usuario=?, id_proveedor=?, producto=?, cantidad=?, precio=?, saldo_anterior=?, plazo=?, total=? WHERE no_compra=? ";
    private static final String SQL_DELETE = "DELETE FROM compra_cpp WHERE no_compra=?";
    private static final String SQL_QUERY = "SELECT no_compra, nombre_usuario, apellido_usuario, id_proveedor, producto, cantidad, precio, saldo_anterior, plazo, total FROM compra_cpp WHERE no_compra=?";

    public List<Compra_cpp> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Compra_cpp> list_compra_cpp = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Compra_cpp compra_cpp = new Compra_cpp();
                compra_cpp.setNo_compra(rs.getInt("no_compra"));
                compra_cpp.setNombre_usuario(rs.getString("nombre_usuario"));
                compra_cpp.setApellido_usuario(rs.getString("apellido_usuario"));
                compra_cpp.setId_proveedor(rs.getInt("id_proveedor"));
                compra_cpp.setProducto(rs.getString("producto"));
                compra_cpp.setCantidad(rs.getInt("cantidad"));
                compra_cpp.setPrecio(rs.getInt("precio"));
                compra_cpp.setSaldo_anterior(rs.getInt("saldo_anterior"));
                compra_cpp.setPlazo(rs.getInt("plazo"));
                compra_cpp.setTotal(rs.getInt("total"));

                list_compra_cpp.add(compra_cpp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_compra_cpp;
    }

    public int insert(Compra_cpp compra_cpp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, compra_cpp.getNo_compra());
            stmt.setString(2, compra_cpp.getNombre_usuario());
            stmt.setString(3, compra_cpp.getApellido_usuario());
            stmt.setInt(4, compra_cpp.getId_proveedor());
            stmt.setString(5, compra_cpp.getProducto());
            stmt.setInt(6, compra_cpp.getCantidad());
            stmt.setInt(7, compra_cpp.getPrecio());
            stmt.setInt(8, compra_cpp.getSaldo_anterior());
            stmt.setInt(9, compra_cpp.getPlazo());
            stmt.setInt(10, compra_cpp.getTotal());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Compra_cpp compra_cpp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, compra_cpp.getNo_compra());
            stmt.setString(2, compra_cpp.getNombre_usuario());
            stmt.setString(3, compra_cpp.getApellido_usuario());
            stmt.setInt(4, compra_cpp.getId_proveedor());
            stmt.setString(5, compra_cpp.getProducto());
            stmt.setInt(6, compra_cpp.getCantidad());
            stmt.setInt(7, compra_cpp.getPrecio());
            stmt.setInt(8, compra_cpp.getSaldo_anterior());
            stmt.setInt(9, compra_cpp.getPlazo());
            stmt.setInt(10, compra_cpp.getTotal());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Compra_cpp compra_cpp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, compra_cpp.getNo_compra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Compra_cpp query(Compra_cpp compra_cpp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, compra_cpp.getNo_compra());
            rs = stmt.executeQuery();
            if (rs.next()) {
                compra_cpp.setNo_compra(rs.getInt("no_compra"));
                compra_cpp.setNombre_usuario(rs.getString("nombre_usuario"));
                compra_cpp.setApellido_usuario(rs.getString("apellido_usuario"));
                compra_cpp.setId_proveedor(rs.getInt("id_proveedor"));
                compra_cpp.setProducto(rs.getString("producto"));
                compra_cpp.setCantidad(rs.getInt("cantidad"));
                compra_cpp.setPrecio(rs.getInt("precio"));
                compra_cpp.setSaldo_anterior(rs.getInt("saldo_anterior"));
                compra_cpp.setPlazo(rs.getInt("plazo"));
                compra_cpp.setTotal(rs.getInt("total"));
                
            }

            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return compra_cpp;
        
    }
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/ReporteCompraCPP/"+ "reporte.comprascpp.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
