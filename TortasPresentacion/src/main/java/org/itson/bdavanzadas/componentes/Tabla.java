/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abe
 */
public class Tabla extends JTable {
public Tabla() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TablaEncabezado header = new TablaEncabezado(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);

                // Establecer el color de fondo y el color de texto según la selección
                if (isSelected) {
                    component.setBackground(new Color(15, 89, 140));
                    component.setForeground(Color.WHITE);
                } else {
                    component.setBackground(Color.WHITE);
                    component.setForeground(new Color(102, 102, 102));
                }

                return component;
            }
        });
    }

    public void agregarFila(Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) getModel();
        modelo.addRow(fila);
    }

    // Método para establecer el renderizador de celdas para los botones de cancelar y pagar
    @Override
    public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);

        // Verificar si la columna es la columna de botones
        if (column == 4 || column == 5) {
            JButton button = (JButton) getValueAt(row, column);
            return button;
        }

        return component;
    }
}
