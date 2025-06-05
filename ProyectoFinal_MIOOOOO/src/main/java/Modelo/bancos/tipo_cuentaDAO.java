package Modelo.bancos;
//CREADO POR Gabriela Pinto  9959-23-1087
import Modelo.Conexion;
import Controlador.bancos.tipo_cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipo_cuentaDAO {

     // Consultas SQL necesarias para operar con la tabla tipo_cuenta
    private static final String SQL_SELECT = "SELECT id_tipo_cuenta, tipo_cuenta, status FROM tipo_cuenta";
    private static final String SQL_INSERT = "INSERT INTO tipo_cuenta(tipo_cuenta, status) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tipo_cuenta SET tipo_cuenta=?, status=? WHERE id_tipo_cuenta = ?";
    private static final String SQL_QUERY = "SELECT id_tipo_cuenta, tipo_cuenta, status FROM tipo_cuenta WHERE id_tipo_cuenta = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_cuenta WHERE id_tipo_cuenta = ?";
    private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tipo_cuenta WHERE tipo_cuenta = ?";
    private static final String SQL_EXISTE_ID = "SELECT 1 FROM tipo_cuenta WHERE id_tipo_cuenta = ?";

     // Método para obtener todos los tipos de cuenta
    public List<tipo_cuenta> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_cuenta tipoCuenta = null;
        List<tipo_cuenta> listTipoCuentas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                String tipoCuentaStr = rs.getString("tipo_cuenta");
                int status = rs.getInt("status");

                tipoCuenta = new tipo_cuenta();
                tipoCuenta.setId_tipo_cuenta(idTipoCuenta);
                tipoCuenta.setTipo_cuenta(tipoCuentaStr);
                tipoCuenta.setStatus(status);

                listTipoCuentas.add(tipoCuenta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return listTipoCuentas;
    }

    // Método para insertar un nuevo tipo de cuenta
    public int insert(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipoCuenta.getTipo_cuenta());
            stmt.setInt(2, tipoCuenta.getStatus());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Método para actualizar un tipo de cuenta existente
    public int update(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipoCuenta.getTipo_cuenta());
            stmt.setInt(2, tipoCuenta.getStatus());
            stmt.setInt(3, tipoCuenta.getId_tipo_cuenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Método para eliminar un tipo de cuenta por ID
    public int delete(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipoCuenta.getId_tipo_cuenta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    // Método para consultar un tipo de cuenta por ID
    public tipo_cuenta query(tipo_cuenta tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipoCuenta.getId_tipo_cuenta());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idTipoCuenta = rs.getInt("id_tipo_cuenta");
                String tipoCuentaStr = rs.getString("tipo_cuenta");
                int status = rs.getInt("status");

                tipoCuenta = new tipo_cuenta();
                tipoCuenta.setId_tipo_cuenta(idTipoCuenta);
                tipoCuenta.setTipo_cuenta(tipoCuentaStr);
                tipoCuenta.setStatus(status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipoCuenta;
    }

    // Verifica si el tipo_cuenta ya existe por nombre
    public boolean existeTipoCuenta(String tipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, tipoCuenta);
            rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
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

    // ✅ Verifica si existe por id_tipo_cuenta
    public boolean existeTipoCuenta(int idTipoCuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE_ID);
            stmt.setInt(1, idTipoCuenta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                existe = true;
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
