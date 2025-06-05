/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ventas_cxc;

import Controlador.inventarios.productos;
import Modelo.inventarios.ProductosDAO;
import Controlador.seguridad.RelPerfApl;
import Modelo.seguridad.RelPerfAplDAO;
import Modelo.ventas_cxc.ClientesDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import org.jfree.base.log.LogConfiguration;
import java.sql.*;
import Modelo.seguridad.PerfilDAO;
import Controlador.seguridad.Perfil;
import Controlador.ventas_cxc.Clientes;
import Controlador.ventas_cxc.Vendedores;
import Controlador.ventas_cxc.Ventascxc;
import Modelo.ventas_cxc.ClientesDAO;
import Modelo.ventas_cxc.VendedoresDAO;
import Modelo.ventas_cxc.VentascxcDAO;
import java.awt.TextField;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modelo.inventarios.ProductosDAO;
import Modelo.Conexion;
import Controlador.inventarios.productos;
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
public class TransaccionalVentasCC extends javax.swing.JInternalFrame {
int APLICACION=360;

// --------------FUNCIONAMIENTO PANELES DE PRODUCTO (VICTORIN)---------------------------------------------------------------------
public void llenadoDeCombos() {
         
    ProductosDAO productosDAO = new ProductosDAO();
    List<productos> productos_ls = productosDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    DefaultListModel<String> modelo2 = new DefaultListModel<>();
    //Recorre la lista :v
    for (productos app : productos_ls) {
    modelo.addElement(app.getProNombre()); 
}
lstAplicD.setModel(modelo);
lstAplicA.setModel(modelo2);

// Listener para detectar la selección del usuario
lstAplicA.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Evita doble evento
            String nombreAppSeleccionada = lstAplicA.getSelectedValue();
          //exixtencias  -- sofi
        if (!e.getValueIsAdjusting()) {
            mostrarExistencias();
        }    
        //fin existencias
            if (nombreAppSeleccionada != null) {
                // Buscar el ID de la aplicación seleccionada
                for (productos app : productos_ls) {
                    if (app.getProNombre().equals(nombreAppSeleccionada)) {
                        int idAppSeleccionada = app.getProCodigo();
                        double precioProdcuto= app.getProPrecio();
                        System.out.println("ID seleccionado: " + idAppSeleccionada); // Opcional: para debug
                        txtproducto.setText(String.valueOf(idAppSeleccionada)); // Asignar el ID a un campo
                        txtprcioproducto.setText(String.valueOf(precioProdcuto));
                        break;
                    }
                }
            }
        }
    }
    }); 
    }
// -----------------------------------------EL FIN DE VICTOR----------------------------------------------------------------------------


// ----------------------------------CARLITOS----------------------------------------------
public TransaccionalVentasCC() {
        initComponents(); 
        llenadoDeCombos();
        llenadoDeComboC(); 
        llenadoDeComboV();
        actualizarTablaVentas();
        
    }


