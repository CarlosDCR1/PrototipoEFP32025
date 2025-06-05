package vista.bancos;

// Importaciones necesarias para el funcionamiento de la vista
import Controlador.seguridad.UsuarioConectado;  // Para obtener el ID del usuario actualmente conectado
import Modelo.seguridad.UsuarioDAO;             // DAO para acceder a datos del usuario
import Controlador.seguridad.permisos;          // Clase que representa los permisos del usuario

import vista.seguridad.*; // Importación general de vistas de seguridad
import Modelo.bancos.detalle_movimientos_bancariosDAO; // DAO para manejar detalles de movimientos bancarios
import Controlador.bancos.detalle_movimientos_bancarios; // Modelo de datos para los detalles
import java.util.List;
import javax.swing.table.DefaultTableModel; // Para manejar la tabla de datos en la interfaz
import java.io.File;
import Controlador.seguridad.Bitacora; // Para registrar acciones del usuario
import Modelo.Conexion; // Clase para manejar la conexión a la base de datos
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*; // Librerías para generación de reportes con JasperReports
import net.sf.jasperreports.view.JasperViewer;

// MANTENIMIENTO CREADO POR Anderson Cristofer Rodríguez Pivaral

/**
 * Clase que representa la ventana interna para el mantenimiento de detalles de movimientos bancarios.
 */
public class MantenimientoDetalle_movimientos_bancarios extends javax.swing.JInternalFrame {

    int APLICACION = 105; // ID de la aplicación para bitácora
    private Connection connectio; // Conexión a la base de datos

    // 🔒 Variables para control de permisos
    private int idUsuarioSesion; // ID del usuario en sesión
    private UsuarioDAO usuarioDAO; // DAO para obtener permisos del usuario
    private permisos permisos; // Objeto que contiene los permisos del usuario

    private permisos permisosUsuarioActual; // (No se usa en este fragmento, posiblemente en otra parte)

    // Método para llenar combos (actualmente vacío, pero preparado para uso futuro)
    public void llenadoDeCombos() {
        detalle_movimientos_bancariosDAO detalle_movimientos_bancariosDAO = new detalle_movimientos_bancariosDAO();
        List<detalle_movimientos_bancarios> salon = detalle_movimientos_bancariosDAO.select();

        for (int i = 0; i < salon.size(); i++) {
            // Aquí se podría llenar un JComboBox con los datos obtenidos
        }
    }

    // Método para llenar la tabla con los datos de la base de datos
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        // Se agregan las columnas al modelo de la tabla
        modelo.addColumn("id_detalle");
        modelo.addColumn("id_movimiento_bancario");
        modelo.addColumn("id_tipo_operacion");
        modelo.addColumn("id_tipo_pago");
        modelo.addColumn("monto");
        modelo.addColumn("descripcion");

        detalle_movimientos_bancariosDAO detalle_movimientos_bancariosDAO = new detalle_movimientos_bancariosDAO();
        List<detalle_movimientos_bancarios> lista = detalle_movimientos_bancariosDAO.select(); // Obtiene los datos

        tablaDetalle_movimientos_bancarios.setModel(modelo); // Asigna el modelo a la tabla
        String[] dato = new String[6]; // Arreglo temporal para cada fila

