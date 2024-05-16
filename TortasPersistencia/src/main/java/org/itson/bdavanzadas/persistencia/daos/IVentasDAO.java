/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.bson.types.ObjectId;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Venta;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public interface IVentasDAO {

    /**
     * Registra una nueva venta en el sistema.
     *
     * @param nuevaVentaDTO los detalles de la nueva venta a registrar
     * @return la venta registrada
     * @throws PersistenciaException si ocurre algún error durante la
     * persistencia de la venta
     */
    public Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws PersistenciaException;

    /**
     * Obtiene todas las ventas almacenadas en el sistema.
     *
     * @return una lista de todas las ventas almacenadas
     * @throws PersistenciaException si ocurre algún error al buscar las ventas
     */
    public List<Venta> obtenerVentas() throws PersistenciaException;

    /**
     * Actualiza la información de una orden mediante su identificador.
     *
     * @param id el identificador único de la orden que se desea actualizar
     * @param orden la orden con la información actualizada que se desea guardar
     * @return la orden actualizada
     * @throws PersistenciaException si ocurre algún error durante la
     * actualización de la orden
     */
    public Orden actualizarOrdenId(ObjectId id, Orden orden) throws PersistenciaException;

}
