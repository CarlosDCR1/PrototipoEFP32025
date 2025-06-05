/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ventas_cxc;
import Modelo.ventas_cxc.ClientesDAO;
import Controlador.ventas_cxc.Clientes;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.RelPerfApl;
import Controlador.seguridad.RelPerfUsu;
import Controlador.seguridad.UsuarioConectado;
import Modelo.Conexion;
import Modelo.seguridad.RelPerfAplDAO;
import Modelo.seguridad.RelPerfUsuDAO;
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
public class MantenimientoClientes extends javax.swing.JInternalFrame {
    
    final int APLICACION=330;
 public void llenadoDeCombos() {
        ClientesDAO clienteDAO = new ClientesDAO();
        List<Clientes> salon = clienteDAO.select();
 /*       cbox_empleado.addItem("Seleccione una opción");
        for (int i = 0; i < salon.size(); i++) {
            cbox_empleado.addItem(salon.get(i).getNombre_cliente());
     
        }
    */
 
 }
  
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
         modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Limite_C");
        modelo.addColumn("Dias_C");
        modelo.addColumn("Estatus");
        modelo.addColumn("Saldo_A");
        
        
        
        
        ClientesDAO clientesDAO = new ClientesDAO();
        List<Clientes> cliente = clientesDAO.select();
        
