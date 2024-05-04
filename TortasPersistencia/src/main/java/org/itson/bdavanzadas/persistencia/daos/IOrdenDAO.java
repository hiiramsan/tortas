/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public interface IOrdenDAO {

    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException;

    public List<Orden> obtenerOrdenes() throws FindException;

    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException;
}
