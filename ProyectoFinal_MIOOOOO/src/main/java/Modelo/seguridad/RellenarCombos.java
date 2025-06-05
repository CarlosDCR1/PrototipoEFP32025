/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author visitante
 */

       public class RellenarCombos {

           public void RellenarComboBox(String tabla, String valor, JComboBox<String> combo) throws SQLException {
               // Limpiar el combo y agregar opción inicial
               combo.removeAllItems();
               combo.addItem("Seleccione una opción");

               String sql = "SELECT " + valor + " FROM " + tabla;  // Selecciona solo la columna necesaria

               // Usar try-with-resources para cierre automático
               try (Connection conexion = Conexion.getConnection(); Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {

                   while (rs.next()) {
                       combo.addItem(rs.getString(valor)); // Llenar con datos de la BD
                   }
               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           
           
           public int obtenerIdPorNombre(String tabla, String columnaNombre, String columnaId, String nombre) throws SQLException {
               String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + columnaNombre + " = ?";

               try (Connection conexion = Conexion.getConnection(); PreparedStatement pst = conexion.prepareStatement(sql)) {

                   pst.setString(1, nombre);
                   ResultSet rs = pst.executeQuery();

                   if (rs.next()) {
                       return rs.getInt(columnaId);
                   } else {
                       throw new SQLException("Registro no encontrado para: " + nombre);
                   }
               }
            }
           public boolean eliminarAsignacion(int idUsuario, int idPerfil) throws SQLException {
               String sql = "DELETE FROM usuario_perfil WHERE id_usuario = ? AND id_perfil = ?";

               try (Connection conexion = Conexion.getConnection(); PreparedStatement pst = conexion.prepareStatement(sql)) {

                   pst.setInt(1, idUsuario);
                   pst.setInt(2, idPerfil);
                   int filasAfectadas = pst.executeUpdate();

                   return filasAfectadas > 0; // True si se eliminó, False si no existía
               }
           }
           public void cargarPerfilesAsignados(int idUsuario, JComboBox<String> comboPerfilesAsignados) throws SQLException {
               String sql = "SELECT p.nombre_perfil "
                       + "FROM usuario_perfil up "
                       + "JOIN perfiles p ON up.id_perfil = p.id_perfil "
                       + "WHERE up.id_usuario = ?";

               comboPerfilesAsignados.removeAllItems(); // Limpiar ComboBox
               comboPerfilesAsignados.addItem("Seleccione un perfil asignado");

               try (Connection conexion = Conexion.getConnection(); PreparedStatement pst = conexion.prepareStatement(sql)) {

                   pst.setInt(1, idUsuario);
                   ResultSet rs = pst.executeQuery();

                   while (rs.next()) {
                       comboPerfilesAsignados.addItem(rs.getString("nombre_perfil"));
                   }
               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
}
       }

