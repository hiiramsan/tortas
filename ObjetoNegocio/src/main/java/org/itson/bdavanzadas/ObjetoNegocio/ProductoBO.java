/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import org.itson.bdavanzadas.persistencia.daos.IProductoDAO;
import org.itson.bdavanzadas.persistencia.daos.ProductoDAO;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
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

    public void agregarNuevoProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        if (!productoDAO.productoExiste(nuevoProductoDTO.getNombrePrevio())) {
            productoDAO.agregarNuevoProducto(convertirAEntidad(nuevoProductoDTO));
        }
    }

    public void actualizarProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        if (productoDAO.productoExiste(nuevoProductoDTO.getNombrePrevio())) {
            productoDAO.actualizarProducto(convertirAEntidad(nuevoProductoDTO));
        }
    }

    public void eliminarProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        if (productoDAO.productoExiste(nuevoProductoDTO.getNombre())) {
            productoDAO.eliminarProducto(convertirAEntidad(nuevoProductoDTO));
        }
    }

    public static Producto convertirAEntidad(NuevoProductoDTO nuevoProductoDTO) {
        Producto producto = new Producto();
        producto.setCantidad(nuevoProductoDTO.getCantidad());
        producto.setNombre(nuevoProductoDTO.getNombre());
        producto.setNombrePrevio(nuevoProductoDTO.getNombrePrevio());
        producto.setDescripcion(nuevoProductoDTO.getDescripcion());
        producto.setPrecio(nuevoProductoDTO.getPrecio());
        producto.setCategoria(nuevoProductoDTO.getCategoria());
        return producto;
    }
}
