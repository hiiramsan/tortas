/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

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
    public void agregarNuevoProducto(String nombre, String descripcion, double precio, String categoria) {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Document> coleccion = base.getCollection(nombreColeccion);

        // Crear un nuevo documento para el producto
        Document nuevoProducto = new Document()
                .append("nombre", nombre)
                .append("descripcion", descripcion)
                .append("precio", precio)
                .append("categoria", categoria)
                .append("cantidad", 0); // Inicialmente, la cantidad será cero

        // Insertar el nuevo producto en la colección
        coleccion.insertOne(nuevoProducto);

        // Log de información
        logger.log(Level.INFO, "Se agregó un nuevo producto: {0}", nuevoProducto.toJson());
    }
}
