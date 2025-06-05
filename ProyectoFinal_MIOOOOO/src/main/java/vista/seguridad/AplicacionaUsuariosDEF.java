/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.seguridad;
//agregado para tabla relusuapl Hecho por Pablo Palencia
import Modelo.seguridad.RelusuaplDAO; //Pablo Palencia: Para boton asignar
import Controlador.seguridad.Relusuapl; //Pablo Palencia: Para boton asignar

import Modelo.seguridad.UsuarioDAO; 
import Modelo.seguridad.PerfilDAO; //se quitará
import Controlador.seguridad.Usuario;
import Controlador.seguridad.Perfil;//se quitará
import Modelo.seguridad.Aplicacion1DAO; //Pablo Palencia: Para llenado de lista
import Controlador.seguridad.Aplicacion1; //Pablo Palencia: Para llenado de lista
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import vista.seguridad.MantenimientoDerecho;
import Modelo.seguridad.BitacoraDAO; //Kathia Contreras : Para bitacora en asignacion de usuario a apl
import Controlador.seguridad.Bitacora;//Kathia Contreras : Para bitacora en asignacion de usuario a apl
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author visitante
 */
/*
public class AplicacionaUsuarios extends javax.swing.JInternalFrame {
    public void llenadoDeCombos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.select();
       // jComboBox1.addItem("Seleccione una opción");
        for (int i = 0; i < usuarios.size(); i++) {
            //jComboBox1.addItem(usuarios.get(i).getUsername());
            jComboBox1.addItem(String.valueOf(usuarios.get(i).getId_usuario()));
        }
    }*/
