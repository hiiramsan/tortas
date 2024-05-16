/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public interface IProductoDAO {

    /**
     * Agrega un nuevo producto al sistema.
     *
     * @param producto el producto que se desea agregar
     * @throws PersistenciaException si ocurre algún error durante la
     * persistencia del producto
     */
    public void agregarNuevoProducto(Producto producto) throws PersistenciaException;

    /**
     * Actualiza la información de un producto existente en el sistema.
     *
     * @param producto el producto con la información actualizada que se desea
     * guardar
     * @throws PersistenciaException si ocurre algún error durante la
     * actualización del producto
     */
    public void actualizarProducto(Producto producto) throws PersistenciaException;

    /**
     * Elimina un producto existente del sistema.
     *
     * @param producto el producto que se desea eliminar
     * @throws PersistenciaException si ocurre algún error durante la
     * eliminación del producto
     */
    public void eliminarProducto(Producto producto) throws PersistenciaException;

    /**
     * Verifica si un producto con un nombre específico existe en el sistema.
     *
     * @param nombre el nombre del producto que se desea verificar
     * @return true si el producto existe, false en caso contrario
     */
    public boolean productoExiste(String nombre);

}
