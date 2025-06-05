/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.compras_cxp;

import Controlador.compras_cxp.Metododepago;
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

/**
 *
 * @author visitante
 */
public class MetododepagoDAO {

    private static final String SQL_SELECT = "SELECT id_metodo_pago, nombre_metodo_pago, estatus_metodo_pago FROM metodo_pago";
    private static final String SQL_INSERT = "INSERT INTO metodo_pago(nombre_metodo_pago, estatus_metodo_pago) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE metodo_pago SET nombre_metodo_pago=?, estatus_metodo_pago=? WHERE id_metodo_pago = ?";
    private static final String SQL_DELETE = "DELETE FROM metodo_pago WHERE id_metodo_pago=?";
    private static final String SQL_QUERY = "SELECT id_metodo_pago, nombre_metodo_pago, estatus_metodo_pago FROM metodo_pago WHERE id_metodo_pago = ?";

    public List<Metododepago> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Metododepago vendedor = null;
        List<Metododepago> vendedores = new ArrayList<Metododepago>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_metodo = rs.getInt("id_metodo_pago");
                String nombre = rs.getString("nombre_metodo_pago");
                String estatus = rs.getString("estatus_metodo_pago");
                
                vendedor = new Metododepago();
                vendedor.setId_metodoPago(id_metodo);
                vendedor.setNombreMetodoPago(nombre);
                vendedor.setEstatusMetodoPago(estatus);
                
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

    public int insert(Metododepago vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vendedor.getNombreMetodoPago());
            stmt.setString(2, vendedor.getEstatusMetodoPago());


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

    public int update(Metododepago vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, vendedor.getNombreMetodoPago());
            stmt.setString(2, vendedor.getEstatusMetodoPago());
            stmt.setInt(3, vendedor.getId_metodoPago());

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

    public int delete(Metododepago vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, vendedor.getId_metodoPago());
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
    public Metododepago query(Metododepago vendedor) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metododepago> vendedores = new ArrayList<Metododepago>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, vendedor.getId_metodoPago());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_metodo = rs.getInt("id_metodo_pago");
                String nombre = rs.getString("nombre_metodo_pago");
                String estatus = rs.getString("estatus_metodo_pago");
                
                vendedor = new Metododepago();
                vendedor.setId_metodoPago(id_metodo);
                vendedor.setNombreMetodoPago(nombre);
                vendedor.setEstatusMetodoPago(estatus);
                
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
        return vendedor;
    }
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reportes_compras_cxp/"+ "ReporteMetodoDePago.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}