public void llenadoDeComboC() {
ClientesDAO clientesDAO = new ClientesDAO();
List<Clientes> listClientes = clientesDAO.select();


cboperfil.addItem("Seleccione un cliente");
for (int i = 0; i < listClientes.size(); i++) {
    cboperfil.addItem(listClientes.get(i).getNombre_cliente());
}

cboperfil.addActionListener(e -> {
    if (cboperfil.getSelectedIndex() > 0) { 
        String nombreSeleccionado = cboperfil.getSelectedItem().toString();
        
        // Buscar el cliente por NOMBRE para obtener su ID
        for (Clientes cliente : listClientes) {
            if (cliente.getNombre_cliente().equals(nombreSeleccionado)) {
                
                txtper.setText(String.valueOf(cliente.getId_cliente())); 
                break;
            }
        }
    } else {
        txtper.setText(""); // Limpiar si se selecciona "Seleccione un cliente"
    }
}); 
}
public void llenadoDeComboV() {
    VendedoresDAO vendedoresDAO = new VendedoresDAO();
    List<Vendedores> listVendedoreses = vendedoresDAO.select();


cboperfil1.addItem("Seleccione un Vendedor");
for (int i = 0; i < listVendedoreses.size(); i++) {
    cboperfil1.addItem(listVendedoreses.get(i).getNombre_vendedor());
}

cboperfil1.addActionListener(e -> {
    if (cboperfil1.getSelectedIndex() > 0) { 
        String nombreSeleccionado = cboperfil1.getSelectedItem().toString();
        
        // Buscar el cliente por NOMBRE para obtener su ID
        for (Vendedores vendedores : listVendedoreses) {
            if (vendedores.getNombre_vendedor().equals(nombreSeleccionado)) {
                
                txtper3.setText(String.valueOf(vendedores.getId_vendedor())); 
                break;
            }
        }
    } else {
        txtper3.setText(""); // Limpiar si se selecciona "Seleccione un cliente"
    }
}); 

}
// ----------------------------EL FIN DE CARLITOS -----------------------------------------------

