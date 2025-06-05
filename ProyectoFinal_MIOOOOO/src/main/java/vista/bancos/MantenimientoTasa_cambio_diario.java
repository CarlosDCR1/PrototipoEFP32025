/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.bancos;
import Controlador.seguridad.UsuarioConectado;  // Para obtener usuario actual
import Modelo.seguridad.UsuarioDAO;               // Para manejar la lógica de usuario (ajusta el paquete si es otro)
import Controlador.seguridad.permisos;          // La clase que representa los permisos del usuario (ajusta el paquete)

import Controlador.bancos.tasa_cambio_diario;
import vista.seguridad.*;
import Modelo.bancos.tipo_operacion_bancariaDAO;
import Controlador.bancos.tipo_operacion_bancaria;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Controlador.seguridad.permisos;
import Modelo.Conexion;
import Modelo.bancos.tasa_cambio_diarioDAO;
import Modelo.seguridad.UsuarioDAO;
import java.awt.Color;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

//MANTENIMINETO CREADO POR Ruddyard Eduardo Castro Chavez 


/**
 * Clase para mantenimiento de tasas de cambio diario (Interfaz gráfica)
 * 
 * Funcionalidades principales:
 * - Llenado de tablas con tasas existentes
 * - Búsqueda de tasas por ID
 * - Validación de permisos de usuario
 * - Manejo de formato de fecha/hora
 * 
 * Componentes:
 * - Tabla para visualización
 * - Campos para búsqueda/registro
 * - Botones CRUD con control de permisos
 * 
 * Seguridad:
 * - Control de acceso por permisos
 * - Registro en bitácora de acciones
 */
public class MantenimientoTasa_cambio_diario extends javax.swing.JInternalFrame {

     int APLICACION = 105; // Ajustar según corresponda
        private Connection connectio;
    // 🔒 Variables para permisos
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;

private permisos permisosUsuarioActual; 
    private tasa_cambio_diarioDAO tasaDAO = new tasa_cambio_diarioDAO();

    public void llenadoDeCombos() {
        // (Opcional) Si necesitas cargar combo de monedas u otra entidad relacionada
        cbox_empleado.addItem("Seleccione una opción");
        // Implementar lógica si es necesaria
    }

 public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Tasa");
        modelo.addColumn("Valor Promedio");
        modelo.addColumn("Fecha-Hora");
        
        List<tasa_cambio_diario> tasas = tasaDAO.select();
        tablaTipo_operacion_bancaria.setModel(modelo);
        
