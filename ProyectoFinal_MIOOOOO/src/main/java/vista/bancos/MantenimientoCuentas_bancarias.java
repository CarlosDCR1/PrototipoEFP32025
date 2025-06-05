/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// 📦 Importación de clases necesarias para seguridad, base de datos y reportes
package vista.bancos;
import Controlador.seguridad.UsuarioConectado;  // Para obtener usuario actual
import Modelo.seguridad.UsuarioDAO;               // Para manejar la lógica de usuario (ajusta el paquete si es otro)
import Controlador.seguridad.permisos;          // La clase que representa los permisos del usuario (ajusta el paquete)

import vista.seguridad.*;
import Modelo.bancos.cuentas_bancariasDAO;
import Controlador.bancos.cuentas_bancarias;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Modelo.Conexion;
import Modelo.bancos.BancoDAO;
import Modelo.bancos.tipo_cuentaDAO;
import Modelo.bancos.tipo_monedaDAO;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
// 📄 Librerías de JasperReports
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

// MANTENIMIENTO CREADO POR GABRIELA PINTO 9959-23-1087


public class MantenimientoCuentas_bancarias extends javax.swing.JInternalFrame {
    int APLICACION = 104; // Código de la aplicación para bitácora
    
    private Connection connectio;
    
    // 🔐 Variables para saber quién está usando y qué permisos tiene
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;

    private permisos permisosUsuarioActual; 
    public void llenadoDeCombos() {
        cuentas_bancariasDAO cuentas_bancariasDAO = new cuentas_bancariasDAO();
        List<cuentas_bancarias> salon = cuentas_bancariasDAO.select();

        for (int i = 0; i < salon.size(); i++) {

        }
    }

      // 📋 Llena la tabla con las cuentas bancarias guardadas
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id_cuenta");
        modelo.addColumn("id_banco");
        modelo.addColumn("id_tipo_cuenta");
        modelo.addColumn("id_tipo_moneda");
        modelo.addColumn ("saldo");

// Llamamos al DAO para obtener la lista desde la base de datos
        cuentas_bancariasDAO cuentas_bancariasDAO = new cuentas_bancariasDAO();
        List<cuentas_bancarias> lista = cuentas_bancariasDAO.select();
        tablaCuentas_bancarias.setModel(modelo);

