/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.adminOrden;

import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import java.util.List;
import org.bson.Document;
import org.itson.bdavanzadas.persistencia.entidades.Orden;

/**
 *
 * @author carlos
 */
public interface IAdminOrden {

    public void generarOrden(NuevaOrdenDTO nuevaOrden);

    public List<NuevaOrdenDTO> obtenerOrdenes();

    public void cancelarOrden(int index);

    public void completarOrden(int index);

    public void especificacionesOrden(NuevaOrdenDTO orden);

    public Double obtenerPrecioPorNombre(String nombreProducto);

    public NuevaOrdenDTO cancelarOrden(NuevaOrdenDTO ordenDTO);

    public void completadaOrden(NuevaOrdenDTO ordenDTO);

    public List<NuevaOrdenDTO> obtenerOrdenesCompletadas();

    public Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden);

    public void cambiarEstadoCancelada(int numeroOrden);

    public void cambiarEstadoCompletada(int numeroOrden);

    public List<Document> obtenerOrdenesPorFechaAscendente();

    public List<Document> obtenerOrdenesPendientesPorCantidadTortas();

    public List<Document> obtenerOrdenesPendientes();

}
