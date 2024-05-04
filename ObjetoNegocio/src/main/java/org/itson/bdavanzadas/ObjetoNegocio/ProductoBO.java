/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import org.itson.bdavanzadas.persistencia.daos.IProductoDAO;
import org.itson.bdavanzadas.persistencia.daos.ProductoDAO;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public class ProductoBO {

    private IProductoDAO productoDAO;

    public ProductoBO() {
        IConexion conexion = new Conexion();
        productoDAO = new ProductoDAO(conexion);
    }

    public void agregarNuevoProducto(String nombre, String descripcion, double precio, int cantidad, String categoria) throws PersistenciaException {
        if (!productoDAO.productoExiste(nombre)) {
            productoDAO.agregarNuevoProducto(nombre, descripcion, precio, cantidad, categoria);
        }
    }

    public void actualizarProducto(String nombrePrevio, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevaCantidad, String nuevaCategoria) throws PersistenciaException {
        if (productoDAO.productoExiste(nombrePrevio)) {
            productoDAO.actualizarProducto(nombrePrevio, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad, nuevaCategoria);
        }
    }

    public void eliminarProducto(String nombre) throws PersistenciaException {
        if (productoDAO.productoExiste(nombre)) {
            productoDAO.eliminarProducto(nombre);
        }
    }
}