        // Llenar cada fila con los datos de las cuentas
        String[] dato = new String[5];
        for (cuentas_bancarias cuenta : lista) {
            dato[0] = Integer.toString(cuenta.getId_cuenta());       // id_cuenta 
            dato[1] = Integer.toString(cuenta.getId_banco());        // id_banco 
            dato[2] = Integer.toString(cuenta.getId_tipo_cuenta()); 
            dato[3] = Integer.toString(cuenta.getId_tipo_moneda());  // id_tipo_moneda 
            dato[4] = Float.toString((float) cuenta.getSaldo());    // saldo 
            modelo.addRow(dato);
        }
    }

 public void buscarCuenta() {
     // Crear objeto de cuenta con el ID a buscar
        cuentas_bancarias cuentaAConsultar = new cuentas_bancarias();
        cuentas_bancariasDAO dao = new cuentas_bancariasDAO();
        // Establecer el ID de la cuenta a buscar (convertido de String a int)
        cuentaAConsultar.setId_cuenta(Integer.parseInt(txtbuscado.getText()));
        // Ejecutar la consulta
        cuentaAConsultar = dao.query(cuentaAConsultar);
        // Mostrar los datos encontrados en los campos de texto
        txtBanco.setText(Integer.toString(cuentaAConsultar.getId_banco()));
        txtTipoCuenta.setText(Integer.toString(cuentaAConsultar.getId_tipo_cuenta()));
        txtMoneda.setText(Integer.toString(cuentaAConsultar.getId_tipo_moneda()));
        txtSaldo.setText(Float.toString((float) cuentaAConsultar.getSaldo()));
        
        // Registrar la acción en la bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Datos cuentas_bancarias");
    }

    public MantenimientoCuentas_bancarias() {
        initComponents(); // Inicialización automática de componentes gráficos
        
        // Llenado inicial de datos
        llenadoDeTablas();
        llenadoDeCombos();
    
     // 🔐 Validación de permisos
       idUsuarioSesion = UsuarioConectado.getIdUsuario();

        usuarioDAO = new UsuarioDAO();
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion);

        // Habilitar/deshabilitar botones según permisos
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
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas_bancarias = new javax.swing.JTable();
        lb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        txtTipoCuenta = new javax.swing.JTextField();
        txtMoneda = new javax.swing.JTextField();
        txtSaldo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnReporte1 = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoCuentas_bancarias");
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
        label1.setText("Cuentas Bancarias");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("ID Bancos");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaCuentas_bancarias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaCuentas_bancarias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_cuentas_bancarias", "id_bancos", "id_tipo_cuentas", "id_tipo_moneda", "saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCuentas_bancarias);
        if (tablaCuentas_bancarias.getColumnModel().getColumnCount() > 0) {
            tablaCuentas_bancarias.getColumnModel().getColumn(0).setResizable(false);
        }

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("ID Tipo Cuentas");

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("ID Tipo Moneda");

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Saldo");

        txtBanco.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtBanco.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtTipoCuenta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipoCuenta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtTipoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoCuentaActionPerformed(evt);
            }
        });

        txtMoneda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMoneda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnReporte1.setText("Reporte");
        btnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporte1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label5)
                        .addGap(12, 12, 12)
                        .addComponent(txtTipoCuenta))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMoneda))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label7)
                                .addGap(65, 65, 65)
                                .addComponent(txtSaldo))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label3)
                                .addGap(251, 251, 251)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReporte1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(txtBanco)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1)
                .addGap(263, 263, 263))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(txtTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnReporte1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      // TODO add your handling code here:
      
      // Crear DAO y objeto para eliminación
        cuentas_bancariasDAO dao = new cuentas_bancariasDAO();
        cuentas_bancarias cuentaAEliminar = new cuentas_bancarias();
        // Establecer ID de la cuenta a eliminar
        cuentaAEliminar.setId_cuenta(Integer.parseInt(txtbuscado.getText()));
        // Ejecutar eliminación
        dao.delete(cuentaAEliminar);
        
        // Actualizar tabla
        llenadoDeTablas();
        
        // Registrar en bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Datos cuentas_bancarias");
      
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
                                           
    // Instancias de DAO
    cuentas_bancariasDAO dao = new cuentas_bancariasDAO();
    cuentas_bancarias cuentaAInsertar = new cuentas_bancarias();

    // Validaciones básicas
    boolean datosValidos = true;
    StringBuilder errores = new StringBuilder();

    int idBanco = 0;
    int idTipoCuenta = 0;
    int idMoneda = 0;
    double saldo = 0;  // mejor double que float para saldo

    // Validación de datos numéricos
    try {
        idBanco = Integer.parseInt(txtBanco.getText().trim());
    } catch (NumberFormatException e) {
        errores.append("❌ ID de banco inválido.\n");
        datosValidos = false;
    }
    // Validación de ID de tipo de cuenta
    try {
        idTipoCuenta = Integer.parseInt(txtTipoCuenta.getText().trim());
    } catch (NumberFormatException e) {
        errores.append("❌ ID de tipo de cuenta inválido.\n");
        datosValidos = false;
    }
    // Validación de ID de tipo de moneda
    try {
        idMoneda = Integer.parseInt(txtMoneda.getText().trim());
    } catch (NumberFormatException e) {
        errores.append("❌ ID de tipo de moneda inválido.\n");
        datosValidos = false;
    }
    // Validación de saldo
    try {
        saldo = Double.parseDouble(txtSaldo.getText().trim());
        if (saldo < 0) {
            errores.append("❌ El saldo no puede ser negativo.\n");
            datosValidos = false;
        }
    } catch (NumberFormatException e) {
        errores.append("❌ Saldo inválido.\n");
        datosValidos = false;
    }

    // Validar existencia en base de datos (dependiendo de DAOs)
    if (datosValidos) {
        BancoDAO bancoDao = new BancoDAO();
        tipo_cuentaDAO tipoCuentaDao = new tipo_cuentaDAO();
        tipo_monedaDAO tipoMonedaDao = new tipo_monedaDAO();

        if (!bancoDao.existeBanco(idBanco)) {
            errores.append("❌ El ID del banco no existe.\n");
            datosValidos = false;
        }

        if (!tipoCuentaDao.existeTipoCuenta(idTipoCuenta)) {
            errores.append("❌ El ID del tipo de cuenta no existe.\n");
            datosValidos = false;
        }

        if (!tipoMonedaDao.existeTipoMoneda(idMoneda)) {
            errores.append("❌ El ID del tipo de moneda no existe.\n");
            datosValidos = false;
        }
    }

    // Mostrar errores si los hay
    if (!datosValidos) {
        JOptionPane.showMessageDialog(this, errores.toString(), "Errores en el formulario", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Si todo está OK, insertar la cuenta
    cuentaAInsertar.setId_banco(idBanco);
    cuentaAInsertar.setId_tipo_cuenta(idTipoCuenta);
    cuentaAInsertar.setId_tipo_moneda(idMoneda);
    cuentaAInsertar.setSaldo(saldo);

    int resultado = dao.insert(cuentaAInsertar);

    if (resultado > 0) {
        JOptionPane.showMessageDialog(this, "✅ ¡Cuenta registrada exitosamente!");
        limpiarFormulario();  // Método para limpiar campos (debes implementarlo)
        llenadoDeTablas();    // Método para actualizar la tabla en pantalla (debes implementarlo)
    } else {
        JOptionPane.showMessageDialog(this, "❌ Error al registrar la cuenta. Inténtalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarCuenta();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
  // Validar que el ID de cuenta esté presente //
    if (txtbuscado.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Primero busca una cuenta para modificar.");
        return;
    }

    try {
        int idCuenta = Integer.parseInt(txtbuscado.getText().trim());

        // Crear objeto con datos actualizados
        cuentas_bancarias cuenta = new cuentas_bancarias();
        cuenta.setId_cuenta(idCuenta);
        cuenta.setId_banco(Integer.parseInt(txtBanco.getText().trim()));
        cuenta.setId_tipo_cuenta(Integer.parseInt(txtTipoCuenta.getText().trim()));
        cuenta.setId_tipo_moneda(Integer.parseInt(txtMoneda.getText().trim()));
        cuenta.setSaldo(Float.parseFloat(txtSaldo.getText().trim()));

        // Ejecutar actualización
        cuentas_bancariasDAO dao = new cuentas_bancariasDAO();
        int resultado = dao.update(cuenta);

        // Manejar resultado
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "✅ Cuenta modificada correctamente.");
            llenadoDeTablas(); // Refresca la tabla
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo modificar la cuenta. Verifica el ID.");
        }

        // Registrar en bitácora
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(
            UsuarioConectado.getIdUsuario(),
            APLICACION,
            "Modificar Datos cuentas_bancarias"
        );
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "❌ Error en los datos numéricos. Verifica que todos los campos tengan números válidos.");
    }
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(
        UsuarioConectado.getIdUsuario(),
        APLICACION,
        "Modificar Datos cuentas_bancarias"
    );
   
             
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
      
// Registrar en bitácora 
      int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar Cuenta");

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
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\AyudasBancos.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\AyudasBancos.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTipoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoCuentaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //actualizar mishelloeiza 9959-23-3457
        llenadoDeTablas(); // Esto recarga los datos en la tabla
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporte1ActionPerformed
        // TODO add your handling code here:
               // TODO add your handling code here:
        
          Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/reporte/banco/ReporteCBANC.jrxml");
            
            print = JasperFillManager.fillReport(report, p, connectio);
            
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
        }
        
        
        
        
    }//GEN-LAST:event_btnReporte1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaCuentas_bancarias;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtMoneda;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtTipoCuenta;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private int obtenerIdCuentaSeleccionada() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void limpiarFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class bancoDao {

        private static boolean existeBanco(int idBanco) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public bancoDao() {
        }
    }
}
