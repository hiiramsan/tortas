/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abel
 */
public interface IInventarioDAO {

    /**
     * Obtiene el inventario de las bebidas
     * @return lista de Productos de tipo bebida
     * @throws FindException si no se encuentra se lanza una excepción
     */
    public List<Producto> obtenerInventario() throws FindException;

    /**
     * Actualiza el inventario de las bebidas
     * @param nombreBebida nombre de la bebida
     * @param cantidad cantidad de la bebida    
     * @throws PersistenciaException en caso de ocurrir un error de persistencia
     */
    public void actualizarInventario(String nombreBebida, int cantidad) throws PersistenciaException;

    /**
     * Obtiene el inventario con rangos
     * @param soloStockLimit
     * @param stockLimit limite del stock
     * @param filtrarPorStockAlto valor si se filtra por stock alto o no
     * @return regresa una lista de Productos
     * @throws FindException si no encuentra el inventairo
     */
    public List<Producto> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) throws FindException;
}
