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

    public void agregarNuevoProducto(Producto producto) throws PersistenciaException;

    public void actualizarProducto(Producto producto) throws PersistenciaException;

    public void eliminarProducto(Producto producto) throws PersistenciaException;

    public boolean productoExiste(String nombre);

}
