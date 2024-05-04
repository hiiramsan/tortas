/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

/**
 *
 * @author Ramosz
 */
public interface IProductoDAO {

    public void agregarNuevoProducto(String nombre, String descripcion, double precio, int cantidad, String categoria);

    public void actualizarProducto(String nombre, String descripcion, double precio, int cantidad, String categoria);

    public void eliminarProducto(String nombre);

}
