/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.adminOrden;

import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import java.util.ArrayList;
import java.util.List;
import org.itson.bdavanzadas.ObjetoNegocio.OrdenBO;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author carlo
 */
public class OrdenControl {

    List<NuevaOrdenDTO> listaOrdenes = new ArrayList<>();
    List<NuevoProductoDTO> listaProductos = new ArrayList<>();
    private List<NuevaOrdenDTO> ordenes;
    private static int contadorOrdenes = 1;
    OrdenBO ordenBO;

    public OrdenControl() {
        this.ordenes = new ArrayList<>();
        ordenBO = new OrdenBO();
    }

    // imprimir orden
    public void generarOrden(NuevaOrdenDTO nuevaOrden) throws PersistenciaException {
        // Lista de productos específica para esta orden
        List<NuevoProductoDTO> listaProductos = new ArrayList<>();

        for (NuevoProductoDTO producto : nuevaOrden.getListaProductos()) {
            listaProductos.add(producto);
        }

        listaOrdenes.add(nuevaOrden);

        // Verificar si la orden se agregó correctamente
        if (listaOrdenes.contains(nuevaOrden)) {
            System.out.println("La orden se ha guardado correctamente.");
            ordenes.add(nuevaOrden);
            ordenBO.agregarOrden(nuevaOrden);
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
            NuevaOrdenDTO orden = ordenes.get(index);
            orden.setEstado(Estado.CANCELADA);
            ordenes.set(index, orden);
        } else {
            System.out.println("La orden no existe.");
        }
    }

    public void completarOrden(int index) {
        if (index >= 0 && index < ordenes.size()) {
            NuevaOrdenDTO orden = ordenes.get(index);
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

    public List<NuevaOrdenDTO> obtenerOrdenes() throws FindException {
        return ordenBO.obtenerOrden();
    }
    
    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException {
        return ordenBO.obtenerPrecioPorNombre(nombreProducto);
    }
}
