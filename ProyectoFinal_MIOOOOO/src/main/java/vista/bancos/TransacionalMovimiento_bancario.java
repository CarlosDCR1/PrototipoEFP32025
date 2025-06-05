/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.bancos;
import Controlador.seguridad.UsuarioConectado;  // Para obtener usuario actual
import Modelo.seguridad.UsuarioDAO;               // Para manejar la lógica de usuario (ajusta el paquete si es otro)
import Controlador.seguridad.permisos;          // La clase que representa los permisos del usuario (ajusta el paquete)

//import Controlador.bancos.tasa_cambio_diario;
import vista.seguridad.*;
import Modelo.bancos.tipo_operacion_bancariaDAO;
import Controlador.bancos.tipo_operacion_bancaria;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Modelo.bancos.tasa_cambio_diarioDAO;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Controlador.bancos.movimiento_bancario;
import Controlador.seguridad.permisos;
import Modelo.Conexion;
import Modelo.bancos.MovimientoBancarioDAO;
import Modelo.seguridad.UsuarioDAO;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * Made by Ruddyard Castro
 */
//Modificado por Anderson Rodriguez
public class TransacionalMovimiento_bancario extends javax.swing.JInternalFrame {

    int APLICACION = 105; // Ajustar según corresponda
       private Connection connectio;
    // 🔒 Variables para permisos
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;

private permisos permisosUsuarioActual; 
    private MovimientoBancarioDAO movimientoDAO = new MovimientoBancarioDAO();
    private float saldoAcumulado = 0.0f;

