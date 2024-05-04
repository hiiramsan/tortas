/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

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
    public List<Orden> obtenerOrdenes() throws FindException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Orden> coleccion = base.getCollection(nombreColeccion, Orden.class);

            List<Orden> ordenes = new ArrayList<>();
            coleccion.find().into(ordenes);
            logger.log(Level.INFO, "Se consultaron {0} productos", ordenes.size());

            return ordenes;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al obtener ordenes", ex);
            throw new FindException("Error al obtener ordenes", ex);
        }
    }

    @Override
    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        try {
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
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al registrar orden", ex);
            throw new PersistenciaException("Error al registrar orden", ex);
        }
    }

    @Override
    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<org.bson.Document> collection = base.getCollection("productos");
            Document query = new Document("nombre", nombreProducto);

            Document result = collection.find(query).first();

            if (result != null) {
                return result.getDouble("precio");
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al obtener precio por nombre", ex);
            throw new FindException("Error al obtener precio por nombre", ex);
        }
    }
}
