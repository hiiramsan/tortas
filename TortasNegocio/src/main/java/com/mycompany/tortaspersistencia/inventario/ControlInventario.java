/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.inventario;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abe
 */
public class ControlInventario {

    private Map<String, Integer> inventario;

    public ControlInventario() {
        inventario = new HashMap<>();
        inventario.put("Coca-cola", 10);
        inventario.put("Pepsi", 4);
        inventario.put("Fanta", 7);
        inventario.put("Jamaica", 2);
        inventario.put("Horchata", 1);
        inventario.put("Agua", 1);
    }

    public boolean verificarDisponibilidad(String nombreBebida, int cantidad) {
        if (inventario.containsKey(nombreBebida)) {
            int cantidadDisponible = inventario.get(nombreBebida);
            return cantidadDisponible >= cantidad;
        }
        return false;
    }

    public void actualizarInventario(String nombreBebida, int cantidad) {
        if (inventario.containsKey(nombreBebida)) {
            int cantidadDisponible = inventario.get(nombreBebida);
            inventario.put(nombreBebida, cantidadDisponible - cantidad);
        }
    }

    public int getProductStock(String nombreBebida) {
        if (inventario.containsKey(nombreBebida)) {
            return inventario.get(nombreBebida);
        }
        return 0;
    }

}
