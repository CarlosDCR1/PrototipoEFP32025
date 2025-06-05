package vista.bancos; // Paquete donde se encuentra esta clase

// Importaciones necesarias para el funcionamiento del formulario
import Controlador.seguridad.UsuarioConectado;  // Para obtener el usuario actualmente conectado
import Modelo.seguridad.UsuarioDAO;             // Acceso a datos del usuario (DAO)
import Controlador.seguridad.permisos;          // Clase que maneja los permisos del usuario

import vista.seguridad.*;                        // Importa vistas relacionadas con seguridad
import Modelo.bancos.tipo_pagoDAO;              // DAO para operaciones con tipo_pago
import Controlador.bancos.tipo_pago;            // Clase del modelo tipo_pago
import java.util.List;                           // Para manejar listas
import javax.swing.table.DefaultTableModel;     // Modelo de tabla para JTable
import java.io.File;                             // Para manejo de archivos
import Controlador.seguridad.Bitacora;          // Para registrar acciones en bitácora
import Controlador.seguridad.UsuarioConectado;  // (Repetido) Para obtener usuario actual
import Modelo.Conexion;                          // Clase para manejar la conexión a BD
import java.sql.Connection;                      // Clase de conexión JDBC
import java.util.HashMap;                        // Mapa para parámetros de reportes
import java.util.Map;                            // Interfaz Map
import javax.swing.JOptionPane;                  // Para mostrar cuadros de diálogo
import net.sf.jasperreports.engine.*;            // Librerías para generar reportes JasperReports
import net.sf.jasperreports.view.JasperViewer;   // Para visualizar reportes Jasper

// Comentario del autor del mantenimiento
// MANTENIMIENTO CREADO POR Anderson Cristofer Rodríguez Pivaral 

/**
 *
 * @author visitante
 */
public class MantenimientoTipo_pago extends javax.swing.JInternalFrame { // Clase que extiende de JInternalFrame
    int APLICACION=110; // ID de la aplicación para bitácora
    private Connection connectio; // Conexión a la base de datos

    // 🔒 Variables para permisos
    private int idUsuarioSesion; // ID del usuario en sesión
    private UsuarioDAO usuarioDAO; // DAO para usuarios
    private permisos permisos; // Permisos del usuario
    private permisos permisosUsuarioActual; // Permisos actuales del usuario

    // Método para llenar la tabla con los datos de tipo_pago
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel(); // Modelo de tabla
        modelo.addColumn("id_tipo_pago"); // Columna ID
        modelo.addColumn("tipo_pago");    // Columna tipo de pago
        modelo.addColumn("status");       // Columna estado

        tipo_pagoDAO tipo_pagoDAO = new tipo_pagoDAO(); // DAO para tipo_pago
        List<tipo_pago> list_tipo_pagos = tipo_pagoDAO.select(); // Obtener lista de tipo_pago
        tablaTipo_pago.setModel(modelo); // Asignar modelo a la tabla