    public void llenadoDeCombos() {
       
        cboTipoSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12));
        cboTipoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione", "Acreedor", "Deudor"}));

        // Listener para actualizar saldo cuando cambia el tipo
        cboTipoSaldo.addActionListener(e -> {
        if (cboTipoSaldo.getSelectedIndex() > 0 && !txtMonto.getText().isEmpty() && !txtIdCuenta.getText().isEmpty()) {
            try {
                int idCuenta = Integer.parseInt(txtIdCuenta.getText());
                float saldoActual = obtenerSaldoCuenta(idCuenta);
                float monto = Float.parseFloat(txtMonto.getText());
                String tipo = cboTipoSaldo.getSelectedItem().toString();

                float saldoActualizado = realizarOperacion(saldoActual, monto, tipo);
                txtSaldo.setText(String.format("%.2f", saldoActual));
                txtSaldoActualizado.setText(String.format("%.2f", saldoActualizado));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        });


        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12));
        txtSaldo.setEditable(false);
        txtSaldo.setText("0.00");
    }
    
    private float realizarOperacion(float saldoActual, float montoOperacion, String tipoOperacion) {
        if ("Acreedor".equals(tipoOperacion)) {
            return saldoActual + montoOperacion;
        } else if ("Deudor".equals(tipoOperacion)) {
            return saldoActual - montoOperacion;
        }
        return saldoActual; // Por defecto no cambia si no hay tipo válido
    }
    

    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Movimiento");
        modelo.addColumn("ID Cuenta");
        modelo.addColumn("Fecha");
        modelo.addColumn("tipo Saldo");
        modelo.addColumn("Monto");
        modelo.addColumn("Saldo Actualizado");
        List<movimiento_bancario> movimientos = movimientoDAO.select();
        tblMovimientos.setModel(modelo);

        String[] dato = new String[6];
        for (movimiento_bancario movimiento : movimientos) {
            dato[0] = String.valueOf(movimiento.getId_movimiento_bancario());
            dato[1] = String.valueOf(movimiento.getId_cuenta());
            dato[2] = movimiento.getFecha().toString();
            dato[3] = movimiento.getTipoSaldo();
            dato[4] = String.valueOf(movimiento.getMonto());
            dato[5] = String.valueOf(movimiento.getSaldoActualizado());
            modelo.addRow(dato);
        }
    }

    public void buscarMovimiento() {
    try {
        if (txtbuscado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        movimiento_bancario movimientoConsulta = new movimiento_bancario();
        movimientoConsulta.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
        movimientoConsulta = movimientoDAO.query(movimientoConsulta);

        if (movimientoConsulta != null) {
            txtIdCuenta.setText(String.valueOf(movimientoConsulta.getId_cuenta()));

            // Formatear la fecha correctamente antes de mostrarla
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String fechaFormateada = movimientoConsulta.getFecha().format(formatter);
            txtFecha.setText(fechaFormateada);
            txtFecha.setForeground(Color.BLACK);

            cboTipoSaldo.setSelectedItem(movimientoConsulta.getTipoSaldo());

            // Mostrar monto y saldo actualizado
            txtMonto.setText(String.format("%.2f", movimientoConsulta.getMonto()));
            txtSaldo.setText(String.format("%.2f", obtenerSaldoCuenta(movimientoConsulta.getId_cuenta())));
            txtSaldoActualizado.setText(String.format("%.2f", movimientoConsulta.getSaldoActualizado()));

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Movimiento");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún movimiento",
                    "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public TransacionalMovimiento_bancario() {
        
        initComponents();

        // Configuración del campo de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Establecer texto guía con fecha actual
        txtFecha.setText(formatter.format(LocalDateTime.now()));
        txtFecha.setForeground(Color.GRAY);

        // Listener para manejar el placeholder dinámico
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtFecha.getForeground().equals(Color.GRAY)) {
                    txtFecha.setText("");
                    txtFecha.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtFecha.getText().isEmpty()) {
                    txtFecha.setText(formatter.format(LocalDateTime.now()));
                    txtFecha.setForeground(Color.GRAY);
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
    private float obtenerSaldoCuenta(int idCuenta) {
        Modelo.bancos.cuentas_bancariasDAO dao = new Modelo.bancos.cuentas_bancariasDAO();
        Controlador.bancos.cuentas_bancarias cuenta = new Controlador.bancos.cuentas_bancarias();
        cuenta.setId_cuenta(idCuenta);
        cuenta = dao.query(cuenta);
        return (float) cuenta.getSaldo();
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
        txtIdCuenta = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        txtFecha = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAyudasTasaDecambioDiario = new javax.swing.JButton();
        btnreporteTasaDecambioDiario = new javax.swing.JButton();
        cboTipoSaldo = new javax.swing.JComboBox<>();
        txtTipoSaldo = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        txtTipoSaldo1 = new javax.swing.JLabel();
        txtTipoSaldo2 = new javax.swing.JLabel();
        txtSaldoActualizado = new javax.swing.JTextField();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Movimineto bancario");
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
        label1.setText("Movimientos Bancarias");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Id_cuenta");

        txtIdCuenta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdCuenta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tblMovimientos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMovimientos);
        if (tblMovimientos.getColumnModel().getColumnCount() > 0) {
            tblMovimientos.getColumnModel().getColumn(0).setResizable(false);
        }

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Fecha ");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

        btnAyudasTasaDecambioDiario.setText("Ayuda");
        btnAyudasTasaDecambioDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudasTasaDecambioDiarioActionPerformed(evt);
            }
        });

        btnreporteTasaDecambioDiario.setText("Reporte");
        btnreporteTasaDecambioDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreporteTasaDecambioDiarioActionPerformed(evt);
            }
        });

        cboTipoSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cboTipoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoSaldoActionPerformed(evt);
            }
        });

        txtTipoSaldo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtTipoSaldo.setText("Tipo Saldo:");

        lblSaldo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lblSaldo.setText("Saldo cuenta:");

        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtSaldo.setEnabled(false);
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        txtTipoSaldo1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtTipoSaldo1.setText("Monto:");

        txtTipoSaldo2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtTipoSaldo2.setText("Saldo actualizado");

        txtSaldoActualizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActualizadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(358, 358, 358)
                                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnAyudasTasaDecambioDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnreporteTasaDecambioDiario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 14, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3)
                                    .addComponent(label5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdCuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTipoSaldo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboTipoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblSaldo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtTipoSaldo2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSaldoActualizado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTipoSaldo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(490, 490, 490))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(txtIdCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSaldo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoSaldo1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTipoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoSaldo))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTipoSaldo2)
                                    .addComponent(txtSaldoActualizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnModificar))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar)
                                    .addComponent(btnLimpiar))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAyudasTasaDecambioDiario)
                                    .addComponent(btnreporteTasaDecambioDiario)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    try {
        int idMovimiento = Integer.parseInt(txtbuscado.getText());
        movimiento_bancario movimientoEliminar = new movimiento_bancario();
        movimientoEliminar.setId_movimiento_bancario(idMovimiento);
        // Obtener datos del movimiento
        movimientoEliminar = movimientoDAO.query(movimientoEliminar); 

        if (movimientoEliminar != null) {
            // Revertir el saldo en cuentas_bancarias
            int idCuenta = movimientoEliminar.getId_cuenta();
            float saldoActual = obtenerSaldoCuenta(idCuenta);
            float nuevoSaldo;

            if ("Acreedor".equals(movimientoEliminar.getTipoSaldo())) {
                nuevoSaldo = saldoActual - movimientoEliminar.getMonto();
            } else {
                nuevoSaldo = saldoActual + movimientoEliminar.getMonto();
            }

            Modelo.bancos.cuentas_bancariasDAO cuentaDAO = new Modelo.bancos.cuentas_bancariasDAO();
            Controlador.bancos.cuentas_bancarias cuenta = new Controlador.bancos.cuentas_bancarias();
            cuenta.setId_cuenta(idCuenta);
            cuenta = cuentaDAO.query(cuenta);
            cuenta.setSaldo(nuevoSaldo);
            cuentaDAO.update(cuenta);

            // Eliminar movimiento
            movimientoDAO.delete(movimientoEliminar);
            llenadoDeTablas();

            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Movimiento");

            JOptionPane.showMessageDialog(this, "Movimiento eliminado y saldo revertido correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Movimiento no encontrado.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }


        /*movimiento_bancario movimientoEliminar = new movimiento_bancario();
        movimientoEliminar.setId_movimiento_bancario(Integer.parseInt(txtbuscado.getText()));
        movimientoDAO.delete(movimientoEliminar);
        llenadoDeTablas();

        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Eliminar Movimiento");*/
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        try {
            // Validaciones
            if (txtIdCuenta.getText().trim().isEmpty()
                    || txtMonto.getText().trim().isEmpty()
                    || cboTipoSaldo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo movimiento
            movimiento_bancario nuevoMovimiento = new movimiento_bancario();
            nuevoMovimiento.setId_cuenta(Integer.parseInt(txtIdCuenta.getText()));
            nuevoMovimiento.setTipoSaldo(cboTipoSaldo.getSelectedItem().toString());
            nuevoMovimiento.setMonto(Float.parseFloat(txtMonto.getText()));

            // Configurar fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fecha = txtFecha.getForeground().equals(Color.GRAY)
                    ? LocalDateTime.now()
                    : LocalDateTime.parse(txtFecha.getText(), formatter);
            nuevoMovimiento.setFecha(fecha);
            
            // Obtener saldo actual y calcular saldo actualizado
            float saldoActual = obtenerSaldoCuenta(nuevoMovimiento.getId_cuenta());
            float saldoActualizado = realizarOperacion(saldoActual, nuevoMovimiento.getMonto(), nuevoMovimiento.getTipoSaldo());
            nuevoMovimiento.setSaldoActualizado(saldoActualizado);


            // Insertar en la base de datos
            movimientoDAO.insert(nuevoMovimiento);
            
            // Actualizar saldo en cuentas_bancarias
            Modelo.bancos.cuentas_bancariasDAO cuentaDAO = new Modelo.bancos.cuentas_bancariasDAO();
            Controlador.bancos.cuentas_bancarias cuenta = new Controlador.bancos.cuentas_bancarias();
            cuenta.setId_cuenta(nuevoMovimiento.getId_cuenta());
            cuenta = cuentaDAO.query(cuenta);
            cuenta.setSaldo(saldoActualizado);
            cuentaDAO.update(cuenta);


            // Actualizar interfaz
            llenadoDeTablas();

            // Registrar en bitácora
            Bitacora bitacoraRegistro = new Bitacora();
            bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Registrar Movimiento");

            JOptionPane.showMessageDialog(this, "Movimiento registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarMovimiento();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
    try {
        if (txtbuscado.getText().trim().isEmpty()
                || txtMonto.getText().trim().isEmpty()
                || cboTipoSaldo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idMovimiento = Integer.parseInt(txtbuscado.getText());

        // Obtener el movimiento anterior
        movimiento_bancario movimientoConsulta = new movimiento_bancario();
        movimientoConsulta.setId_movimiento_bancario(idMovimiento);
        movimiento_bancario movimientoAnterior = movimientoDAO.query(movimientoConsulta);

        if (movimientoAnterior == null) {
            JOptionPane.showMessageDialog(this, "Movimiento no encontrado.");
            return;
        }

        // Revertir saldo anterior
        int idCuenta = Integer.parseInt(txtIdCuenta.getText());
        float saldoActual = obtenerSaldoCuenta(idCuenta);
        float saldoRevertido = "Acreedor".equals(movimientoAnterior.getTipoSaldo())
                ? saldoActual - movimientoAnterior.getMonto()
                : saldoActual + movimientoAnterior.getMonto();

        // Aplicar nuevo movimiento
        float nuevoMonto = Float.parseFloat(txtMonto.getText());
        String nuevoTipo = cboTipoSaldo.getSelectedItem().toString();
        float nuevoSaldo = realizarOperacion(saldoRevertido, nuevoMonto, nuevoTipo);

        // Actualizar movimiento
        movimiento_bancario movimientoActualizar = new movimiento_bancario();
        movimientoActualizar.setId_movimiento_bancario(idMovimiento);
        movimientoActualizar.setId_cuenta(idCuenta);
        movimientoActualizar.setTipoSaldo(nuevoTipo);
        movimientoActualizar.setMonto(nuevoMonto);
        movimientoActualizar.setSaldoActualizado(nuevoSaldo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        movimientoActualizar.setFecha(LocalDateTime.parse(txtFecha.getText(), formatter));

        movimientoDAO.update(movimientoActualizar);

        // Actualizar cuenta
        Modelo.bancos.cuentas_bancariasDAO cuentaDAO = new Modelo.bancos.cuentas_bancariasDAO();
        Controlador.bancos.cuentas_bancarias cuenta = new Controlador.bancos.cuentas_bancarias();
        cuenta.setId_cuenta(idCuenta);
        cuenta = cuentaDAO.query(cuenta);
        cuenta.setSaldo(nuevoSaldo);
        cuentaDAO.update(cuenta);

        llenadoDeTablas();
        JOptionPane.showMessageDialog(this, "Movimiento modificado correctamente.");

        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Movimiento");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    txtIdCuenta.setText("");
    txtFecha.setText("");
    txtMonto.setText("");
    txtSaldo.setText("0.00");
    txtSaldoActualizado.setText("");
    txtbuscado.setText("");
    cboTipoSaldo.setSelectedIndex(0);
    saldoAcumulado = 0.0f;

    // Restablecer fecha con formato y color gris
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    txtFecha.setText(formatter.format(LocalDateTime.now()));
    txtFecha.setForeground(Color.GRAY);

    // Registrar en bitácora
    Bitacora bitacoraRegistro = new Bitacora();
    bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Limpieza de campos");

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed
    /*
     // TODO add your handling code here:
        MantenimientoAula ventana = new MantenimientoAula();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
     */
    private void btnAyudasTasaDecambioDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudasTasaDecambioDiarioActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudasMovimientoBancario.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudasMovimientoBancario.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAyudasTasaDecambioDiarioActionPerformed
   
    private void btnreporteTasaDecambioDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreporteTasaDecambioDiarioActionPerformed
               // TODO add your handling code here:
        
          Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/reporte/banco/ReporteMovimiento.jrxml");
            
            print = JasperFillManager.fillReport(report, p, connectio);
            
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
        }
        


    }//GEN-LAST:event_btnreporteTasaDecambioDiarioActionPerformed

    private void cboTipoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoSaldoActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

    private void txtSaldoActualizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActualizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActualizadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyudasTasaDecambioDiario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnreporteTasaDecambioDiario;
    private javax.swing.JComboBox<String> cboTipoSaldo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdCuenta;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSaldoActualizado;
    private javax.swing.JLabel txtTipoSaldo;
    private javax.swing.JLabel txtTipoSaldo1;
    private javax.swing.JLabel txtTipoSaldo2;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
