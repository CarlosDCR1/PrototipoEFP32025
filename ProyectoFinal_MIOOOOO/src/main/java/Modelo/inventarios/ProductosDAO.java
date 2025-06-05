/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Modelo.inventarios;

import Modelo.ventas_cxc.*;
import Controlador.inventarios.productos;
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
public class ProductosDAO {

    private static final String SQL_SELECT = "SELECT proCodigo , linCodigo , marCodigo ,proNombre,proPrecios,proExistencias,proEstatus FROM tbl_productos";
   
    public List<productos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        productos productos_ls = null;
        List<productos> list_productos = new ArrayList<productos>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int proCodigo = rs.getInt("proCodigo");
                int linCodigo = rs.getInt("linCodigo");
                int marCodigo = rs.getInt("marCodigo");
                String proNombre = rs.getString("proNombre");
                Double proPrecios = rs.getDouble("proPrecios");
                int proExistencias = rs.getInt("proExistencias");
                String proEstatus = rs.getString("proEstatus");
                
                
                 productos_ls= new productos();
                productos_ls.setProCodigo(proCodigo);
                productos_ls.setLinCodigo(linCodigo);
                productos_ls.setMarCodigo(marCodigo);
                productos_ls.setProNombre(proNombre);
                productos_ls.setProPrecio(proPrecios);
                productos_ls.setProExistencias(proExistencias);
                productos_ls.setProEstatus(proEstatus);
               
              
                
                list_productos.add(productos_ls);
              
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_productos;
            
}
public productos getById(int idProducto) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    productos producto = null;
    
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT * FROM tbl_productos WHERE proCodigo = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProducto);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            producto = new productos();
            producto.setProCodigo(rs.getInt("proCodigo"));
            producto.setProNombre(rs.getString("proNombre"));
            producto.setProPrecio(rs.getDouble("proPrecios"));
            // Agrega aquí otros campos necesarios
        }
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return producto;
}
public void actualizarExistencias(int proCodigo, int cantidadVendida) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = Conexion.getConnection();
        String sql = "UPDATE tbl_productos SET proExistencias = proExistencias - ? WHERE proCodigo = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cantidadVendida);
        stmt.setInt(2, proCodigo);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(stmt);
        Conexion.close(conn);
    }
}




}
        