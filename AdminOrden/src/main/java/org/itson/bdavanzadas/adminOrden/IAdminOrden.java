/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.adminOrden;

import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import java.util.List;

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
    
    /**
     * Cancela una orden mediante una ordenDTO
     * @param ordenDTO valor de la orden a cancelar
     */
    public NuevaOrdenDTO cancelarOrden(NuevaOrdenDTO ordenDTO);
    /**
     * Obtiene todas las ordenes que ya hayan sido completadas
     * @return regresa las ordenes ya completadas
     */
    public List<NuevaOrdenDTO> obtenerOrdenesCompletadas();
    
}