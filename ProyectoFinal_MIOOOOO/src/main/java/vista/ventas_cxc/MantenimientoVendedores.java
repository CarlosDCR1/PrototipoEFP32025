/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ventas_cxc;

import Controlador.seguridad.Bitacora;
import Controlador.seguridad.RelPerfApl;
import Controlador.seguridad.RelPerfUsu;
import Controlador.seguridad.UsuarioConectado;
import Modelo.ventas_cxc.VendedoresDAO;
import Controlador.ventas_cxc.Vendedores;
import Modelo.Conexion;
import Modelo.seguridad.RelPerfAplDAO;
import Modelo.seguridad.RelPerfUsuDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
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
 * @author visitante
 */
public class MantenimientoVendedores extends javax.swing.JInternalFrame {
int APLICACION=340;
    public void llenadoDeCombos() {
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        List<Vendedores> secciones = vendedoresDAO.select();
       /* cbox_empleado.addItem("Seleccione una opción");
        for (int i = 0; i < secciones.size(); i++) {
            cbox_empleado.addItem(secciones.get(i).getNombre_vendedor());
        }
    */
    }

    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel(); //estamos creando el modelo, un grid
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Comision");
        modelo.addColumn("Estatus");
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        List<Vendedores> vendedores = vendedoresDAO.select(); //vendedores=alumnos, alumno=vendedor
        tablaSedes.setModel(modelo);
        String[] dato = new String[8];
        for (int i = 0; i < vendedores.size(); i++) {
            dato[0] = Integer.toString(vendedores.get(i).getId_vendedor());
            dato[1] = vendedores.get(i).getNombre_vendedor();
            dato[2] = vendedores.get(i).getApellido_vendedor();
            dato[3] = vendedores.get(i).getDireccion_vendedor();
            dato[4] = vendedores.get(i).getTelefono_vendedor();
            dato[5] = vendedores.get(i).getEmail_vendedor();
            dato[6] = vendedores.get(i).getComision();
            dato[7] = vendedores.get(i).getEstatus_vendedor();
            //System.out.println("vendedor:" + vendedores);
            modelo.addRow(dato);
        }
    }
