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

    @Override
    public void agregarNuevoProducto(String nombre, String descripcion, double precio, int cantidad, String categoria) throws PersistenciaException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un nuevo documento para el producto
            Document nuevoProducto = new Document()
                    .append("nombre", nombre)
                    .append("descripcion", descripcion)
                    .append("precio", precio)
                    .append("cantidad", cantidad)
                    .append("categoria", categoria);

            // Insertar el nuevo producto en la colección
            coleccion.insertOne(nuevoProducto);

            // Log de información
            logger.log(Level.INFO, "Se agregó un nuevo producto: {0}", nuevoProducto.toJson());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al agregar nuevo producto", ex);
            throw new PersistenciaException("Error al agregar nuevo producto", ex);
        }
    }

    @Override
    public void actualizarProducto(String nombrePrevio, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevaCantidad, String nuevaCategoria) throws PersistenciaException {
        try {
            // Obtener la conexión a la base de datos
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un filtro para encontrar el producto por su nombre previo
            Bson filtro = Filters.eq("nombre", nombrePrevio);

            // Crear un documento con los nuevos valores
            Document nuevosValores = new Document()
                    .append("nombre", nuevoNombre)
                    .append("descripcion", nuevaDescripcion)
                    .append("precio", nuevoPrecio)
                    .append("cantidad", nuevaCantidad)
                    .append("categoria", nuevaCategoria);

            // Crear un documento de actualización
            Document updateDocumento = new Document("$set", nuevosValores);

            // Actualizar el producto en la colección
            UpdateResult resultado = coleccion.updateOne(filtro, updateDocumento);

            // Verificar si se actualizó correctamente
            if (resultado.getModifiedCount() > 0) {
                // Log de información
                logger.log(Level.INFO, "Se actualizó el producto {0}", nuevoNombre);
            } else {
                logger.log(Level.WARNING, "No se encontró el producto {0} para actualizar.", nombrePrevio);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al actualizar producto", ex);
            throw new PersistenciaException("Error al actualizar producto", ex);
        }
    }

    @Override
    public void eliminarProducto(String nombre) throws PersistenciaException {
        try {
            // Obtener la conexión a la base de datos
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

            // Crear un filtro para encontrar el producto por su nombre
            Bson filtro = Filters.eq("nombre", nombre);

            // Eliminar el producto de la colección
            DeleteResult resultado = coleccion.deleteOne(filtro);

            // Verificar si se eliminó correctamente
            if (resultado.getDeletedCount() > 0) {
                // Log de información
                logger.log(Level.INFO, "Se eliminó el producto {0}", nombre);
            } else {
                logger.log(Level.WARNING, "No se encontró el producto {0} para eliminar.", nombre);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al eliminar producto", ex);
            throw new PersistenciaException("Error al eliminar producto", ex);
        }
    }

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