// ------------------------TRANSACCIONAL ISAPRO-------------------
 public void generarVenta() {
    try {
        // Obtener datos de los campos
        int idCliente = Integer.parseInt(txtper.getText());
        int idVendedor = Integer.parseInt(txtper3.getText());
        int idProducto = Integer.parseInt(txtproducto.getText());
        int cantidad = Integer.parseInt(txtper1.getText());
        double proPrecio = Double.parseDouble(txtprcioproducto.getText());
        
       

        // Obtener datos del cliente (incluyendo crédito y saldo)
        ClientesDAO clientesDAO = new ClientesDAO();
        Clientes cliente = clientesDAO.getById(idCliente);
        
        // Obtener datos del producto
        ProductosDAO productosDAO = new ProductosDAO();
        productos producto = productosDAO.getById(idProducto);
        
        VentascxcDAO ventasDAO = new VentascxcDAO();
        Ventascxc ultimaVenta = ventasDAO.UltiVenta(idCliente);
        
        double saldoActual;
        
        // Buscar si existe factura para el cliente
        if (ultimaVenta != null) {
            // Usa el saldo nuevo
            saldoActual = ultimaVenta.getTotal();
        } else {
            // Si no hay factura usa saldo base
            saldoActual = cliente.getSaldo_actual_CLE();
        }
       

       
        double subtotal = (cantidad * proPrecio);
        double total = subtotal + saldoActual;
         double totalVenta = (cantidad * proPrecio) + saldoActual;
        //------------------------------------------------------------------------------------------------------
        double limcredito = cliente.getLimite_credito_CLE();
         if (totalVenta > limcredito) {
            String mensaje = String.format(
                "LÍMITE DE CRÉDITO INSUFICIENTE\n\n" +
                "Cliente: %s %s\n" +
                "Límite actual: $%,.2f\n" +
                "Saldo anterior: $%,.2f\n" +
                "Total venta: $%,.2f\n\n" +
                "Faltan: $%,.2f para completar la operación",
                cliente.getNombre_cliente(),
                cliente.getApellido_cliente(),
                limcredito,
                saldoActual,
                totalVenta,
                (totalVenta - limcredito)
            );

            JOptionPane.showMessageDialog(this,  mensaje, "Límite Excedido",JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        // Crear objeto venta
        Ventascxc venta = new Ventascxc();
        
        // Configurar campos que vienen del cliente
        // Configurar la venta con los métodos CORRECTOS (_CLE)
        venta.setDias_credito(cliente.getDias_credito_CLE());
        venta.setSaldo_actual(saldoActual);
        
        
        
        
        String numGua = String.valueOf(numG);
        venta.setNo_factura(idCliente);
        venta.setNo_venta(numGua);
        venta.setId_vendedor(idVendedor);
        venta.setNombre_cliente(cliente.getNombre_cliente());
        venta.setApellido_cliente(cliente.getApellido_cliente());
        venta.setPro_codigo(idProducto);
        venta.setCantidad(cantidad);
        venta.setProPrecios(proPrecio);
        venta.setProNombre(producto.getProNombre());
        venta.setTotal(total);

        // Insertar en la base de datos
        boolean exito = new VentascxcDAO().insert(venta);
         
    

        if (exito) {
            JOptionPane.showMessageDialog(this, 
                "Venta generada exitosamente\nN° Factura: " + venta.getNo_factura()+"\nN° Venta: " + venta.getNo_venta(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTablaVentas();
         
               /*txtper.setText(" ");
                txtproducto.setText(" ");
                DefaultListModel model = (DefaultListModel)  lstAplicA.getModel();
                model.clear();  
                txtprcioproducto.setText(" ");
                txtper1.setText("");
                txtper1.requestFocus();
                fechav.setText(" ");
                fechav.setText(" ");
                exitxt.setText(" ");
            */
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar la venta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
    
                
    
    }
    
    
    
    
             
    
}
//---------------------------------ACTUALIZA-----------------------------------------------
public void actualizarTablaVentas() {
    DefaultTableModel modelo = (DefaultTableModel) transaccional_VCXC.getModel();
    modelo.setRowCount(0);

    List<Ventascxc> ventas = new VentascxcDAO().select();
    for (Ventascxc venta : ventas) {
        modelo.addRow(new Object[]{
            venta.getNo_factura(),
            venta.getNo_venta(),
            venta.getId_vendedor(),
            venta.getNombre_cliente(),
            venta.getApellido_cliente(),
            venta.getProNombre(),
            venta.getCantidad(),
            venta.getProPrecios(),
            venta.getSaldo_actual(),
            venta.getDias_credito(),
            venta.getTotal(),

        });
    
               
    }

    
    
    
    
    
}

// --------------------------------------FIN DE ISAPRO-------------------------------------------

//-------------VICTOR--------------------------------------------------------------------------------------

public void llenadoperfilesaplicaciones(){
// 1. Obtener todas las aplicaciones disponibles

ProductosDAO prductosDAO = new ProductosDAO();
List<productos> productos_ls = prductosDAO.select();

// 2. Modelos para las listas
DefaultListModel<String> modelo = new DefaultListModel<>(); // Para listAplicD (todas las apps)
DefaultListModel<String> modelo2 = new DefaultListModel<>(); // Para listAplicA (apps del perfil)

// 3. Llenar listAplicD con TODAS las aplicaciones
for (productos aplicacion : productos_ls) {
    modelo.addElement(aplicacion.getProNombre());
}
lstAplicD.setModel(modelo);

// 4. Listener para cuando seleccionen un perfil
cboperfil.addActionListener(e -> {
    // Limpiar modelo2 antes de agregar nuevos elementos
    modelo2.clear();
    
    try {
        // Obtener perfil seleccionado
        String idSelec = cboperfil.getSelectedItem().toString();
        int idSeleccionado = Integer.parseInt(idSelec);
        
        // Obtener relaciones perfil-aplicación
        RelPerfAplDAO relPerfAplDAO = new RelPerfAplDAO();
        List<RelPerfApl> relaciones = relPerfAplDAO.select();
        
        // Filtrar aplicaciones del perfil seleccionado
        for (RelPerfApl relacion : relaciones) {
            if (relacion.getPerfil_codigo() == idSeleccionado) {
                // Buscar la aplicación por ID
                for (productos app : productos_ls) {
                    if (app.getProCodigo()== relacion.getAplicacion_codigo()) {
                        modelo2.addElement(app.getProNombre());
                              
                        break; // Salir del for interno
                    }
                }
            }
        }
        
        lstAplicA.setModel(modelo2);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar Productos: " + ex.getMessage());
    }
});

}
//------------------------ Fin de victor --------------------------------------------------------------

    public void llenarlistaUnoaUno() {
    int indice=0;
    String cadena; 
     
    indice = lstAplicD.getSelectedIndex();
    if (indice != -1) {
        
    cadena = (String) lstAplicD.getSelectedValue();
    DefaultListModel<String> modeloAplA;
    
    if (lstAplicA.getModel() == null) {
        modeloAplA = new DefaultListModel<>();
        lstAplicA.setModel(modeloAplA);
        
    } else {
        
        modeloAplA = (DefaultListModel<String>) lstAplicA.getModel();
                
    }
    modeloAplA.addElement(cadena);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una Aplicacion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Un Producto");    
   
    }
    
    public void llenarlista() {
    ProductosDAO productosDAO = new ProductosDAO();
    List<productos> aplicaciones = productosDAO.select(); 
    DefaultListModel<String> modelo = new DefaultListModel<>(); 
    //Recorre la lista :v
    for (productos app : aplicaciones) {
    modelo.addElement(app.getProNombre());
    
}
lstAplicA.setModel(modelo);
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Asignar Todos Los Productos");    
   
    }
    
    public void vaciarlista() {
 
    DefaultListModel<String> modelo = new DefaultListModel<>();
    
    modelo.clear();
    lstAplicA.setModel(modelo);
      
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar Todo Los Productos");    
   
    }
    
    public void vaciarlistaUnoaUno() {
    
    int indice = lstAplicA.getSelectedIndex();
    if (indice != -1) {
        ((DefaultListModel<String>) lstAplicA.getModel()).remove(indice);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una Aplicacion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
     
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Eliminar un Producto");    
   
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
        btnAsignarT = new javax.swing.JButton();
        btnAsignarU = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        btnEliminarU = new javax.swing.JButton();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        label6 = new javax.swing.JLabel();
        cboperfil = new javax.swing.JComboBox<>();
        txtper = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAplicA = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstAplicD = new javax.swing.JList<>();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        txtproducto = new javax.swing.JTextField();
        label9 = new javax.swing.JLabel();
        label10 = new javax.swing.JLabel();
        label11 = new javax.swing.JLabel();
        label12 = new javax.swing.JLabel();
        label13 = new javax.swing.JLabel();
        txtper1 = new javax.swing.JTextField();
        label14 = new javax.swing.JLabel();
        label15 = new javax.swing.JLabel();
        cboperfil1 = new javax.swing.JComboBox<>();
        txtper3 = new javax.swing.JTextField();
        label16 = new javax.swing.JLabel();
        txtprcioproducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        transaccional_VCXC = new javax.swing.JTable();
        label18 = new javax.swing.JLabel();
        fechav = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        exitxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setBackground(new java.awt.Color(204, 204, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventas y Cuentas por Cobrar");
        setVisible(true);

        btnAsignarT.setBackground(new java.awt.Color(153, 255, 153));
        btnAsignarT.setText("▶▶");
        btnAsignarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarTActionPerformed(evt);
            }
        });

        btnAsignarU.setBackground(new java.awt.Color(153, 255, 153));
        btnAsignarU.setText("▶");
        btnAsignarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarUActionPerformed(evt);
            }
        });

        btnEliminarT.setBackground(new java.awt.Color(255, 153, 153));
        btnEliminarT.setText("◀◀️");
        btnEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Productos Asignados");

        btnEliminarU.setBackground(new java.awt.Color(255, 153, 153));
        btnEliminarU.setText("◀️");
        btnEliminarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUActionPerformed(evt);
            }
        });

        label4.setBackground(new java.awt.Color(153, 153, 255));
        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Cliente a selecionar");

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Asignar");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        btnEditar.setBackground(new java.awt.Color(153, 255, 153));
        btnEditar.setText("$");
        btnEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Productos Disponibles");

        cboperfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboperfilActionPerformed(evt);
            }
        });

        txtper.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper.setEnabled(false);
        txtper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtperActionPerformed(evt);
            }
        });

        lstAplicA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(lstAplicA);

        lstAplicD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lstAplicD.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lstAplicDComponentAdded(evt);
            }
        });
        lstAplicD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lstAplicDAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(lstAplicD);

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Quitar");

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label8.setText("Generar Venta");

        txtproducto.setEnabled(false);

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Id del producto seleccionado");

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("Nombre");

        label11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label11.setText("Id");

        label12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label12.setText("Precio producto:");

        label13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label13.setText("Cantidad Producto:");

        txtper1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper1.setBorder(null);
        txtper1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtper1ActionPerformed(evt);
            }
        });

        label14.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label14.setText("Vendedor a selecionar");

        label15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label15.setText("Nombre");

        cboperfil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboperfil1ActionPerformed(evt);
            }
        });

        txtper3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtper3.setEnabled(false);
        txtper3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtper3ActionPerformed(evt);
            }
        });

        label16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label16.setText("Id");

        txtprcioproducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtprcioproducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtprcioproducto.setEnabled(false);
        txtprcioproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprcioproductoActionPerformed(evt);
            }
        });

        transaccional_VCXC.setBackground(new java.awt.Color(204, 204, 255));
        transaccional_VCXC.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        transaccional_VCXC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No_factura", "no_venta", "id_vendedor", "Nombre", "Apellido", "Producto", "Cantidad", "Precio", "Saldo Anterior", "Plazo", "Total"
            }
        ));
        jScrollPane1.setViewportView(transaccional_VCXC);

        label18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label18.setText("Fecha de venta: ");

        fechav.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechav.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechav.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fechav.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Existencias: ");

        exitxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exitxt.setEnabled(false);
        exitxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitxtActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setText("AYUDA");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setText("REPORTES");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(label4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(label10)
                                .addGap(132, 132, 132)
                                .addComponent(label11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(txtper, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(label6))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label12)
                                .addGap(28, 28, 28)
                                .addComponent(txtprcioproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label13)
                                .addGap(15, 15, 15)
                                .addComponent(txtper1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label18)
                                .addGap(30, 30, 30)
                                .addComponent(fechav, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(380, 380, 380)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnEliminarT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnEliminarU, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(label16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(380, 380, 380)
                                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(label15))
                            .addComponent(label5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(label14))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(label3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(434, 434, 434)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnAsignarT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnAsignarU, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(label9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(txtper3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(cboperfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(54, 54, 54)
                        .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(210, 210, 210)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label10)
                            .addComponent(label11))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(label6)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprcioproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label13))
                            .addComponent(txtper1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label18))
                            .addComponent(fechav, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(btnEliminarT))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(btnEliminarU))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(label16))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(label15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(label5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(label14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(label3))
                    .addComponent(lb)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btnAsignarT))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnAsignarU))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(label9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(txtper3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(cboperfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(label7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnAsignarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarTActionPerformed
        // TODO add your handling code here:
   llenarlista();
    }//GEN-LAST:event_btnAsignarTActionPerformed

    private void btnAsignarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarUActionPerformed
    llenarlistaUnoaUno();
    }//GEN-LAST:event_btnAsignarUActionPerformed

    private void btnEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTActionPerformed
//        // TODO add your handling code here:
vaciarlista();
txtproducto.setText(" "); 
DefaultListModel<String> modelo2 = new DefaultListModel<>();
lstAplicA.setModel(modelo2);
    }//GEN-LAST:event_btnEliminarTActionPerformed

//metodo para las existencias ---- sofi
    private void mostrarExistencias() {
    String productoSeleccionado = (String) lstAplicA.getSelectedValue();

    if (productoSeleccionado == null || productoSeleccionado.isEmpty()) {
        exitxt.setText("");  // Limpiar si no hay selección
        return;
    }

    try {
        Connection conn = Conexion.getConnection();
        String sql = "SELECT proExistencias FROM tbl_productos WHERE proNombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, productoSeleccionado);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int existencias = rs.getInt("proExistencias");
            exitxt.setText(String.valueOf(existencias));
        } else {
            exitxt.setText("0"); // No encontrado
        }

        rs.close();
        ps.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al consultar existencias: " + ex.getMessage());
    }
}

