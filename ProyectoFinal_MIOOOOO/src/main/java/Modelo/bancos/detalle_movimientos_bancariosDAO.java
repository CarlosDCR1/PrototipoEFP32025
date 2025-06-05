package Modelo.bancos;

// Importación de clases necesarias para la funcionalidad del DAO
import Modelo.seguridad.*;
import Controlador.bancos.detalle_movimientos_bancarios;
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
 * DAO (Data Access Object) para la entidad detalle_movimientos_bancarios.
 * Proporciona métodos para realizar operaciones CRUD sobre la base de datos.
 */
public class detalle_movimientos_bancariosDAO {

    // Consultas SQL utilizadas por el DAO
    private static final String SQL_SELECT = "SELECT id_detalle, id_movimiento_bancario, id_tipo_operacion, id_tipo_pago, monto, descripcion FROM detalle_movimientos_bancarios";
    private static final String SQL_INSERT = "INSERT INTO detalle_movimientos_bancarios(id_movimiento_bancario, id_tipo_operacion, id_tipo_pago, monto, descripcion) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE detalle_movimientos_bancarios SET id_movimiento_bancario=?, id_tipo_operacion=?, id_tipo_pago=?, monto=?, descripcion=? WHERE id_detalle=?";
    private static final String SQL_DELETE = "DELETE FROM detalle_movimientos_bancarios WHERE id_detalle=?";
    private static final String SQL_QUERY  = "SELECT id_detalle, id_movimiento_bancario, id_tipo_operacion, id_tipo_pago, monto, descripcion FROM detalle_movimientos_bancarios WHERE id_detalle=?";

    // Método para obtener todos los registros de detalle_movimientos_bancarios
    public List<detalle_movimientos_bancarios> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        detalle_movimientos_bancarios detalle = null;
        List<detalle_movimientos_bancarios> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection(); // Establece conexión con la base de datos
            stmt = conn.prepareStatement(SQL_SELECT); // Prepara la consulta SELECT
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Itera sobre los resultados y los agrega a la lista
            while (rs.next()) {
                detalle = new detalle_movimientos_bancarios();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdMovimiento(rs.getInt("id_movimiento_bancario"));
                detalle.setIdTipoOperacion(rs.getInt("id_tipo_operacion"));
                detalle.setIdTipoPago(rs.getInt("id_tipo_pago"));
                detalle.setMonto(rs.getFloat("monto"));
                detalle.setDescripcion(rs.getString("descripcion"));
                detalles.add(detalle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            // Cierra recursos en orden inverso de apertura
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return detalles; // Retorna la lista de detalles
    }

    // Método para insertar un nuevo registro en la tabla
    public int insert(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_INSERT); // Prepara la consulta INSERT
            // Asigna valores a los parámetros de la consulta
            stmt.setInt(1, detalle.getIdMovimiento());
            stmt.setInt(2, detalle.getIdTipoOperacion());
            stmt.setInt(3, detalle.getIdTipoPago());
            stmt.setFloat(4, detalle.getMonto());
            stmt.setString(5, detalle.getDescripcion());

            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate(); // Ejecuta la inserción
            System.out.println("Registros insertados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas insertadas
    }

    // Método para actualizar un registro existente
    public int update(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_UPDATE); // Prepara la consulta UPDATE
            // Asigna valores a los parámetros de la consulta
            stmt.setInt(1, detalle.getIdMovimiento());
            stmt.setInt(2, detalle.getIdTipoOperacion());
            stmt.setInt(3, detalle.getIdTipoPago());
            stmt.setFloat(4, detalle.getMonto());
            stmt.setString(5, detalle.getDescripcion());
            stmt.setInt(6, detalle.getIdDetalle());

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = stmt.executeUpdate(); // Ejecuta la actualización
            System.out.println("Registros actualizados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas actualizadas
    }

    // Método para eliminar un registro
    public int delete(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_DELETE); // Prepara la consulta DELETE
            stmt.setInt(1, detalle.getIdDetalle()); // Asigna el ID del detalle a eliminar

            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = stmt.executeUpdate(); // Ejecuta la eliminación
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas eliminadas
    }

    // Método para consultar un registro específico por ID
    public detalle_movimientos_bancarios query(detalle_movimientos_bancarios detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_QUERY); // Prepara la consulta SELECT con WHERE
            stmt.setInt(1, detalle.getIdDetalle()); // Asigna el ID del detalle a buscar
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Si se encuentra el registro, se llena el objeto detalle
            while (rs.next()) {
                detalle = new detalle_movimientos_bancarios();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdMovimiento(rs.getInt("id_movimiento_bancario"));
                detalle.setIdTipoOperacion(rs.getInt("id_tipo_operacion"));
                detalle.setIdTipoPago(rs.getInt("id_tipo_pago"));
                detalle.setMonto(rs.getFloat("monto"));
                detalle.setDescripcion(rs.getString("descripcion"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return detalle; // Retorna el objeto detalle encontrado
    }

    // Método para generar e imprimir un reporte de los detalles de movimientos bancarios
    public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap(); // Mapa de parámetros para el reporte (vacío en este caso)
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection(); // Establece conexión con la base de datos

            // Compila el archivo .jrxml del reporte ubicado en la ruta especificada
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                + "/src/main/java/reporte/banco/" + "ReporteDetalleMovimientosBancarios.jrxml");

            // Llena el reporte con los datos obtenidos desde la base de datos
            print = JasperFillManager.fillReport(report, p, conn);

            // Muestra el reporte en una ventana
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Detalle de movimientos bancarios");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores generales
        }
    }

    // Método para verificar si existe un movimiento bancario con el ID especificado
    public boolean existeMovimientoBancario(int id) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM movimientos_bancarios WHERE id_movimiento_bancario = ?")) {
            stmt.setInt(1, id); // Asigna el ID al parámetro de la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            return rs.next(); // Retorna true si existe al menos un resultado
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
            return false;
        }
    }

    // Método para verificar si existe un tipo de operación con el ID especificado
    public boolean existeTipoOperacion(int id) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM tipo_operacion_bancaria WHERE id_tipo_operacion = ?")) {
            stmt.setInt(1, id); // Asigna el ID al parámetro de la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            return rs.next(); // Retorna true si existe al menos un resultado
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
            return false;
        }
    }

    // Método para verificar si existe un tipo de pago con el ID especificado
    public boolean existeTipoPago(int id) {
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM tipo_pago WHERE id_tipo_pago = ?")) {
            stmt.setInt(1, id); // Asigna el ID al parámetro de la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            return rs.next(); // Retorna true si existe al menos un resultado
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
            return false;
        }
    }
}
