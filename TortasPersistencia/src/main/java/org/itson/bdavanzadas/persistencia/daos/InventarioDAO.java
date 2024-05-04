/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public class InventarioDAO implements IInventarioDAO {

    public IConexion conexion;
    public String nombreColeccion = "productos";
    static final Logger logger = Logger.getLogger(InventarioDAO.class.getName());

    public InventarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Producto> obtenerInventario() throws FindException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Producto> coleccion = base.getCollection(nombreColeccion, Producto.class);

            List<Producto> productos = new ArrayList<>();
            coleccion.find().into(productos);
            logger.log(Level.INFO, "Se consultaron {0} productos", productos.size());

            return productos;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al obtener inventario", ex);
            throw new FindException("Error al obtener inventario", ex);
        }
    }

    @Override
    public void actualizarInventario(String nombreBebida, int cantidad) throws PersistenciaException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Producto> coleccion = base.getCollection(nombreColeccion, Producto.class);

            // Consultar el producto por su nombre
            Producto producto = coleccion.find(eq("nombre", nombreBebida)).first();

            if (producto != null) {
                // Actualizar la cantidad del producto usando $inc para restar la cantidad proporcionada
                coleccion.updateOne(eq("nombre", nombreBebida),
                        new Document("$inc", new Document("cantidad", -cantidad)));

                // Log de información
                logger.log(Level.INFO, "Se actualizó el inventario de {0} restando {1}", new Object[]{nombreBebida, cantidad});
            } else {
                logger.log(Level.WARNING, "No se encontró la bebida {0} en el inventario", nombreBebida);
            }

            // Devolver todos los productos
            List<Producto> productos = new ArrayList<>();
            coleccion.find().into(productos);
            logger.log(Level.INFO, "Se consultaron {0} productos", productos.size());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al actualizar inventario", ex);
            throw new PersistenciaException("Error al actualizar inventario", ex);
        }
    }

    @Override
    public List<Producto> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) throws FindException {
        try {
            MongoDatabase base = conexion.obtenerBaseDatos();
            MongoCollection<Producto> coleccion = base.getCollection(nombreColeccion, Producto.class);

            List<Producto> productos = new ArrayList<>();
            Bson filtro = soloStockLimit ? Filters.lte("cantidad", stockLimit) : new Document();
            coleccion.find(filtro).into(productos);
            if (filtrarPorStockAlto) {
                productos.sort(Comparator.comparingInt(Producto::getCantidad).reversed());
            } else {
                productos.sort(Comparator.comparingInt(Producto::getCantidad));
            }

            logger.log(Level.INFO, "Se consultaron {0} productos", productos.size());

            return productos;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al obtener inventario", ex);
            throw new FindException("Error al obtener inventario", ex);
        }
    }
}
