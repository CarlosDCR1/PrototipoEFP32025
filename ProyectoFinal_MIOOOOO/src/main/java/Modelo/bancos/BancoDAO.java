package Modelo.bancos;
//MISHEL LOEIZA 9959-23-3457
import Controlador.bancos.bancos;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {

    private static final String SQL_SELECT = "SELECT id_banco, nombre, sede, direccion, telefono, email, status FROM banco";
    private static final String SQL_INSERT = "INSERT INTO banco(nombre, sede, direccion, telefono, email, status) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE banco SET nombre = ?, sede = ?, direccion = ?, telefono = ?, email = ?, status = ? WHERE id_banco = ?";
    private static final String SQL_DELETE = "DELETE FROM banco WHERE id_banco = ?";
    private static final String SQL_QUERY = "SELECT id_banco, nombre, sede, direccion, telefono, email, status FROM banco WHERE id_banco = ?";

    public List<bancos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<bancos> list_bancos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bancos banco = new bancos();
                banco.setId_banco(rs.getInt("id_banco"));
                banco.setNombre(rs.getString("nombre"));
                banco.setSede(rs.getString("sede"));
                banco.setDireccion(rs.getString("direccion"));
                banco.setTelefono(rs.getString("telefono"));
                banco.setEmail(rs.getString("email"));
                banco.setStatus(rs.getString("status"));
                list_bancos.add(banco);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_bancos;
    }

    public int insert(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, banco.getNombre());
            stmt.setString(2, banco.getSede());
            stmt.setString(3, banco.getDireccion());
            stmt.setString(4, banco.getTelefono());
            stmt.setString(5, banco.getEmail());
            stmt.setString(6, banco.getStatus());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, banco.getNombre());
            stmt.setString(2, banco.getSede());
            stmt.setString(3, banco.getDireccion());
            stmt.setString(4, banco.getTelefono());
            stmt.setString(5, banco.getEmail());
            stmt.setString(6, banco.getStatus());
            stmt.setInt(7, banco.getId_banco());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, banco.getId_banco());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public bancos query(bancos banco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, banco.getId_banco());
            rs = stmt.executeQuery();
            if (rs.next()) {
                banco.setNombre(rs.getString("nombre"));
                banco.setSede(rs.getString("sede"));
                banco.setDireccion(rs.getString("direccion"));
                banco.setTelefono(rs.getString("telefono"));
                banco.setEmail(rs.getString("email"));
                banco.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return banco;
    }

    public boolean existeBanco(int idBanco) {
        String sql = "SELECT 1 FROM banco WHERE id_banco = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBanco);
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
