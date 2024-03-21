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

    // imprimir orden
    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        System.out.println("*************************************************");
        System.out.println("Cliente: " + nuevaOrden.getNombreCliente());

        for (NuevoProductoDTO listaProducto : nuevaOrden.getProductos()) {
            System.out.println("Item: x" + listaProducto.getCantidad() + " " + listaProducto.getNombre());
        }
        System.out.println("Estado de orden: " + nuevaOrden.getEstado());
        System.out.println("Total: " + nuevaOrden.getTotal());
        
        System.out.println("*************************************************");
    }
    
    /*
        generar orden {
            almacenarla en BD
            tener el estado: en pendiente, entregada
            fecha -- falta
            cliente, precio -- YA
            
    
        }
    
    */
    
    
    /*
    METODOS POSIBLES: 
    
    marcarOrdenCompletada(cambiar estado de pendiente a cancelada)
    
    cancelarOrden(estado de pendiente a cancelada)
    */
    
}
