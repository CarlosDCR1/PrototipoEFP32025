package vista.seguridad;

import Controlador.seguridad.RelPerfUsu;
import Modelo.seguridad.RelPerfUsuDAO;
import Modelo.seguridad.UsuarioDAO;
import Controlador.seguridad.Usuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import org.jfree.base.log.LogConfiguration;
import java.sql.*;
import Modelo.seguridad.PerfilDAO;
import Controlador.seguridad.Perfil;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modelo.Conexion;

import Modelo.seguridad.RellenarCombos;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JList;
//REVISÒN GENERAL DE ASIGNACIÒN PERFIL A USUARIO- COMPLETADO- MISHEL LOEIZA-
//BITACORA ASIGNADA
public class MantenimientoRelPerfUsu extends javax.swing.JInternalFrame {
    int APLICACION = 101;

    public void llenadoDeCombos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> salon = usuarioDAO.select();
        cbousuario.addItem("Seleccione una opción");
        for (int i = 0; i < salon.size(); i++) {
            cbousuario.addItem(String.valueOf(salon.get(i).getId_usuario()));
            cbousuario.addActionListener(e -> {
            // mandamos a traer el ID
            String idSelec = cbousuario.getSelectedItem().toString();
            int idSeleccionado = Integer.parseInt(idSelec);
            // Busca el perfil en la lista
            for (Usuario usuario : salon) {
                if (usuario.getId_usuario() == idSeleccionado) {
              txtusu.setText(usuario.getUsername());
            
             break;
             }
        }});
        }
            
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> perfiles = perfilDAO.select(); 
        DefaultListModel<String> modelo = new DefaultListModel<>();
        DefaultListModel<String> modelo2 = new DefaultListModel<>();
        for (Perfil app : perfiles) {
            modelo.addElement(app.getNombre_perfil()); 
        }
        lstPerfD.setModel(modelo);
        lstPerfA.setModel(modelo2);
        // Listener para detectar la selección del usuario
lstPerfA.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Evita doble evento
            String nombrePerfSeleccionado = lstPerfA.getSelectedValue();
            
            if (nombrePerfSeleccionado != null) {
                // Buscar el ID del perfil seleccionada
                for (Perfil app : perfiles) {
                    if (app.getNombre_perfil().equals(nombrePerfSeleccionado)) {
                        int idPerfSeleccionado = app.getId_perfil();
                        System.out.println("ID seleccionado: " + idPerfSeleccionado); // Opcional: para debug
                        txtidPerf.setText(String.valueOf(idPerfSeleccionado)); // Asignar el ID a un campo
                        break;
                    }
                }
            }
        }
    }
});
    }
    
