/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.daos.IInventarioDAO;
import org.itson.bdavanzadas.persistencia.daos.InventarioDAO;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public class InventarioProductosBO {

    private static InventarioProductosBO instancia;
    private final Map<String, Integer> inventario;
    private IInventarioDAO inventarioDAO;

    private InventarioProductosBO() {
        IConexion conexion = new Conexion();
        inventarioDAO = new InventarioDAO(conexion);
        inventario = new HashMap<>();
    }

    public static InventarioProductosBO obtenerInstancia() {
        if (instancia == null) {
            instancia = new InventarioProductosBO();
        }
        return instancia;
    }

    public Map<String, Integer> obtenerInventario() throws FindException {
        List<Producto> productos = inventarioDAO.obtenerInventario();

        for (Producto producto : productos) {
            inventario.put(producto.getNombre(), producto.getCantidad());
        }
        return inventario;
    }

    public void actualizarInventario(String nombreBebida, int cantidad) throws PersistenciaException {
        inventarioDAO.actualizarInventario(nombreBebida, cantidad);
    }

    public List<NuevoProductoDTO> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) throws FindException {
        List<Producto> productos = inventarioDAO.obtenerInventario(soloStockLimit, stockLimit, filtrarPorStockAlto);
        List<NuevoProductoDTO> productosDTO = new ArrayList<>();
        for (Producto prod : productos) {
            NuevoProductoDTO productoDTO = new NuevoProductoDTO();
            productoDTO.setNombre(prod.getNombre());
            productoDTO.setCantidad(prod.getCantidad());
            productoDTO.setPrecio(prod.getPrecio());
            productoDTO.setDescripcion(prod.getDescripcion());
            productoDTO.setCategoria(prod.getCategoria());
            productoDTO.setNotas(prod.getNotas());
            productosDTO.add(productoDTO);
        }
        return productosDTO;
    }

    public List<NuevoProductoDTO> obtenerInventarioCompleto() throws FindException {
        List<Producto> productos = inventarioDAO.obtenerInventario();
        List<NuevoProductoDTO> productosDTO = new ArrayList<>();
        for (Producto prod : productos) {
            NuevoProductoDTO productoDTO = new NuevoProductoDTO();
            productoDTO.setNombre(prod.getNombre());
            productoDTO.setCantidad(prod.getCantidad());
            productoDTO.setPrecio(prod.getPrecio());
            productoDTO.setDescripcion(prod.getDescripcion());
            productoDTO.setCategoria(prod.getCategoria());
            productoDTO.setNotas(prod.getNotas());
            productosDTO.add(productoDTO);
        }
        return productosDTO;
    }
}