        String[] dato = new String[3]; // Arreglo para almacenar datos de cada fila
        for (int i = 0; i < list_tipo_pagos.size(); i++) {
            dato[0] = Integer.toString(list_tipo_pagos.get(i).getIdTipoPago()); // ID
            dato[1] = list_tipo_pagos.get(i).getTipoPago(); // Tipo de pago
            dato[2] = list_tipo_pagos.get(i).getStatus();   // Estado
            modelo.addRow(dato); // Agregar fila al modelo
        }
    }

    // Método para buscar un tipo de pago específico
    public void buscarTipoPago() {
        tipo_pago tipo_pagoAConsultar = new tipo_pago(); // Crear objeto tipo_pago
        tipo_pagoDAO tipo_pagoDAO = new tipo_pagoDAO();  // DAO para tipo_pago
        tipo_pagoAConsultar.setIdTipoPago(Integer.parseInt(txtbuscado.getText())); // Establecer ID a buscar
        tipo_pagoAConsultar = tipo_pagoDAO.query(tipo_pagoAConsultar); // Consultar en BD

        txtTipo_pago.setText(tipo_pagoAConsultar.getTipoPago()); // Mostrar tipo de pago
        txtStatus.setText(tipo_pagoAConsultar.getStatus());       // Mostrar estado

        int resultadoBitacora=0; // Variable para resultado de bitácora
        Bitacora bitacoraRegistro = new Bitacora(); // Crear objeto bitácora
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
            UsuarioConectado.getIdUsuario(), APLICACION,  "Buscar Datos tipo_pago"); // Registrar acción en bitácora
    }

    // Constructor de la clase
    public MantenimientoTipo_pago() {
        initComponents(); // Inicializar componentes gráficos
        llenadoDeTablas(); // Llenar tabla al iniciar

        // 🔐 Validación de permisos
        idUsuarioSesion = UsuarioConectado.getIdUsuario(); // Obtener ID del usuario en sesión
        usuarioDAO = new UsuarioDAO(); // Crear DAO de usuario
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion); // Obtener permisos del usuario

        // Habilitar o deshabilitar botones según permisos
        btnEliminar.setEnabled(permisos.isPuedeEliminar());
        btnRegistrar.setEnabled(permisos.isPuedeRegistrar());
        btnModificar.setEnabled(permisos.isPuedeModificar());
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
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();
        txtTipo_pago = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipo_pago = new javax.swing.JTable();
        txtStatus = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoTipo_Pago");
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
        label1.setText("Tipo Pago");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Tipo Pago");

        txtTipo_pago.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipo_pago.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaTipo_pago.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaTipo_pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_tipo_pago", "tipo_pago", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTipo_pago);
        if (tablaTipo_pago.getColumnModel().getColumnCount() > 0) {
            tablaTipo_pago.getColumnModel().getColumn(0).setResizable(false);
        }

        txtStatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtStatus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("status");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3)
                            .addComponent(label5))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStatus)
                            .addComponent(txtTipo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1)
                .addGap(287, 287, 287))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5))
                        .addGap(153, 153, 153)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnReporte)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
                                             
    // Acción al presionar el botón Eliminar
    tipo_pagoDAO tipo_pagoDAO = new tipo_pagoDAO(); // Crear instancia del DAO
    tipo_pago tipo_pagoAEliminar = new tipo_pago(); // Crear objeto tipo_pago
    tipo_pagoAEliminar.setIdTipoPago(Integer.parseInt(txtbuscado.getText())); // Obtener ID desde el campo de texto
    tipo_pagoDAO.delete(tipo_pagoAEliminar); // Eliminar el registro en la base de datos
    llenadoDeTablas(); // Actualizar la tabla con los datos restantes

    UsuarioConectado usuarioEnSesion = new UsuarioConectado(); // Obtener usuario en sesión
    int resultadoBitacora=0; // Variable para resultado de bitácora
    Bitacora bitacoraRegistro = new Bitacora(); // Crear objeto bitácora
    resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
        usuarioEnSesion.getIdUsuario(), APLICACION,  "Eliminar Datos tipo_pago"); // Registrar acción en bitácora
                                         

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                                
    // Acción al presionar el botón Registrar
    tipo_pagoDAO tipo_pagoDAO = new tipo_pagoDAO(); // Crear instancia del DAO
    tipo_pago tipo_pagoAInsertar = new tipo_pago(); // Crear objeto tipo_pago
    tipo_pagoAInsertar.setTipoPago(txtTipo_pago.getText()); // Establecer tipo de pago desde campo de texto
    tipo_pagoAInsertar.setStatus(txtStatus.getText()); // Establecer estado desde campo de texto
    tipo_pagoDAO.insert(tipo_pagoAInsertar); // Insertar nuevo registro en la base de datos
    llenadoDeTablas(); // Actualizar la tabla con los nuevos datos

    UsuarioConectado usuarioEnSesion = new UsuarioConectado(); // Obtener usuario en sesión
    int resultadoBitacora=0; // Variable para resultado de bitácora
    Bitacora bitacoraRegistro = new Bitacora(); // Crear objeto bitácora
    resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
        usuarioEnSesion.getIdUsuario(), APLICACION,  "Insertar Datos tipo_pago"); // Registrar acción en bitácora
                                            

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Acción al presionar el botón Buscar
        buscarTipoPago(); // Llamar al método que realiza la búsqueda
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
                                                     
    // Acción al presionar el botón Modificar
    tipo_pagoDAO tipo_pagoDAO = new tipo_pagoDAO(); // Crear instancia del DAO
    tipo_pago tipo_pagoAActualizar = new tipo_pago(); // Crear objeto tipo_pago
    tipo_pagoAActualizar.setIdTipoPago(Integer.parseInt(txtbuscado.getText())); // Obtener ID desde campo de texto
    tipo_pagoAActualizar.setTipoPago(txtTipo_pago.getText()); // Obtener nuevo tipo de pago
    tipo_pagoAActualizar.setStatus(txtStatus.getText()); // Obtener nuevo estado
    tipo_pagoDAO.update(tipo_pagoAActualizar); // Actualizar el registro en la base de datos
    llenadoDeTablas(); // Actualizar la tabla con los datos modificados

    int resultadoBitacora=0; // Variable para resultado de bitácora
    Bitacora bitacoraRegistro = new Bitacora(); // Crear objeto bitácora
    resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
        UsuarioConectado.getIdUsuario(), APLICACION,  "Modificar Datos tipo_pago"); // Registrar acción en bitácora                                           


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    // Recorre todos los componentes dentro del panel principal//NUEVO METODO FUNCIONAL
    for (java.awt.Component comp : this.getContentPane().getComponents()) {
        if (comp instanceof javax.swing.JTextField) {
            ((javax.swing.JTextField) comp).setText("");
        } else if (comp instanceof javax.swing.JComboBox) {
            ((javax.swing.JComboBox<?>) comp).setSelectedIndex(0);
        }
    }
    // Aquí se habilitan los botones según los permisos actuales, no todos en true
    aplicarPermisos(permisosUsuarioActual);


    // botones estén habilitados
    btnRegistrar.setEnabled(true);
    btnModificar.setEnabled(true);
    btnEliminar.setEnabled(true);

    System.out.println("Todos los campos han sido limpiados automáticamente.");
      UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar TIPO DE PAGO");

    }//GEN-LAST:event_btnLimpiarActionPerformed
