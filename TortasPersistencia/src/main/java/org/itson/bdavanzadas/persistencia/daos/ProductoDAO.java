/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public class ProductoDAO implements IProductoDAO {

    public IConexion conexion;
    public String nombreColeccion = "productos";
    static final Logger logger = Logger.getLogger(ProductoDAO.class.getName());

    public ProductoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Agrega un nuevo producto al sistema.
     *
     * @param producto el producto que se desea agregar
     * @throws PersistenciaException si ocurre algún error durante la
     * persistencia del producto
     */
    @Override
    public void agregarNuevoProducto(Producto producto) throws PersistenciaException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un nuevo documento para el producto
            Document nuevoProducto = new Document()
                    .append("nombre", producto.getNombre())
                    .append("descripcion", producto.getDescripcion())
                    .append("precio", producto.getPrecio())
                    .append("cantidad", producto.getCantidad())
                    .append("categoria", producto.getCategoria());

            // Insertar el nuevo producto en la colección
            coleccion.insertOne(nuevoProducto);

            // Log de información
            logger.log(Level.INFO, "Se agregó un nuevo producto: {0}", nuevoProducto.toJson());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al agregar nuevo producto", ex);
            throw new PersistenciaException("Error al agregar nuevo producto", ex);
        }
    }

    /**
     * Actualiza la información de un producto existente en el sistema.
     *
     * @param producto el producto con la información actualizada que se desea
     * guardar
     * @throws PersistenciaException si ocurre algún error durante la
     * actualización del producto
     */
    @Override
    public void actualizarProducto(Producto producto) throws PersistenciaException {
        try {
            // Obtener la conexión a la base de datos
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un filtro para encontrar el producto por su nombre previo
            Bson filtro = Filters.eq("nombre", producto.getNombrePrevio());

            // Crear un documento con los nuevos valores
            Document nuevosValores = new Document()
                    .append("nombre", producto.getNombre())
                    .append("descripcion", producto.getDescripcion())
                    .append("precio", producto.getPrecio())
                    .append("cantidad", producto.getCantidad())
                    .append("categoria", producto.getCategoria());

            // Crear un documento de actualización
            Document updateDocumento = new Document("$set", nuevosValores);

            // Actualizar el producto en la colección
            UpdateResult resultado = coleccion.updateOne(filtro, updateDocumento);

            // Verificar si se actualizó correctamente
            if (resultado.getModifiedCount() > 0) {
                // Log de información
                logger.log(Level.INFO, "Se actualizó el producto {0}", producto.getNombre());
            } else {
                logger.log(Level.WARNING, "No se encontró el producto {0} para actualizar.", producto.getNombrePrevio());
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al actualizar producto", ex);
            throw new PersistenciaException("Error al actualizar producto", ex);
        }
    }

    /**
     * Elimina un producto existente del sistema.
     *
     * @param producto el producto que se desea eliminar
     * @throws PersistenciaException si ocurre algún error durante la
     * eliminación del producto
     */
    @Override
    public void eliminarProducto(Producto producto) throws PersistenciaException {
        try {
            // Obtener la conexión a la base de datos
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un filtro para encontrar el producto por su nombre
            Bson filtro = Filters.eq("nombre", producto.getNombre());

            // Eliminar el producto de la colección
            DeleteResult resultado = coleccion.deleteOne(filtro);

            // Verificar si se eliminó correctamente
            if (resultado.getDeletedCount() > 0) {
                // Log de información
                logger.log(Level.INFO, "Se eliminó el producto {0}", producto.getNombre());
            } else {
                logger.log(Level.WARNING, "No se encontró el producto {0} para eliminar.", producto.getNombre());
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al eliminar producto", ex);
            throw new PersistenciaException("Error al eliminar producto", ex);
        }
    }

    /**
     * Verifica si un producto con un nombre específico existe en el sistema.
     *
     * @param nombre el nombre del producto que se desea verificar
     * @return true si el producto existe, false en caso contrario
     */
    @Override
    public boolean productoExiste(String nombre) {
        try {
            // Obtener la conexión a la base de datos
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un filtro para encontrar el producto por su nombre
            Bson filtro = Filters.eq("nombre", nombre);

            // Buscar el producto en la colección
            Document productoEncontrado = coleccion.find(filtro).first();

            // Verificar si se encontró un producto
            return productoEncontrado != null;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al buscar producto", ex);
            return false;
        }
    }
}
