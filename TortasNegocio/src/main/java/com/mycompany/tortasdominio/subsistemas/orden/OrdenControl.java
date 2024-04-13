/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortasdominio.entidades.Orden;
import com.mycompany.tortasdominio.entidades.Producto;
import com.mycompany.tortaspersistencia.dtos.Estado;
import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import com.mycompany.tortaspersistencia.dtos.NuevoProductoDTO;
import com.mycompany.tortaspersistencia.dtos.TortaDTO;
import com.mycompany.tortaspersistencia.inventario.FacadeAdminInventario;
import com.mycompany.tortaspersistencia.inventario.IInventario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramosz
 */
public class OrdenControl {

    List<NuevaOrdenDTO> listaOrdenes = new ArrayList<>();
    List<Producto> listaProductos = new ArrayList<>();
    private List<Orden> ordenes;
    private static int contadorOrdenes = 1;

    public OrdenControl() {
        this.ordenes = new ArrayList<>();
    }

    // imprimir orden
    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        // Lista de productos específica para esta orden
        List<Producto> listaProductos = new ArrayList<>();

        for (NuevoProductoDTO nuevoProductoDTO : nuevaOrden.getListaProductos()) {
            Producto producto = new Producto(nuevoProductoDTO.getNombre(), nuevoProductoDTO.getCantidad(), nuevoProductoDTO.getPrecio());
            listaProductos.add(producto);
        }

        listaOrdenes.add(nuevaOrden);

        // Verificar si la orden se agregó correctamente
        if (listaOrdenes.contains(nuevaOrden)) {
            System.out.println("La orden se ha guardado correctamente.");
            Orden orden = new Orden(nuevaOrden.getTotal(), listaProductos, nuevaOrden.getNombreCliente(), nuevaOrden.getEstado(), nuevaOrden.getNumeroOrden(), nuevaOrden.getFecha());
            ordenes.add(orden);
            actualizarInventrio(listaProductos);
        } else {
            System.out.println("Error al guardar la orden.");
        }
        contadorOrdenes++;
    }

    public void imprimirOrden(NuevaOrdenDTO nuevaOrden) {
        System.out.println("*************************************************");
        System.out.println("Cliente: " + nuevaOrden.getNombreCliente());
        for (NuevoProductoDTO listaProducto : nuevaOrden.getListaProductos()) {
            System.out.println("Item: x" + listaProducto.getCantidad() + " " + listaProducto.getNombre());
        }

        System.out.println("Estado de orden: " + nuevaOrden.getEstado());
        System.out.println("Numero de orden: " + nuevaOrden.getNumeroOrden());
        System.out.println("Total: " + nuevaOrden.getTotal());
        System.out.println("Fecha: " + nuevaOrden.getFecha());

        System.out.println("*************************************************");

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

    public void especificacionesOrden(NuevaOrdenDTO orden) {
        List<NuevoProductoDTO> productos = orden.getListaProductos();
        for (NuevoProductoDTO producto : productos) {
            //Si es instancia de torta solo se realizara eso
            if (producto instanceof TortaDTO) {
                TortaDTO torta = (TortaDTO) producto; // Hacemos un casting a Torta
                System.out.println("Ingredientes de la torta:");
                System.out.println("Cebolla: " + torta.getCantCebolla());
                System.out.println("Tomate: " + torta.getCantTomate());
                System.out.println("Repollo: " + torta.getCantRepollo());
                System.out.println("Jalapeño: " + torta.getCantJalapeño());
                System.out.println("Mayonesa: " + torta.getCantMayonesa());
                System.out.println("Mostaza: " + torta.getCantMostaza());
                System.out.println("Carne: " + torta.getCantCarne());

            }
        }
    }

    public void actualizarInventrio(List<Producto> listaProductos) {
        IInventario inventario = new FacadeAdminInventario();

        for (Producto producto : listaProductos) {
            inventario.actualizarInventario(producto.getNombre(), producto.getCantidad());
            System.out.println(producto.getNombre());
        }
    }

    public List<Orden> obtenerOrdenes() {
        return ordenes;
    }
}
