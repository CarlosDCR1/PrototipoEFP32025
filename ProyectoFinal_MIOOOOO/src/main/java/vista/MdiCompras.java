package vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Diseño del MDI realizado por Alisson López
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import vista.compras_cxp.Compras;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.seguridad.MantenimientoUsuario;
import vista.seguridad.MantenimientoPerfiles;
import vista.seguridad.MantenimientoAplicacion;
import vista.seguridad.MantenimientoBitacora;
import vista.seguridad.AplicacionaUsuariosDEF;
import vista.seguridad.MantenimientoRelPerfApl;
import vista.seguridad.MantenimientoRelPerfUsu;
import vista.compras_cxp.MantenimientoProveedores;
import vista.compras_cxp.MantenimientoMetododePago;
//import extras para seguridad hecho por: Pablo Palencia
import Modelo.seguridad.RelPerfUsuDAO;
import Controlador.seguridad.RelPerfUsu;
import Modelo.seguridad.RelPerfAplDAO;
import Controlador.seguridad.RelPerfApl;
import Modelo.seguridad.RelusuaplDAO;
import Controlador.seguridad.Relusuapl;
import java.util.List;

/**
 *
 * @author visitante
 */
public class MdiCompras extends javax.swing.JFrame {

    /**
     * Creates new form MdiGeneral
     */
    final int APLICACION=99;
    //variables "globales" añadidas para validar derechos de aplicacion. by Pablo Palencia
    //toman los derechos de proveedor
    private String eli;
    private String reg;
    private String bus;
    private String mod;
    //toman los derechos de metodo pago
    private String eli2;
    private String reg2;
    private String bus2;
    private String mod2;
    
    public MdiCompras() {
        initComponents();
        filtros();
        setLocationRelativeTo(null);
        this.setExtendedState(MdiCompras.MAXIMIZED_BOTH);
        this.setTitle("MDI Compras");
        initComponents();
        jMenuBar1.setBackground(Color.YELLOW );
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        cerrar();

    }
    
        //Seguridad. Metodo para verificar acceso a mantenimientos mediante filtros, hecho por Pablo Palencia
    public void filtros() {
        //crea objeto relperdusuDao para buscar usuario conectado
            RelPerfUsuDAO relPerfUsuDAO = new RelPerfUsuDAO();
            List<RelPerfUsu> PerfUsu = relPerfUsuDAO.select(); 
    //crea objeto relusuapl para verificar aplicacion y derechos de usuario conectado
            RelusuaplDAO relusuaplDAO = new RelusuaplDAO();
            List<Relusuapl> UsuApl = relusuaplDAO.select();
   //toma el usuario conectado (el que inicia sesion)
            int idusuarioC = UsuarioConectado.getIdUsuario();
            //si existe realiza dos for
                if (idusuarioC != 0) {
                    //For que recorre la lista para encontrar el ID del usuario conectado
                    for (RelPerfUsu app : PerfUsu) {
                        if (app.getUsuario_codigo()==(idusuarioC)) {
                            int Idusuario = app.getUsuario_codigo();
                        
                            //for que recorre lista para encontrar ID aplicacion de usuario conectado
                            for (Relusuapl app2 : UsuApl) {
                                if (app2.getId_usuario()==(Idusuario)) {
                                int Idaplicacion = app2.getId_aplicacion();
                        
                            switch(Idaplicacion) {
                            case 202:  
                                Proveedores.setEnabled(true);
                                //variables globales toman valor de los derechos del usuario
                                eli = app2.getDer_eliminar();
                                mod = app2.getDer_editar();
                                reg = app2.getDer_insertar();
                                bus = app2.getDer_imprimir();
                            break;
                            case 203:  
                                MetodoDePago.setEnabled(true);
                                //variables globales toman valor de los derechos del usuario
                                eli2 = app2.getDer_eliminar();
                                mod2 = app2.getDer_editar();
                                reg2 = app2.getDer_insertar();
                                bus2 = app2.getDer_imprimir();
                            break;
                            case 204:  
                                transCompras.setEnabled(true);
                            break;
                            default:
        
                            }
                    
                            }
                        }
                       
                    }   
                }   
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        panel2 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        MetodoDePago = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panel4 = new java.awt.Panel();
        Proveedores = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        panel5 = new java.awt.Panel();
        Bitacora = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panel10 = new java.awt.Panel();
        Sistema = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panel11 = new java.awt.Panel();
        jLabel6 = new javax.swing.JLabel();
        transCompras = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        panel12 = new java.awt.Panel();
        Ayudabtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1593, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 713, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        panel2.setBackground(new java.awt.Color(255, 255, 102));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Bienvenido a Compras y Cuentas por Pagar");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        panel1.setBackground(new java.awt.Color(255, 255, 102));

        MetodoDePago.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        MetodoDePago.setText("Método de Pago");
        MetodoDePago.setEnabled(false);
        MetodoDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MetodoDePagoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Mantenimiento");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MetodoDePago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        panel4.setBackground(new java.awt.Color(255, 255, 102));

        Proveedores.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        Proveedores.setText("Proveedores");
        Proveedores.setEnabled(false);
        Proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Mantenimiento");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        panel5.setBackground(new java.awt.Color(255, 255, 102));

        Bitacora.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        Bitacora.setText("Consulta Bitacora");
        Bitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BitacoraActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Bitacora");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                        .addComponent(Bitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel10.setBackground(new java.awt.Color(255, 255, 102));

        Sistema.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        Sistema.setText("Salir del Sistema");
        Sistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SistemaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 32)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Sistema");

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(Sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel11.setBackground(new java.awt.Color(255, 255, 102));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Transaccional");

        transCompras.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        transCompras.setText("Compras");
        transCompras.setEnabled(false);
        transCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                        .addComponent(transCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fondo.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\vista\\compras_cxp\\FondoCompras.png"));
        fondo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        panel12.setBackground(new java.awt.Color(255, 255, 102));

        Ayudabtn.setFont(new java.awt.Font("Barlow Condensed ExtraLight", 1, 18)); // NOI18N
        Ayudabtn.setText("Ayuda");
        Ayudabtn.setMaximumSize(new java.awt.Dimension(141, 31));
        Ayudabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AyudabtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(Ayudabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel12Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(Ayudabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jDesktopPane1.setLayer(panel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fondo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                    .addGap(155, 155, 155)))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(63, Short.MAX_VALUE)))
        );

        fondo.getAccessibleContext().setAccessibleParent(transCompras);

        jMenuBar1.setBackground(new java.awt.Color(255, 204, 0));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transComprasActionPerformed
        Compras ventana = new Compras();
        jDesktopPane1.add(ventana);
        ventana.setVisible(true);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    }//GEN-LAST:event_transComprasActionPerformed

