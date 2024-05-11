/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;
import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import org.itson.bdavanzadas.persistencia.daos.IOrdenDAO;
import org.itson.bdavanzadas.persistencia.daos.IVentasDAO;
import org.itson.bdavanzadas.persistencia.daos.OrdenDAO;
import org.itson.bdavanzadas.persistencia.daos.VentasDAO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.entidades.Venta;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public class VentasBO {

    private static VentasBO instancia;
    private IVentasDAO ventasDAO;
    private IOrdenDAO ordenDAO;
    private IConexion conexion;
    private static final Logger LOG = Logger.getLogger(VentasBO.class.getName());

    
    /**
     * Constructor del objeto de negocios de venta
     */
    public VentasBO() {
        conexion = new Conexion();
        ventasDAO = new VentasDAO(conexion);
        ordenDAO = new OrdenDAO(conexion);
    }

    /**
     * Obtiene el valor de la instancia estatica de ventasBO
     * @return regrea la instancia
     */
    public static VentasBO obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentasBO();
        }
        return instancia;
    }


    /**
     * Registra una venta
     * @param nuevaVentaDTO valor de la venta a registrar
     * @return regresa la venta registrada
     * @throws NegocioException  si ocurre un error de registra la venta
     */
    public NuevaVentaDTO registarVenta(NuevaVentaDTO nuevaVentaDTO) throws NegocioException{
        try {
            Venta venta = ventasDAO.registrarVenta(nuevaVentaDTO);
            
            NuevaVentaDTO ventaDTO = new NuevaVentaDTO();
            ventaDTO.setFechaVenta(venta.getFechaVenta());
            ventaDTO.setMetodoPago(venta.getMetodoPago())
                    ;
            Orden orden = ordenDAO.obtenerOrdenPorNumeroOrden(nuevaVentaDTO.getOrden().getNumeroOrden());
            
            NuevaOrdenDTO ordenAsignar = new NuevaOrdenDTO();
            ordenAsignar.setEstado(orden.getEstado());
            ordenAsignar.setFecha(orden.getFecha());
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
            
            
            ordenAsignar.setListaProductos(productosDTO);
            ordenAsignar.setNombreCliente(orden.getNombreCliente());
            ordenAsignar.setNumeroOrden(orden.getNumeroOrden());
            ordenAsignar.setTotal(orden.getTotal());
            
            ventaDTO.setOrden(ordenAsignar);
            ventaDTO.setTotal((double)orden.getTotal());
            
            return ventaDTO;
        } catch (PersistenciaException pE) {
            throw new NegocioException("No se pudo registrar la venta", pE);
        }
        
        
    }
}
