/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import com.mycompany.admininventariar.FacadeAdminInventariar;
import com.mycompany.admininventariar.IInventariar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.adminInventario.FacadeAdminInventario;
import org.itson.bdavanzadas.adminInventario.IInventario;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.exception.FindException;

/**
 *
 * @author Ramosz
 */
public class FrmInventariar extends javax.swing.JFrame {

    // stock
    int stockCocaCola;
    int stockPepsi;
    int stockFanta;
    int stockJamaica;
    int stockHorchata;
    int stockAgua;

    // stock temporal
    int stockTemporalCocaCola;
    int stockTemporalPepsi;
    int stockTemporalFanta;
    int stockTemporalJamaica;
    int stockTemporalHorchata;
    int stockTemporalAgua;

    int cantidadCoca = 0;
    int cantidadCocaTest = 0;

    int cantidadPepsi = 0;
    int cantidadPepsiTest = 0;

    int cantidadFanta = 0;
    int cantidadFantaTest = 0;

    int cantidadJamaica = 0;
    int cantidadJamaicaTest = 0;

    int cantidadHorchata = 0;
    int cantidadHorchataTest = 0;

    int cantidadAgua = 0;
    int cantidadAguaTest = 0;

    NuevoProductoDTO coca = new NuevoProductoDTO("Coca-cola", 0, "Bebida");
    NuevoProductoDTO pepsi = new NuevoProductoDTO("Pepsi", 0, "Bebida");
    NuevoProductoDTO fanta = new NuevoProductoDTO("Fanta", 0, "Bebida");
    NuevoProductoDTO jamaica = new NuevoProductoDTO("Jamaica", 0, "Bebida");
    NuevoProductoDTO horchata = new NuevoProductoDTO("Horchata", 0, "Bebida");
    NuevoProductoDTO agua = new NuevoProductoDTO("Agua", 0, "Bebida");

    private static List<NuevoProductoDTO> listaProductos = new ArrayList<>();
    IInventario inventario;
    IInventariar inventariar;

