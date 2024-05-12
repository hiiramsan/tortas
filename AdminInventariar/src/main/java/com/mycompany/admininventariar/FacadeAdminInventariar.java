/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.admininventariar;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Ramosz
 */
public class FacadeAdminInventariar implements IInventariar {

    private ControlInventariar control;

    public FacadeAdminInventariar() {
        this.control = new ControlInventariar();
    }

    @Override
    public void agregarNuevoProducto(NuevoProductoDTO nuevoProductoDTO) {
        try {
            control.agregarNuevoProducto(nuevoProductoDTO);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeAdminInventariar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarProducto(NuevoProductoDTO nuevoProductoDTO) {
        try {
            control.actualizarProducto(nuevoProductoDTO);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeAdminInventariar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarProducto(NuevoProductoDTO nuevoProductoDTO) {
        try {
            control.eliminarProducto(nuevoProductoDTO);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeAdminInventariar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
