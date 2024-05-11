/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;
import org.itson.bdavanzadas.persistencia.daos.IOrdenDAO;
import org.itson.bdavanzadas.persistencia.daos.OrdenDAO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

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

    public void agregarOrden(NuevaOrdenDTO nuevaOrdenDTO) throws PersistenciaException {
        NuevaOrdenDTO ordenAgregar = new NuevaOrdenDTO();

        ordenAgregar.setEstado(nuevaOrdenDTO.getEstado());
        ordenAgregar.setFecha(nuevaOrdenDTO.getFecha());
        ordenAgregar.setNombreCliente(nuevaOrdenDTO.getNombreCliente() != null ? nuevaOrdenDTO.getNombreCliente() : "");
        ordenAgregar.setNumeroOrden(nuevaOrdenDTO.getNumeroOrden());
        ordenAgregar.setTotal(nuevaOrdenDTO.getTotal());

        List<NuevoProductoDTO> listaProductos = new LinkedList<>();

        for (NuevoProductoDTO producto : nuevaOrdenDTO.getListaProductos()) {
            if (producto instanceof TortaDTO) {
                TortaDTO tortaDTO = new TortaDTO();
                TortaDTO tortaOriginal = (TortaDTO) producto;

                tortaDTO.setNombre(tortaOriginal.getNombre());
                tortaDTO.setCantidad(tortaOriginal.getCantidad());
                tortaDTO.setPrecio(tortaOriginal.getPrecio());
                tortaDTO.setCategoria(tortaOriginal.getCategoria());

                tortaDTO.setCantCebolla(tortaOriginal.getCantCebolla());
                tortaDTO.setCantTomate(tortaOriginal.getCantTomate());
                tortaDTO.setCantRepollo(tortaOriginal.getCantRepollo());
                tortaDTO.setCantMayonesa(tortaOriginal.getCantMayonesa());
                tortaDTO.setCantMostaza(tortaOriginal.getCantMostaza());
                tortaDTO.setCantJalapeno(tortaOriginal.getCantJalapeno());
                tortaDTO.setCantCarne(tortaOriginal.getCantCarne());

                listaProductos.add(tortaDTO);
            } else {
                listaProductos.add(producto);
            }
        }

        ordenAgregar.setListaProductos(listaProductos);

        ordenDAO.registrarOrden(ordenAgregar);
    }

    public List<NuevaOrdenDTO> obtenerOrden() throws FindException, NegocioException {
        List<Orden> ordenes = ordenDAO.obtenerOrdenes();
        List<NuevaOrdenDTO> ordenesDTO = new LinkedList<>();
        for (Orden orden : ordenes) {
            NuevaOrdenDTO ordenDTO = new NuevaOrdenDTO();
            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha(orden.getFecha());

            List<NuevoProductoDTO> productosDTO = new LinkedList<>();

            for (Producto producto : orden.getListaProductos()) {
                NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO();
                nuevoProductoDTO.setCantidad(producto.getCantidad());
                nuevoProductoDTO.setCategoria(producto.getCategoria());
                nuevoProductoDTO.setDescripcion(producto.getDescripcion());
                nuevoProductoDTO.setNombre(producto.getNombre());
                nuevoProductoDTO.setNotas(producto.getNotas());
                nuevoProductoDTO.setPrecio(producto.getPrecio());
                System.out.println("cantidad´de infredientes" + producto.getIngredientes().size());

                if (producto.getIngredientes().isEmpty()) {
                    productosDTO.add(nuevoProductoDTO);
                } else {
                    TortaDTO tortaDTO = new TortaDTO();

                    tortaDTO.setNombre(producto.getNombre());
                    tortaDTO.setCantidad(producto.getCantidad());
                    tortaDTO.setPrecio(producto.getPrecio());
                    tortaDTO.setCantCebolla(producto.getIngredientes().get(0).getCantidad());
                    tortaDTO.setCantTomate(producto.getIngredientes().get(1).getCantidad());
                    tortaDTO.setCantRepollo(producto.getIngredientes().get(2).getCantidad());
                    tortaDTO.setCantMayonesa(producto.getIngredientes().get(3).getCantidad());
                    tortaDTO.setCantMostaza(producto.getIngredientes().get(4).getCantidad());
                    tortaDTO.setCantJalapeno(producto.getIngredientes().get(5).getCantidad());
                    tortaDTO.setCantCarne(producto.getIngredientes().get(6).getCantidad());
                    tortaDTO.setCategoria(producto.getCategoria());

                    productosDTO.add(tortaDTO);
                }

            }
            try {
                ordenDTO.setListaProductos(productosDTO);
                ordenDTO.setNombreCliente(orden.getNombreCliente());
                ordenDTO.setNumeroOrden(orden.getNumeroOrden());
                ordenDTO.setTotal(orden.getTotal());

                ordenesDTO.add(ordenDTO);

            } catch (Exception e) {
                throw new NegocioException("ocurrio un error al obtener las ordenes completadas", e);
            }
        }
        return ordenesDTO;

    }

    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException {
        return ordenDAO.obtenerPrecioPorNombre(nombreProducto);
    }

    public NuevaOrdenDTO obtenerOrdenPorNumeroOrden(int numeroOrden) throws NegocioException {

        try {
            Orden orden = ordenDAO.obtenerOrdenPorNumeroOrden(numeroOrden);
            NuevaOrdenDTO ordenDTO = new NuevaOrdenDTO();
            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha(orden.getFecha());

            List<NuevoProductoDTO> productosDTO = new LinkedList<>();
            List<Producto> producto = orden.getListaProductos();
            for (Producto producto1 : producto) {
                NuevoProductoDTO productoDTO = new NuevoProductoDTO();
                productoDTO.setCantidad(producto1.getCantidad());
                productoDTO.setCategoria(producto1.getCategoria());
                productoDTO.setDescripcion(producto1.getDescripcion());
                productoDTO.setNombre(producto1.getNombre());
                productoDTO.setNotas(producto1.getNotas());
                productoDTO.setPrecio(producto1.getPrecio());
            }
            ordenDTO.setListaProductos(productosDTO);
            ordenDTO.setNombreCliente(orden.getNombreCliente());
            ordenDTO.setNumeroOrden(orden.getNumeroOrden());
            ordenDTO.setTotal(orden.getTotal());
            return ordenDTO;

        } catch (PersistenciaException pE) {
            throw new NegocioException("Ocurrio un error al obtener la orden", pE);
        }

    }

    public List<NuevaOrdenDTO> obtenerOrdenesCompletadas() throws NegocioException {

        List<Orden> ordenes = ordenDAO.obtenerOrdenesCompletadas();
        List<NuevaOrdenDTO> ordenesDTO = new LinkedList<>();
        for (Orden orden : ordenes) {
            NuevaOrdenDTO ordenDTO = new NuevaOrdenDTO();
            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha(orden.getFecha());

            List<NuevoProductoDTO> productosDTO = new LinkedList<>();

            for (Producto producto : orden.getListaProductos()) {
                NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO();
                nuevoProductoDTO.setCantidad(producto.getCantidad());
                nuevoProductoDTO.setCategoria(producto.getCategoria());
                nuevoProductoDTO.setDescripcion(producto.getDescripcion());
                nuevoProductoDTO.setNombre(producto.getNombre());
                nuevoProductoDTO.setNotas(producto.getNotas());
                nuevoProductoDTO.setPrecio(producto.getPrecio());

                if (producto.getIngredientes().isEmpty()) {
                    productosDTO.add(nuevoProductoDTO);
                } else {
                    TortaDTO tortaDTO = new TortaDTO();

                    tortaDTO.setNombre(producto.getNombre());
                    tortaDTO.setCantidad(producto.getCantidad());
                    tortaDTO.setPrecio(producto.getPrecio());
                    tortaDTO.setCantCebolla(producto.getIngredientes().get(0).getCantidad());
                    tortaDTO.setCantTomate(producto.getIngredientes().get(1).getCantidad());
                    tortaDTO.setCantRepollo(producto.getIngredientes().get(2).getCantidad());
                    tortaDTO.setCantMayonesa(producto.getIngredientes().get(3).getCantidad());
                    tortaDTO.setCantMostaza(producto.getIngredientes().get(4).getCantidad());
                    tortaDTO.setCantJalapeno(producto.getIngredientes().get(5).getCantidad());
                    tortaDTO.setCantCarne(producto.getIngredientes().get(6).getCantidad());
                    tortaDTO.setCategoria(producto.getCategoria());

                    productosDTO.add(tortaDTO);
                }

            }
            try {
                ordenDTO.setListaProductos(productosDTO);
                ordenDTO.setNombreCliente(orden.getNombreCliente());
                ordenDTO.setNumeroOrden(orden.getNumeroOrden());
                ordenDTO.setTotal(orden.getTotal());

                ordenesDTO.add(ordenDTO);

            } catch (Exception e) {
                throw new NegocioException("ocurrio un error al obtener las ordenes completadas", e);
            }
        }
        return ordenesDTO;

    }

    public NuevaOrdenDTO cancelarOrden(NuevaOrdenDTO ordenDTO) throws NegocioException {
        Orden orden;
        NuevaOrdenDTO ordenObtenidaDTO = new NuevaOrdenDTO();
        try {
            orden = ordenDAO.cancelarOrden(ordenDTO);

            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha(orden.getFecha());

            List<NuevoProductoDTO> productosDTO = new LinkedList<>();

            for (Producto producto : orden.getListaProductos()) {
                NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO();
                nuevoProductoDTO.setCantidad(producto.getCantidad());
                nuevoProductoDTO.setCategoria(producto.getCategoria());
                nuevoProductoDTO.setDescripcion(producto.getDescripcion());
                nuevoProductoDTO.setNombre(producto.getNombre());
                nuevoProductoDTO.setNotas(producto.getNotas());
                nuevoProductoDTO.setPrecio(producto.getPrecio());

                if (producto.getIngredientes().isEmpty()) {
                    productosDTO.add(nuevoProductoDTO);
                } else {
                    TortaDTO tortaDTO = new TortaDTO();

                    tortaDTO.setNombre(producto.getNombre());
                    tortaDTO.setCantidad(producto.getCantidad());
                    tortaDTO.setPrecio(producto.getPrecio());
                    tortaDTO.setCantCebolla(producto.getIngredientes().get(0).getCantidad());
                    tortaDTO.setCantTomate(producto.getIngredientes().get(1).getCantidad());
                    tortaDTO.setCantRepollo(producto.getIngredientes().get(2).getCantidad());
                    tortaDTO.setCantMayonesa(producto.getIngredientes().get(3).getCantidad());
                    tortaDTO.setCantMostaza(producto.getIngredientes().get(4).getCantidad());
                    tortaDTO.setCantJalapeno(producto.getIngredientes().get(5).getCantidad());
                    tortaDTO.setCantCarne(producto.getIngredientes().get(6).getCantidad());
                    tortaDTO.setCategoria(producto.getCategoria());

                    productosDTO.add(tortaDTO);
                }

            }
        } catch (PersistenciaException pe) {
            throw new NegocioException("Error al cancelar la orden", pe);
        }
        return ordenObtenidaDTO;
    }
    
    public void cambiarEstadoCompletada(int numeroOrden) {
        ordenDAO.cambiarEstadoCompletada(numeroOrden);
    }

    public void cambiarEstadoCancelada(int numeroOrden) {
        ordenDAO.cambiarEstadoCancelada(numeroOrden);
    }

    public List<Document> obtenerOrdenesPorFechaAscendente() {
        return ordenDAO.obtenerOrdenesPorFechaAscendente();
    }

    public List<Document> obtenerOrdenesPendientesPorCantidadTortas() {
        return ordenDAO.obtenerOrdenesPendientesPorCantidadTortas();
    }

    public List<Document> obtenerOrdenesPendientes() {
        return ordenDAO.obtenerOrdenesPendientes();
    }

}
