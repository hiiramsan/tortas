/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.objetonegocio;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ramosz
 */
public class InventarioProductos {

    private static InventarioProductos instancia = null;
    private final Map<String, Integer> inventario;

    // Constructor privado para evitar instanciación directa desde fuera de la clase
    private InventarioProductos() {
        inventario = new HashMap<>();
        inventario.put("Coca-cola", 10);
        inventario.put("Pepsi", 4);
        inventario.put("Fanta", 7);
        inventario.put("Jamaica", 2);
        inventario.put("Horchata", 1);
        inventario.put("Agua", 1);
    }

    // Método estático para obtener la instancia única de la clase
    public static InventarioProductos obtenerInstancia() {
        if (instancia == null) {
            instancia = new InventarioProductos();
        }
        return instancia;
    }

    public Map<String, Integer> obtenerInventario() {
        return inventario;
    }
}
