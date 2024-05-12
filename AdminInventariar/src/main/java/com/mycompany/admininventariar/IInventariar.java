/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.admininventariar;

import org.itson.bdavanzadas.dtos.NuevoProductoDTO;

/**
 *
 * @author Ramosz
 */
public interface IInventariar {

    void agregarNuevoProducto(NuevoProductoDTO nuevoProductoDTO);

    void actualizarProducto(NuevoProductoDTO nuevoProductoDTO);

    void eliminarProducto(NuevoProductoDTO nuevoProductoDTO);
}
