/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import conexion.Conexion;
import conexion.IConexion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.itson.bdavanzadas.persistencia.daos.IInventarioDAO;
import org.itson.bdavanzadas.persistencia.daos.InventarioDAO;
import org.itson.bdavanzadas.persistencia.entidades.Producto;

/**
 *
 * @author Ramosz
 */
public class InventarioProductosBO {

    private static InventarioProductosBO instancia ;
    private final Map<String, Integer> inventario;
    private IInventarioDAO inventarioDAO;
    
    private InventarioProductosBO() {
        IConexion conexion = new Conexion();
        inventarioDAO = new InventarioDAO(conexion);
        
        inventario = new HashMap<>();

    }
    
    public static InventarioProductosBO obtenerInstancia() {
        if (instancia == null) {
            instancia = new InventarioProductosBO();
        }
        return instancia;
    }

    public Map<String, Integer> obtenerInventario() {
        List<Producto> productos = inventarioDAO.obtenerInventario();
        int cantidadCoca;
        int cantidadPepsi;
        int cantidadFanta;
        int cantidadJamaica;
        int cantidadHorchata;
        int cantindadAgua;
                
        for (Producto producto : productos) {
            inventario.put(producto.getNombre(), producto.getCantidad());
        }
   
        return inventario;
    }
}
