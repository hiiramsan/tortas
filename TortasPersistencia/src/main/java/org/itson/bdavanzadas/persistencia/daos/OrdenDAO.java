/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.match;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import org.itson.bdavanzadas.persistencia.entidades.Ingrediente;
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
            orden.setNumeroOrden(NumeroOrdenes() + 1);
            List<NuevoProductoDTO> productosDTO = ordenDTO.getListaProductos();
            List<Producto> productos = new ArrayList<>();
            
            for (NuevoProductoDTO producto : ordenDTO.getListaProductos()) {
                Producto productoAgregar = new Producto();
                productoAgregar.setCantidad(producto.getCantidad());
                productoAgregar.setCategoria(producto.getCategoria());
                productoAgregar.setDescripcion(producto.getDescripcion());
                productoAgregar.setNombre(producto.getNombre());
                productoAgregar.setNotas(producto.getNotas());
                productoAgregar.setPrecio(producto.getPrecio());
                if (producto instanceof TortaDTO) {
                    TortaDTO torta = (TortaDTO) producto;
                    List<Ingrediente> ingredientes = new LinkedList<>();
                    ingredientes.add(new Ingrediente("cantCarne", torta.getCantCarne()));
                    ingredientes.add(new Ingrediente("cantCebolla", torta.getCantCebolla()));
                    ingredientes.add(new Ingrediente("cantJalapeño", torta.getCantJalapeno()));
                    ingredientes.add(new Ingrediente("cantMayonesa", torta.getCantMayonesa()));
                    ingredientes.add(new Ingrediente("cantMostaza", torta.getCantMostaza()));
                    ingredientes.add(new Ingrediente("cantRepollo", torta.getCantRepollo()));
                    ingredientes.add(new Ingrediente("cantTomate", torta.getCantTomate()));
                    productoAgregar.setIngredientes(ingredientes);
                } else {
                    List<Ingrediente> ingredientes = new LinkedList<>();
                    productoAgregar.setIngredientes(ingredientes);
                }
                productos.add(productoAgregar);
            }
            
            orden.setListaProductos(productos);
            orden.setTotal(ordenDTO.getTotal());
            orden.setFecha(ordenDTO.getFecha());
            orden.setEstado(ordenDTO.getEstado());
            
            coleccion.insertOne(orden);
            NumeroOrdenes();
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
    
    public int NumeroOrdenes() throws PersistenciaException {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccion = base.getCollection(nombreColeccion, Orden.class);
        
        int cantidad = (int) coleccion.countDocuments();
        System.out.println(cantidad);
        return cantidad;
    }
    
    @Override
    public List<Orden> obtenerOrdenesCompletadas() {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccion = base.getCollection(nombreColeccion, Orden.class);
        
        List<Orden> ordenesCompleatadas = new LinkedList<>();
        AggregateIterable<Orden> result = coleccion.aggregate(Arrays.asList(
                match(eq("estado", "COMPLETADA"))
        ), Orden.class);
        
        result.into(ordenesCompleatadas);
        logger.log(Level.INFO, "Se consultaron {0} ordenes", ordenesCompleatadas.size());
        return ordenesCompleatadas;
        
    }
    
    @Override
    public Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccionOrdenes = base.getCollection("ordenes", Orden.class);
        Orden ordenEncontrada = coleccionOrdenes.find(eq("numeroOrden", numeroOrden)).first();
        
        if (ordenEncontrada == null) {
            throw new PersistenciaException("No se encontró la orden con el número especificado");
        }
        return ordenEncontrada;
    }
    
    @Override
    public Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccionOrdenes = base.getCollection(nombreColeccion, Orden.class);
        
        try {
            Orden ordenEncontrada = obtenerOrdenPorNumeroOrden(ordenDTO.getNumeroOrden());
            
            UpdateResult resultado = coleccionOrdenes.updateOne(eq("_id", ordenEncontrada.getId()), set("estado", "CANCELADA"));
            
            if (resultado.getModifiedCount() > 0) {
                logger.log(Level.INFO, "Orden cancelada con éxito: {0}", ordenEncontrada.toString());
                ordenEncontrada.setEstado(Estado.PAGADA);
                
                List<Producto> productos = new LinkedList<>();
                for (Producto producto : ordenEncontrada.getListaProductos()) {
                    if (cancelarProducto(producto.getNombre(), producto.getCantidad())) {
                        logger.log(Level.INFO, "Producto inventarioado con éxito: {0}", producto.toString());
                    } else {
                        logger.log(Level.INFO, "No se sumo al inventario el producto: {0}", producto.toString());
                    }
                }
                
                return ordenEncontrada;
            } else {
                throw new PersistenciaException("No se pudo encontrar la orden con ID: " + ordenEncontrada.getId());
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar la orden con numero de orden " + ordenDTO.getNumeroOrden(), e);
        }
    }
    
    public boolean cancelarProducto(String nombreBebida, int cantidad) throws PersistenciaException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Producto> coleccion = base.getCollection("productos", Producto.class);
            
            Producto producto = coleccion.find(eq("nombre", nombreBebida)).first();
            
            if (producto != null) {
                coleccion.updateOne(eq("nombre", nombreBebida),
                        new Document("$inc", new Document("cantidad", +cantidad)));
                
                logger.log(Level.INFO, "Se actualizó el inventario de {0} sumando {1}", new Object[]{nombreBebida, cantidad});
            } else {
                logger.log(Level.WARNING, "No se encontró la bebida {0} en el inventario", nombreBebida);
                return false;
                
            }
            
            List<Producto> productos = new ArrayList<>();
            coleccion.find().into(productos);
            
            logger.log(Level.INFO, "Se consultaron {0} productos", productos.size());
            return true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al actualizar inventario", ex);
            throw new PersistenciaException("Error al actualizar inventario", ex);
        }
        
    }
    
    @Override
    public void cambiarEstadoCompletada(int numeroOrden) {
        MongoDatabase base = conexion.obtenerBaseDatos();
        
        MongoCollection<Document> collection = base.getCollection("ordenes");
        
        collection.updateOne(eq("numeroOrden", numeroOrden),
                new Document("$set", new Document("estado", Estado.COMPLETADA.toString())));
        
    }
    
    @Override
    public void cambiarEstadoCancelada(int numeroOrden) {
        MongoDatabase base = conexion.obtenerBaseDatos();
        
        MongoCollection<Document> collection = base.getCollection("ordenes");
        
        collection.updateOne(eq("numeroOrden", numeroOrden),
                new Document("$set", new Document("estado", Estado.CANCELADA.toString())));
    }
    
    public List<Document> obtenerOrdenesPendientes() {
        List<Document> ordenesPendientes = new ArrayList<>();
        
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            FindIterable<Document> result = base.getCollection(nombreColeccion)
                    .find(eq("estado", "PENDIENTE"));
            for (Document doc : result) {
                ordenesPendientes.add(doc);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener las órdenes pendientes: " + e.getMessage());
        }
        
        return ordenesPendientes;
    }
    
    public List<Document> obtenerOrdenesPorFechaAscendente() {
        List<Document> ordenes = new ArrayList<>();
        MongoDatabase base = conexion.obtenerBaseDatos();
        
        MongoCollection<Document> collection = base.getCollection("ordenes");
        // Crea un filtro para las órdenes con estado pendiente
        Bson filter = Filters.eq("estado", "PENDIENTE");

        // Crea un filtro para ordenar por fecha ascendente
        Bson sort = Sorts.ascending("fecha");

        // Combina los filtros
        Bson finalFilter = Filters.and(filter);

        // Realiza la consulta
        try (MongoCursor<Document> cursor = collection.find(finalFilter).sort(sort).iterator()) {
            while (cursor.hasNext()) {
                Document orden = cursor.next();
                ordenes.add(orden);
            }
        }
        
        return ordenes;
    }
    
    public List<Document> obtenerOrdenesPendientesPorCantidadTortas() {
        List<Document> ordenesPendientes = obtenerOrdenesPendientes();
        
        ordenesPendientes.sort((orden1, orden2) -> {
            List<Document> listaProductos1 = (List<Document>) orden1.get("listaProductos");
            List<Document> listaProductos2 = (List<Document>) orden2.get("listaProductos");
            
            int cantidadTortas1 = 0;
            int cantidadTortas2 = 0;
            
            for (Document producto : listaProductos1) {
                if (producto.getString("categoria").equals("Torta")) {
                    cantidadTortas1 += producto.getInteger("cantidad");
                }
            }
            
            for (Document producto : listaProductos2) {
                if (producto.getString("categoria").equals("Torta")) {
                    cantidadTortas2 += producto.getInteger("cantidad");
                }
            }

            // Ordenar de mayor a menor cantidad de tortas
            return cantidadTortas2 - cantidadTortas1;
        });
        
        return ordenesPendientes;
    }
    
    @Override
    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        cambiarEstadoCompletada(ordenDTO.getNumeroOrden());
    }
    
}
