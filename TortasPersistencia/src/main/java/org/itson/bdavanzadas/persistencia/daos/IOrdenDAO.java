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

    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    public List<Orden> obtenerOrdenes() throws FindException;

    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException;

    /**
     * Obtiene todas las ordenes que ya han sido completadas
     *
     * @return regresa una lista de las ordenes completadas
     */
    List<Orden> obtenerOrdenesCompletadas();

    /**
     * Obtiene una orden mediante su número de orden
     *
     * @param numeroOrden numero de la orden
     * @return orden encontrada mediante su numero
     * @throws org.itson.bdavanzadas.persistencia.exception.PersistenciaException
     */
    public Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException;

    /**
     * Cancela una orden
     *
     * @param ordenDTO
     * @return
     * @throws PersistenciaException
     */
    public Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    public void cambiarEstadoCompletada(int numeroOrden);

    public void cambiarEstadoCancelada(int numeroOrden);

    public List<Document> obtenerOrdenesPorFechaAscendente();

    public List<Document> obtenerOrdenesPendientesPorCantidadTortas();

    public List<Document> obtenerOrdenesPendientes();
}
