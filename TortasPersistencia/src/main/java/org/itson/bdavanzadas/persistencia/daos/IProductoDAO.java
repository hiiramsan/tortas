/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public interface IProductoDAO {

    public void agregarNuevoProducto(String nombre, String descripcion, double precio, int cantidad, String categoria) throws PersistenciaException;

    public void actualizarProducto(String nombrePrevio, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevaCantidad, String nuevaCategoria) throws PersistenciaException;

    public void eliminarProducto(String nombre) throws PersistenciaException;

    public boolean productoExiste(String nombre);

}