// fin de metodo de exixtencias 
    private void btnEliminarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUActionPerformed
        vaciarlistaUnoaUno();
        txtproducto.setText(" ");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarUActionPerformed
private int numG;
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      
// valida exixtencias antes de generar la venta --- sofi
    int cantidadSolicitada = Integer.parseInt(txtper1.getText());
int existenciasActuales = Integer.parseInt(exitxt.getText());

if (cantidadSolicitada > existenciasActuales) {
    JOptionPane.showMessageDialog(null, "Lo siento, venta fallida por falta de productos.\n" +
                                            "Cantidad disponible: " + existenciasActuales +
                                            "\nCantidad solicitada: " + cantidadSolicitada);
    return;
}

// Primero validar crédito llamando a generarVenta() pero sin actualizar existencias aún
if (!validarCreditoSuficiente()) {  
    return;     
}

// Si pasó ambas validaciones, entonces proceder con la actualización de existencias y venta
numG = generaNum();
System.out.println("Número generado: " + numG);    

generarVenta();
       
       //nuevo
       VentascxcDAO ventascxcDAO = new VentascxcDAO();
       Ventascxc ventasconfirmar = new Ventascxc();
       
       
       

    // existencias de productos --- Sofia
   try {
     
    Connection conn = Conexion.getConnection();
    int cantidadComprada = Integer.parseInt(txtper1.getText());
    int proCodigo = Integer.parseInt(txtproducto.getText());

    String sqlSelect = "SELECT proExistencias FROM tbl_productos WHERE proCodigo = ?";
    PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
    psSelect.setInt(1, proCodigo);
    ResultSet rs = psSelect.executeQuery();

    if (rs.next()) {
        int stockActual = rs.getInt("proExistencias");

        if (cantidadComprada > stockActual) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock. Disponible: " + stockActual);
            return;
        }

        String sqlUpdate = "UPDATE tbl_productos SET proExistencias = proExistencias - ? WHERE proCodigo = ?";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setInt(1, cantidadComprada);
        psUpdate.setInt(2, proCodigo);

        int filas = psUpdate.executeUpdate();

        if (filas > 0) {

            // Consultar y mostrar el stock actualizado en el JTextField "exitxt"
            String sqlNuevoStock = "SELECT proExistencias FROM tbl_productos WHERE proCodigo = ?";
            PreparedStatement psNuevoStock = conn.prepareStatement(sqlNuevoStock);
            psNuevoStock.setInt(1, proCodigo);
            ResultSet rsNuevoStock = psNuevoStock.executeQuery();

            if (rsNuevoStock.next()) {
                int stockActualizado = rsNuevoStock.getInt("proExistencias");
                exitxt.setText(String.valueOf(stockActualizado)); // Mostramos el nuevo stock
                
               
            }

            rsNuevoStock.close();
            psNuevoStock.close();

        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el producto para actualizar.");
        }
     
        psUpdate.close();
    }
    rs.close();
    psSelect.close();
    conn.close();

} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, "Por favor, ingresá solo números válidos.");
} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + ex.getMessage());
}        
    
   // fin de existencias --sofi
     
       
