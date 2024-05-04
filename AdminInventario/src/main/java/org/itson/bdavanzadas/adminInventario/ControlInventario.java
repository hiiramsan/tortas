/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.adminInventario;

import java.util.List;
import org.itson.bdavanzadas.ObjetoNegocio.InventarioProductosBO;
import java.util.Map;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author carlo
 */
public class ControlInventario {

    private Map<String, Integer> inventario;
    private InventarioProductosBO operacion;

    public ControlInventario() throws FindException {
        operacion = InventarioProductosBO.obtenerInstancia();
        inventario = operacion.obtenerInventario();
    }

    public boolean verificarDisponibilidad(String nombreBebida, int cantidad) {
        if (inventario.containsKey(nombreBebida)) {
            int cantidadDisponible = inventario.get(nombreBebida);
            return cantidadDisponible >= cantidad;
        }
        return false;
    }

    public void actualizarInventario(String nombreBebida, int cantidad) throws PersistenciaException {
        if (inventario.containsKey(nombreBebida)) {
            int cantidadDisponible = inventario.get(nombreBebida);
            inventario.put(nombreBebida, cantidadDisponible - cantidad);
            operacion.actualizarInventario(nombreBebida, cantidad);
        }
    }

    public int getProductStock(String nombreBebida) {
        if (inventario.containsKey(nombreBebida)) {
            return inventario.get(nombreBebida);
        }
        return 0;
    }

    public List<NuevoProductoDTO> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) throws FindException {
        return operacion.obtenerInventario(soloStockLimit, stockLimit, filtrarPorStockAlto);
    }

    public List<NuevoProductoDTO> obtenerInventario() throws FindException {
        return operacion.obtenerInventarioCompleto();
    }
}
