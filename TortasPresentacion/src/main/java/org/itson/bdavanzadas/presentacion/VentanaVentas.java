/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import java.lang.module.FindException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.AdminVentas.FacadeAdminVentas;
import org.itson.bdavanzadas.AdminVentas.IVentas;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;
import org.itson.bdavanzadas.adminTarjeta.VentanaPagarTarjeta3;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.tabla.TableActionCellEditor;
import org.itson.bdavanzadas.tabla.TableActionCellRender;
import org.itson.bdavanzadas.tabla.TableActionEvent;

/**
 *
 * @author Abe
 */
public class VentanaVentas extends javax.swing.JDialog {

    private IVentas ventas;
    private IAdminOrden adminOrden;
    private VentanaPagarTarjeta3 ventanaTarjeta;
    DefaultTableModel modelo;

    /**
     * Creates new form VentanaVentas
     */
    public VentanaVentas(java.awt.Frame parent, boolean modal) throws FindException {
        super(parent, modal);
        ventas = new FacadeAdminVentas();
        adminOrden = new FacadeAdminOrden();
        initComponents();
        consultar();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void enCancelar(int row) {
                List<NuevaOrdenDTO> ordenes = adminOrden.obtenerOrdenesCompletadas();
                NuevaOrdenDTO OrdenSeleccionada = ordenes.get(row);
                OrdenSeleccionada.toString();
                VentanaCancelar VC = new VentanaCancelar(null, true, OrdenSeleccionada);
                VC.setVisible(true);
                if (tblOrdenes2.isEditing()) {
                    tblOrdenes2.getCellEditor().stopCellEditing();
                }
                if (VentanaCancelar.seCancelo()) {
                    DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                    model.removeRow(row);
                }
                System.out.println("cancelar" + row + OrdenSeleccionada.getNumeroOrden());
            }

            @Override
            public void enPagar(int row) {
                List<NuevaOrdenDTO> ordenes = adminOrden.obtenerOrdenesCompletadas();
                NuevaOrdenDTO OrdenSeleccionada = ordenes.get(row);
                System.out.println("pagar" + row);
                VentanaOrdenPagar ordenPagar = new VentanaOrdenPagar(null, true, OrdenSeleccionada);

                ordenPagar.setVisible(true);
                if (tblOrdenes2.isEditing()) {
                    tblOrdenes2.getCellEditor().stopCellEditing();
                }
                if (VentanaPagarEfectivo.sePago()) {
                    DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                    model.removeRow(row);
                }

                if (VentanaPagarTarjeta3.sePago()) {
                    DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                    model.removeRow(row);
                    System.out.println("SE PAGO LA VENTA POR TARJETA");
                } else {
                    System.out.println("NO SE PAGo");
                }
            }
        };
        tblOrdenes2.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tblOrdenes2.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        tblOrdenes2.getColumnModel().getColumn(4).setPreferredWidth(150);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblTomarOrden = new javax.swing.JLabel();
        lblOrdenes = new javax.swing.JLabel();
        lblVentas = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrdenes2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(147, 187, 204));

        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHora.setText(" ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(lblHora)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setText("Pago de pedido");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel38)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblTomarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/OrdenesBlanca.png"))); // NOI18N
        lblTomarOrden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTomarOrdenMouseClicked(evt);
            }
        });

        lblOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono1.png"))); // NOI18N
        lblOrdenes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOrdenesMouseClicked(evt);
            }
        });

        lblVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Venta.png"))); // NOI18N
        lblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTomarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTomarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(164, 180, 148));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblOrdenes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Cliente", "Total", "Fecha", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrdenes2.setRowHeight(40);
        jScrollPane3.setViewportView(tblOrdenes2);
        if (tblOrdenes2.getColumnModel().getColumnCount() > 0) {
            tblOrdenes2.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblOrdenes2.getColumnModel().getColumn(4).setPreferredWidth(196);
        }

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 920, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOrdenesMouseClicked

    }//GEN-LAST:event_lblOrdenesMouseClicked

    private void lblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentasMouseClicked


    }//GEN-LAST:event_lblVentasMouseClicked

    private void lblTomarOrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTomarOrdenMouseClicked
        try {
            VentanaPrincipal.getInstance().setVisible(true);
        } catch (org.itson.bdavanzadas.persistencia.exception.FindException ex) {
            Logger.getLogger(VentanaVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();

    }//GEN-LAST:event_lblTomarOrdenMouseClicked

    private List<NuevaOrdenDTO> consultar() {
        // Obtener la lista de socios
        List<NuevaOrdenDTO> listaOrdens;
        try {
            listaOrdens = adminOrden.obtenerOrdenesCompletadas();

            modelo = new DefaultTableModel();

            modelo.addColumn("#");
            modelo.addColumn("Cliente");
            modelo.addColumn("Total");
            modelo.addColumn("Fecha");
            modelo.addColumn("");

            for (NuevaOrdenDTO orden : listaOrdens) {

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                String fechaFormateada = formato.format(orden.getFecha());

                Object[] fila = {
                    orden.getNumeroOrden(),
                    orden.getNombreCliente(),
                    orden.getTotal(),
                    fechaFormateada,};
                modelo.addRow(fila);
            }
            TableActionEvent event = new TableActionEvent() {
                @Override
                public void enCancelar(int row) {
                    List<NuevaOrdenDTO> ordenes = adminOrden.obtenerOrdenesCompletadas();
                    NuevaOrdenDTO OrdenSeleccionada = ordenes.get(row);
                    OrdenSeleccionada.toString();
                    VentanaCancelar VC = new VentanaCancelar(null, true, OrdenSeleccionada);
                    VC.setVisible(true);
                    if (tblOrdenes2.isEditing()) {
                        tblOrdenes2.getCellEditor().stopCellEditing();
                    }
                    if (VentanaCancelar.seCancelo()) {
                        DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                        model.removeRow(row);
                        tblOrdenes2.setModel(model);;
                    }
                    System.out.println("cancelar" + row + OrdenSeleccionada.getNumeroOrden());
                }

                @Override
                public void enPagar(int row) {
                    List<NuevaOrdenDTO> ordenes = adminOrden.obtenerOrdenesCompletadas();
                    NuevaOrdenDTO OrdenSeleccionada = ordenes.get(row);
                    System.out.println("pagar" + row);
                    VentanaOrdenPagar ordenPagar = new VentanaOrdenPagar(null, true, OrdenSeleccionada);

                    ordenPagar.setVisible(true);
                    if (tblOrdenes2.isEditing()) {
                        tblOrdenes2.getCellEditor().stopCellEditing();
                    }
                    if (VentanaPagarEfectivo.sePago()) {
                        DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                        model.removeRow(row);
                    }

                    if (VentanaPagarTarjeta3.sePago()) {
                        DefaultTableModel model = (DefaultTableModel) tblOrdenes2.getModel();
                        model.removeRow(row);
                        System.out.println("SE PAGO LA VENTA POR TARJETA");
                    } else {
                        System.out.println("NO SE PAGo");
                    }

                }
            };
            tblOrdenes2.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
            tblOrdenes2.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));

            tblOrdenes2.setModel(modelo);
            return listaOrdens;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la información de las ordenes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

    //    private void consultar() {
    //        // Obtener la lista de socios
    //        List<NuevaOrdenDTO> listaOrdens;
    //        try {
    //            listaOrdens = adminOrden.obtenerOrdenesCompletadas();
    //
    //            modelo = new DefaultTableModel() {
    //                @Override
    //                public boolean isCellEditable(int row, int column) {
    //                    return false; // No permitir la edición de celdas
    //                }
    //            };
    //
    //            modelo.addColumn("#");
    //            modelo.addColumn("Cliente");
    //            modelo.addColumn("Total");
    //            modelo.addColumn("Fecha");
    //            modelo.addColumn("");
    //
    //            for (NuevaOrdenDTO orden : listaOrdens) {
    //                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //                String fechaFormateada = formato.format(orden.getFecha());
    //
    //                JButton btnCancelar = new JButton("Cancelar");
    //                btnCancelar.addActionListener(new ActionListener() {
    //                    @Override
    //                    public void actionPerformed(ActionEvent e) {
    //                        // Acción para cancelar la orden
    //                        // Aquí puedes obtener el número de orden y realizar la acción de cancelación
    //                        int fila = tblOrdenes.getSelectedRow();
    //                        int numeroOrden = (int) tblOrdenes.getValueAt(fila, 0);
    //                        System.out.println("Hola");
    //                        // adminOrden.cancelarOrden(numeroOrden);
    //                        // Después de cancelar, podrías actualizar la tabla llamando a consultar()
    //                        consultar();
    //                    }
    //                });
    //
    //                JButton btnPagar = new JButton("Pagar");
    //                btnPagar.addActionListener(new ActionListener() {
    //                    @Override
    //                    public void actionPerformed(ActionEvent e) {
    //                        // Acción para pagar la orden
    //                        // Aquí puedes obtener el número de orden y realizar la acción de pago
    //                        int fila = tblOrdenes.getSelectedRow();
    //                        int numeroOrden = (int) tblOrdenes.getValueAt(fila, 0);
    //                        System.out.println("Adios");
    //                        // Realizar la acción de pago con el número de orden
    //                    }
    //                });
    //                // Agregar los botones como componentes a la tabla
    //
    //                Object[] fila = {
    //                    orden.getNumeroOrden(),
    //                    orden.getNombreCliente(),
    //                    orden.getTotal(),
    //                    fechaFormateada,
    //                    // Agregamos el panel con los botones a la tabla
    //                };
    //                modelo.addRow(fila);
    //            }
    //
    //            tblOrdenes.setModel(modelo);
    //
    ////            tblOrdenes.addMouseListener(new MouseAdapter() {
    ////                @Override
    ////                public void mouseClicked(MouseEvent e) {
    ////                    int fila = tblOrdenes.rowAtPoint(e.getPoint());
    ////                    int columna = tblOrdenes.columnAtPoint(e.getPoint());
    ////                    if (fila >= 0 && columna == 4) {
    ////                        // Se hizo clic en el botón "Cancelar"
    ////                        int modelRow = tblOrdenes.convertRowIndexToModel(fila);
    ////                        new ButtonAction("Cancelar").performAction(modelRow);
    ////                    } else if (fila >= 0 && columna == 5) {
    ////                        // Se hizo clic en el botón "Pagar"
    ////                        int modelRow = tblOrdenes.convertRowIndexToModel(fila);
    ////                        new ButtonAction("Pagar").performAction(modelRow);
    ////                    }
    ////                }
    ////            });
    //            //            List<String> buttonTexts = new ArrayList<>();
    //            //            buttonTexts.add("Cancelar");
    //            //            
    //            //            List<String> buttonTextss = new ArrayList<>();
    //            //            buttonTextss.add("Pagar");
    //            //
    //            //            List<ActionListener> cancelarListeners = new ArrayList<>();
    //            //            cancelarListeners.add(new ActionListener() {
    //            //                @Override
    //            //                public void actionPerformed(ActionEvent e) {
    //            //                    int fila = tblOrdenes.convertRowIndexToModel(tblOrdenes.getSelectedRow());
    //            //                    NuevaOrdenDTO ordenSeleccionada = listaOrdens.get(fila);
    //            //                    System.out.println("Presiono cancelar");
    //            //                    consultar();
    //            //                }
    //            //            });
    //            //
    //            //            List<ActionListener> pagarListeners = new ArrayList<>();
    //            //            pagarListeners.add(new ActionListener() {
    //            //                @Override
    //            //                public void actionPerformed(ActionEvent e) {
    //            //                    int fila = tblOrdenes.convertRowIndexToModel(tblOrdenes.getSelectedRow());
    //            //                    NuevaOrdenDTO ordenSeleccionada = listaOrdens.get(fila);
    //            //                    System.out.println("Presiono pagar");
    //            //                }
    //            //            });
    //            //
    //            //            ButtonColumn cancelarColumn = new ButtonColumn(buttonTexts, cancelarListeners);
    //            //            ButtonColumn pagarColumn = new ButtonColumn(buttonTextss, pagarListeners);
    //            //
    //            //            tblOrdenes.getColumnModel().getColumn(4).setCellRenderer(cancelarColumn);
    //            //            tblOrdenes.getColumnModel().getColumn(4).setCellEditor(cancelarColumn);
    //            //
    //            //            tblOrdenes.getColumnModel().getColumn(5).setCellRenderer(pagarColumn);
    //            //            tblOrdenes.getColumnModel().getColumn(5).setCellEditor(pagarColumn);
    //            //            List<String> buttonTexts = new ArrayList<>();
    //            //            buttonTexts.add("Cancelar");
    //            //            buttonTexts.add("Pagar");
    //            //
    //            //            List<ActionListener> actionListeners = new ArrayList<>();
    //            //            actionListeners.add(new ActionListener() {
    //            //                @Override
    //            //                public void actionPerformed(ActionEvent e) {
    //            //                    int fila = tblOrdenes.convertRowIndexToModel(tblOrdenes.getSelectedRow());
    //            //                    NuevaOrdenDTO ordenSeleccionada = listaOrdens.get(fila);
    //            //                    System.out.println("Presiono cancelar");
    //            //                    consultar();
    //            //
    //            ////                    try {
    //            ////                        adminOrden.cancelarOrden(ordenSeleccionada);
    //            ////                        consultar();
    //            ////                    } catch (Exception ex) {
    //            ////                        JOptionPane.showMessageDialog(VentanaVentas.this, "Error al cancelar la orden: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    //            ////                    }
    //            //                }
    //            //            });
    //            //            actionListeners.add(new ActionListener() {
    //            //                @Override
    //            //                public void actionPerformed(ActionEvent e) {
    //            //                    int fila = tblOrdenes.convertRowIndexToModel(tblOrdenes.getSelectedRow());
    //            //                    NuevaOrdenDTO ordenSeleccionada = listaOrdens.get(fila);
    //            //                    System.out.println("Presiono pagar");
    //            ////                    VentanaOrdenPagar ordenPagar = new VentanaOrdenPagar(null, true, ordenSeleccionada);
    //            ////                    ordenPagar.setVisible(true);
    //            ////                    consultar();
    //            //                }
    //            //            });
    //            //
    //            //            ButtonColumn buttonColumn = new ButtonColumn(buttonTexts, actionListeners);
    //            //
    //            //            tblOrdenes.getColumnModel().getColumn(4).setCellRenderer(buttonColumn);
    //            //            tblOrdenes.getColumnModel().getColumn(4).setCellEditor(buttonColumn);
    //            //
    //            //            tblOrdenes.getColumnModel().getColumn(5).setCellRenderer(buttonColumn);
    //            //            tblOrdenes.getColumnModel().getColumn(5).setCellEditor(buttonColumn);
    //        } catch (Exception e) {
    //            Logger.getLogger(VentanaVentas.class.getName()).log(Level.SEVERE, "Error al consultar la información de las ordenes", e);
    //        }
    //    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblOrdenes;
    private javax.swing.JLabel lblTomarOrden;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JTable tblOrdenes2;
    // End of variables declaration//GEN-END:variables
}
