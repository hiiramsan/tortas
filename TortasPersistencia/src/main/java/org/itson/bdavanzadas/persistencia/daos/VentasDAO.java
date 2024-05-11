/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;
import org.itson.bdavanzadas.dtos.Estado;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Venta;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public class VentasDAO implements IVentasDAO {

    public IConexion conexion;
    public String nombreColeccion = "ventas";
    static final Logger logger = Logger.getLogger(VentasDAO.class.getName());
    public IOrdenDAO ordenDAO;

    public VentasDAO(IConexion conexion) {
        this.conexion = conexion;
        ordenDAO = new OrdenDAO(conexion);
    }

    /**
     * Regresa todas la ventas
     *
     * @return valor total de las ventas
     */
    @Override
    public List<Venta> obtenerVentas() throws PersistenciaException {

        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Venta> coleccion = base.getCollection(nombreColeccion, Venta.class);
        List<Venta> ventas = new LinkedList<>();
        try {
            coleccion.find().into(ventas);
            logger.log(Level.INFO, "Se consultaron {0} productos", ventas.size());
            return ventas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener las ventas", e);
        }

    }

    @Override
    public Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws PersistenciaException {

        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Venta> coleccion = base.getCollection(nombreColeccion, Venta.class);

        Venta venta = new Venta();
        Orden orden = null;

        MongoCollection<Orden> coleccionOrdenes = base.getCollection("ordenes", Orden.class);
        Orden ordenEncontrada = ordenDAO.obtenerOrdenPorNumeroOrden(nuevaVentaDTO.getOrden().getNumeroOrden());
        
        ordenEncontrada = actualizarOrdenId( ordenEncontrada.getId(),ordenEncontrada);
       
        venta.setNumeroVenta(numeroVenta()+1);
        venta.setFechaVenta(nuevaVentaDTO.getFechaVenta());
        venta.setIdOrden(ordenEncontrada.getId());
        venta.setMetodoPago(nuevaVentaDTO.getMetodoPago());
        venta.setTotal(nuevaVentaDTO.getTotal());

        try {
            coleccion.insertOne(venta);
            logger.log(Level.INFO, "Se insertaron {0} venta", venta.toString());
            return venta;
        } catch (Exception e) {
            throw new PersistenciaException("Ocurrio un error al guardar la venta");
        }

    }

    public Orden actualizarOrdenId(ObjectId id, Orden orden) throws PersistenciaException {
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Orden> coleccionOrdenes = base.getCollection("ordenes", Orden.class);

        try {

            UpdateResult resultado = coleccionOrdenes.updateOne(eq("_id", id),set("estado", "PAGADA"));
        
            if (resultado.getModifiedCount() > 0) {
                logger.log(Level.INFO, "Orden actualizada con éxito: {0}", orden.toString());
                orden.setEstado(Estado.PAGADA);
                return orden;
            } else {
                throw new PersistenciaException("No se pudo encontrar la orden con ID: " + id);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar la orden con ID: " + id, e);
        }
    }

    /**
     * Regresa el numero total de ventas mas uno
     * @return regresa el total de ventas mas uno
     */
    public int numeroVenta(){
        MongoDatabase base = conexion.obtenerBaseDatos();
        MongoCollection<Venta> coleccion = base.getCollection(nombreColeccion, Venta.class);

        int cantidad = (int) coleccion.countDocuments();
        System.out.println(cantidad);
        return cantidad;
    }

}
