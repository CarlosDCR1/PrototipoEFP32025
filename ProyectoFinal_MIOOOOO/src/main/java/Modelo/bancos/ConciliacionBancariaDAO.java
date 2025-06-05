package Modelo.bancos;

import Controlador.bancos.conciliacion_bancaria;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//Hecho por Mishel
// modificado por Anderson Rodriguez
public class ConciliacionBancariaDAO {

    // Sentencias SQL para operaciones CRUD
    private static final String SQL_SELECT = "SELECT id_conciliacion, id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status FROM conciliacion_bancaria";
    private static final String SQL_INSERT = "INSERT INTO conciliacion_bancaria(id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE conciliacion_bancaria SET id_cuenta=?, id_movimiento_bancario=?, fecha=?, saldo=?, saldo_actualizado=?, status=? WHERE id_conciliacion = ?";
    private static final String SQL_DELETE = "DELETE FROM conciliacion_bancaria WHERE id_conciliacion=?";
    private static final String SQL_QUERY = "SELECT id_conciliacion, id_cuenta, id_movimiento_bancario, fecha, saldo, saldo_actualizado, status FROM conciliacion_bancaria WHERE id_conciliacion = ?";

    // Método para obtener todas las conciliaciones bancarias
    public List<conciliacion_bancaria> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<conciliacion_bancaria> conciliaciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection(); // Establece conexión con la base de datos
            stmt = conn.prepareStatement(SQL_SELECT); // Prepara la consulta SELECT
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Itera sobre los resultados y construye objetos conciliacion_bancaria
            while (rs.next()) {
                int id = rs.getInt("id_conciliacion");
                int idCuenta = rs.getInt("id_cuenta");
                int idMovimiento = rs.getInt("id_movimiento_bancario");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                float saldo = rs.getFloat("saldo");
                float saldoActualizado = rs.getFloat("saldo_actualizado");
                String status = rs.getString("status");

                conciliacion_bancaria conciliacion = new conciliacion_bancaria();
                conciliacion.setId_conciliacion(id);
                conciliacion.setId_cuenta(idCuenta);
                conciliacion.setId_movimiento_bancario(idMovimiento);
                conciliacion.setFecha(fecha);
                conciliacion.setSaldo(saldo);
                conciliacion.setSaldo_actualizado(saldoActualizado);
                conciliacion.setStatus(status);

                conciliaciones.add(conciliacion); // Agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            // Cierra recursos en orden inverso a su apertura
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return conciliaciones;
    }

    // Método para insertar una nueva conciliación bancaria
    public int insert(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            stmt = conn.prepareStatement(SQL_INSERT); // Prepara la sentencia INSERT

            // Asigna valores a los parámetros de la sentencia
            stmt.setInt(1, conciliacion.getId_cuenta());
            stmt.setInt(2, conciliacion.getId_movimiento_bancario());
            stmt.setTimestamp(3, Timestamp.valueOf(conciliacion.getFecha()));
            stmt.setFloat(4, conciliacion.getSaldo());
            stmt.setFloat(5, conciliacion.getSaldo_actualizado());
            stmt.setString(6, conciliacion.getStatus());

            rows = stmt.executeUpdate(); // Ejecuta la inserción
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows; // Retorna el número de filas afectadas
    }

    // Método para actualizar una conciliación existente
    public int update(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_UPDATE); // Prepara la sentencia UPDATE

            // Asigna los nuevos valores
            stmt.setInt(1, conciliacion.getId_cuenta());
            stmt.setInt(2, conciliacion.getId_movimiento_bancario());
            stmt.setTimestamp(3, Timestamp.valueOf(conciliacion.getFecha()));
            stmt.setFloat(4, conciliacion.getSaldo());
            stmt.setFloat(5, conciliacion.getSaldo_actualizado());
            stmt.setString(6, conciliacion.getStatus());
            stmt.setInt(7, conciliacion.getId_conciliacion());

            rows = stmt.executeUpdate(); // Ejecuta la actualización
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    // Método para eliminar una conciliación bancaria
    public int delete(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la base de datos
            stmt = conn.prepareStatement(SQL_DELETE); // Prepara la sentencia DELETE
            stmt.setInt(1, conciliacion.getId_conciliacion()); // Asigna el ID a eliminar
            rows = stmt.executeUpdate(); // Ejecuta la eliminación
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    // Método para consultar una conciliación específica por ID
    public conciliacion_bancaria query(conciliacion_bancaria conciliacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            stmt = conn.prepareStatement(SQL_QUERY); // Prepara la consulta
            stmt.setInt(1, conciliacion.getId_conciliacion()); // Asigna el ID a buscar
            rs = stmt.executeQuery(); // Ejecuta la consulta
            // Si se encuentra el registro, se llena el objeto con los datos
            if (rs.next()) {
                conciliacion = new conciliacion_bancaria();
                conciliacion.setId_conciliacion(rs.getInt("id_conciliacion"));
                conciliacion.setId_cuenta(rs.getInt("id_cuenta"));
                conciliacion.setId_movimiento_bancario(rs.getInt("id_movimiento_bancario"));
                conciliacion.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                conciliacion.setSaldo(rs.getFloat("saldo"));
                conciliacion.setSaldo_actualizado(rs.getFloat("saldo_actualizado"));
                conciliacion.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return conciliacion; // Retorna el objeto encontrado o el original si no se encontró
    }
}

