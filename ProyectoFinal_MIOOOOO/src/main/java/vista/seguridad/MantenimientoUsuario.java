package vista.seguridad;

import vista.*;
import Controlador.seguridad.Utilidades;
import Modelo.seguridad.UsuarioDAO;
import Controlador.seguridad.Usuario;
import java.io.File;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.security.NoSuchAlgorithmException;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;

public class MantenimientoUsuario extends javax.swing.JInternalFrame {
     final int APLICACION100=100;  
    
    
    

  // Método para llenar la tabla de usuarios
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id_Usuario");
        modelo.addColumn("Nombre usuario");
        modelo.addColumn("Contraseña Usuario");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.select();

        tablaUsuario.setModel(modelo);
        String[] dato = new String[3];
        for (int i = 0; i < usuarios.size(); i++) {
            dato[0] = Integer.toString(usuarios.get(i).getId_usuario());
            dato[1] = usuarios.get(i).getUsername();
            // No mostrar la contraseña en texto claro, sino "********"
            dato[2] = "********";
            modelo.addRow(dato);
        }
    }
public void registrarUsuario() {
    try {
        String username = txtNombre.getText();
        String contrasena = txtPassword.getText();
        // Encriptar la contraseña
        String contrasenaEncriptada = Utilidades.encriptarContrasena(contrasena);

        // Crear el objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(contrasenaEncriptada);

        // Insertar el nuevo usuario en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insert(nuevoUsuario);

        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
        llenadoDeTablas();  // Recargar la tabla de usuarios

    } catch (NoSuchAlgorithmException e) {
        JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.");
    }
}

    // Método para buscar usuario
    public void buscarUsuario() {
        try {
            // Verificar si el campo de búsqueda no está vacío
            if (txtUpdate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de usuario.");
                return; // Salir si el campo está vacío
            }

            // Intentar convertir el texto a número
            int idUsuario = Integer.parseInt((String) txtUpdate.getText());

            // Crear el objeto usuario a consultar
            Usuario usuarioAconsultar = new Usuario();
            usuarioAconsultar.setId_usuario(idUsuario);

            // Crear la instancia del DAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Consultar el usuario en la base de datos
            usuarioAconsultar = usuarioDAO.query(usuarioAconsultar);

            // Verificar si el usuario fue encontrado
            if (usuarioAconsultar != null) {
                // Encriptar la contraseña ingresada por el usuario
                String contrasenaIngresada = txtPassword.getText();
                String contrasenaEncriptada = Utilidades.encriptarContrasena(contrasenaIngresada);

                // Comparar la contraseña encriptada
                if (usuarioAconsultar.getPassword().equals(contrasenaEncriptada)) {
                    // Si las contraseñas coinciden, mostrar la información
                    txtNombre.setText(usuarioAconsultar.getUsername());
                    txtPassword.setText(usuarioAconsultar.getPassword());
                } else {
                    // Si la contraseña no es correcta
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                }
            } else {
                // Si el usuario no existe, mostrar mensaje
                txtNombre.setText("");
                txtPassword.setText("");
                System.out.println("No se encontró el usuario.");
            }
        } catch (NumberFormatException e) {
            // Manejar el caso cuando el ID no es un número válido
            JOptionPane.showMessageDialog(null, "El ID ingresado no es válido. Asegúrese de ingresar un número.");
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.");
        }
    }
    
    
    
    public void buscarUsuario2(){
    try {
    

    int idUsuario = Integer.parseInt(txtbuscado.getText());

    Usuario usuarioAconsultar = new Usuario();
    usuarioAconsultar.setId_usuario(idUsuario);

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    usuarioAconsultar = usuarioDAO.query2(usuarioAconsultar);

    if (usuarioAconsultar != null) {
        // Rellenar campos directamente con los datos obtenidos
        txtNombre.setText(usuarioAconsultar.getUsername());
        txtPassword.setText(usuarioAconsultar.getPassword());  // Contraseña cifrada
    } else {
        txtNombre.setText("");
        txtPassword.setText("");
        JOptionPane.showMessageDialog(null, "Usuario no encontrado");
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Formato de ID inválido. Use solo números");
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
}
    
    
    
    
    
    }
    
    
    
    
    // Constructor de la clase
    public MantenimientoUsuario() {
        initComponents();
        llenadoDeTablas();
    }

    @SuppressWarnings("unchecked")
    // Aquí iría el código generado automáticamente por el editor de GUI
    // TODO: incluir código del diseño de tu interfaz


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        txtUpdate = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        txtPassword = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Usuario");
        setVisible(true);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("Usuario");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblNombre.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Vendedor", "ID Empleado", "Correo", "Telefono", "Direccion", "Porcentaje", "Comision", "prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        txtPassword.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        lblDireccion.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblDireccion.setText("Contraseña");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");

        txtbuscado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtbuscado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(163, 163, 163))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblDireccion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblNombre)
                                        .addGap(38, 38, 38)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(294, 294, 294))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(490, 490, 490))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(555, 555, 555))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNombre))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblDireccion)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnEliminar))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnLimpiar)
                                        .addComponent(btnModificar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar))
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)))
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAEliminar = new Usuario();
        usuarioAEliminar.setId_usuario(Integer.parseInt(txtUpdate.getText()));
        usuarioDAO.delete(usuarioAEliminar);
        llenadoDeTablas();
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION100,  "Borrar Datos Usuarios");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                           
    try {
        // Crear una instancia del DAO para usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Crear el objeto usuario a insertar
        Usuario usuarioAInsertar = new Usuario();
        
        // Establecer el nombre de usuario
        usuarioAInsertar.setUsername(txtNombre.getText());
        
        // Encriptar la contraseña ingresada antes de asignarla
        String contrasenaEncriptada = Utilidades.encriptarContrasena(txtPassword.getText());
        
        // Establecer la contraseña encriptada
        usuarioAInsertar.setPassword(contrasenaEncriptada);
        
        // Insertar el usuario en la base de datos
        usuarioDAO.insert(usuarioAInsertar);
        
        // Registrar en la bitácora
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        Bitacora bitacoraRegistro = new Bitacora();
        int resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
            usuarioEnSesion.getIdUsuario(), 
            APLICACION100,  
            "Ingreso Datos Usuario"
        );

        // Recargar la tabla de usuarios
        llenadoDeTablas();
        
        // Mensaje de éxito
        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
        
    } catch (NoSuchAlgorithmException e) {
        // Manejo de error si ocurre un problema en la encriptación
        JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.");
    }
 
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
     buscarUsuario2();
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAActualizar = new Usuario();
        usuarioAActualizar.setId_usuario(Integer.parseInt(txtUpdate.getText()));
        usuarioAActualizar.setUsername(txtNombre.getText());
        usuarioAActualizar.setPassword(txtPassword.getText());
        
        
        usuarioDAO.update(usuarioAActualizar);
        llenadoDeTablas();
         UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION100,  "Actualizacion Datos Usuarios");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtPassword.setText("");
        txtUpdate.setText("");
        
       
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\ProcesoMayor.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\ProcesoMayor.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbuscadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaUsuario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUpdate;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
