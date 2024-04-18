/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.admininventario;

import com.mycompany.objetonegocio.InventarioProductos;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class ControlInventario {

    private Map<String, Integer> inventario;

    public ControlInventario() {
        InventarioProductos inventarioProductos = InventarioProductos.obtenerInstancia();
        inventario = inventarioProductos.obtenerInventario();
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
