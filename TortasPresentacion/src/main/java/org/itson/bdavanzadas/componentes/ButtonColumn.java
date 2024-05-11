package org.itson.bdavanzadas.componentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final List<JButton> buttons;
    private Object currentValue;

    public ButtonColumn(List<String> buttonTexts, List<ActionListener> actionListeners) {
        buttons = new ArrayList<>();
        for (int i = 0; i < buttonTexts.size(); i++) {
            JButton button = new JButton(buttonTexts.get(i));
            button.setFocusPainted(false);
            button.addActionListener(actionListeners.get(i));
            buttons.add(button);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        for (JButton button : buttons) {
            if (hasFocus) {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            } else if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }

            button.setText((value == null) ? "" : value.toString());
        }
        return buttons.get(0); // Devuelve el primer botón, puedes cambiar esto si lo necesitas
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = value;
        for (JButton button : buttons) {
            button.setText((value == null) ? "" : value.toString());
        }
        return buttons.get(0); // Devuelve el primer botón, puedes cambiar esto si lo necesitas
    }

    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

    public void setButtonText(int buttonIndex, String text) {
        if (buttonIndex >= 0 && buttonIndex < buttons.size()) {
            buttons.get(buttonIndex).setText(text);
        }
    }
}