//28/04 en proceso 
    
     public void seguridad_botones(){
     RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
    List<RelPerfUsu> productos_ls = relPerfUsuDAO.select(); 
    
    RelPerfAplDAO relPerfAplDAO = new RelPerfAplDAO();
    List<RelPerfApl> ApliUsu = relPerfAplDAO.select(); 
   
        
            int idusuarioC = UsuarioConectado.getIdUsuario();
            String eli;
            String mod;
             String reg;
             String rep;
             String bus;
            if (idusuarioC != 0) {
                // Buscar el ID de la aplicación seleccionada
                for (RelPerfUsu app : productos_ls) {
                    //valida si el idUsuario de la BDD es igual al usiario conectado al sistema
                    if (app.getUsuario_codigo()==(idusuarioC)) {
                        //si esto es verdadero obtendentremos el perfil que esta vinculado con ese codigo de usuario
                        int Idperfil = app.getPerfil_codigo(); //se declara la variable de idperfil                                                                                                       
                     for (RelPerfApl app2 : ApliUsu) {//recorrera la tabla RelperfApli
                        int Idaplicacion = app2.getAplicacion_codigo(); 
                        //si el PC de la BDD es identico a IDperfil obtenido y el CA de la BDD es identico al IDaplicacion obtenido
                           if (app2.getPerfil_codigo()==(Idperfil)&&(app2.getAplicacion_codigo()==Idaplicacion)) {
                      //obtendra los valores de los siguientes campos
                        eli = app2.getEliminar_rpa();
                        mod = app2.getActualizar_rpa();
                        reg = app2.getInsertar_rpa();
                        rep = app2.getImprimir_rpa();
                        bus = app2.getConsultar_rpa();
                       
                          
                       //validacion para habilitar los botones
                       if(Idaplicacion==APLICACION){
                        if("1".equals(reg)){
            habilitarRegistrar(true);
        } else {
          habilitarRegistrar(false); 
        }
        if("1".equals(eli)){
            habilitarEliminar(true);
        } else {
            habilitarEliminar(false);
        }
        if("1".equals(mod)){
            habilitarModificar(true);
        } else {
            habilitarModificar(false);
        }
        if("1".equals(rep)){
            habilitarReportes(true);
        } else {
           habilitarReportes(false);
        }
        if("1".equals(bus)){
            habilitarBuscar(true);
        } else {
           habilitarBuscar(false);
        }
        }
                         
                      
                    
                    }
                   }
                       
                    }   
                }   
             
            }
    }
    public void buscarVendedor() {
         Vendedores vendedorAConsultar = new Vendedores();
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        vendedorAConsultar.setId_vendedor(Integer.parseInt(txtbuscado.getText()));
        vendedorAConsultar = vendedoresDAO.query(vendedorAConsultar);
        txtNombre.setText(vendedorAConsultar.getNombre_vendedor());
        txtApellido.setText(vendedorAConsultar.getApellido_vendedor());
        txtDireccion.setText(vendedorAConsultar.getDireccion_vendedor());
        txtTelefono.setText(vendedorAConsultar.getTelefono_vendedor());
        txtEmail.setText(vendedorAConsultar.getEmail_vendedor());
        txtComision.setText(vendedorAConsultar.getComision());
        txtEstatus.setText(vendedorAConsultar.getEstatus_vendedor());
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Buscar Datos Vendedores");
        
    }
    public void habilitarEliminar(boolean habilitado) {
        btnEliminar.setEnabled(habilitado);
    }
    
    public void habilitarRegistrar(boolean habilitado) {
        btnRegistrar.setEnabled(habilitado);
    }
    
    public void habilitarReportes(boolean habilitado) {
        reportes.setEnabled(habilitado);
    }
     public void habilitarBuscar(boolean habilitado) {
       btnBuscar.setEnabled(habilitado);
    }
    
    public void habilitarModificar(boolean habilitado) {
        btnModificar.setEnabled(habilitado);
    }

    public MantenimientoVendedores() {
        initComponents();
        llenadoDeTablas();
        llenadoDeCombos();
        seguridad_botones();
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
        txtTelefono = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSedes = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        txtnombre = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        t = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtComision = new javax.swing.JTextField();
        txtEstatus = new javax.swing.JTextField();
        reportes = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Vendedores");
        setVisible(true);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.setEnabled(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("Vendedores");

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Direccion");

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaSedes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaSedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sede", "Nombre", "Estatus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaSedes);

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtnombre.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtnombre.setText("Nombre");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        t.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        t.setText("Apellido");

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Telefono");

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Email");

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Comisión");

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Estatus");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtComision.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtComision.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtEstatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEstatus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        reportes.setText("Reportes");
        reportes.setEnabled(false);
        reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre)
                            .addComponent(t)
                            .addComponent(label3)
                            .addComponent(label5)
                            .addComponent(label6)
                            .addComponent(label7)
                            .addComponent(label8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(294, 294, 294))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reportes)))
                .addGap(477, 477, 477))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnombre)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label8)
                            .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(reportes))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        Vendedores vendedorAEliminar = new Vendedores();
        vendedorAEliminar.setId_vendedor(Integer.parseInt(txtbuscado.getText()));
        vendedoresDAO.delete(vendedorAEliminar);
        llenadoDeTablas();
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Eliminar Datos Vendedor");
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        Vendedores vendedorAInsertar = new Vendedores();
        vendedorAInsertar.setNombre_vendedor(txtNombre.getText());
        vendedorAInsertar.setApellido_vendedor(txtApellido.getText());
        vendedorAInsertar.setDireccion_vendedor(txtDireccion.getText());
        vendedorAInsertar.setTelefono_vendedor(txtTelefono.getText());
        vendedorAInsertar.setEmail_vendedor(txtEmail.getText());
        vendedorAInsertar.setComision(txtComision.getText());
        vendedorAInsertar.setEstatus_vendedor(txtEstatus.getText());
        
        vendedoresDAO.insert(vendedorAInsertar);
        llenadoDeTablas();
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Registrar Datos Vendedores");
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarVendedor();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
        VendedoresDAO vendedoresDAO = new VendedoresDAO();
        Vendedores vendedorAActualizar = new Vendedores();
        vendedorAActualizar.setId_vendedor(Integer.parseInt(txtbuscado.getText()));
        vendedorAActualizar.setNombre_vendedor(txtNombre.getText());
        vendedorAActualizar.setApellido_vendedor(txtApellido.getText());
        vendedorAActualizar.setDireccion_vendedor(txtDireccion.getText());
        vendedorAActualizar.setTelefono_vendedor(txtTelefono.getText());
        vendedorAActualizar.setEmail_vendedor(txtEmail.getText());
        vendedorAActualizar.setComision(txtComision.getText());
        vendedorAActualizar.setEstatus_vendedor(txtEstatus.getText());
        
        vendedoresDAO.update(vendedorAActualizar);
        llenadoDeTablas();
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Modificar Datos Vendedores");
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        //cbox_empleado.setSelectedIndex(0);
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtComision.setText("");
        txtEstatus.setText("");
        txtbuscado.setText("");
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar Datos Vendedores");
        

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\AyudaVentas.chm")).exists()) {
                Process p = Runtime
                .getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\AyudaVentas.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Ayuda Vendedores");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesActionPerformed
        // TODO add your handling code here:
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            Connection connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/Reportes_VentasCC/reportevendedores.jrxml");
            
            print = JasperFillManager.fillReport(report, p, connectio);
            
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
        }
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Reportes Vendedores");
        
    }//GEN-LAST:event_reportesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JButton reportes;
    private javax.swing.JLabel t;
    private javax.swing.JTable tablaSedes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstatus;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtbuscado;
    private javax.swing.JLabel txtnombre;
    // End of variables declaration//GEN-END:variables
}
