/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.persistencia.entidades.Producto;

/**
 *
 * @author Abe
 */
public interface IInventarioDAO {

    public List<Producto> obtenerInventario();

    public void actualizarInventario(String nombreBebida, int cantidad);
}