        tablaSedes.setModel(modelo);
        String[] dato = new String[10];
        for (int i = 0; i < cliente.size(); i++) {
            dato[0] = Integer.toString(cliente.get(i).getId_cliente());
            dato[1] = cliente.get(i).getNombre_cliente();
            dato[2] = cliente.get(i).getApellido_cliente();
            dato[3] = cliente.get(i).getDireccion_cliente();
            dato[4] = cliente.get(i).getTelefono_cliente();
            dato[5] = cliente.get(i).getEmaill_cliente();
            dato[6] = Double.toString(cliente.get(i).getLimite_credito_CLE());
            dato[7] = Integer.toString(cliente.get(i).getDias_credito_CLE());
            dato[8] = cliente.get(i).getEstatus_cliente();
            dato[9] = Double.toString(cliente.get(i).getSaldo_actual_CLE());
            
            
            modelo.addRow(dato);
        }
    }

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
    public void buscarPerfil() {
        Clientes clienteAConsultar = new Clientes();
        ClientesDAO clienteDAO = new ClientesDAO();
        clienteAConsultar.setId_cliente(Integer.parseInt(txtbuscado.getText()));
        clienteAConsultar = clienteDAO.query(clienteAConsultar);
        
        
       txtNombre.setText(clienteAConsultar.getNombre_cliente());
        txtApellido.setText(clienteAConsultar.getApellido_cliente());
        txtDireccion.setText(clienteAConsultar.getDireccion_cliente());
        txtTelefono.setText(clienteAConsultar.getTelefono_cliente());
        txtEmail.setText(clienteAConsultar.getEmaill_cliente());
        txtLMCredito.setText(Double.toString(clienteAConsultar.getLimite_credito_CLE()));
        txtDCreditos.setText(Integer.toString(clienteAConsultar.getDias_credito_CLE()));
        txtEstatus.setText(clienteAConsultar.getEstatus_cliente());
         txtASaldo.setText(Double.toString(clienteAConsultar.getSaldo_actual_CLE()));
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Consulta Datos clientes");
    }
    public void habilitarEliminar(boolean habilitado) {
        btnEliminar.setEnabled(habilitado);
    }
    
    public void habilitarRegistrar(boolean habilitado) {
        btnRegistrar.setEnabled(habilitado);
    }
    
    public void habilitarBuscar(boolean habilitado) {
        btnBuscar.setEnabled(habilitado);
    }
    
    public void habilitarModificar(boolean habilitado) {
        btnModificar.setEnabled(habilitado);
    }
    public void habilitarReportes(boolean habilitado) {
        reportes.setEnabled(habilitado);
    }

    public MantenimientoClientes() {
        initComponents();
        llenadoDeTablas();
         llenadoDeCombos();
         seguridad_botones();
        //se quito el llena combos al no contar con empleados
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
        txtApellido = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSedes = new javax.swing.JTable();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        label8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        label7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        label9 = new javax.swing.JLabel();
        txtLMCredito = new javax.swing.JTextField();
        txtASaldo = new javax.swing.JTextField();
        label10 = new javax.swing.JLabel();
        label11 = new javax.swing.JLabel();
        txtEstatus = new javax.swing.JTextField();
        txtDCreditos = new javax.swing.JTextField();
        label12 = new javax.swing.JLabel();
        reportes = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Clientes");
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
        label1.setText("Clientes");

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Apellido");

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

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
                "ID Vendedor", "ID Empleado", "Correo", "Telefono", "Direccion", "Porcentaje", "Comision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaSedes);

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton1.setText("jButton1");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Direccion");

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Telefono");

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Email");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Limite Credito");

        txtLMCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtLMCredito.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtASaldo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtASaldo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("Saldo Actual");

        label11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label11.setText("Estatus ");

        txtEstatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEstatus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        txtDCreditos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDCreditos.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label12.setText("Dias Credito");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(label3)
                                                .addGap(38, 38, 38))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(label8)
                                                .addGap(31, 31, 31)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                            .addComponent(txtDireccion))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label7)
                                            .addComponent(label9)
                                            .addComponent(label6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                            .addComponent(txtLMCredito)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label11)
                                            .addComponent(label10)
                                            .addComponent(label12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDCreditos)
                                            .addComponent(txtEstatus)
                                            .addComponent(txtASaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(294, 294, 294))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(476, 476, 476))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(reportes)
                        .addGap(48, 434, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3)))
                            .addComponent(lb))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLMCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtASaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(reportes))
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
       ClientesDAO clienteDAO = new ClientesDAO();
        Clientes clienteAEliminar = new Clientes();
        clienteAEliminar.setId_cliente(Integer.parseInt(txtbuscado.getText()));
        clienteDAO.delete(clienteAEliminar);
        llenadoDeTablas();
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Borrar Datos clientes");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        ClientesDAO clienteDAO = new ClientesDAO();
        Clientes clienteAInsertar = new Clientes();
       
        clienteAInsertar.setNombre_cliente(txtNombre.getText());
        clienteAInsertar.setApellido_cliente(txtApellido.getText());
        clienteAInsertar.setDireccion_cliente(txtDireccion.getText());
         clienteAInsertar.setTelefono_cliente(txtTelefono.getText());
         clienteAInsertar.setEmaill_cliente(txtEmail.getText());
      clienteAInsertar.setLimite_credito_CLE(Double.parseDouble(txtLMCredito.getText()));
       clienteAInsertar.setDias_credito_CLE(Integer.parseInt(txtDCreditos.getText()));
        clienteAInsertar.setEstatus_cliente(txtEstatus.getText());
        clienteAInsertar.setSaldo_actual_CLE(Double.parseDouble(txtASaldo.getText()));
        
        clienteDAO.insert(clienteAInsertar);
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Ingreso Datos clientes");
        llenadoDeTablas();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarPerfil();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        // TODO add your handling code here:
        ClientesDAO clienteDAO = new ClientesDAO();
        Clientes clienteAActualizar = new Clientes();
        clienteAActualizar.setId_cliente(Integer.parseInt(txtbuscado.getText()));
        clienteAActualizar.setNombre_cliente(txtNombre.getText());
        clienteAActualizar.setApellido_cliente(txtApellido.getText());
        clienteAActualizar.setDireccion_cliente(txtDireccion.getText());
         clienteAActualizar.setTelefono_cliente(txtTelefono.getText());
         clienteAActualizar.setEmaill_cliente(txtEmail.getText());
      clienteAActualizar.setLimite_credito_CLE(Double.parseDouble(txtLMCredito.getText()));
       clienteAActualizar.setDias_credito_CLE(Integer.parseInt(txtDCreditos.getText()));
        clienteAActualizar.setEstatus_cliente(txtEstatus.getText());
        clienteAActualizar.setSaldo_actual_CLE(Double.parseDouble(txtASaldo.getText()));
        clienteDAO.update(clienteAActualizar);
        llenadoDeTablas();
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Actualizacion Datos Clientes");
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        //cbox_empleado.setSelectedIndex(0);
        txtApellido.setText("");
        txtDireccion.setText("");
        txtbuscado.setText("");
        txtNombre.setText("");
        txtTelefono.setText(" ");
        txtASaldo.setText("");
        txtDCreditos.setText("");
        txtEmail.setText("");
        txtEstatus.setText("");
        txtLMCredito.setText("");
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);

        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Actualizacion Datos Clientes");
        
        
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
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Ayuda Clientes");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesActionPerformed
        // TODO add your handling code here:
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            Connection connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/Reportes_VentasCC/reporteclientes.jrxml");
            
            print = JasperFillManager.fillReport(report, p, connectio);
            
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
        }
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Reporte Clientes");
        
        
    }//GEN-LAST:event_reportesActionPerformed


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
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JButton reportes;
    private javax.swing.JTable tablaSedes;
    private javax.swing.JTextField txtASaldo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDCreditos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstatus;
    private javax.swing.JTextField txtLMCredito;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
