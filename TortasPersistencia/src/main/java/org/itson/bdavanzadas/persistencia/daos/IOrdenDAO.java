/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;

/**
 *
 * @author Abe
 */
public interface IOrdenDAO {

    Orden registrarOrden(NuevaOrdenDTO ordenDTO);

    List<Orden> obtenerOrdenes();

    public Double obtenerPrecioPorNombre(String nombreProducto);
}