/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import conexion.IConexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.persistencia.entidades.Producto;

/**
 *
 * @author Abe
 */
public class InventarioDAO implements IInventarioDAO {
    
    public IConexion conexion;
    public String nombreColeccion = "productos";
    static final Logger logger = Logger.getLogger(InventarioDAO.class.getName());
    
    public InventarioDAO(IConexion conexion){
        this.conexion = conexion;
    }
    
    @Override
    public List<Producto> obtenerInventario(){
        MongoDatabase base= conexion.obtenerBaseDatos();
        MongoCollection<Producto> coleccion = base.getCollection(nombreColeccion,Producto.class);
        
        List<Producto> productos = new ArrayList<>();
        coleccion.find().into(productos);
        logger.log(Level.INFO, "Se consultaron {0} productos", productos.size());
        
        return productos;
    }
}
