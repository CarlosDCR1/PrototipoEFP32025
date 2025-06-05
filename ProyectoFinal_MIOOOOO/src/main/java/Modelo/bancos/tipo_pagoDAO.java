package Modelo.bancos;

// Importaciones necesarias para conexión y manipulación de datos
import Modelo.seguridad.*;
import Controlador.bancos.tipo_pago;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para la entidad tipo_pago.
 * Permite realizar operaciones CRUD sobre la tabla tipo_pago en la base de datos.
 */
public class tipo_pagoDAO {

    // Consultas SQL utilizadas por los métodos del DAO
    private static final String SQL_SELECT = "SELECT id_tipo_pago, tipo_pago, status FROM tipo_pago";
    private static final String SQL_INSERT = "INSERT INTO tipo_pago(tipo_pago, status) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE tipo_pago SET  tipo_pago=?, status=? WHERE id_tipo_pago = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_pago WHERE id_tipo_pago=?";
    private static final String SQL_QUERY = "SELECT id_tipo_pago, tipo_pago, status FROM tipo_pago WHERE id_tipo_pago = ?";

    // Método para obtener todos los registros de tipo_pago
    public List<tipo_pago> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_pago tipo_pago = null;
        List<tipo_pago> list_tipo_pagos = new ArrayList<>();

        try {
            conn = Conexion.getConnection(); // Establece conexión con la base de datos
            stmt = conn.prepareStatement(SQL_SELECT); // Prepara la consulta SELECT
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Recorre los resultados y los agrega a la lista
            while (rs.next()) {
                int idTipoPago = rs.getInt("id_tipo_pago");
                String tipoPago = rs.getString("tipo_pago");
                String Status = rs.getString("status");

                tipo_pago = new tipo_pago();
                tipo_pago.setIdTipoPago(idTipoPago);
                tipo_pago.setTipoPago(tipoPago);
                tipo_pago.setStatus(Status);

                list_tipo_pagos.add(tipo_pago);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            // Cierre de recursos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_pagos; // Retorna la lista de resultados
    }

    // Método para insertar un nuevo registro en la tabla tipo_pago
    public int insert(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            stmt = conn.prepareStatement(SQL_INSERT); // Prepara la consulta INSERT
            stmt.setString(1, tipo_pago.getTipoPago());
            stmt.setString(2, tipo_pago.getStatus());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate(); // Ejecuta la inserción
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas insertadas
    }

    // Método para actualizar un registro existente
    public int update(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE); // Prepara la consulta UPDATE
            stmt.setString(1, tipo_pago.getTipoPago());
            stmt.setString(2, tipo_pago.getStatus());
            stmt.setInt(3, tipo_pago.getIdTipoPago());

            rows = stmt.executeUpdate(); // Ejecuta la actualización
            System.out.println("Registros actualizado:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas actualizadas
    }

    // Método para eliminar un registro por su ID
    public int delete(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE); // Prepara la consulta DELETE
            stmt.setInt(1, tipo_pago.getIdTipoPago());

            rows = stmt.executeUpdate(); // Ejecuta la eliminación
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas eliminadas
    }

    // Método para consultar un registro específico por su ID
    public tipo_pago query(tipo_pago tipo_pago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tipo_pago> list_tipo_pago = new ArrayList<>();
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Establece conexión
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY); // Prepara la consulta SELECT con WHERE
            stmt.setInt(1, tipo_pago.getIdTipoPago());
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Si se encuentra el registro, se llena el objeto
            while (rs.next()) {
                int idTipoPago = rs.getInt("id_tipo_pago");
                String tipoPago = rs.getString("tipo_pago");
                String Status = rs.getString("status");

                tipo_pago = new tipo_pago();
                tipo_pago.setIdTipoPago(idTipoPago);
                tipo_pago.setTipoPago(tipoPago);
                tipo_pago.setStatus(Status);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipo_pago; // Retorna el objeto encontrado
    }
}
