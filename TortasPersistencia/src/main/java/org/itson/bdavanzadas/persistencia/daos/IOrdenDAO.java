/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.bson.Document;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abel
 */
public interface IOrdenDAO {

    /**
     * Registra una nueva orden en el sistema.
     *
     * @param ordenDTO los detalles de la nueva orden a registrar
     * @return la orden registrada
     * @throws PersistenciaException si ocurre algún error durante la
     * persistencia de la orden
     */
    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    /**
     * Obtiene todas las órdenes almacenadas en el sistema.
     *
     * @return una lista de todas las órdenes almacenadas
     * @throws FindException si ocurre algún error al buscar las órdenes
     */
    public List<Orden> obtenerOrdenes() throws FindException;

    /**
     * Obtiene el precio de un producto por su nombre.
     *
     * @param nombreProducto el nombre del producto del cual se desea obtener el
     * precio
     * @return el precio del producto
     * @throws FindException si ocurre algún error al buscar el precio del
     * producto
     */
    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException;

    /**
     * Obtiene todas las órdenes que ya han sido completadas.
     *
     * @return una lista de las órdenes completadas
     */
    List<Orden> obtenerOrdenesCompletadas();

    /**
     * Obtiene una orden mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea obtener
     * @return la orden encontrada
     * @throws PersistenciaException si ocurre algún error durante la búsqueda
     * de la orden
     */
    public Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException;

    /**
     * Cancela una orden existente en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea cancelar
     * @return la orden cancelada
     * @throws PersistenciaException si ocurre algún error durante la
     * cancelación de la orden
     */
    public Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    /**
     * Marca una orden como completada en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea marcar como
     * completada
     * @throws PersistenciaException si ocurre algún error durante la
     * actualización del estado de la orden
     */
    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * completada
     */
    public void cambiarEstadoCompletada(int numeroOrden);

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * cancelada
     */
    public void cambiarEstadoCancelada(int numeroOrden);

    /**
     * Obtiene todas las órdenes almacenadas en el sistema, ordenadas por fecha
     * de manera ascendente.
     *
     * @return una lista de órdenes ordenadas por fecha ascendente
     */
    public List<Orden> obtenerOrdenesPorFechaAscendente();

    /**
     * Obtiene todas las órdenes pendientes ordenadas por la cantidad de tortas.
     *
     * @return una lista de órdenes pendientes ordenadas por la cantidad de
     * tortas
     */
    public List<Orden> obtenerOrdenesPendientesPorCantidadTortas();

    /**
     * Obtiene todas las órdenes pendientes almacenadas en el sistema.
     *
     * @return una lista de órdenes pendientes
     */
    public List<Orden> obtenerOrdenesPendientes();
}
