/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public interface IInventarioDAO {

    public List<Producto> obtenerInventario() throws FindException;

    public void actualizarInventario(String nombreBebida, int cantidad) throws PersistenciaException;

    public List<Producto> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) throws FindException;
}
