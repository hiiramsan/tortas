/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;

/**
 *
 * @author Abe
 */
public class OrdenDAO implements IOrdenDAO {

    public IConexion conexion;
    public String nombreColeccion = "ordenes";
    static final Logger logger = Logger.getLogger(OrdenDAO.class.getName());

    public OrdenDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Orden> obtenerOrdenes() {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccion = base.getCollection(nombreColeccion, Orden.class);

        List<Orden> ordenes = new ArrayList<>();
        coleccion.find().into(ordenes);
        logger.log(Level.INFO, "Se consultaron {0} productos", ordenes.size());

        return ordenes;
    }

    @Override
    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccion = base.getCollection(nombreColeccion, Orden.class);

        Orden orden = new Orden();
        orden.setNombreCliente(ordenDTO.getNombreCliente());
        orden.setNumeroOrden(ordenDTO.getNumeroOrden());
        List<NuevoProductoDTO> productosDTO = ordenDTO.getListaProductos();
        List<Producto> productos = new ArrayList<>();

        for (NuevoProductoDTO producto : productosDTO) {
            Producto productoAgregar = new Producto();
            productoAgregar.setCantidad(producto.getCantidad());
            productoAgregar.setCategoria(producto.getCategoria());
            productoAgregar.setDescripcion(producto.getDescripcion());
            productoAgregar.setNombre(producto.getNombre());
            productoAgregar.setNotas(producto.getNotas());
            productoAgregar.setPrecio(producto.getPrecio());
            productos.add(productoAgregar);
        }
        orden.setListaProductos(productos);
        orden.setTotal(ordenDTO.getTotal());
        orden.setFecha(ordenDTO.getFecha());
        orden.setEstado(ordenDTO.getEstado());

        coleccion.insertOne(orden);
        logger.log(Level.INFO, "Se insertaron {0} ordenes", orden.toString());

        return orden;
    }
}
