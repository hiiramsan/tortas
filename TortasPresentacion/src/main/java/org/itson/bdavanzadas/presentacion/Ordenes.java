/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;

/**
 *
 * @author crist
 */
public class Ordenes extends javax.swing.JFrame {

    List<NuevaOrdenDTO> listaOrden;
    IAdminOrden adminOrden = new FacadeAdminOrden();

    /**
     * Creates new form Ordenes
     *
     * @param listaOrden
     */
    public Ordenes(List<NuevaOrdenDTO> listaOrden) {
        listaOrden = adminOrden.obtenerOrdenes();
        initComponents();
        llenarTabla(listaOrden);
    }

    private void llenarTabla(List<NuevaOrdenDTO> listaOrden) {
        DefaultTableModel ordenesEncontradas = new DefaultTableModel();
        ordenesEncontradas.addColumn("Nombre");
        ordenesEncontradas.addColumn("Productos");
        ordenesEncontradas.addColumn("Total");
        ordenesEncontradas.addColumn("Estado");
        ordenesEncontradas.addColumn("Número de orden");
        ordenesEncontradas.addColumn("Fecha");

        for (NuevaOrdenDTO orden : listaOrden) {
            StringBuilder productosString = new StringBuilder(); // Crear una nueva cadena para cada orden
            for (NuevoProductoDTO producto : orden.getListaProductos()) {
                productosString.append(producto.getNombre()).append(" x").append(producto.getCantidad()).append(", ");
            }
            String productosFormatted = productosString.toString();
            if (productosFormatted.length() > 2) {
                productosFormatted = productosFormatted.substring(0, productosFormatted.length() - 2); // Remove the trailing comma and space
            }
            Object[] fila = {
                orden.getNombreCliente(),
                productosFormatted,
                orden.getTotal(),
                orden.getEstado(),
                orden.getNumeroOrden(),
                orden.getFecha()
            };
            ordenesEncontradas.addRow(fila);
        }
        jTPOrden.setModel(ordenesEncontradas);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eliminarBoton = new javax.swing.JButton();
        entregadaBoton = new javax.swing.JButton();
        tablaOrdenes = new javax.swing.JScrollPane();
        jTPOrden = new javax.swing.JTable();

        eliminarBoton.setText("Cancelar");
        eliminarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBotonActionPerformed(evt);
            }
        });

        entregadaBoton.setText("Listo");
        entregadaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregadaBotonActionPerformed(evt);
            }
        });

        jTPOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre ", "Productos", "Total", "Estado", "Numero de orden", "Fecha"
            }
        ));
        tablaOrdenes.setViewportView(jTPOrden);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(eliminarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(entregadaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(tablaOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(tablaOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entregadaBoton)
                    .addComponent(eliminarBoton))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBotonActionPerformed
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = jTPOrden.getSelectedRow();

        // Verificar si se seleccionó una fila
        if (filaSeleccionada != -1) {
            // Obtener la orden correspondiente al índice de fila seleccionado
            NuevaOrdenDTO orden = listaOrden.get(filaSeleccionada);

            // Cambiar el estado de la orden a "entregado"
            orden.setEstado(Estado.CANCELADA);

            // Actualizar la tabla de órdenes
            llenarTabla(listaOrden);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para marcar como entregada.");
        }
    }//GEN-LAST:event_eliminarBotonActionPerformed

    private void entregadaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregadaBotonActionPerformed
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = jTPOrden.getSelectedRow();

        // Verificar si se seleccionó una fila
        if (filaSeleccionada != -1) {
            // Obtener la orden correspondiente al índice de fila seleccionado
            NuevaOrdenDTO orden = listaOrden.get(filaSeleccionada);

            // Cambiar el estado de la orden a "entregado"
            orden.setEstado(Estado.COMPLETADA);

            // Actualizar la tabla de órdenes
            llenarTabla(listaOrden);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para marcar como entregada.");
        }
    }//GEN-LAST:event_entregadaBotonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminarBoton;
    private javax.swing.JButton entregadaBoton;
    private javax.swing.JTable jTPOrden;
    private javax.swing.JScrollPane tablaOrdenes;
    // End of variables declaration//GEN-END:variables
}
