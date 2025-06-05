package Modelo.bancos;
//CREADO POR MISHEL LOEIZA 9959-23-3457
// Modificado por Ruddyard Castro 
import Modelo.Conexion;
import Controlador.bancos.tipo_moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipo_monedaDAO {

    private static final String SQL_SELECT = "SELECT id_tipo_moneda, tipo_moneda, id_tasa_cambio_diario FROM tipo_moneda";
    private static final String SQL_INSERT = "INSERT INTO tipo_moneda(tipo_moneda, id_tasa_cambio_diario) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tipo_moneda SET tipo_moneda=?, id_tasa_cambio_diario=? WHERE id_tipo_moneda = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_moneda WHERE id_tipo_moneda=?";
    private static final String SQL_QUERY = "SELECT id_tipo_moneda, tipo_moneda, id_tasa_cambio_diario FROM tipo_moneda WHERE id_tipo_moneda = ?";
    private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tipo_moneda WHERE tipo_moneda = ?";
    private static final String SQL_EXISTE_ID = "SELECT 1 FROM tipo_moneda WHERE id_tipo_moneda = ?";
    private static final String SQL_GET_VALOR_TASA = "SELECT tcd.valor_promedio_dia FROM tasas_cambio_diario tcd WHERE tcd.id_tasa_cambio_diario = ?";

    public List<tipo_moneda> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_moneda tipo_moneda = null;
        List<tipo_moneda> list_tipo_monedas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tipo_moneda = rs.getInt("id_tipo_moneda");
                String tipoMoneda = rs.getString("tipo_moneda");
                int idTasaCambio = rs.getInt("id_tasa_cambio_diario");

                tipo_moneda = new tipo_moneda();
                tipo_moneda.setId_tipo_moneda(id_tipo_moneda);
                tipo_moneda.setTipo_moneda(tipoMoneda);
                tipo_moneda.setId_tasa_cambio_diario(idTasaCambio);

                list_tipo_monedas.add(tipo_moneda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_monedas;
    }

    public int insert(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipo_moneda.getTipo_moneda());
            stmt.setInt(2, tipo_moneda.getId_tasa_cambio_diario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipo_moneda.getTipo_moneda());
            stmt.setInt(2, tipo_moneda.getId_tasa_cambio_diario());
            stmt.setInt(3, tipo_moneda.getId_tipo_moneda());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tipo_moneda.getId_tipo_moneda());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public tipo_moneda query(tipo_moneda tipo_moneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tipo_moneda.getId_tipo_moneda());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id_tipo_moneda = rs.getInt("id_tipo_moneda");
                String tipoMoneda = rs.getString("tipo_moneda");
                int idTasaCambio = rs.getInt("id_tasa_cambio_diario");

                tipo_moneda = new tipo_moneda();
                tipo_moneda.setId_tipo_moneda(id_tipo_moneda);
                tipo_moneda.setTipo_moneda(tipoMoneda);
                tipo_moneda.setId_tasa_cambio_diario(idTasaCambio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipo_moneda;
    }

    public boolean existeTipoMoneda(String tipoMoneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setString(1, tipoMoneda);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    existe = true;
                }
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

    public double obtenerValorTasaCambio(int idTasaCambio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double valorTasa = 0.0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_VALOR_TASA);
            stmt.setInt(1, idTasaCambio);
            rs = stmt.executeQuery();

            if (rs.next()) {
                valorTasa = rs.getDouble("valor_promedio_dia");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return valorTasa;
    }

    // Implementación completa del método que faltaba
    public boolean existeTipoMoneda(int idMoneda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE_ID);
            stmt.setInt(1, idMoneda);
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