public void llenadousuariosperfiles(){
// 1. Obtener todos los perfiles disponibles

PerfilDAO perfilDAO = new PerfilDAO();
List<Perfil> perfiles = perfilDAO.select();

// 2. Modelos para las listas
DefaultListModel<String> modelo = new DefaultListModel<>(); // Para listAplicD (todas las apps)
DefaultListModel<String> modelo2 = new DefaultListModel<>(); // Para listAplicA (apps del perfil)

// 3. Llenar listAplicD con TODOS los perfiles
for (Perfil perfil : perfiles) {
    modelo.addElement(perfil.getNombre_perfil());
}
lstPerfD.setModel(modelo);

// 4. Listener para cuando seleccionen un perfil
cbousuario.addActionListener(e -> {
    // Limpiar modelo2 antes de agregar nuevos elementos
    modelo2.clear();
    
    try {
        // Obtener perfil seleccionado
        String idSelec = cbousuario.getSelectedItem().toString();
        if (!idSelec.equals("Seleccione una opción")) {
            int idSeleccionado = Integer.parseInt(idSelec);
        
            // Obtener relaciones perfil-aplicación
            RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
            List<RelPerfUsu> relaciones = relPerfUsuDAO.select();
        
            // Filtrar aplicaciones del perfil seleccionado
            for (RelPerfUsu relacion : relaciones) {
                if (relacion.getUsuario_codigo() == idSeleccionado) {
                    // Buscar la aplicación por ID
                    for (Perfil perfil : perfiles) {
                        if (perfil.getId_perfil() == relacion.getPerfil_codigo()) {
                            modelo2.addElement(perfil.getNombre_perfil());
                            break; // Salir del for interno
                    }
                }
            }
        }
        
        lstPerfA.setModel(modelo2);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar perfiles: " + ex.getMessage());
    }
});

}

    public void llenarlistaUnoaUno() {
    int indice=0;
    String cadena; 
     
    indice = lstPerfD.getSelectedIndex();
    if (indice != -1) {
        
    cadena = (String) lstPerfD.getSelectedValue();
    DefaultListModel<String> modeloPerfA;
    
    if (lstPerfA.getModel() == null) {
        modeloPerfA = new DefaultListModel<>();
        lstPerfA.setModel(modeloPerfA);
        
    } else {
        
        modeloPerfA = (DefaultListModel<String>) lstPerfA.getModel();
                
    }
    modeloPerfA.addElement(cadena);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un Perfil", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Perfil a Usuario");    
   
    }
    
    public void llenarlista() {
    PerfilDAO perfilDAO = new PerfilDAO();
    List<Perfil> perfiles = perfilDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>(); 
    //Recorre la lista :v
    for (Perfil app : perfiles) {
    modelo.addElement(app.getNombre_perfil()); 
}
lstPerfA.setModel(modelo);

        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Todos Los Perfiles a Usuario");    
   
    }
    
    public void vaciarlista() {
 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    
    modelo.clear();
    lstPerfA.setModel(modelo);
      
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar Todos Los Perfiles a Usuario");    
   
    }
    
    public void vaciarlistaUnoaUno() {
    int indice = lstPerfA.getSelectedIndex();
    if (indice != -1) {
        String perfilSeleccionado = lstPerfA.getSelectedValue();
        DefaultListModel<String> modeloAsignados = (DefaultListModel<String>) lstPerfA.getModel();
        modeloAsignados.remove(indice);

        try {
            // Validar si se seleccionó un usuario válido
            Object itemSeleccionado = cbousuario.getSelectedItem();
            if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
                JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido antes de eliminar un perfil", "Advertencia", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el ID del usuario seleccionado
            int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());

            // Buscar el ID del perfil por su nombre
            PerfilDAO perfilDAO = new PerfilDAO();
            List<Perfil> perfiles = perfilDAO.select();
            int idPerfil = -1;

            for (Perfil perfil : perfiles) {
                if (perfil.getNombre_perfil().equals(perfilSeleccionado)) {
                    idPerfil = perfil.getId_perfil();
                    break;
                }
            }

            if (idPerfil != -1) {
                // Eliminar la relación de la base de datos
                RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
                RelPerfUsu relPerfUsu = new RelPerfUsu();
                relPerfUsu.setUsuario_codigo(codigoUsuario);
                relPerfUsu.setPerfil_codigo(idPerfil);
                relPerfUsuDAO.delete(relPerfUsu);

                JOptionPane.showMessageDialog(this, "Perfil eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el perfil: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un Perfil para eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    public MantenimientoRelPerfUsu() {
        initComponents(); 
        llenadoDeCombos(); 
        llenadousuariosperfiles();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        cbousuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPerfD = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPerfA = new javax.swing.JList<>();
        btnAgregarU = new javax.swing.JButton();
        btnEliminarU = new javax.swing.JButton();
        txtusu = new javax.swing.JTextField();
        btnAgregarT = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtidPerf = new javax.swing.JTextField();
        label9 = new javax.swing.JLabel();
        bntconfir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setBackground(new java.awt.Color(255, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Usuario Perfil");
        setVisible(true);
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Usuario");

        cbousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbousuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("Perfiles disponibles");

        jLabel3.setText("👤 ASIGNAR PERFIL");

        jLabel4.setText("👤RETIRAR PERFIL");

        lstPerfD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(lstPerfD);

        lstPerfA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(lstPerfA);

        btnAgregarU.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregarU.setText("▶");
        btnAgregarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUActionPerformed(evt);
            }
        });

        btnEliminarU.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarU.setText("◀️");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        txtusu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtusu.setEnabled(false);
        txtusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuActionPerformed(evt);
            }
        });

        btnAgregarT.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregarT.setText("▶▶");
        btnAgregarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTActionPerformed(evt);
            }
        });

        btnEliminarT.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarT.setText("◀◀️");
        btnEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        label3.setText("Perfiles Asignados");

        txtidPerf.setEnabled(false);

        label9.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        label9.setText("Perfil Seleccionado");

        bntconfir.setBackground(new java.awt.Color(153, 255, 204));
        bntconfir.setText("📝 Guardar");
        bntconfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntconfirActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(204, 204, 255));
        btnSalir.setText("Salir 🏃️");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel5.setText("👤");

        jLabel6.setText("👤");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarU, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(bntconfir, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtidPerf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(297, 297, 297)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label3)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(145, 145, 145))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnSalir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidPerf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(label3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarU)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntconfir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbousuarioActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbousuarioActionPerformed

    private void btnAgregarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUActionPerformed
        // TODO add your handling code here:
        llenarlistaUnoaUno();
    }//GEN-LAST:event_btnAgregarUActionPerformed

    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
        // TODO add your handling code here:
        vaciarlistaUnoaUno();
    txtidPerf.setText(""); // Limpiar el campo de texto que muestra el ID del perfil
    }//GEN-LAST:event_btnEliminarUActionPerformed

    private void btnAgregarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTActionPerformed
       // TODO add your handling code here:
        llenarlista();
    }//GEN-LAST:event_btnAgregarTActionPerformed

    private void btnEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTActionPerformed
     vaciarlista(); // Limpia visualmente la lista de perfiles asignados
    txtidPerf.setText("");

    // Validar si se seleccionó un usuario válido
    Object itemSeleccionado = cbousuario.getSelectedItem();
    if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
        JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido antes de eliminar perfiles", "Advertencia", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Obtener el ID del usuario seleccionado
        int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());

        // Instanciar el DAO para eliminar relaciones
        RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
        int registrosEliminados = relPerfUsuDAO.deleteByUserId(codigoUsuario);

        // Confirmar el éxito 
        JOptionPane.showMessageDialog(this, "Se eliminaron " + registrosEliminados + " perfiles del usuario seleccionado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar el usuario seleccionado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar los perfiles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarTActionPerformed

    private void bntconfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntconfirActionPerformed
  // TODO add your handling code here:
        Object itemSeleccionado = cbousuario.getSelectedItem();
    if (itemSeleccionado == null || itemSeleccionado.toString().equals("Seleccione una opción")) {
        JOptionPane.showMessageDialog(this, "Selecciona un Usuario válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int codigoUsuario = Integer.parseInt(itemSeleccionado.toString());
        ListModel<String> modeloAsignados = lstPerfA.getModel();

        if (modeloAsignados.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "No hay perfiles asignados para confirmar", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        List<Perfil> perfiles = perfilDAO.select();

        // Insertar todos los perfiles asignados
        for (int i = 0; i < modeloAsignados.getSize(); i++) {
            String nombrePerfil = modeloAsignados.getElementAt(i);
            for (Perfil perfil : perfiles) {
                if (perfil.getNombre_perfil().equals(nombrePerfil)) {
                    RelPerfUsu relPerfUsu = new RelPerfUsu();
                    relPerfUsu.setUsuario_codigo(codigoUsuario);
                    relPerfUsu.setPerfil_codigo(perfil.getId_perfil());

                    relPerfUsuDAO.insert(relPerfUsu);
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Perfiles asignados correctamente al usuario", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_bntconfirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
// Muestra un cuadro de diálogo de confirmación para salir del mantenimiento.        
// Devuelve un valor según la opción seleccionada por el usuario (Sí o No)..
        int valor=JOptionPane.showConfirmDialog(this,"¿Està seguro de salir del Mantenimiento?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor==JOptionPane.YES_OPTION)
        {
            this.dispose(); // Cierra la ventana del mantenimiento.

        }
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Salio de Mantenimiento RelAplPerf");

    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntconfir;
    private javax.swing.JButton btnAgregarT;
    private javax.swing.JButton btnAgregarU;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbousuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lstPerfA;
    private javax.swing.JList<String> lstPerfD;
    private javax.swing.JTextField txtidPerf;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
