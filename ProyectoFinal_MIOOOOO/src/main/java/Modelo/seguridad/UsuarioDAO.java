package Modelo.seguridad;

import Modelo.*;
import Controlador.seguridad.Usuario;
import Controlador.seguridad.permisos;

import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class UsuarioDAO {

    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username=?, password=? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";
    private static final String SQL_QUERY = "SELECT id_usuario, username, password FROM usuario WHERE username = ?";
    private static final String SQL_QUERY2 = "SELECT id_usuario, username, password FROM usuario WHERE id_usuario = ?";

    public List<Usuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

    public int insert(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_usuario());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Usuario query(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, usuario.getUsername());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }

    public Usuario query2(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY2);
            stmt = conn.prepareStatement(SQL_QUERY2);
            stmt.setInt(1, usuario.getId_usuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }

 public permisos obtenerPermisosPorUsuario(int idUsuario) {
    permisos permisos = new permisos();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = Conexion.getConnection();
        String sql = "SELECT puede_mantenimiento, puede_procesos, puede_eliminar, puede_registrar, puede_modificar " +
                     "FROM permisos_usuario WHERE id_usuario = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();

        if (rs.next()) {
            permisos.setPuedeMantenimiento(rs.getBoolean("puede_mantenimiento"));
            permisos.setPuedeProcesos(rs.getBoolean("puede_procesos"));
            permisos.setPuedeEliminar(rs.getBoolean("puede_eliminar"));
            permisos.setPuedeRegistrar(rs.getBoolean("puede_registrar"));
            permisos.setPuedeModificar(rs.getBoolean("puede_modificar"));
            permisos.setIdUsuario(idUsuario);
        }

    } catch (SQLException e) {
        e.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(conn);
    }
    return permisos;
}


public boolean eliminarPermisos(int idUsuario) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = Conexion.getConnection();
        String sql = "DELETE FROM permisos_usuario WHERE id_usuario = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        int rows = ps.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace(System.out);
        return false;
    } finally {
        Conexion.close(ps);
        Conexion.close(conn);
    }
}

public boolean actualizarPermisos(int idUsuario, boolean puedeMantenimiento, boolean puedeProcesos,
                                  boolean puedeEliminar, boolean puedeRegistrar, boolean puedeModificar) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = Conexion.getConnection();
        String sql = "UPDATE permisos_usuario SET puede_mantenimiento = ?, puede_procesos = ?, " +
                     "puede_eliminar = ?, puede_registrar = ?, puede_modificar = ? WHERE id_usuario = ?";
        ps = conn.prepareStatement(sql);
        ps.setBoolean(1, puedeMantenimiento);
        ps.setBoolean(2, puedeProcesos);
        ps.setBoolean(3, puedeEliminar);
        ps.setBoolean(4, puedeRegistrar);
        ps.setBoolean(5, puedeModificar);
        ps.setInt(6, idUsuario);

        int rows = ps.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace(System.out);
        return false;
    } finally {
        Conexion.close(ps);
        Conexion.close(conn);
    }
}

public boolean insertarpermisos(int idUsuario, boolean puedeMantenimiento, boolean puedeProcesos,
                               boolean puedeEliminar, boolean puedeRegistrar, boolean puedeModificar) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = Conexion.getConnection();
        String sql = "INSERT INTO permisos_usuario (id_usuario, puede_mantenimiento, puede_procesos, " +
                     "puede_eliminar, puede_registrar, puede_modificar) VALUES (?, ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setBoolean(2, puedeMantenimiento);
        ps.setBoolean(3, puedeProcesos);
        ps.setBoolean(4, puedeEliminar);
        ps.setBoolean(5, puedeRegistrar);
        ps.setBoolean(6, puedeModificar);

        int rows = ps.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace(System.out);
        return false;
    } finally {
        Conexion.close(ps);
        Conexion.close(conn);
    }
}

public boolean existeUsuario(int idUsuario) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT 1 FROM usuario WHERE id_usuario = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace(System.out);
        return false;
    } finally {
        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(conn);
    }
}

public List<Integer> obtenerTodosIds() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Integer> ids = new ArrayList<>();
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT id_usuario FROM usuario";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("id_usuario"));
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(conn);
    }
    return ids;
}

public String obtenerUsernamePorId(Integer idUsuario) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String username = null;
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT username FROM usuario WHERE id_usuario = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(conn);
    }
    return username;
}

public List<permisos> obtenerPermisos() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<permisos> listaPermisos = new ArrayList<>();
    try {
        conn = Conexion.getConnection();
        String sql = "SELECT id_usuario, puede_mantenimiento, puede_procesos, puede_eliminar, puede_registrar, puede_modificar FROM permisos_usuario";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            permisos p = new permisos();
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setPuedeMantenimiento(rs.getBoolean("puede_mantenimiento"));
            p.setPuedeProcesos(rs.getBoolean("puede_procesos"));
            p.setPuedeEliminar(rs.getBoolean("puede_eliminar"));
            p.setPuedeRegistrar(rs.getBoolean("puede_registrar"));
            p.setPuedeModificar(rs.getBoolean("puede_modificar"));
            listaPermisos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(conn);
    }
    return listaPermisos;
 }
public List<Usuario> obtenerTodosLosUsuarios() {
    List<Usuario> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexion.getConnection();
        // Consulta SQL: tomar id_usuario y username tal cual
        stmt = conn.prepareStatement("SELECT id_usuario, username FROM usuario");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId_usuario(rs.getInt("id_usuario"));  // Asignar id_usuario correctamente
            u.setUsername(rs.getString("username"));   // Asignar username correctamente
            lista.add(u);
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return lista;
}

}
