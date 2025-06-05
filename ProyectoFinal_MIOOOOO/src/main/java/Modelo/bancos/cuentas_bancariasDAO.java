package Modelo.bancos;

import Modelo.Conexion;
import Controlador.bancos.cuentas_bancarias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cuentas_bancariasDAO {
//Hecho por Gabriela Pinto
// DAO para operaciones CRUD sobre la tabla cuentas_bancarias, incluye info relacionada de tipo_moneda y tasas_cambio_diario
    
    // Consultas SQL para SELECT, INSERT, UPDATE, DELETE, QUERY y verificación de existencia
    private static final String SQL_SELECT = 
        "SELECT cb.id_cuenta, cb.id_banco, cb.id_tipo_cuenta, cb.id_tipo_moneda, cb.saldo, " +
        "tm.tipo_moneda, tcd.valor_promedio_dia AS tasa_cambio " +
        "FROM cuentas_bancarias cb " +
        "JOIN tipo_moneda tm ON cb.id_tipo_moneda = tm.id_tipo_moneda " +
        "LEFT JOIN tasas_cambio_diario tcd ON tm.id_tasa_cambio_diario = tcd.id_tasa_cambio_diario";

    private static final String SQL_INSERT = 
        "INSERT INTO cuentas_bancarias(id_banco, id_tipo_cuenta, id_tipo_moneda, saldo) VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = 
        "UPDATE cuentas_bancarias SET id_banco=?, id_tipo_cuenta=?, id_tipo_moneda=?, saldo=? WHERE id_cuenta = ?";

    private static final String SQL_QUERY = 
        "SELECT cb.id_cuenta, cb.id_banco, cb.id_tipo_cuenta, cb.id_tipo_moneda, cb.saldo, " +
        "tm.tipo_moneda, tcd.valor_promedio_dia AS tasa_cambio " +
        "FROM cuentas_bancarias cb " +
        "JOIN tipo_moneda tm ON cb.id_tipo_moneda = tm.id_tipo_moneda " +
        "LEFT JOIN tasas_cambio_diario tcd ON tm.id_tasa_cambio_diario = tcd.id_tasa_cambio_diario " +
        "WHERE cb.id_cuenta = ?";

    private static final String SQL_DELETE = 
        "DELETE FROM cuentas_bancarias WHERE id_cuenta = ?";

    private static final String SQL_EXISTE = 
        "SELECT COUNT(*) FROM cuentas_bancarias WHERE id_cuenta = ?";

    public List<cuentas_bancarias> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<cuentas_bancarias> listCuentasBancarias = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cuentas_bancarias cuentaBancaria = new cuentas_bancarias();
                cuentaBancaria.setId_cuenta(rs.getInt("id_cuenta"));
                cuentaBancaria.setId_banco(rs.getInt("id_banco"));
                cuentaBancaria.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                cuentaBancaria.setId_tipo_moneda(rs.getInt("id_tipo_moneda"));
                cuentaBancaria.setSaldo(rs.getDouble("saldo"));
                // Estos campos nuevos que traemos de la join:
                cuentaBancaria.setTipo_moneda(rs.getString("tipo_moneda")); // Asume que tienes este setter
                cuentaBancaria.setTasa_cambio(rs.getDouble("tasa_cambio")); // Asume que tienes este setter
                listCuentasBancarias.add(cuentaBancaria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listCuentasBancarias;
    }

    // Insertar una nueva cuenta bancaria
    public int insert(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cuentaBancaria.getId_banco());
            stmt.setInt(2, cuentaBancaria.getId_tipo_cuenta());
            stmt.setInt(3, cuentaBancaria.getId_tipo_moneda());
            stmt.setDouble(4, cuentaBancaria.getSaldo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Actualizar los datos de una cuenta bancaria
    public int update(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, cuentaBancaria.getId_banco());
            stmt.setInt(2, cuentaBancaria.getId_tipo_cuenta());
            stmt.setInt(3, cuentaBancaria.getId_tipo_moneda());
            stmt.setDouble(4, cuentaBancaria.getSaldo());
            stmt.setInt(5, cuentaBancaria.getId_cuenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Eliminar una cuenta bancaria por ID
    public int delete(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cuentaBancaria.getId_cuenta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Consultar una cuenta bancaria por su ID
    public cuentas_bancarias query(cuentas_bancarias cuentaBancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, cuentaBancaria.getId_cuenta());
            rs = stmt.executeQuery();
            if (rs.next()) {
                cuentaBancaria = new cuentas_bancarias();
                cuentaBancaria.setId_cuenta(rs.getInt("id_cuenta"));
                cuentaBancaria.setId_banco(rs.getInt("id_banco"));
                cuentaBancaria.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
                cuentaBancaria.setId_tipo_moneda(rs.getInt("id_tipo_moneda"));
                cuentaBancaria.setSaldo(rs.getDouble("saldo"));
                // Nuevos campos de la join:
                cuentaBancaria.setTipo_moneda(rs.getString("tipo_moneda"));
                cuentaBancaria.setTasa_cambio(rs.getDouble("tasa_cambio"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cuentaBancaria;
    }

    // Verifica si existe una cuenta bancaria por su ID
    public boolean existeCuenta(int idCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setInt(1, idCuenta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existe;
    }
}
