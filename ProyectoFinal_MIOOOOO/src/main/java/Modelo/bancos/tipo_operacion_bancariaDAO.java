package Modelo.bancos;

// Importaciones necesarias para conexión y manipulación de datos
import Modelo.seguridad.*;
import Controlador.bancos.tipo_operacion_bancaria;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad tipo_operacion_bancaria.
 * Permite realizar operaciones CRUD sobre la tabla tipo_operacion_bancaria.
 */
public class tipo_operacion_bancariaDAO {

    // Consultas SQL utilizadas por los métodos del DAO
    private static final String SQL_SELECT = "SELECT id_tipo_operacion, tipo_operacion, descripcion FROM tipo_operacion_bancaria";
    private static final String SQL_INSERT = "INSERT INTO tipo_operacion_bancaria(tipo_operacion, descripcion) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE tipo_operacion_bancaria SET  tipo_operacion=?, descripcion=? WHERE id_tipo_operacion = ?";
    private static final String SQL_DELETE = "DELETE FROM tipo_operacion_bancaria WHERE id_tipo_operacion=?";
    private static final String SQL_QUERY = "SELECT id_tipo_operacion, tipo_operacion, descripcion FROM tipo_operacion_bancaria WHERE id_tipo_operacion = ?";

    // Método para obtener todos los registros de tipo_operacion_bancaria
    public List<tipo_operacion_bancaria> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tipo_operacion_bancaria tipo_operacion_bancaria = null;
        List<tipo_operacion_bancaria> list_tipo_operaciones_bancarias = new ArrayList<>();

        try {
            conn = Conexion.getConnection(); // Establece conexión
            stmt = conn.prepareStatement(SQL_SELECT); // Prepara la consulta
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Recorre los resultados y los agrega a la lista
            while (rs.next()) {
                int id_tipo_operacion = rs.getInt("id_tipo_operacion");
                String tipo_operacion = rs.getString("tipo_operacion");
                String descripcion = rs.getString("descripcion");

                tipo_operacion_bancaria = new tipo_operacion_bancaria();
                tipo_operacion_bancaria.setId_tipo_operacion(id_tipo_operacion);
                tipo_operacion_bancaria.setTipo_operacion(tipo_operacion);
                tipo_operacion_bancaria.setDescripcion(descripcion);

                list_tipo_operaciones_bancarias.add(tipo_operacion_bancaria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            // Cierre de recursos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return list_tipo_operaciones_bancarias; // Retorna la lista de resultados
    }

    // Método para insertar un nuevo registro
    public int insert(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la BD
            stmt = conn.prepareStatement(SQL_INSERT); // Prepara la consulta
            stmt.setString(1, tipo_operacion_bancaria.getTipo_operacion());
            stmt.setString(2, tipo_operacion_bancaria.getDescripcion());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate(); // Ejecuta la inserción
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows; // Retorna el número de filas afectadas
    }

    // Método para actualizar un registro existente
    public int update(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la BD
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE); // Prepara la consulta
            stmt.setString(1, tipo_operacion_bancaria.getTipo_operacion());
            stmt.setString(2, tipo_operacion_bancaria.getDescripcion());
            stmt.setInt(3, tipo_operacion_bancaria.getId_tipo_operacion());

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

    // Método para eliminar un registro por ID
    public int delete(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la BD
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE); // Prepara la consulta
            stmt.setInt(1, tipo_operacion_bancaria.getId_tipo_operacion());
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

    // Método para consultar un registro específico por ID
    public tipo_operacion_bancaria query(tipo_operacion_bancaria tipo_operacion_bancaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tipo_operacion_bancaria> list_tipo_operacion_bancaria = new ArrayList<>();
        int rows = 0;

        try {
            conn = Conexion.getConnection(); // Conexión a la BD
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY); // Prepara la consulta
            stmt.setInt(1, tipo_operacion_bancaria.getId_tipo_operacion());
            rs = stmt.executeQuery(); // Ejecuta la consulta

            // Si se encuentra el registro, se llena el objeto
            while (rs.next()) {
                int id_tipo_operacion = rs.getInt("id_tipo_operacion");
                String tipo_operacion = rs.getString("tipo_operacion");
                String descripcion = rs.getString("descripcion");

                tipo_operacion_bancaria = new tipo_operacion_bancaria();
                tipo_operacion_bancaria.setId_tipo_operacion(id_tipo_operacion);
                tipo_operacion_bancaria.setTipo_operacion(tipo_operacion);
                tipo_operacion_bancaria.setDescripcion(descripcion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // Manejo de errores
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return tipo_operacion_bancaria; // Retorna el objeto encontrado
    }
}
