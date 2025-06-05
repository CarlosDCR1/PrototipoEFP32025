package Modelo.bancos;

import Controlador.bancos.movimiento_bancario;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// Made By Ruddyard Castro 
public class MovimientoBancarioDAO {
    // Actualizadas para incluir los nuevos campos
 /*
 * Declaración de constantes SQL para operaciones CRUD en la tabla 'movimientos_bancarios':
 * SQL_SELECT: Consulta para obtener todos los registros de movimientos (ID, cuenta asociada, fecha, tipo de saldo, monto y saldo actualizado)
 * SQL_INSERT: Consulta para crear nuevos movimientos bancarios con todos sus campos requeridos (excepto ID que es autoincremental)
 * SQL_UPDATE: Consulta para modificar todos los campos de un movimiento bancario existente, identificado por su ID único
 * SQL_DELETE: Consulta para eliminar permanentemente un movimiento bancario específico usando su ID como filtro
 * SQL_QUERY: Consulta para recuperar un movimiento bancario particular filtrando por su ID único
 * Todas las consultas usan parámetros preparados (?) para prevenir inyección SQL y mejorar seguridad
 */
    private static final String SQL_SELECT = "SELECT id_movimiento_bancario, id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado FROM movimientos_bancarios";
    private static final String SQL_INSERT = "INSERT INTO movimientos_bancarios(id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE movimientos_bancarios SET id_cuenta=?, fecha=?, tipo_saldo=?, monto=?, saldo_actualizado=? WHERE id_movimiento_bancario = ?";
    private static final String SQL_DELETE = "DELETE FROM movimientos_bancarios WHERE id_movimiento_bancario=?";
    private static final String SQL_QUERY = "SELECT id_movimiento_bancario, id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado FROM movimientos_bancarios WHERE id_movimiento_bancario = ?";

    
    /*
 * Método para obtener todos los movimientos bancarios registrados en la base de datos.
 * Establece conexión con la BD y ejecuta la consulta SQL_SELECT definida previamente.
 * Recorre el ResultSet obtenido, creando objetos movimiento_bancario con los datos de cada registro:
 *   - ID del movimiento
 *   - ID de la cuenta asociada
 *   - Fecha y hora del movimiento
 *   - Tipo de saldo (débito/crédito)
 *   - Monto de la transacción
 *   - Saldo actualizado después del movimiento
 * Almacena todos los objetos en una lista y la retorna.
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally asegura el cierre adecuado de todos los recursos:
 *   - ResultSet
 *   - PreparedStatement
 *   - Connection
 * Retorna una lista de movimientos bancarios, vacía si no hay registros.
 */
     public List<movimiento_bancario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<movimiento_bancario> movimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idCuenta = rs.getInt("id_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                String tipoSaldo = rs.getString("tipo_saldo");
                float monto = rs.getFloat("monto");
                float saldoActualizado = rs.getFloat("saldo_actualizado");

                movimiento_bancario movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_cuenta(idCuenta);
                movimiento.setFecha(fecha);
                movimiento.setTipoSaldo(tipoSaldo);
                movimiento.setMonto(monto);
                movimiento.setSaldoActualizado(saldoActualizado);

                movimientos.add(movimiento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimientos;
    }

    public int insert(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, movimiento.getId_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setString(3, movimiento.getTipoSaldo());
            stmt.setFloat(4, movimiento.getMonto());
            stmt.setFloat(5, movimiento.getSaldoActualizado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    /*
 * Método para insertar un nuevo movimiento bancario en la base de datos.
 * Recibe como parámetro un objeto movimiento_bancario con todos los datos necesarios:
 *   - ID de la cuenta asociada
 *   - Fecha y hora del movimiento
 *   - Tipo de operación (débito/crédito)
 *   - Monto de la transacción
 *   - Saldo resultante
 * Establece conexión con la BD y prepara la sentencia SQL_INSERT con parámetros.
 * Asigna cada valor del objeto movimiento a los parámetros correspondientes en la consulta.
 * Ejecuta la inserción y retorna el número de filas afectadas (1 si fue exitoso, 0 si falló).
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally libera los recursos utilizados (Statement y Connection).
 * Retorna el resultado de la operación para verificar si se completó correctamente.
 */
    public int update(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, movimiento.getId_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setString(3, movimiento.getTipoSaldo());
            stmt.setFloat(4, movimiento.getMonto());
            stmt.setFloat(5, movimiento.getSaldoActualizado());
            stmt.setInt(6, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    /*
 * Método para eliminar un movimiento bancario específico de la base de datos.
 * Recibe como parámetro un objeto movimiento_bancario que contiene el ID del movimiento a eliminar.
 * Establece conexión con la BD y prepara la sentencia SQL_DELETE.
 * Asigna el ID del movimiento al parámetro de la consulta.
 * Ejecuta la eliminación y retorna el número de filas afectadas (1 si fue exitoso, 0 si no encontró el registro).
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally libera los recursos utilizados (Statement y Connection).
 * Retorna el resultado de la operación para verificar si se completó correctamente.
 */
    public int delete(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
/*
 * Método para consultar un movimiento bancario específico por su ID.
 * Recibe como parámetro un objeto movimiento_bancario con el ID del movimiento a buscar.
 * Establece conexión con la BD y prepara la sentencia SQL_QUERY.
 * Asigna el ID del movimiento al parámetro de la consulta y ejecuta la búsqueda.
 * Si encuentra resultados, crea un nuevo objeto movimiento_bancario con todos los datos:
 *   - ID del movimiento
 *   - ID de la cuenta asociada
 *   - Fecha y hora
 *   - Tipo de operación
 *   - Monto
 *   - Saldo actualizado
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally libera todos los recursos utilizados (ResultSet, Statement y Connection).
 * Retorna el objeto movimiento con los datos encontrados o null si no existe el registro.
 */
    public movimiento_bancario query(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idCuenta = rs.getInt("id_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                String tipoSaldo = rs.getString("tipo_saldo");
                float monto = rs.getFloat("monto");
                float saldoActualizado = rs.getFloat("saldo_actualizado");

                movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_cuenta(idCuenta);
                movimiento.setFecha(fecha);
                movimiento.setTipoSaldo(tipoSaldo);
                movimiento.setMonto(monto);
                movimiento.setSaldoActualizado(saldoActualizado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimiento;
    }

}