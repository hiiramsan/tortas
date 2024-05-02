/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import conexion.Conexion;
import conexion.IConexion;
import java.util.ArrayList;
import java.util.List;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.daos.IOrdenDAO;
import org.itson.bdavanzadas.persistencia.daos.OrdenDAO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;

/**
 *
 * @author Abe
 */
public class OrdenBO {

    private IOrdenDAO ordenDAO;

    public OrdenBO() {
        IConexion conexion = new Conexion();
        ordenDAO = new OrdenDAO(conexion);
    }

    public void agregarOrden(NuevaOrdenDTO nuevaOrdenDTO) {
        ordenDAO.registrarOrden(nuevaOrdenDTO);
    }

    public List<NuevaOrdenDTO> obtenerOrden() {
        List<Orden> ordenes = ordenDAO.obtenerOrdenes();
        List<NuevaOrdenDTO> ordenesDTO = new ArrayList<>();

        for (Orden orden : ordenes) {
            NuevaOrdenDTO ordenDTO = new NuevaOrdenDTO();
            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha(orden.getFecha());
            ordenDTO.setNombreCliente(orden.getNombreCliente());
            ordenDTO.setNumeroOrden(orden.getNumeroOrden());

            List<NuevoProductoDTO> productosDTO = new ArrayList<>();
            float total = 0.0f;

            for (Producto producto : orden.getListaProductos()) {
                NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO();
                nuevoProductoDTO.setNombre(producto.getNombre());
                nuevoProductoDTO.setPrecio(producto.getPrecio());
                nuevoProductoDTO.setCantidad(producto.getCantidad());
                productosDTO.add(nuevoProductoDTO);
                total += producto.getCantidad() * producto.getPrecio();
            }
            ordenDTO.setListaProductos(productosDTO);
            ordenDTO.setTotal(total);
            ordenesDTO.add(ordenDTO);
        }
        return ordenesDTO;
    }

    public Double obtenerPrecioPorNombre(String nombreProducto) {
        return ordenDAO.obtenerPrecioPorNombre(nombreProducto);
    }
}