/*
     // TODO add your handling code here:
        MantenimientoAula ventana = new MantenimientoAula();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                                                
    // Acción al presionar el botón de ayuda
    try {
        // Verifica si el archivo de ayuda existe en la ruta especificada
        if ((new File("src\\main\\java\\ayudas\\banco\\AyudaBanco.chm")).exists()) {
            // Ejecuta el archivo de ayuda utilizando el sistema operativo
            Process p = Runtime
                    .getRuntime()
                    .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudaBanco.chm");
            p.waitFor(); // Espera a que el proceso termine
        } else {
            // Mensaje en consola si el archivo no se encuentra
            System.out.println("La ayuda no Fue encontrada");
        }
        // Mensaje en consola si todo fue correcto
        System.out.println("Correcto");
    } catch (Exception ex) {
        // Imprime el error en caso de excepción
        ex.printStackTrace();
    }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
                                            
    // Acción al presionar el botón de generar reporte
    Map p = new HashMap(); // Mapa para parámetros del reporte
    JasperReport report; // Objeto para el reporte compilado
    JasperPrint print;   // Objeto para el reporte generado

    try {
        // Obtener conexión a la base de datos
        Connection connectio = Conexion.getConnection();

        // Compilar el archivo .jrxml del reporte desde su ruta
        report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                + "/src/main/java/reporte/banco/ReporteTipoPago.jrxml");

        // Llenar el reporte con los datos y parámetros
        print = JasperFillManager.fillReport(report, p, connectio);

        // Crear una vista del reporte generado
        JasperViewer view = new JasperViewer(print, false);

        // Establecer título de la ventana del reporte
        view.setTitle("Prueba reporte");
        view.setVisible(true); // Mostrar la ventana del reporte
    } catch (Exception e) {
        // Imprimir error en consola y mostrar mensaje al usuario
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
    }
        
                                              
    }//GEN-LAST:event_btnReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaTipo_pago;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTipo_pago;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