    /**
     * Creates new form FrmInventariar
     *
     * @throws org.itson.bdavanzadas.persistencia.exception.FindException
     */
    public FrmInventariar() throws FindException {
        this.inventario = new FacadeAdminInventario();
        this.inventariar = new FacadeAdminInventariar();
        initComponents();
        actualizarStock();
        llenarTabla();
        
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Abrir la VentanaPrincipal al cerrar este frame
                VentanaPrincipal vp = null;
                try {
                    vp = new VentanaPrincipal();
                } catch (FindException ex) {
                    Logger.getLogger(FrmInventariar.class.getName()).log(Level.SEVERE, null, ex);
                }
                vp.setVisible(true);
            }
        });
        
    }

    public void actualizarStock() {
        stockCocaCola = inventario.getProductStock("Coca-cola");
        stockTemporalCocaCola = stockCocaCola;
        stockCocaColaTxt.setText("Stock: " + String.valueOf(stockCocaCola));

        stockPepsi = inventario.getProductStock("Pepsi");
        stockTemporalPepsi = stockPepsi;
        stockPepsiTxt.setText("Stock: " + String.valueOf(stockPepsi));

        stockFanta = inventario.getProductStock("Fanta");
        stockTemporalFanta = stockFanta;
        stockFantaTxt.setText("Stock: " + String.valueOf(stockFanta));

        stockJamaica = inventario.getProductStock("Jamaica");
        stockTemporalJamaica = stockJamaica;
        stockJamaicaTxt.setText("Stock: " + String.valueOf(stockJamaica));

        stockHorchata = inventario.getProductStock("Horchata");
        stockTemporalHorchata = stockHorchata;
        stockHorchataTxt.setText("Stock: " + String.valueOf(stockHorchata));

        stockAgua = inventario.getProductStock("Agua");
        stockTemporalAgua = stockAgua;
        stockAguaTxt.setText("Stock: " + String.valueOf(stockAgua));
    }

    public void actualizarStockTemporal() {
        stockCocaColaTxt.setText("Stock: " + String.valueOf(stockTemporalCocaCola));
        stockPepsiTxt.setText("Stock: " + String.valueOf(stockTemporalPepsi));
        stockFantaTxt.setText("Stock: " + String.valueOf(stockTemporalFanta));
        stockJamaicaTxt.setText("Stock: " + String.valueOf(stockTemporalJamaica));
        stockHorchataTxt.setText("Stock: " + String.valueOf(stockTemporalHorchata));
        stockAguaTxt.setText("Stock: " + String.valueOf(stockTemporalAgua));
    }

    public void actualizarStockBtnActualizar() {
        stockTemporalCocaCola = stockCocaCola;
        stockTemporalPepsi = stockPepsi;
        stockTemporalFanta = stockFanta;
        stockTemporalJamaica = stockJamaica;
        stockTemporalHorchata = stockHorchata;
        stockTemporalAgua = stockAgua;

        stockCocaColaTxt.setText("Stock: " + String.valueOf(stockTemporalCocaCola));
        stockPepsiTxt.setText("Stock: " + String.valueOf(stockTemporalPepsi));
        stockFantaTxt.setText("Stock: " + String.valueOf(stockTemporalFanta));
        stockJamaicaTxt.setText("Stock: " + String.valueOf(stockTemporalJamaica));
        stockHorchataTxt.setText("Stock: " + String.valueOf(stockTemporalHorchata));
        stockAguaTxt.setText("Stock: " + String.valueOf(stockTemporalAgua));
    }

    public void resetCantidades() {
        cantidadCoca = 0;
        cantidadCocaTest = 0;
        cantidadPepsi = 0;
        cantidadPepsiTest = 0;
        cantidadFanta = 0;
        cantidadFantaTest = 0;
        cantidadJamaica = 0;
        cantidadJamaicaTest = 0;
        cantidadHorchata = 0;
        cantidadHorchataTest = 0;
        cantidadAgua = 0;
        cantidadAguaTest = 0;

        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");

        cantJamaica.setText("0");
        cantPepsi.setText("0");
        cantCoca.setText("0");
        cantAgua.setText("0");
        cantHorchata.setText("0");
        cantFanta.setText("0");
    }

    private void llenarTabla() {
        List<NuevoProductoDTO> inventarioDespliegue = inventario.obtenerInventarioCompleto();
        DefaultTableModel inventarioEncontrado = new DefaultTableModel();
        inventarioEncontrado.addColumn("Nombre");
        inventarioEncontrado.addColumn("Cantidad");
        inventarioEncontrado.addColumn("Descripción");
        inventarioEncontrado.addColumn("Precio");
        inventarioEncontrado.addColumn("Categoría");

        for (NuevoProductoDTO inventario : inventarioDespliegue) {
            Object[] fila = {
                inventario.getNombre(),
                inventario.getCantidad(),
                inventario.getDescripcion(),
                inventario.getPrecio(),
                inventario.getCategoria(),};
            inventarioEncontrado.addRow(fila);
        }
        jTInventario.setModel(inventarioEncontrado);
    }

    public boolean validarDatos() {
        // Obtener los valores de los campos de texto
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecio.getText();

        // Verificar que el campo de nombre no esté vacío
        if (nombre.isEmpty()) {
            // Mostrar mensaje de error
            mostrarMensajeError("El nombre no puede estar vacío.");
            return false;
        }

        // Verificar que el campo de descripción no esté vacío
        if (descripcion.isEmpty()) {
            // Mostrar mensaje de error
            mostrarMensajeError("La descripción no puede estar vacía.");
            return false;
        }

        // Verificar que el campo de cantidad no esté vacío
        if (cantidadStr.isEmpty()) {
            // Mostrar mensaje de error
            mostrarMensajeError("La cantidad no puede estar vacía.");
            return false;
        }

        // Verificar que el campo de precio no esté vacío
        if (precioStr.isEmpty()) {
            // Mostrar mensaje de error
            mostrarMensajeError("El precio no puede estar vacío.");
            return false;
        }

        // Verificar que el campo de cantidad sea un número válido
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            // Mostrar mensaje de error
            mostrarMensajeError("La cantidad debe ser un número entero mayor que cero.");
            return false;
        }

        // Verificar que el campo de precio sea un número válido
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            // Mostrar mensaje de error
            mostrarMensajeError("El precio debe ser un número decimal mayor que cero.");
            return false;
        }

        // Todos los campos son válidos
        return true;
    }

    public void mostrarMensajeError(String mensaje) {
        // Mostrar un cuadro de diálogo emergente con el mensaje de error
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnRestarPepsi = new javax.swing.JButton();
        cantPepsi = new javax.swing.JLabel();
        panelCoca = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        stockCocaColaTxt = new javax.swing.JLabel();
        panelPepsi = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        stockFantaTxt = new javax.swing.JLabel();
        panelJamaica = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        stockJamaicaTxt = new javax.swing.JLabel();
        btnSumarCoca = new javax.swing.JButton();
        btnRestarCoca = new javax.swing.JButton();
        cantCoca = new javax.swing.JLabel();
        btnRestarJamaica = new javax.swing.JButton();
        btnSumarJamaica = new javax.swing.JButton();
        cantJamaica = new javax.swing.JLabel();
        btnSumarPepsi = new javax.swing.JButton();
        panelPepsi1 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        stockPepsiTxt = new javax.swing.JLabel();
        panelPepsi2 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        stockHorchataTxt = new javax.swing.JLabel();
        panelPepsi3 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        stockAguaTxt = new javax.swing.JLabel();
        btnSumarPepsi1 = new javax.swing.JButton();
        cantFanta = new javax.swing.JLabel();
        btnRestarPepsi1 = new javax.swing.JButton();
        btnSumarPepsi2 = new javax.swing.JButton();
        cantHorchata = new javax.swing.JLabel();
        btnRestarPepsi2 = new javax.swing.JButton();
        btnSumarPepsi3 = new javax.swing.JButton();
        cantAgua = new javax.swing.JLabel();
        btnRestarPepsi3 = new javax.swing.JButton();
        panelAgregarProducto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnAgregar1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTInventario = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1098, 720));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel9.setText("Inventario");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRestarPepsi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarPepsi.setText("-");
        btnRestarPepsi.setBorder(null);
        btnRestarPepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarPepsiActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 30, 40));

        cantPepsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantPepsi.setText("0");
        jPanel2.add(cantPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 32, 40));

        panelCoca.setBackground(new java.awt.Color(255, 255, 255));
        panelCoca.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton22.setBorder(null);
        jButton22.setOpaque(true);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel27.setText("Coca cola");

        stockCocaColaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockCocaColaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockCocaColaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelCocaLayout = new javax.swing.GroupLayout(panelCoca);
        panelCoca.setLayout(panelCocaLayout);
        panelCocaLayout.setHorizontalGroup(
            panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCocaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(stockCocaColaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelCocaLayout.setVerticalGroup(
            panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCocaLayout.createSequentialGroup()
                .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCocaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCocaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockCocaColaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, 190, 30));

        panelPepsi.setBackground(new java.awt.Color(255, 255, 255));
        panelPepsi.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton25.setBorder(null);
        jButton25.setOpaque(true);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel30.setText("Fanta");

        stockFantaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockFantaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockFantaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelPepsiLayout = new javax.swing.GroupLayout(panelPepsi);
        panelPepsi.setLayout(panelPepsiLayout);
        panelPepsiLayout.setHorizontalGroup(
            panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(stockFantaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelPepsiLayout.setVerticalGroup(
            panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockFantaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 150, 190, 40));

        panelJamaica.setBackground(new java.awt.Color(255, 255, 255));
        panelJamaica.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton34.setBorder(null);
        jButton34.setOpaque(true);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel39.setText("Jamaica");

        stockJamaicaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockJamaicaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockJamaicaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelJamaicaLayout = new javax.swing.GroupLayout(panelJamaica);
        panelJamaica.setLayout(panelJamaicaLayout);
        panelJamaicaLayout.setHorizontalGroup(
            panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamaicaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(stockJamaicaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelJamaicaLayout.setVerticalGroup(
            panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamaicaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockJamaicaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 50, 190, 40));

        btnSumarCoca.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarCoca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarCoca.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarCoca.setText("+");
        btnSumarCoca.setBorder(null);
        btnSumarCoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarCocaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 40));

        btnRestarCoca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarCoca.setText("-");
        btnRestarCoca.setBorder(null);
        btnRestarCoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarCocaActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 30, 40));

        cantCoca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantCoca.setText("0");
        jPanel2.add(cantCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 40));

        btnRestarJamaica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarJamaica.setText("-");
        btnRestarJamaica.setBorder(null);
        btnRestarJamaica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarJamaicaActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 30, 40));

        btnSumarJamaica.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarJamaica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarJamaica.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarJamaica.setText("+");
        btnSumarJamaica.setBorder(null);
        btnSumarJamaica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarJamaicaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 30, 40));

        cantJamaica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantJamaica.setText("0");
        jPanel2.add(cantJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 30, 40));

        btnSumarPepsi.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarPepsi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarPepsi.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarPepsi.setText("+");
        btnSumarPepsi.setBorder(null);
        btnSumarPepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarPepsiActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 30, 40));

        panelPepsi1.setBackground(new java.awt.Color(255, 255, 255));
        panelPepsi1.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton26.setBorder(null);
        jButton26.setOpaque(true);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel31.setText("Pepsi");

        stockPepsiTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockPepsiTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockPepsiTxt.setText("Stock: ");

        javax.swing.GroupLayout panelPepsi1Layout = new javax.swing.GroupLayout(panelPepsi1);
        panelPepsi1.setLayout(panelPepsi1Layout);
        panelPepsi1Layout.setHorizontalGroup(
            panelPepsi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(stockPepsiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelPepsi1Layout.setVerticalGroup(
            panelPepsi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPepsi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockPepsiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelPepsi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 100, 190, 40));

        panelPepsi2.setBackground(new java.awt.Color(255, 255, 255));
        panelPepsi2.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton27.setBorder(null);
        jButton27.setOpaque(true);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel32.setText("Horchata");

        stockHorchataTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockHorchataTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockHorchataTxt.setText("Stock: ");

        javax.swing.GroupLayout panelPepsi2Layout = new javax.swing.GroupLayout(panelPepsi2);
        panelPepsi2.setLayout(panelPepsi2Layout);
        panelPepsi2Layout.setHorizontalGroup(
            panelPepsi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(stockHorchataTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPepsi2Layout.setVerticalGroup(
            panelPepsi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPepsi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockHorchataTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelPepsi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 190, 40));

        panelPepsi3.setBackground(new java.awt.Color(255, 255, 255));
        panelPepsi3.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton28.setBorder(null);
        jButton28.setOpaque(true);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel33.setText("Agua");

        stockAguaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockAguaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockAguaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelPepsi3Layout = new javax.swing.GroupLayout(panelPepsi3);
        panelPepsi3.setLayout(panelPepsi3Layout);
        panelPepsi3Layout.setHorizontalGroup(
            panelPepsi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(stockAguaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelPepsi3Layout.setVerticalGroup(
            panelPepsi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsi3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPepsi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockAguaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelPepsi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 250, 190, 40));

        btnSumarPepsi1.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarPepsi1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarPepsi1.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarPepsi1.setText("+");
        btnSumarPepsi1.setBorder(null);
        btnSumarPepsi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarPepsi1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarPepsi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 30, 40));

        cantFanta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantFanta.setText("0");
        jPanel2.add(cantFanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 32, 40));

        btnRestarPepsi1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarPepsi1.setText("-");
        btnRestarPepsi1.setBorder(null);
        btnRestarPepsi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarPepsi1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarPepsi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 30, 40));

        btnSumarPepsi2.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarPepsi2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarPepsi2.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarPepsi2.setText("+");
        btnSumarPepsi2.setBorder(null);
        btnSumarPepsi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarPepsi2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarPepsi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 30, 40));

        cantHorchata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantHorchata.setText("0");
        jPanel2.add(cantHorchata, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 32, 40));

        btnRestarPepsi2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarPepsi2.setText("-");
        btnRestarPepsi2.setBorder(null);
        btnRestarPepsi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarPepsi2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarPepsi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 30, 40));

        btnSumarPepsi3.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarPepsi3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarPepsi3.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarPepsi3.setText("+");
        btnSumarPepsi3.setBorder(null);
        btnSumarPepsi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarPepsi3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSumarPepsi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 30, 40));

        cantAgua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantAgua.setText("0");
        jPanel2.add(cantAgua, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 32, 40));

        btnRestarPepsi3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarPepsi3.setText("-");
        btnRestarPepsi3.setBorder(null);
        btnRestarPepsi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarPepsi3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnRestarPepsi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 30, 40));

        panelAgregarProducto.setBackground(new java.awt.Color(255, 255, 255));
        panelAgregarProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Descripción:");

        jLabel3.setText("Precio");

        jLabel4.setText("Categoría");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bebida", "torta", "sabritas", "otro" }));

        btnActualizar.setBackground(new java.awt.Color(36, 123, 160));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel5.setText("Cantidad");

        btnEliminar.setBackground(new java.awt.Color(36, 123, 160));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(36, 123, 160));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnAgregar1.setBackground(new java.awt.Color(36, 123, 160));
        btnAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setText("Limpiar");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarProductoLayout = new javax.swing.GroupLayout(panelAgregarProducto);
        panelAgregarProducto.setLayout(panelAgregarProductoLayout);
        panelAgregarProductoLayout.setHorizontalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad)
                    .addComponent(txtPrecio)
                    .addComponent(txtNombre)
                    .addComponent(txtDescripcion)
                    .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                        .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAgregarProductoLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(btnAgregar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAgregarProductoLayout.setVerticalGroup(
            panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jButton3.setBackground(new java.awt.Color(36, 123, 160));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Actualizar Inventario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTInventario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "nombre", "cantidad", "descripcion", "precio", "categoria"
            }
        ));
        jTInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTInventario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(328, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(490, 490, 490))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(313, 313, 313))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(panelAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void btnSumarCocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarCocaActionPerformed
        stockTemporalCocaCola++;
        actualizarStockTemporal();
        if (!listaProductos.contains(coca)) {
            listaProductos.add(coca);
        }
        cantidadCoca--;
        cantidadCocaTest++;
        coca.setCantidad(cantidadCoca);
        String cCoca = String.valueOf(cantidadCocaTest);
        cantCoca.setText(cCoca);
    }//GEN-LAST:event_btnSumarCocaActionPerformed

    private void btnRestarCocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarCocaActionPerformed
        if (stockTemporalCocaCola > 0) {
            stockTemporalCocaCola--;
            actualizarStockTemporal();
            if (!listaProductos.contains(coca)) {
                listaProductos.add(coca);
            }
            cantidadCoca++;
            cantidadCocaTest--;
            coca.setCantidad(cantidadCoca);
            String cCoca = String.valueOf(cantidadCocaTest);
            cantCoca.setText(cCoca);
        } else {
            mostrarMensajeError("El stock de Coca-Cola ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarCocaActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void btnSumarPepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarPepsiActionPerformed
        stockTemporalPepsi++;
        actualizarStockTemporal();
        if (!listaProductos.contains(pepsi)) {
            listaProductos.add(pepsi);
        }
        cantidadPepsi--;
        cantidadPepsiTest++;
        pepsi.setCantidad(cantidadPepsi);
        String cPepsi = String.valueOf(cantidadPepsiTest);
        cantPepsi.setText(cPepsi);
    }//GEN-LAST:event_btnSumarPepsiActionPerformed

    private void btnRestarPepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarPepsiActionPerformed
        if (stockTemporalPepsi > 0) {
            stockTemporalPepsi--;
            actualizarStockTemporal();
            if (!listaProductos.contains(pepsi)) {
                listaProductos.add(pepsi);
            }
            cantidadPepsi++; // Incrementa la cantidad de Pepsi en el inventario
            cantidadPepsiTest--;
            pepsi.setCantidad(cantidadPepsi); // Actualiza la cantidad en el objeto pepsi
            String cPepsi = String.valueOf(cantidadPepsiTest);
            cantPepsi.setText(cPepsi);
        } else {
            mostrarMensajeError("El stock de Pepsi ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarPepsiActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void btnSumarJamaicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarJamaicaActionPerformed
        stockTemporalJamaica++;
        actualizarStockTemporal();
        if (!listaProductos.contains(jamaica)) {
            listaProductos.add(jamaica);
        }
        cantidadJamaica--;
        cantidadJamaicaTest++;
        jamaica.setCantidad(cantidadJamaica);
        String cJamaica = String.valueOf(cantidadJamaicaTest);
        cantJamaica.setText(cJamaica);
    }//GEN-LAST:event_btnSumarJamaicaActionPerformed

    private void btnRestarJamaicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarJamaicaActionPerformed
        if (stockTemporalJamaica > 0) {
            stockTemporalJamaica--;
            actualizarStockTemporal();
            if (!listaProductos.contains(jamaica)) {
                listaProductos.add(jamaica);
            }
            cantidadJamaica++; // Incrementa la cantidad de Jamaica en el inventario
            cantidadJamaicaTest--;
            jamaica.setCantidad(cantidadJamaica); // Actualiza la cantidad en el objeto Jamaica
            String cJamaica = String.valueOf(cantidadJamaicaTest);
            cantJamaica.setText(cJamaica);
        } else {
            mostrarMensajeError("El stock de Jamaica ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarJamaicaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validarDatos()) {
            int seleccion = jTInventario.getSelectedRow();
            if (seleccion == -1) {
                // No se ha seleccionado ninguna fila, mostrar un mensaje o manejar el caso de manera apropiada
                mostrarMensajeError("No se ha seleccionado ningún producto.");
                return; // Salir del método para evitar continuar con la lógica que depende de la selección
            }

            // Se ha seleccionado una fila, continuar con el proceso de actualización del producto
            String nombrePrevio = jTInventario.getValueAt(seleccion, 0).toString();
            NuevoProductoDTO nuevoProducto = new NuevoProductoDTO(Integer.parseInt(txtCantidad.getText()), txtNombre.getText(), nombrePrevio, txtDescripcion.getText(), Double.parseDouble(txtPrecio.getText()), cmbCategoria.getSelectedItem().toString());
            inventariar.actualizarProducto(nuevoProducto);
            llenarTabla();
            resetCantidades();
            actualizarStockBtnActualizar();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Crear un StringBuilder para almacenar los cambios en el inventario
        StringBuilder cambiosInventario = new StringBuilder();

        // Iterar sobre la lista de productos para registrar los cambios
        for (NuevoProductoDTO listaProducto : listaProductos) {
            cambiosInventario.append("Producto: ").append(listaProducto.getNombre()).append(", Cantidad a actualizar: ").append(-1 * listaProducto.getCantidad()).append("x\n");
        }

        // Mostrar un JOptionPane para confirmar la actualización del inventario
        int opcionConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea confirmar la actualización del inventario?\n\nCambios a realizar:\n\n" + cambiosInventario.toString(), "Confirmar actualización de inventario", JOptionPane.YES_NO_OPTION);

        // Verificar la respuesta del usuario
        if (opcionConfirmacion == JOptionPane.YES_OPTION) {
            // Iterar sobre la lista de productos para actualizar el inventario
            for (NuevoProductoDTO listaProducto : listaProductos) {
                inventario.actualizarInventario(listaProducto.getNombre(), listaProducto.getCantidad());
            }

            // Actualizar la tabla y limpiar la lista de productos
            resetCantidades();
            llenarTabla();
            actualizarStockTemporal();
            listaProductos.clear();
        } else {
            resetCantidades();
            actualizarStock();
            actualizarStockTemporal();
            listaProductos.clear();
            // El usuario seleccionó "No" o cerró el diálogo, no realizar ninguna acción adicional
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void btnSumarPepsi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarPepsi1ActionPerformed
        stockTemporalFanta++;
        actualizarStockTemporal();
        if (!listaProductos.contains(fanta)) {
            listaProductos.add(fanta);
        }
        cantidadFanta--;
        cantidadFantaTest++;
        fanta.setCantidad(cantidadFanta);
        String cFanta = String.valueOf(cantidadFantaTest);
        cantFanta.setText(cFanta);
    }//GEN-LAST:event_btnSumarPepsi1ActionPerformed

    private void btnRestarPepsi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarPepsi1ActionPerformed
        if (stockTemporalFanta > 0) {
            stockTemporalFanta--;
            actualizarStockTemporal();
            if (!listaProductos.contains(fanta)) {
                listaProductos.add(fanta);
            }
            cantidadFanta++; // Incrementa la cantidad de Fanta en el inventario
            cantidadFantaTest--;
            fanta.setCantidad(cantidadFanta); // Actualiza la cantidad en el objeto fanta
            String cFanta = String.valueOf(cantidadFantaTest);
            cantFanta.setText(cFanta);
        } else {
            mostrarMensajeError("El stock de Fanta ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarPepsi1ActionPerformed

    private void btnSumarPepsi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarPepsi2ActionPerformed
        stockTemporalHorchata++;
        actualizarStockTemporal();
        if (!listaProductos.contains(horchata)) {
            listaProductos.add(horchata);
        }
        cantidadHorchata--;
        cantidadHorchataTest++;
        horchata.setCantidad(cantidadHorchata);
        String cHorchata = String.valueOf(cantidadHorchataTest);
        cantHorchata.setText(cHorchata);
    }//GEN-LAST:event_btnSumarPepsi2ActionPerformed

    private void btnRestarPepsi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarPepsi2ActionPerformed
        if (stockTemporalHorchata > 0) {
            stockTemporalHorchata--;
            actualizarStockTemporal();
            if (!listaProductos.contains(horchata)) {
                listaProductos.add(horchata);
            }
            cantidadHorchata++; // Incrementa la cantidad de Horchata en el inventario
            cantidadHorchataTest--;
            horchata.setCantidad(cantidadHorchata); // Actualiza la cantidad en el objeto horchata
            String cHorchata = String.valueOf(cantidadHorchataTest);
            cantHorchata.setText(cHorchata);
        } else {
            mostrarMensajeError("El stock de Horchata ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarPepsi2ActionPerformed

    private void btnSumarPepsi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarPepsi3ActionPerformed
        stockTemporalAgua++;
        actualizarStockTemporal();
        if (!listaProductos.contains(agua)) {
            listaProductos.add(agua);
        }
        cantidadAgua--;
        cantidadAguaTest++;
        agua.setCantidad(cantidadAgua);
        String cAgua = String.valueOf(cantidadAguaTest);
        cantAgua.setText(cAgua);
    }//GEN-LAST:event_btnSumarPepsi3ActionPerformed

    private void btnRestarPepsi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarPepsi3ActionPerformed
        if (stockTemporalAgua > 0) {
            stockTemporalAgua--;
            actualizarStockTemporal();
            if (!listaProductos.contains(agua)) {
                listaProductos.add(agua);
            }
            cantidadAgua++; // Incrementa la cantidad de Agua en el inventario
            cantidadAguaTest--;
            agua.setCantidad(cantidadAgua); // Actualiza la cantidad en el objeto agua
            String cAgua = String.valueOf(cantidadAguaTest);
            cantAgua.setText(cAgua);
        } else {
            mostrarMensajeError("El stock de Agua ya está agotado.");
            return; // Salir del método para evitar que el resto del código se ejecute
        }
    }//GEN-LAST:event_btnRestarPepsi3ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int seleccion = jTInventario.getSelectedRow();
        if (validarDatos()) {
            NuevoProductoDTO nuevoProducto = new NuevoProductoDTO(jTInventario.getValueAt(seleccion, 0).toString());
            inventariar.eliminarProducto(nuevoProducto);
            resetCantidades();
            llenarTabla();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (validarDatos()) {
            NuevoProductoDTO nuevoProducto = new NuevoProductoDTO(Integer.parseInt(txtCantidad.getText()), txtNombre.getText(), txtDescripcion.getText(), Double.parseDouble(txtPrecio.getText()), cmbCategoria.getSelectedItem().toString());
            inventariar.agregarNuevoProducto(nuevoProducto);
            resetCantidades();
            llenarTabla();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jTInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTInventarioMouseClicked
        // TODO add your handling code here:
        int seleccion = jTInventario.getSelectedRow();
        txtNombre.setText(jTInventario.getValueAt(seleccion, 0).toString());
        txtNombre.setEnabled(false);
        txtDescripcion.setText(jTInventario.getValueAt(seleccion, 2).toString());
        txtPrecio.setText(jTInventario.getValueAt(seleccion, 3).toString());
        txtCantidad.setText(jTInventario.getValueAt(seleccion, 1).toString());
        cmbCategoria.setSelectedItem(jTInventario.getValueAt(seleccion, 4).toString());
    }//GEN-LAST:event_jTInventarioMouseClicked

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        txtNombre.setText("");
        txtNombre.setEnabled(true);
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        cmbCategoria.setSelectedItem("");
        llenarTabla();
    }//GEN-LAST:event_btnAgregar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInventariar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInventariar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInventariar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInventariar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmInventariar().setVisible(true);
                } catch (FindException ex) {
                    Logger.getLogger(FrmInventariar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRestarCoca;
    private javax.swing.JButton btnRestarJamaica;
    private javax.swing.JButton btnRestarPepsi;
    private javax.swing.JButton btnRestarPepsi1;
    private javax.swing.JButton btnRestarPepsi2;
    private javax.swing.JButton btnRestarPepsi3;
    private javax.swing.JButton btnSumarCoca;
    private javax.swing.JButton btnSumarJamaica;
    private javax.swing.JButton btnSumarPepsi;
    private javax.swing.JButton btnSumarPepsi1;
    private javax.swing.JButton btnSumarPepsi2;
    private javax.swing.JButton btnSumarPepsi3;
    private javax.swing.JLabel cantAgua;
    private javax.swing.JLabel cantCoca;
    private javax.swing.JLabel cantFanta;
    private javax.swing.JLabel cantHorchata;
    private javax.swing.JLabel cantJamaica;
    private javax.swing.JLabel cantPepsi;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton34;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTInventario;
    private javax.swing.JPanel panelAgregarProducto;
    private javax.swing.JPanel panelCoca;
    private javax.swing.JPanel panelJamaica;
    private javax.swing.JPanel panelPepsi;
    private javax.swing.JPanel panelPepsi1;
    private javax.swing.JPanel panelPepsi2;
    private javax.swing.JPanel panelPepsi3;
    private javax.swing.JLabel stockAguaTxt;
    private javax.swing.JLabel stockCocaColaTxt;
    private javax.swing.JLabel stockFantaTxt;
    private javax.swing.JLabel stockHorchataTxt;
    private javax.swing.JLabel stockJamaicaTxt;
    private javax.swing.JLabel stockPepsiTxt;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