        // Recorre la lista de detalles y los agrega al modelo de la tabla
        for (detalle_movimientos_bancarios detalle : lista) {
            dato[0] = Integer.toString(detalle.getIdDetalle());
            dato[1] = Integer.toString(detalle.getIdMovimiento());
            dato[2] = Integer.toString(detalle.getIdTipoOperacion());
            dato[3] = Integer.toString(detalle.getIdTipoPago());
            dato[4] = Float.toString(detalle.getMonto());
            dato[5] = detalle.getDescripcion();
            modelo.addRow(dato); // Agrega la fila al modelo
        }
    }

    // Método para buscar un detalle específico por su ID
    public void buscarDetalle() {
        detalle_movimientos_bancarios detalleAConsultar = new detalle_movimientos_bancarios();
        detalle_movimientos_bancariosDAO dao = new detalle_movimientos_bancariosDAO();

        // Se obtiene el ID desde el campo de texto y se realiza la consulta
        detalleAConsultar.setIdDetalle(Integer.parseInt(txtbuscado.getText()));
        detalleAConsultar = dao.query(detalleAConsultar);

        // Se muestran los datos obtenidos en los campos correspondientes
        txtIdMovimiento.setText(Integer.toString(detalleAConsultar.getIdMovimiento()));
        txtIdTipoOperacion.setText(Integer.toString(detalleAConsultar.getIdTipoOperacion()));
        txtIdTipoPago.setText(Integer.toString(detalleAConsultar.getIdTipoPago()));
        txtMonto.setText(Float.toString(detalleAConsultar.getMonto()));
        txtDescripcion.setText(detalleAConsultar.getDescripcion());

        // Se registra la acción en la bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Datos detalle_movimientos_bancarios");
    }

    // Constructor de la clase: inicializa componentes, llena tabla y combos, y valida permisos
    public MantenimientoDetalle_movimientos_bancarios() {
        initComponents(); // Inicializa los componentes gráficos
        llenadoDeTablas(); // Llena la tabla con datos
        llenadoDeCombos(); // Llena los combos (aunque actualmente está vacío)

        // 🔐 Validación de permisos del usuario actual
        idUsuarioSesion = UsuarioConectado.getIdUsuario(); // Obtiene el ID del usuario en sesión
        usuarioDAO = new UsuarioDAO(); // Instancia del DAO de usuario
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion); // Obtiene los permisos

        // Habilita o deshabilita botones según los permisos del usuario
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
        txtbuscado = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalle_movimientos_bancarios = new javax.swing.JTable();
        txtIdMovimiento = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        txtIdTipoPago = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        txtIdTipoOperacion = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        label8 = new javax.swing.JLabel();
        label9 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoDetalle_movimientos_bancarios");
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
        label1.setText("Detalle Movimientos Bancarios");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaDetalle_movimientos_bancarios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaDetalle_movimientos_bancarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaDetalle_movimientos_bancarios);
        if (tablaDetalle_movimientos_bancarios.getColumnModel().getColumnCount() > 0) {
            tablaDetalle_movimientos_bancarios.getColumnModel().getColumn(0).setResizable(false);
        }

        txtIdMovimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdMovimiento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Id Movimiento");

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

        txtIdTipoPago.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdTipoPago.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Id Tipo Pago");

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Id Tipo Operacion");

        txtIdTipoOperacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdTipoOperacion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        txtMonto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMonto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Monto");

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Descripcion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label7)
                                    .addComponent(label6)
                                    .addComponent(label8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(650, Short.MAX_VALUE)
                .addComponent(label1)
                .addGap(225, 225, 225))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(txtIdMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(txtIdTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(txtIdTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label9)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnReporte))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
                                         
    // Acción del botón Eliminar: elimina un registro según el ID ingresado
    detalle_movimientos_bancariosDAO dao = new detalle_movimientos_bancariosDAO();
    detalle_movimientos_bancarios detalleAEliminar = new detalle_movimientos_bancarios();
    detalleAEliminar.setIdDetalle(Integer.parseInt(txtbuscado.getText())); // Obtiene el ID desde el campo de texto
    dao.delete(detalleAEliminar); // Llama al método DAO para eliminar el registro
    llenadoDeTablas(); // Refresca la tabla para mostrar los cambios

    // Registra la acción en la bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Datos detalle_movimientos_bancarios");
                                          
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                                
    // Acción del botón Registrar: inserta un nuevo registro si los datos son válidos
    detalle_movimientos_bancariosDAO dao = new detalle_movimientos_bancariosDAO();
    String errores = ""; // Variable para acumular mensajes de error

    // Obtiene los valores ingresados por el usuario
    int idMovimiento = Integer.parseInt(txtIdMovimiento.getText());
    int idTipoOperacion = Integer.parseInt(txtIdTipoOperacion.getText());
    int idTipoPago = Integer.parseInt(txtIdTipoPago.getText());

    // Validaciones: verifica si los IDs existen en la base de datos
    if (!dao.existeMovimientoBancario(idMovimiento)) {
        errores += "❌ El ID del movimiento bancario no existe.\n";
    }
    if (!dao.existeTipoOperacion(idTipoOperacion)) {
        errores += "❌ El ID del tipo de operación no existe.\n";
    }
    if (!dao.existeTipoPago(idTipoPago)) {
        errores += "❌ El ID del tipo de pago no existe.\n";
    }

    // Si hay errores, se muestran al usuario y se detiene el proceso
    if (!errores.isEmpty()) {
        JOptionPane.showMessageDialog(this, errores, "Errores de validación", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Si todo es válido, se crea el objeto y se inserta en la base de datos
    detalle_movimientos_bancarios detalleAInsertar = new detalle_movimientos_bancarios();
    detalleAInsertar.setIdMovimiento(idMovimiento);
    detalleAInsertar.setIdTipoOperacion(idTipoOperacion);
    detalleAInsertar.setIdTipoPago(idTipoPago);
    detalleAInsertar.setMonto(Float.parseFloat(txtMonto.getText()));
    detalleAInsertar.setDescripcion(txtDescripcion.getText());

    dao.insert(detalleAInsertar); // Inserta el nuevo registro
    llenadoDeTablas(); // Refresca la tabla

    // Registra la acción en la bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Insertar Datos detalle_movimientos_bancarios");                                          

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Acción del botón Buscar: ejecuta el método para buscar un detalle por ID
        buscarDetalle();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
                                                 
    // Acción del botón Modificar: actualiza un registro existente con nuevos datos
    detalle_movimientos_bancariosDAO dao = new detalle_movimientos_bancariosDAO();
    detalle_movimientos_bancarios detalleAActualizar = new detalle_movimientos_bancarios();

    // Se obtienen los datos desde los campos de texto
    detalleAActualizar.setIdDetalle(Integer.parseInt(txtbuscado.getText()));
    detalleAActualizar.setIdMovimiento(Integer.parseInt(txtIdMovimiento.getText()));
    detalleAActualizar.setIdTipoOperacion(Integer.parseInt(txtIdTipoOperacion.getText()));
    detalleAActualizar.setIdTipoPago(Integer.parseInt(txtIdTipoPago.getText()));
    detalleAActualizar.setMonto(Float.parseFloat(txtMonto.getText()));
    detalleAActualizar.setDescripcion(txtDescripcion.getText());

    dao.update(detalleAActualizar); // Actualiza el registro en la base de datos
    llenadoDeTablas(); // Refresca la tabla

    // Registra la acción en la bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Datos detalle_movimientos_bancarios");                                            


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
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar DETALLE DE MOVIMIENTO");
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
    // 📘 Abre el archivo de ayuda .chm si existe en la ruta especificada
    try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudaBanco.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudaBanco.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
                                            
    // Acción del botón Reporte: genera y muestra un reporte con JasperReports

    Map p = new HashMap(); // Mapa de parámetros para el reporte (vacío en este caso)
    JasperReport report;   // Objeto que representa el reporte compilado
    JasperPrint print;     // Objeto que representa el reporte ya lleno con datos

    try {
        // Establece la conexión a la base de datos
        connectio = Conexion.getConnection();

        // Compila el archivo .jrxml del reporte ubicado en la ruta especificada
        report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                + "/src/main/java/reporte/banco/ReporteDetalleMovimientosBancarios.jrxml");

        // Llena el reporte con los datos de la base de datos y los parámetros
        print = JasperFillManager.fillReport(report, p, connectio);

        // Crea una vista del reporte y la muestra al usuario
        JasperViewer view = new JasperViewer(print, false);
        view.setTitle("Prueba reporte"); // Título de la ventana del reporte
        view.setVisible(true); // Hace visible la ventana del reporte
    } catch (Exception e) {
        // Manejo de errores: muestra un mensaje si ocurre una excepción
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
    }

        
    }//GEN-LAST:event_btnReporteActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed


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
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaDetalle_movimientos_bancarios;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdMovimiento;
    private javax.swing.JTextField txtIdTipoOperacion;
    private javax.swing.JTextField txtIdTipoPago;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
