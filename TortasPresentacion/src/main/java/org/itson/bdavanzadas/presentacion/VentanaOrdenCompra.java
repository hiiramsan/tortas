package org.itson.bdavanzadas.presentacion;

import com.mycompany.adminmailer.FacadeAdminMailer;
import com.mycompany.adminmailer.IMailer;
import com.mycompany.adminreporte.FacadeAdminReporte;
import com.mycompany.adminreporte.IReporte;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.adminInventario.FacadeAdminInventario;
import org.itson.bdavanzadas.adminInventario.IInventario;
import org.itson.bdavanzadas.persistencia.exception.FindException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author carlo
 */
public class VentanaOrdenCompra extends javax.swing.JFrame {

    IInventario inventario;
    IReporte adminReportes = new FacadeAdminReporte();
    IMailer adminMailer = new FacadeAdminMailer();

    // inventario de stock
    List<NuevoProductoDTO> inventarioStock;

    // orden de compra
    private List<NuevoProductoDTO> listaProductos = new ArrayList<>();

    // 
    /**
     * Creates new form productStockManagement
     */
    public VentanaOrdenCompra() throws FindException {
        this.inventario = new FacadeAdminInventario();
        initComponents();
        stockLimitPanel.setVisible(false);
        errTxt.setVisible(false);

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

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        allProductsRB = new javax.swing.JRadioButton();
        stockLimitRB = new javax.swing.JRadioButton();
        stockLimitPanel = new utils.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        stockLimitTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        filtradoCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        panelRound1 = new utils.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOrden = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btn1 = new utils.Btn();
        jLabel7 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JLabel();
        proveedorCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btn2 = new utils.Btn();
        jScrollPane3 = new javax.swing.JScrollPane();
        comentariosTxt = new javax.swing.JTextArea();
        errTxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        visualizarBtn = new utils.Btn();
        agregarBtn = new utils.Btn();
        agregarBtn1 = new utils.Btn();
        agregarBtn2 = new utils.Btn();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1080, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        allProductsRB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(allProductsRB);
        allProductsRB.setText("Todos los productos");
        allProductsRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allProductsRBActionPerformed(evt);
            }
        });
        jPanel1.add(allProductsRB, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 137, -1, -1));

        stockLimitRB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(stockLimitRB);
        stockLimitRB.setText("Limite de stock");
        stockLimitRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockLimitRBActionPerformed(evt);
            }
        });
        jPanel1.add(stockLimitRB, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 164, -1, -1));

        stockLimitPanel.setBackground(new java.awt.Color(102, 153, 255));
        stockLimitPanel.setRoundBottomLeft(20);
        stockLimitPanel.setRoundBottomRight(20);
        stockLimitPanel.setRoundTopLeft(20);
        stockLimitPanel.setRoundTopRight(20);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stock Limit");

        stockLimitTxt.setBorder(null);
        stockLimitTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockLimitTxtActionPerformed(evt);
            }
        });
        stockLimitTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stockLimitTxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout stockLimitPanelLayout = new javax.swing.GroupLayout(stockLimitPanel);
        stockLimitPanel.setLayout(stockLimitPanelLayout);
        stockLimitPanelLayout.setHorizontalGroup(
            stockLimitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockLimitPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockLimitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stockLimitPanelLayout.setVerticalGroup(
            stockLimitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockLimitPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(stockLimitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(stockLimitTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(stockLimitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 160, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Buscar:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 103, -1, -1));

        filtradoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock mas bajo", "Stock mas alto" }));
        filtradoCB.setBorder(null);
        jPanel1.add(filtradoCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, 40));

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tablaInventario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, 170));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Filtrar por:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel1.setText("Orden de Compra");

        tablaOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cantidad", "Producto", "Precio"
            }
        ));
        jScrollPane2.setViewportView(tablaOrden);

        jLabel6.setText("Comentarios");

        btn1.setBackground(new java.awt.Color(102, 153, 255));
        btn1.setBorder(null);
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("<html> <b>Generar Compra</b> </html>");
        btn1.setBorderColor(new java.awt.Color(242, 242, 242));
        btn1.setColor(new java.awt.Color(102, 153, 255));
        btn1.setColorClick(new java.awt.Color(0, 102, 153));
        btn1.setColorOver(new java.awt.Color(51, 153, 255));
        btn1.setRadius(10);
        btn1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btn1FocusLost(evt);
            }
        });
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Total:");

        totalTxt.setText("$5,000.00");

        proveedorCB.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        proveedorCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "carloshiramsanchez@gmail.com", "alondraloustanou@gmail.com" }));
        proveedorCB.setBorder(null);

        jLabel8.setText("Proveedor");

        btn2.setBackground(new java.awt.Color(255, 0, 0));
        btn2.setBorder(null);
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("<html> <b>Eliminar</b> </html>");
        btn2.setBorderColor(new java.awt.Color(242, 242, 242));
        btn2.setBorderPainted(false);
        btn2.setColor(new java.awt.Color(255, 0, 0));
        btn2.setColorClick(new java.awt.Color(204, 0, 0));
        btn2.setColorOver(new java.awt.Color(153, 0, 0));
        btn2.setRadius(10);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        comentariosTxt.setColumns(20);
        comentariosTxt.setRows(5);
        comentariosTxt.setBorder(null);
        jScrollPane3.setViewportView(comentariosTxt);

        errTxt.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        errTxt.setForeground(new java.awt.Color(255, 51, 51));
        errTxt.setText("Orden sin productos");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(btn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalTxt))
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane3)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(errTxt)
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(14, 14, 14)
                                    .addComponent(proveedorCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proveedorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel1.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 430, 500));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel2.setText("Suppliers");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 22, -1, 40));

        visualizarBtn.setBackground(new java.awt.Color(102, 153, 255));
        visualizarBtn.setBorder(null);
        visualizarBtn.setForeground(new java.awt.Color(255, 255, 255));
        visualizarBtn.setText("<html>\n<b>Visualizar</b>\n</html>");
        visualizarBtn.setColor(new java.awt.Color(102, 153, 255));
        visualizarBtn.setColorClick(new java.awt.Color(0, 102, 153));
        visualizarBtn.setColorOver(new java.awt.Color(51, 153, 255));
        visualizarBtn.setRadius(15);
        visualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarBtnActionPerformed(evt);
            }
        });
        jPanel1.add(visualizarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 90, 40));

        agregarBtn.setBorder(null);
        agregarBtn.setForeground(new java.awt.Color(255, 255, 255));
        agregarBtn.setText("<html> <b>Todos</b> </html>");
        agregarBtn.setColor(new java.awt.Color(102, 102, 102));
        agregarBtn.setColorClick(new java.awt.Color(102, 102, 102));
        agregarBtn.setColorOver(new java.awt.Color(51, 51, 51));
        agregarBtn.setRadius(15);
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        jPanel1.add(agregarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 110, 40));

        agregarBtn1.setBorder(null);
        agregarBtn1.setForeground(new java.awt.Color(255, 255, 255));
        agregarBtn1.setText("<html> <b>Agregar</b> </html>");
        agregarBtn1.setColor(new java.awt.Color(0, 153, 51));
        agregarBtn1.setColorClick(new java.awt.Color(0, 102, 51));
        agregarBtn1.setColorOver(new java.awt.Color(0, 153, 51));
        agregarBtn1.setRadius(15);
        agregarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(agregarBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 110, 40));

        agregarBtn2.setBorder(null);
        agregarBtn2.setForeground(new java.awt.Color(255, 255, 255));
        agregarBtn2.setText("<html> <b>Agregar</b> </html>");
        agregarBtn2.setColor(new java.awt.Color(0, 153, 51));
        agregarBtn2.setColorClick(new java.awt.Color(0, 102, 51));
        agregarBtn2.setColorOver(new java.awt.Color(0, 153, 51));
        agregarBtn2.setRadius(15);
        agregarBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(agregarBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void stockLimitRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockLimitRBActionPerformed
        // TODO add your handling code here:
        if (stockLimitRB.isSelected()) {
            stockLimitPanel.setVisible(true);
        }

    }//GEN-LAST:event_stockLimitRBActionPerformed

    private void stockLimitTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockLimitTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockLimitTxtActionPerformed

    private void allProductsRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allProductsRBActionPerformed
        // TODO add your handling code here:
        if (allProductsRB.isSelected()) {
            stockLimitPanel.setVisible(false);
        }
    }//GEN-LAST:event_allProductsRBActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed

        generarOrdenCompra();
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:

        int selectedRow = tablaOrden.getSelectedRow();
        if (selectedRow != -1) {
            String prodSelected = tablaOrden.getValueAt(selectedRow, 1).toString();

            Object value = tablaOrden.getValueAt(selectedRow, 1);
            String attributeName = value.toString();

            for (Iterator<NuevoProductoDTO> iterator = listaProductos.iterator(); iterator.hasNext();) {
                NuevoProductoDTO item = iterator.next();
                if (item.getNombre().equals(attributeName)) {
                    iterator.remove();
                    break;
                }
            }

            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            //no hay fila selecetd
        }

    }//GEN-LAST:event_btn2ActionPerformed

    private void visualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizarBtnActionPerformed
        // TODO add your handling code here:
        boolean soloStockLimit = stockLimitRB.isSelected();
        int stockLimit = 0;
        if (soloStockLimit) {
            stockLimit = Integer.parseInt(stockLimitTxt.getText());
        }
        boolean filtrarPorStockAlto = filtradoCB.getSelectedItem().toString().equals("Stock mas alto");
        System.out.println(filtrarPorStockAlto);

        inventarioStock = inventario.obtenerInventario(soloStockLimit, stockLimit, filtrarPorStockAlto);

        cargarDatosTabla(tablaInventario, inventarioStock);


    }//GEN-LAST:event_visualizarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed

        int cantidad = 0;
        boolean cantidadIngresada = false;

        if (!cantidadIngresada) {
            String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad:", "Agregar todos a orden de compra", JOptionPane.QUESTION_MESSAGE);

            try {
                cantidad = Integer.parseInt(cantidadStr);
                cantidadIngresada = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        for (NuevoProductoDTO item : inventarioStock) {

            if (listaProductos.contains(item)) {
                for (NuevoProductoDTO productoExistente : listaProductos) {
                    if (productoExistente.equals(item)) {
                        productoExistente.setCantidad(productoExistente.getCantidad() + cantidad);
                        break;
                    }
                }
            } else {
                item.setCantidad(cantidad);
                listaProductos.add(item);
            }
        }

        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);


    }//GEN-LAST:event_agregarBtnActionPerformed

    private void agregarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtn1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tablaInventario.getSelectedRow();
        if (selectedRow != -1) {
            Object value = tablaInventario.getValueAt(selectedRow, 1);
            String attributeName = value.toString();

            String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad de " + attributeName + ":", "Agregar a orden de compra", JOptionPane.QUESTION_MESSAGE);

            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean productoEncontrado = false;

                    for (NuevoProductoDTO item : inventarioStock) {
                        if (item.getNombre().equals(attributeName)) {
                            if (listaProductos.contains(item)) {
                                for (NuevoProductoDTO productoExistente : listaProductos) {
                                    if (productoExistente.equals(item)) {
                                        productoExistente.setCantidad(productoExistente.getCantidad() + cantidad);
                                        productoEncontrado = true;
                                        break;
                                    }
                                }
                            } else {
                                item.setCantidad(cantidad);
                                listaProductos.add(item);
                                productoEncontrado = true;
                            }
                            break;
                        }
                    }

                    if (!productoEncontrado) {
                        JOptionPane.showMessageDialog(null, "El producto seleccionado no está en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    cargarDatosTabla(tablaOrden, listaProductos);
                    actualizarTotal(listaProductos);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto de la tabla de inventario.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_agregarBtn1ActionPerformed

    private void agregarBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtn2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_agregarBtn2ActionPerformed

    private void stockLimitTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockLimitTxtKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_stockLimitTxtKeyTyped

    private void btn1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn1FocusLost
        // TODO add your handling code here:
        errTxt.setText("");
        errTxt.setVisible(false);
    }//GEN-LAST:event_btn1FocusLost

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
            java.util.logging.Logger.getLogger(VentanaOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    // Set cross-platform Java L&F (also called "Metal")
                    UIManager.setLookAndFeel(
                            UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VentanaOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VentanaOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VentanaOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(VentanaOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    new VentanaOrdenCompra().setVisible(true);
                } catch (FindException ex) {
                    Logger.getLogger(VentanaOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void cargarDatosTabla(JTable tabla, List<NuevoProductoDTO> listaProductos) {
        DefaultTableModel modelo = new DefaultTableModel();

        tabla.setModel(modelo);

        modelo.addColumn("cantidad");
        modelo.addColumn("producto");
        modelo.addColumn("precio");

        for (NuevoProductoDTO pr : listaProductos) {
            Object[] filas = {
                pr.getCantidad(),
                pr.getNombre(),
                pr.getPrecio()
            };
            modelo.addRow(filas);
        }
    }

    public int calcularTotal(List<NuevoProductoDTO> listaProductos) {
        int total = 0;
        for (NuevoProductoDTO producto : listaProductos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }

    public void actualizarTotal(List<NuevoProductoDTO> listaProductos) {
        String totalStr = String.valueOf(calcularTotal(listaProductos));
        totalTxt.setText("$" + totalStr + "");
    }

    public void generarOrdenCompra() {
        if (!listaProductos.isEmpty()) {
            StringBuilder cuerpoMensaje = new StringBuilder();
            cuerpoMensaje.append("Buen día,\n");
            cuerpoMensaje.append("Adjunto se encuentra una orden de compra con los siguientes productos:\n\n");

            for (NuevoProductoDTO producto : listaProductos) {
                cuerpoMensaje.append(producto.getNombre()).append("x").append(producto.getCantidad()).append("\n");
            }
            String comentarios = comentariosTxt.getText();
            cuerpoMensaje.append("\n").append(comentarios).append("\n\n");

            adminReportes.generarReporteOrdenDeCompra(listaProductos, comentariosTxt.getText(), proveedorCB.getSelectedItem().toString());

            String filename = "OrdenDeCompra.pdf";

            String proveedorMail = proveedorCB.getSelectedItem().toString();
            try {
                String projectDirectory = System.getProperty("user.dir");
                String filePath = projectDirectory + File.separator + filename;
                File file = new File(filePath);
                String absolutePath = file.getAbsolutePath();
                adminMailer.sendEmailWithAttachment("carloshiramsanchez@gmail.com", proveedorMail, "Orden de Compra", cuerpoMensaje.toString(), absolutePath);
            } catch (Exception e) {
                adminMailer.sendPersonalizedEmail("carloshiramsanchez@gmail.com", proveedorMail, "Orden de Compra", cuerpoMensaje.toString());
            }

        } else {
            errTxt.setText("Orden sin productos");
            errTxt.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.Btn agregarBtn;
    private utils.Btn agregarBtn1;
    private utils.Btn agregarBtn2;
    private javax.swing.JRadioButton allProductsRB;
    private utils.Btn btn1;
    private utils.Btn btn2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea comentariosTxt;
    private javax.swing.JLabel errTxt;
    private javax.swing.JComboBox<String> filtradoCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private utils.PanelRound panelRound1;
    private javax.swing.JComboBox<String> proveedorCB;
    private utils.PanelRound stockLimitPanel;
    private javax.swing.JRadioButton stockLimitRB;
    private javax.swing.JTextField stockLimitTxt;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTable tablaOrden;
    private javax.swing.JLabel totalTxt;
    private utils.Btn visualizarBtn;
    // End of variables declaration//GEN-END:variables
}
