/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.AdminVentas.FacadeAdminVentas;
import org.itson.bdavanzadas.AdminVentas.IVentas;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;
import org.itson.bdavanzadas.dtos.MetodoPago;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;

/**
 *
 * @author Abe
 */
public class VentanaPagarEfectivo extends javax.swing.JDialog {

    private NuevaOrdenDTO ordenDTO;
    private IAdminOrden adminOrden;
    private IVentas adminVenta;
    private float cambio;
    private float cantidadEfectivo;
    public static NuevaOrdenDTO ordenPagada;
    public static boolean pagado;

    /**
     * Creates new form JDialog
     */
    public VentanaPagarEfectivo(java.awt.Frame parent, boolean modal, NuevaOrdenDTO ordenDTO) {
        super(parent, modal);
        this.ordenDTO = ordenDTO;
        adminOrden = new FacadeAdminOrden();
        adminVenta = new FacadeAdminVentas();
        initComponents();
        lblTotal.setText("$" + String.valueOf(ordenDTO.getTotal()));
        lblCliente.setText(ordenDTO.getNombreCliente());
        cambio = -1;
        cantidadEfectivo =0;
        pagado = false;
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (ordenPagada != null) {
                    ordenPagada.setNumeroOrden(-1);
                }
            }
        });
    }

    public static boolean sePago() {
        if (ordenPagada != null) {
            if (ordenPagada.getNumeroOrden() >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        botonPagar1 = new org.itson.bdavanzadas.componentes.BotonPagar();
        botonCancelar1 = new org.itson.bdavanzadas.componentes.BotonCancelar();
        lblCliente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        txtCantidadEfectivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(739, 598));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pago en efectivo");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(397, 16));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Cliente:  ");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(397, 16));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Cantidad efectivo");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setPreferredSize(new java.awt.Dimension(397, 16));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Cambio: ");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setPreferredSize(new java.awt.Dimension(397, 16));

        botonPagar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagar.png"))); // NOI18N
        botonPagar1.setText("Pagar");
        botonPagar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonPagar1.setRadius(30);
        botonPagar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPagar1ActionPerformed(evt);
            }
        });

        botonCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        botonCancelar1.setText("Cancelar");
        botonCancelar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonCancelar1.setRadius(30);
        botonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelar1ActionPerformed(evt);
            }
        });

        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCliente.setText("Nombre cliente");
        lblCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCliente.setPreferredSize(new java.awt.Dimension(397, 16));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Total: ");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(397, 16));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("$0");
        lblTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTotal.setPreferredSize(new java.awt.Dimension(397, 16));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblCambio.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCambio.setText("$0");
        lblCambio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCambio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCambio.setPreferredSize(new java.awt.Dimension(397, 16));

        txtCantidadEfectivo.setBackground(new java.awt.Color(190, 224, 239));
        txtCantidadEfectivo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txtCantidadEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidadEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadEfectivoActionPerformed(evt);
            }
        });
        txtCantidadEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadEfectivoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidadEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(botonPagar1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(botonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadEfectivo))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonPagar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelar1ActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelar1ActionPerformed

    private void botonPagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPagar1ActionPerformed
        NuevaVentaDTO nuevaVenta = new NuevaVentaDTO();
        if (cantidadEfectivo >= ordenDTO.getTotal()) {
            nuevaVenta.setFechaVenta(new Date());
            nuevaVenta.setMetodoPago(MetodoPago.EFECTIVO);
            nuevaVenta.setOrden(ordenDTO);
            nuevaVenta.setTotal((double) ordenDTO.getTotal());
            NuevaVentaDTO ventaPagada = adminVenta.registrarVenta(nuevaVenta);
            VentanaPagarEfectivo.ordenPagada = ventaPagada.getOrden();
            dispose();
            VentanaMensajePago VMP = new VentanaMensajePago(null, true);
            VMP.setVisible(true);
        } else {
            JOptionPane jp = new JOptionPane("Favor de ingresar el monto correctamente",JOptionPane.INFORMATION_MESSAGE);
        }

        
    }//GEN-LAST:event_botonPagar1ActionPerformed

    private void txtCantidadEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadEfectivoKeyReleased
        float total = ordenDTO.getTotal();
        float cantidadEfectivo;

        if (txtCantidadEfectivo.getText().isBlank()) {
            cantidadEfectivo = 0;
        } else {
            cantidadEfectivo = Float.parseFloat(txtCantidadEfectivo.getText());
        }

        cambio = cantidadEfectivo - total;
        this.cantidadEfectivo= cantidadEfectivo;
        lblCambio.setText("$" + String.valueOf(cambio));
    }//GEN-LAST:event_txtCantidadEfectivoKeyReleased

    private void txtCantidadEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadEfectivoActionPerformed

    }//GEN-LAST:event_txtCantidadEfectivoActionPerformed

    private void txtCantidadEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadEfectivoKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txtCantidadEfectivo.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadEfectivoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.itson.bdavanzadas.componentes.BotonCancelar botonCancelar1;
    private org.itson.bdavanzadas.componentes.BotonPagar botonPagar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCantidadEfectivo;
    // End of variables declaration//GEN-END:variables
}
