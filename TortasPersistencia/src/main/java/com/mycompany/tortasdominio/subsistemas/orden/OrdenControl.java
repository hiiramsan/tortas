/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortasdominio.entidades.Orden;
import com.mycompany.tortaspersistencia.dtos.Estado;
import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import com.mycompany.tortaspersistencia.dtos.NuevoProductoDTO;
import com.mycompany.tortaspersistencia.dtos.TortaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramosz
 */
public class OrdenControl {

    List<NuevaOrdenDTO> listaOrdenes = new ArrayList<>();
    private List<Orden> ordenes;
    private static int contadorOrdenes = 1;
    
    public OrdenControl() {
        this.ordenes = new ArrayList<>();
    }

    // imprimir orden
    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        System.out.println("*************************************************");
        System.out.println("Cliente: " + nuevaOrden.getNombreCliente());

        for (NuevoProductoDTO listaProducto : nuevaOrden.getListaProductos()) {
            System.out.println("Item: x" + listaProducto.getCantidad() + " " + listaProducto.getNombre());
        }
        
        System.out.println("Estado de orden: " + nuevaOrden.getEstado());
         System.out.println("Numero de orden: " + nuevaOrden.getNumeroOrden());
        System.out.println("Total: " + nuevaOrden.getTotal());

        System.out.println("*************************************************");
        
         listaOrdenes.add(nuevaOrden);
    
    listaOrdenes.add(nuevaOrden);

    // Verificar si la orden se agregó correctamente
    if (listaOrdenes.contains(nuevaOrden)) {
        System.out.println("La orden se ha guardado correctamente.");
    } else {
        System.out.println("Error al guardar la orden.");
    }
  
        contadorOrdenes++;
    }

    public void cancelarOrden(int index) {
        if (index >= 0 && index < ordenes.size()) {
            Orden orden = ordenes.get(index);
            orden.setEstado(Estado.CANCELADA);
            ordenes.set(index, orden);
        } else {
            System.out.println("La orden no existe.");
        }
    }
    
     public void completarOrden(int index) {
        if (index >= 0 && index < ordenes.size()) {
            Orden orden = ordenes.get(index);
            orden.setEstado(Estado.COMPLETADA);
            ordenes.set(index, orden);
        } else {
            System.out.println("La orden no existe.");
        }
    }
    /*
    METODOS POSIBLES: 
    
    marcarOrdenCompletada(cambiar estado de pendiente a cancelada)
    
    cancelarOrden(estado de pendiente a cancelada)
     */

}
