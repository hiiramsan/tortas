package org.itson.bdavanzadas.componentes;


import java.awt.event.ActionEvent;

public class ButtonAction implements Action {
    private final String buttonText;

    public ButtonAction(String buttonText) {
        this.buttonText = buttonText;
    }

    @Override
    public void performAction(int row) {
        if ("Cancelar".equals(buttonText)) {
            // Acción para cancelar
            System.out.println("Se ha cancelado la orden en la fila: " + row);
        } else if ("Pagar".equals(buttonText)) {
            // Acción para pagar
            System.out.println("Se ha pagado la orden en la fila: " + row);
        }
    }
}