//fecha
       // Obtener fecha actual
        java.util.Date fechaActual = new java.util.Date();
      // Formatear la fecha (puedes ajustar el formato según necesites)
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
      String fechaFormateada = sdf.format(fechaActual);

     // Asignar fecha al JTextField
     fechav.setText(fechaFormateada); 
        
      // Asignar la fecha al objeto
        ventasconfirmar.setFecha_venta(fechaFormateada); 
        
        
        
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Generando Venta");    
   
       /*txtper.setText(" ");
       txtproducto.setText(" ");
       DefaultListModel model = (DefaultListModel)  lstAplicA.getModel();
       model.clear();  
       txtprcioproducto.setText(" ");
       txtper1.setText(" ");
       fechav.setText(" ");
       fechav.setText(" ");
       exitxt.setText(" ");*/
       
       
      
       
     
     
    }//GEN-LAST:event_btnEditarActionPerformed
 
    private boolean validarCreditoSuficiente() {
    try {
        int idCliente = Integer.parseInt(txtper.getText());
        int cantidad = Integer.parseInt(txtper1.getText());
        double proPrecio = Double.parseDouble(txtprcioproducto.getText());

        ClientesDAO clientesDAO = new ClientesDAO();
        Clientes cliente = clientesDAO.getById(idCliente);
        
        VentascxcDAO ventasDAO = new VentascxcDAO();
        Ventascxc ultimaVenta = ventasDAO.UltiVenta(idCliente);
        
        double saldoActual = (ultimaVenta != null) ? ultimaVenta.getTotal() : cliente.getSaldo_actual_CLE();
        double totalVenta = (cantidad * proPrecio) + saldoActual;
        double limcredito = cliente.getLimite_credito_CLE();
        
        if (totalVenta > limcredito) {
            String mensaje = String.format(
                "LÍMITE DE CRÉDITO INSUFICIENTE\n\n" +
                "Cliente: %s %s\n" +
                "Límite actual: $%,.2f\n" +
                "Saldo anterior: $%,.2f\n" +
                "Total venta: $%,.2f\n\n" +
                "Faltan: $%,.2f para completar la operación",
                cliente.getNombre_cliente(),
                cliente.getApellido_cliente(),
                limcredito,
                saldoActual,
                totalVenta,
                (totalVenta - limcredito)
            );
            JOptionPane.showMessageDialog(this, mensaje, "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al validar crédito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
    
    
    private int generaNum() {
     
    int min = 001;  // Valor mínimo
    int max = 100;  // Valor máximo
    return (int) (Math.random() * (max - min + 1) + min);
}
 
    
    private void lstAplicDComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lstAplicDComponentAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lstAplicDComponentAdded

    private void lstAplicDAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lstAplicDAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lstAplicDAncestorAdded

    private void cboperfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboperfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboperfilActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int valor=JOptionPane.showConfirmDialog(this,"¿Està seguro de salir del Mantenimiento?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (valor==JOptionPane.YES_OPTION) 
                    {
                     this.dispose();
                        
                    }
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION,  "Salio de Transaccional VentasXc");    

        
        
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtperActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtperActionPerformed

    private void txtper1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtper1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtper1ActionPerformed

    private void cboperfil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboperfil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboperfil1ActionPerformed

    private void txtper3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtper3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtper3ActionPerformed

    private void txtprcioproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprcioproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprcioproductoActionPerformed

    private void txtprcioproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprcioproductoKeyReleased
        mostrarExistencias();
    }//GEN-LAST:event_txtprcioproductoKeyReleased

    private void exitxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            Connection connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
            + "/src/main/java/Reportes_VentasCC/reportefactura.jrxml");
            
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
        
        
    
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarT;
    private javax.swing.JButton btnAsignarU;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnEliminarU;
    private javax.swing.JComboBox<String> cboperfil;
    private javax.swing.JComboBox<String> cboperfil1;
    private javax.swing.JTextField exitxt;
    private javax.swing.JTextField fechav;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label15;
    private javax.swing.JLabel label16;
    private javax.swing.JLabel label18;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JList<String> lstAplicA;
    private javax.swing.JList<String> lstAplicD;
    private javax.swing.JTable transaccional_VCXC;
    private javax.swing.JTextField txtper;
    private javax.swing.JTextField txtper1;
    private javax.swing.JTextField txtper3;
    private javax.swing.JTextField txtprcioproducto;
    private javax.swing.JTextField txtproducto;
    // End of variables declaration//GEN-END:variables
}
