package Modelo.ventas_cxc;

import Controlador.ventas_cxc.Ventascxc;
import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentascxcDAO {

    private static final String SQL_SELECT = "SELECT no_factura, no_venta, id_vendedor, nombre_cliente, apellido_cliente, proCodigo, cantidad, proPrecios, saldo_actual, proNombre, dias_credito, total, fecha_venta FROM transaccionalvxc";

    private static final String SQL_INSERT = "INSERT INTO transaccionalvxc(no_factura, no_venta, id_vendedor, nombre_cliente, apellido_cliente, proCodigo, cantidad, proPrecios, saldo_actual, proNombre, dias_credito, total,fecha_venta) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";

    public List<Ventascxc> select() {
        List<Ventascxc> listaTransacciones = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ventascxc transaccion = new Ventascxc();
                transaccion.setNo_factura(rs.getInt("no_factura"));
                transaccion.setNo_venta(rs.getString("no_venta"));
                transaccion.setId_vendedor(rs.getInt("id_vendedor"));
                transaccion.setNombre_cliente(rs.getString("nombre_cliente"));
                transaccion.setApellido_cliente(rs.getString("apellido_cliente"));
                transaccion.setPro_codigo(rs.getInt("proCodigo"));
                transaccion.setCantidad(rs.getInt("cantidad"));
                transaccion.setProPrecios(rs.getDouble("proPrecios"));
                transaccion.setSaldo_actual(rs.getDouble("saldo_actual"));
                transaccion.setProNombre(rs.getString("proNombre"));
                transaccion.setDias_credito(rs.getInt("dias_credito"));
                transaccion.setTotal(rs.getDouble("total"));
                transaccion.setFecha_venta(rs.getString("fecha_venta"));
                listaTransacciones.add(transaccion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return listaTransacciones;
    }

    public boolean insert(Ventascxc venta) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, venta.getNo_factura());
            stmt.setString(2, venta.getNo_venta());
            stmt.setInt(3, venta.getId_vendedor());
            stmt.setString(4, venta.getNombre_cliente());
            stmt.setString(5, venta.getApellido_cliente());
            stmt.setInt(6, venta.getPro_codigo());
            stmt.setInt(7, venta.getCantidad());
            stmt.setDouble(8, venta.getProPrecios());
            stmt.setDouble(9, venta.getSaldo_actual());
            stmt.setString(10, venta.getProNombre());
            stmt.setInt(11, venta.getDias_credito());
            stmt.setDouble(12, venta.getTotal());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    public String obtenerUltimoNoVenta() {
        String sql = "SELECT MAX(no_venta) FROM ventas";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Ventascxc UltiVenta(int idCliente) {
    String sql = "SELECT * FROM transaccionalvxc WHERE no_factura = ? ORDER BY fecha_venta DESC, no_venta DESC LIMIT 1";
    
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            Ventascxc venta = new Ventascxc();
            // Mapear todos los campos necesarios
            venta.setNo_venta(rs.getString("no_venta"));
            venta.setNo_factura(rs.getInt("no_factura"));
            venta.setTotal(rs.getDouble("total"));
            // ... otros campos que necesites
            return venta;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener última venta: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
    return null;
}
    
    
    
    
    
    
    
}
