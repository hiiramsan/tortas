/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.admininventariar;

import org.itson.bdavanzadas.ObjetoNegocio.ProductoBO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public class ControlInventariar {

    private ProductoBO operacion;

    public ControlInventariar() {
        operacion = new ProductoBO();
    }

    public void agregarNuevoProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        operacion.agregarNuevoProducto(nuevoProductoDTO);
    }

    public void actualizarProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        operacion.actualizarProducto(nuevoProductoDTO);
    }

    public void eliminarProducto(NuevoProductoDTO nuevoProductoDTO) throws PersistenciaException {
        operacion.eliminarProducto(nuevoProductoDTO);
    }
}
