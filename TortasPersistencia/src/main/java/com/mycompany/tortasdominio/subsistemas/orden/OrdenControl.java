/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import com.mycompany.tortaspersistencia.dtos.NuevoProductoDTO;

/**
 *
 * @author Ramosz
 */
public class OrdenControl {

    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        System.out.println("*************************************************");
        System.out.println("Cliente: " + nuevaOrden.getNombreCliente());

        for (NuevoProductoDTO listaProducto : nuevaOrden.getProductos()) {
            System.out.println("Cantidad: x" + listaProducto.getCantidad() + " " + listaProducto.getNombre());
        }

        System.out.println("Total: " + nuevaOrden.getTotal());
        System.out.println("*************************************************");
    }
}
