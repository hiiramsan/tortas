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
    public Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws PersistenciaException;
    
    public List<Venta> obtenerVentas() throws PersistenciaException;
    
    public Orden actualizarOrdenId(ObjectId id, Orden orden) throws PersistenciaException;
}