//--------------------------------------------------------------------------prueba
    public class AplicacionaUsuariosDEF extends javax.swing.JInternalFrame {
    public void llenadoDeCombos() {
        //DESPLIEGUE DE USUARIOS EN COMBOBOX HECHO POR KATHIA CONTRERAS
        //crea objeto
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        //crea lista llenandola con todos los usuarios mediante select
        List<Usuario> usuarios = usuarioDAO.select();
       //ciclo que llena combobox
        for (int i = 0; i < usuarios.size(); i++) {
            //jComboBox1.addItem(usuarios.get(i).getUsername());
            jComboBox1.addItem(String.valueOf(usuarios.get(i).getId_usuario()));
        }
        
        //Pablo Palencia - 736: Llenado de las aplicaciones disponibles para la lista #1
        Aplicacion1DAO aplicacionDAO = new Aplicacion1DAO();//crea objeto 
        List<Aplicacion1> aplicaciones = aplicacionDAO.select(); //crea una lista
         DefaultListModel<String> modelo = new DefaultListModel<>();//crea un modelo para la lista
        for (Aplicacion1 app : aplicaciones) { //recorre la lista aplicaciones
            modelo.addElement(app.getNombre_aplicacion()); //agrega los nombres de aplicaciones 
        }
        lst1App.setModel(modelo);//manda el modelo a la lista (visualmente)
        
     //HECHO POR KATHIA CONTRERAS - 8246 
     //MANDA ID DE APLICACION SELECCIONADO
     lst2App.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // evitar eventos duplicados y mejora el rendimiento con break.
            String nombreaplisele = lst2App.getSelectedValue();// variable que obtiene el nombre de aplicacion seleccionada
            //verifica que no esté vacía
            if (nombreaplisele != null) {
                // Ciclo que busca el ID de la aplicación seleccionada
                for (Aplicacion1 appli : aplicaciones) {
                    //verifica si el nombre del objeto es igual al nombre seleccionado
                    if (appli.getNombre_aplicacion().equals(nombreaplisele)) {
                        int idAppSeleccionada = appli.getId_aplicacion();
                        System.out.println("ID seleccionado: " + idAppSeleccionada); // Opcional: para debug
                        jTextField2.setText(String.valueOf(idAppSeleccionada)); // Asignar el ID a un campo
                        break;
                    }
                }
            }
        }
    }
        });
    }
    
    public AplicacionaUsuariosDEF() {
        initComponents();
        llenadoDeCombos();
        //HECHO POR KATHIA CONTRERAS OCULTAMIENDO DE DERECHOS
       ins.setVisible(false); 
       impr.setVisible(false); 
       elim.setVisible(false); 
       edit.setVisible(false); 
       
       jLabel8.setVisible(false);
      
       ASIGNAR.setVisible(false);
       
       //agregado extra Pablo Palencia: para ID relusuapl
       jTextField4.setVisible(false);
       jLabel7.setVisible(false);
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst1App = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst2App = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        edit = new javax.swing.JCheckBox();
        ins = new javax.swing.JCheckBox();
        impr = new javax.swing.JCheckBox();
        elim = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ASIGNAR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aplicacion a Usuarios");
        setToolTipText("");
        setVisible(true);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Aplicaciones Disponibles");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Asignar");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Aplicaciones Asignadas");

        lst1App.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst1AppMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lst1App);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Usuario Seleccionado");

        lst2App.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst2AppMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lst2App);

        jButton1.setText("Limpiar Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Quitar");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Editar");

        jButton3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton3.setText("▶▶");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jButton4.setText("☷");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton5.setText("▶");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton6.setText("◀◀");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton7.setText("◀");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        edit.setText("Editar");

        ins.setText("Insertar");

        impr.setText("Imprimir");

        elim.setText("Eliminar");

        jLabel8.setText("Derechos");

        jLabel9.setText("Fecha");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Código Aplicación");

        ASIGNAR.setText("ASIGNAR");
        ASIGNAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASIGNARActionPerformed(evt);
            }
        });

        jLabel7.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel1))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel5)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel6)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(8, 8, 8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jButton1)
                                .addGap(26, 26, 26)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ins)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(elim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(edit)
                                        .addGap(18, 18, 18)
                                        .addComponent(impr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ASIGNAR)))
                                .addGap(48, 48, 48)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ins)
                            .addComponent(elim)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit)
                            .addComponent(impr)
                            .addComponent(ASIGNAR))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         //HECHO POR KATHIA CONTRERAS OCULTAMIENDO DE DERECHOS
       ins.setVisible(true); 
       impr.setVisible(true); 
       elim.setVisible(true); 
       edit.setVisible(true); 
      
       jLabel8.setVisible(true);
     
       ASIGNAR.setVisible(true);
       
       //agregado Pablo Palencia para ID
       jTextField4.setVisible(true);
       jLabel7.setVisible(true);
        /*        for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
            frame.dispose(); 
       }
       MantenimientoDerecho ventana = new MantenimientoDerecho();
       jDesktopPane1.add(ventana);
       Dimension desktopSize = jDesktopPane1.getSize();
       Dimension FrameSize = ventana.getSize();
       ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //Alisson Rocio Abigail López Ortíz
        modelo.remove(lst2App.getSelectedIndex());
       // modelo.addElement(apli); 
        lst2App.setModel(modelo);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        //hecho por Pablo Palencia
          //String id_busca = (String) jComboBox1.getSelectedItem();
          String seleccionado = (String) jComboBox1.getSelectedItem(); //conversion a string
          Usuario usuarioAConsultar = new Usuario(); //crea objeto
          UsuarioDAO usuarioDAO = new UsuarioDAO(); //crea objeto
          usuarioAConsultar.setId_usuario(Integer.valueOf(seleccionado)); //toma el id seleccionado
          usuarioAConsultar = usuarioDAO.query2(usuarioAConsultar); //realiza una busqueda
          jTextField1.setText(usuarioAConsultar.getUsername()); //captura el username y lo manda
           
    }//GEN-LAST:event_jComboBox1ActionPerformed
    //LLENADO DE TABLA UNO POR UNO HECHO POR KATHIA CONTRERAS
    DefaultListModel<String> modelo = new DefaultListModel<>();
    String apli="";
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       //LLENADO DE TABLA UNO POR UNO HECHO POR KATHIA CONTRERAS
        apli= lst1App.getSelectedValue().toString();
        modelo.addElement(apli); 
        lst2App.setModel(modelo);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //llenado de lista2 HECHO POR KATHIA CONTRERAS
        Aplicacion1DAO aplicacionDAO = new Aplicacion1DAO();
        List<Aplicacion1> aplicaciones = aplicacionDAO.select(); 
        DefaultListModel<String> modelo = new DefaultListModel<>(); 

        for (Aplicacion1 app : aplicaciones) {
        modelo.addElement(app.getNombre_aplicacion()); 
        }
        lst2App.setModel(modelo);
        //PENDIENTE VERIFICAR
        /*int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Todas Las Aplicaciones"); */
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //HECHO POR KATHIA CONTRERAS
        DefaultListModel<String> modelo = new DefaultListModel<>();
    
        modelo.clear();
        lst2App.setModel(modelo);
      
       /* int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar Todas Las Aplicaciones");   */ 
   
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         jTextField1.setText(" ");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void lst1AppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst1AppMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lst1AppMouseClicked

    private void lst2AppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst2AppMouseClicked
       
    }//GEN-LAST:event_lst2AppMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Boton para cerrar la ventana, hecho por Pablo Palencia 
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ASIGNARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASIGNARActionPerformed
        //HECHO POR ALISSON LÓPEZ
        if (jTextField3.getText().trim().isEmpty() || lst2App.getSelectedValue() == null || jTextField4.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog( null,"Por favor llene los valores faltantes","Error", 
        JOptionPane.ERROR_MESSAGE);
        }
       else {
            //Hecho por Pablo Palencia 736: Validacion y llenado de datos
            RelusuaplDAO relusuaplDAO = new RelusuaplDAO();//crea objeto 
            Relusuapl relusuaplAInsertar = new Relusuapl();//crea objeto
            relusuaplAInsertar.setId_relusuapl(Integer.valueOf(jTextField4.getText())); //autoincrement?
            relusuaplAInsertar.setId_aplicacion(Integer.valueOf(jTextField2.getText()));//enviamos id ap
            relusuaplAInsertar.setId_usuario(Integer.valueOf((String) jComboBox1.getSelectedItem()));//enviamos id usu
            //los checkbox seleccionados mandan "1", de lo contrario, "0"
            relusuaplAInsertar.setDer_insertar(ins.isSelected() ? "1" : "0");
            relusuaplAInsertar.setDer_editar(edit.isSelected() ? "1" : "0");
            relusuaplAInsertar.setDer_eliminar(elim.isSelected() ? "1" : "0");
            relusuaplAInsertar.setDer_imprimir(impr.isSelected() ? "1" : "0");
            //manda fecha
            relusuaplAInsertar.setFecha_relusuapl(jTextField3.getText());
            //inserta a la tabla
            relusuaplDAO.insert(relusuaplAInsertar);
            JOptionPane.showMessageDialog(this, "Asignacion Exitosa", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
            //Hecho por ALISSON LÓPEZ 7225 : Reinicio del ciclo 
            ins.setVisible(false); 
            impr.setVisible(false); 
            elim.setVisible(false); 
            edit.setVisible(false);      
            jLabel8.setVisible(false);     
            ASIGNAR.setVisible(false);
            jTextField4.setVisible(false);
            jLabel7.setVisible(false);
            
            jTextField1.setText(" ");
            jTextField2.setText(" ");
            jTextField3.setText(" ");
            jTextField4.setText(" ");
            
            lst2App.clearSelection();
        }
    }//GEN-LAST:event_ASIGNARActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ASIGNAR;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox edit;
    private javax.swing.JCheckBox elim;
    private javax.swing.JCheckBox impr;
    private javax.swing.JCheckBox ins;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lst1App;
    private javax.swing.JList<String> lst2App;
    // End of variables declaration//GEN-END:variables
}
