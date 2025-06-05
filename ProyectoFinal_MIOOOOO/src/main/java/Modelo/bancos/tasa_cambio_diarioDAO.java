package Modelo.bancos;
// Cambio por Ruddyard Eduardo Castro Chavez 9959-23-1409
import Controlador.bancos.tasa_cambio_diario;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class tasa_cambio_diarioDAO {
/*
    
 * Declaración de constantes SQL para operaciones CRUD en la tabla 'tasas_cambio_diario':
 * SQL_SELECT: Consulta para obtener todos los registros (id, valor promedio y fecha) de la tabla
 * SQL_INSERT: Consulta para insertar nuevos registros con valores de tipo promedio y fecha/hora
 * SQL_UPDATE: Consulta para modificar registros existentes, actualizando valor y fecha por su ID
 * SQL_DELETE: Consulta para eliminar registros específicos de la tabla usando su ID como filtro
 * SQL_QUERY: Consulta para buscar un registro particular filtrando por su ID específico
 */

    private static final String SQL_SELECT = "SELECT id_tasa_cambio_diario, valor_promedio_dia, fecha_hora FROM tasas_cambio_diario";
    private static final String SQL_INSERT = "INSERT INTO tasas_cambio_diario(valor_promedio_dia, fecha_hora) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE tasas_cambio_diario SET valor_promedio_dia=?, fecha_hora=? WHERE id_tasa_cambio_diario = ?";
    private static final String SQL_DELETE = "DELETE FROM tasas_cambio_diario WHERE id_tasa_cambio_diario=?";
    private static final String SQL_QUERY = "SELECT id_tasa_cambio_diario, valor_promedio_dia, fecha_hora FROM tasas_cambio_diario WHERE id_tasa_cambio_diario = ?";
    //private static final String SQL_EXISTE = "SELECT COUNT(*) FROM tasa_cambio_diario WHERE fecha_hora = ?";

 /*
 * Método para consultar y obtener todos los registros de tasas de cambio diario de la base de datos.
 * Realiza las siguientes operaciones:
 * 1. Establece conexión con la BD usando la clase Conexion
 * 2. Prepara y ejecuta la consulta SQL_SELECT definida previamente
 * 3. Procesa el ResultSet obtenido, creando objetos tasa_cambio_diario con los datos de cada registro
 * 4. Almacena todos los objetos en una lista para retornarlos
 * 5. Maneja posibles excepciones SQL imprimiendo el error
 * 6. En el bloque finally asegura que se cierren todos los recursos (ResultSet, Statement y Connection)
 * Retorna una lista de objetos tasa_cambio_diario con todos los registros encontrados
 */
    
       
    
    public List<tasa_cambio_diario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<tasa_cambio_diario> tasas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_tasa_cambio_diario");
                float valor = rs.getFloat("valor_promedio_dia");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                tasa_cambio_diario tasa = new tasa_cambio_diario();
                tasa.setId_tasa_cambio_diario(id);
                tasa.setValor_promedio_dia(valor);
                tasa.setFecha_hora(fechaHora);

                tasas.add(tasa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tasas;
    }
/*
 * Método para insertar un nuevo registro de tasa de cambio diario en la base de datos.
 * Recibe como parámetro un objeto tasa_cambio_diario con los datos a insertar.
 * Establece conexión con la BD, prepara la sentencia SQL_INSERT con parámetros (valor promedio y fecha/hora).
 * Ejecuta la inserción y retorna el número de filas afectadas.
 * Maneja posibles excepciones SQL imprimiendo el error en consola.
 * En el bloque finally asegura el cierre adecuado de los recursos (Statement y Connection).
 * Retorna 1 si la inserción fue exitosa, 0 en caso contrario.
 */
    public int insert(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setFloat(1, tasa.getValor_promedio_dia());
            stmt.setTimestamp(2, Timestamp.valueOf(tasa.getFecha_hora()));
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
 * Método para actualizar un registro existente de tasa de cambio diario en la base de datos.
 * Recibe como parámetro un objeto tasa_cambio_diario con los datos actualizados.
 * Establece conexión con la BD y prepara la sentencia SQL_UPDATE definida previamente.
 * Asigna los parámetros al PreparedStatement (valor promedio, fecha/hora y ID del registro a modificar).
 * Ejecuta la actualización y retorna el número de filas afectadas.
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally garantiza la liberación de recursos (Statement y Connection).
 * Retorna 1 si la actualización fue exitosa (se modificó el registro), 0 si no se encontró el registro especificado.
 */
    public int update(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setFloat(1, tasa.getValor_promedio_dia());
            stmt.setTimestamp(2, Timestamp.valueOf(tasa.getFecha_hora()));
            stmt.setInt(3, tasa.getId_tasa_cambio_diario());
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
 * Método para eliminar un registro existente de tasa de cambio diario en la base de datos.
 * Recibe como parámetro un objeto tasa_cambio_diario con los datos actualizados.
 * Establece conexión con la BD y prepara la sentencia SQL_UPDATE definida previamente.
 * Asigna los parámetros al PreparedStatement (valor promedio, fecha/hora y ID del registro a modificar).
 * Ejecuta la eliminacion y retorna el número de filas afectadas.
 * Maneja posibles excepciones SQL mostrando el error en consola.
 * En el bloque finally garantiza la liberación de recursos (Statement y Connection).
 * Retorna 1 si la actualización fue exitosa (se elimino el registro), 0 si no se encontró el registro especificado.
 */
    public int delete(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tasa.getId_tasa_cambio_diario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
/* * Método para consultar y obtener  los registros de tasas de cambio diario de la base de datos.
 * Realiza las siguientes operaciones:
 * 1. Establece conexión con la BD usando la clase Conexion
 * 2. Prepara y ejecuta la consulta SQL_SELECT definida previamente
 * 3. Procesa el ResultSet obtenido, creando objetos tasa_cambio_diario con los datos de cada registro
 * 4. Almacena todos los objetos en una lista para retornarlos
 * 5. Maneja posibles excepciones SQL imprimiendo el error
 * 6. En el bloque finally asegura que se cierren todos los recursos (ResultSet, Statement y Connection)
 * Retorna una lista de objetos tasa_cambio_diario con todos los registros encontrados
 */
    
    public tasa_cambio_diario query(tasa_cambio_diario tasa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, tasa.getId_tasa_cambio_diario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_tasa_cambio_diario");
                float valor = rs.getFloat("valor_promedio_dia");
                LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                tasa = new tasa_cambio_diario();
                tasa.setId_tasa_cambio_diario(id);
                tasa.setValor_promedio_dia(valor);
                tasa.setFecha_hora(fechaHora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tasa;
    }
/*
    public boolean existeFechaHora(LocalDateTime fechaHora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_EXISTE);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaHora));
            rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }*/
}