        String[] dato = new String[3];
        for (tasa_cambio_diario tasa : tasas) {
            dato[0] = String.valueOf(tasa.getId_tasa_cambio_diario());
            dato[1] = String.valueOf(tasa.getValor_promedio_dia());
            dato[2] = tasa.getFecha_hora().toString();
            modelo.addRow(dato);
        }
    }
 
    public void buscarTasa() {
        tasa_cambio_diario tasaConsulta = new tasa_cambio_diario();
        tasaConsulta.setId_tasa_cambio_diario(Integer.parseInt(txtbuscado.getText()));
        tasaConsulta = tasaDAO.query(tasaConsulta);
        
        txtValorPromedio.setText(String.valueOf(tasaConsulta.getValor_promedio_dia()));
        txtFechaHora.setText(tasaConsulta.getFecha_hora().toString());
        
        // Bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Tasa");
    }

    public MantenimientoTasa_cambio_diario() {
        initComponents();
         // Configuración del campo de fecha después de inicializar componentes
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    // Establecer texto guía con fecha actual
    txtFechaHora.setText(formatter.format(LocalDateTime.now()));
    txtFechaHora.setForeground(Color.GRAY);
    
    // Listener para manejar el placeholder dinámico
    txtFechaHora.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            if(txtFechaHora.getForeground().equals(Color.GRAY)) {
                txtFechaHora.setText("");
                txtFechaHora.setForeground(Color.BLACK);
            }
        }
        
        public void focusLost(java.awt.event.FocusEvent evt) {
            if(txtFechaHora.getText().isEmpty()) {
                txtFechaHora.setText(formatter.format(LocalDateTime.now()));
                txtFechaHora.setForeground(Color.GRAY);
            }
        }
    });
        llenadoDeTablas();
        llenadoDeCombos();
    // 🔐 Validación de permisos
       idUsuarioSesion = UsuarioConectado.getIdUsuario();

        usuarioDAO = new UsuarioDAO();
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion);

        
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
        txtValorPromedio = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipo_operacion_bancaria = new javax.swing.JTable();
        cbox_empleado = new javax.swing.JComboBox<>();
        label4 = new javax.swing.JLabel();
        txtFechaHora = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoTasa_de_cambio_diario");
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
        label1.setText("TASA DE CAMBIO DIARIO");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Valor Promedio");

        txtValorPromedio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtValorPromedio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaTipo_operacion_bancaria.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaTipo_operacion_bancaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_tipo_operacion", "tipo_operacion", "descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTipo_operacion_bancaria);
        if (tablaTipo_operacion_bancaria.getColumnModel().getColumnCount() > 0) {
            tablaTipo_operacion_bancaria.getColumnModel().getColumn(0).setResizable(false);
        }

        cbox_empleado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbox_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_empleadoActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Empleado:");

        txtFechaHora.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFechaHora.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Fecha Hora");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

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
                            .addComponent(txtFechaHora)
                            .addComponent(txtValorPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(70, 70, 70)
                                .addComponent(label4)
                                .addGap(46, 46, 46)
                                .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(253, 253, 253))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValorPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label4)
                                .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(btnReporte))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        /**
 * Elimina una tasa de cambio por ID y actualiza la tabla
 * 
 * Pasos:
 * 1. Crea objeto tasa con ID del campo txtbuscado
 * 2. Ejecuta eliminación a través del DAO
 * 3. Refresca datos en tabla
 * 4. Registra acción en bitácora
 * 
 * Seguridad:
 * - El botón Eliminar ya tiene validación de permisos
 * - Registra la acción con usuario y aplicación
 */
      tasa_cambio_diario tasaEliminar = new tasa_cambio_diario();
        tasaEliminar.setId_tasa_cambio_diario(Integer.parseInt(txtbuscado.getText()));
        tasaDAO.delete(tasaEliminar);
        llenadoDeTablas();
        
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Tasa");
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                                
    try {
        tasa_cambio_diario nuevaTasa = new tasa_cambio_diario();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        // Validar y obtener valor promedio
        if(txtValorPromedio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor promedio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        nuevaTasa.setValor_promedio_dia(Float.parseFloat(txtValorPromedio.getText()));
        
        // Manejo de fecha - si está vacío o es el placeholder
        LocalDateTime fechaHora;
        if(txtFechaHora.getText().trim().isEmpty() || 
           txtFechaHora.getForeground().equals(Color.GRAY)) {
            fechaHora = LocalDateTime.now();
            // Mostrar la fecha actual como texto guía visible
            /* Ejecuta código en el hilo de la interfaz gráfica (Swing EDT) */
            SwingUtilities.invokeLater(() -> {
                txtFechaHora.setText(formatter.format(fechaHora));
                txtFechaHora.setForeground(Color.BLACK);
            });
        } else {
            try {
                fechaHora = LocalDateTime.parse(txtFechaHora.getText(), formatter);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Formato de fecha inválido. Use yyyy-MM-dd HH:mm\nEjemplo: " + formatter.format(LocalDateTime.now()), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        nuevaTasa.setFecha_hora(fechaHora);
        
        // Insertar y actualizar tabla
        tasaDAO.insert(nuevaTasa);
        llenadoDeTablas();
        
        // Bitácora y limpieza
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Insertar Tasa");
        
        txtValorPromedio.setText("");
        // Establecer la fecha actual como nuevo placeholder
        SwingUtilities.invokeLater(() -> {
            txtFechaHora.setText(formatter.format(LocalDateTime.now()));
            txtFechaHora.setForeground(Color.GRAY);
        });
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Valor promedio debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarTasa();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
         tasa_cambio_diario tasaActualizar = new tasa_cambio_diario();
        tasaActualizar.setId_tasa_cambio_diario(Integer.parseInt(txtbuscado.getText()));
        tasaActualizar.setValor_promedio_dia(Float.parseFloat(txtValorPromedio.getText()));
        tasaActualizar.setFecha_hora(LocalDateTime.parse(txtFechaHora.getText()));
        
        tasaDAO.update(tasaActualizar);
        llenadoDeTablas();
        
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Tasa");
            
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
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar TASA DE CAMBIO");
  // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbox_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_empleadoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_empleadoActionPerformed
/*
     // TODO add your handling code here:
        MantenimientoAula ventana = new MantenimientoAula();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudasTasaCambioDiario.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudasTasaCambioDiario.chm");
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
        // TODO add your handling code here:
                       Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
                           Connection connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reporte/banco/tasaCambioDiario.jrxml");
//
            print = JasperFillManager.fillReport(report, p, connectio);

            JasperViewer view = new JasperViewer(print, false);

            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
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
    private javax.swing.JComboBox<String> cbox_empleado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaTipo_operacion_bancaria;
    private javax.swing.JTextField txtFechaHora;
    private javax.swing.JTextField txtValorPromedio;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