    private void AyudabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AyudabtnActionPerformed
        // Ayuda Implementada por Alisson López
        try {
            if ((new File("src\\main\\java\\ayudas\\ayudasComprasyCuentasPorPagar.chm")).exists()) {
                Process p = Runtime
                .getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\ayudasComprasyCuentasPorPagar.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_AyudabtnActionPerformed

    private void SistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SistemaActionPerformed
        // TODO add your handling code here:
        for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
            frame.dispose(); // Cierra cada ventana abierta
        }
        //
        int valor=JOptionPane.showConfirmDialog(this,"¿Està seguro de cerrar?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor==JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto", "Gracias", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_SistemaActionPerformed

    private void BitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BitacoraActionPerformed
        // TODO add your handling code here:
        for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
            frame.dispose();
        }
        MantenimientoBitacora ventana = new MantenimientoBitacora();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation(
            (desktopSize.width - FrameSize.width) / 2,
            (desktopSize.height - FrameSize.height) / 2
        );

        ventana.setVisible(true);
    }//GEN-LAST:event_BitacoraActionPerformed

    private void ProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresActionPerformed
        // TODO add your handling code here:
        MantenimientoProveedores ventana = new MantenimientoProveedores();
        jDesktopPane1.add(ventana);
        ventana.setVisible(true);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        
        //Seguridad.Verifica los derechos a los que tiene acceso. Hecho por Pablo Palencia
        if("1".equals(reg)){
            ventana.habilitarRegistrar(true);
        } else {
            ventana.habilitarRegistrar(false); 
        }
        if("1".equals(eli)){
            ventana.habilitarEliminar(true);
        } else {
            ventana.habilitarEliminar(false);
        }
        if("1".equals(mod)){
            ventana.habilitarModificar(true);
        } else {
            ventana.habilitarModificar(false);
        }
        if("1".equals(bus)){
            ventana.habilitarBuscar(true);
        } else {
            ventana.habilitarBuscar(false);
        }
    }//GEN-LAST:event_ProveedoresActionPerformed

    private void MetodoDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MetodoDePagoActionPerformed
        // TODO add your handling code here:
        MantenimientoMetododePago ventana = new MantenimientoMetododePago();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setVisible(true);
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        
        //Seguridad.Verifica los derechos a los que tiene acceso. Hecho por Pablo Palencia
        if("1".equals(reg2)){
            ventana.habilitarRegistrar(true);
        } else {
            ventana.habilitarRegistrar(false); 
        }
        if("1".equals(eli2)){
            ventana.habilitarEliminar(true);
        } else {
            ventana.habilitarEliminar(false);
        }
        if("1".equals(mod2)){
            ventana.habilitarModificar(true);
        } else {
            ventana.habilitarModificar(false);
        }
        if("1".equals(bus2)){
            ventana.habilitarBuscar(true);
        } else {
            ventana.habilitarBuscar(false);
        }
        
    }//GEN-LAST:event_MetodoDePagoActionPerformed
    public void cerrar(){
        try 
        {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent e) 
                {
                    confirmarSalida();
                }
            });
            this.setVisible(true);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }    
    public void confirmarSalida() 
    {
        int valor=JOptionPane.showConfirmDialog(this,"¿Està seguro de cerrar?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (valor==JOptionPane.YES_OPTION) 
                    {
                        JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto", "Gracias", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MdiCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MdiCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MdiCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MdiCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MdiCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ayudabtn;
    private javax.swing.JButton Bitacora;
    private javax.swing.JButton MetodoDePago;
    private javax.swing.JButton Proveedores;
    private javax.swing.JButton Sistema;
    private javax.swing.JLabel fondo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private java.awt.Panel panel1;
    private java.awt.Panel panel10;
    private java.awt.Panel panel11;
    private java.awt.Panel panel12;
    private java.awt.Panel panel2;
    private java.awt.Panel panel4;
    private java.awt.Panel panel5;
    private javax.swing.JButton transCompras;
    // End of variables declaration//GEN-END:variables
}
