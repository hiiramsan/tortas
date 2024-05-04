/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.adminInventario;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author carlo
 */
public class FacadeAdminInventario implements IInventario {

    private ControlInventario control;

    public FacadeAdminInventario() throws FindException {
        this.control = new ControlInventario();
    }

    @Override
    public boolean verificarDisponibilidad(String nombreBebida, int cantidad) {
        return control.verificarDisponibilidad(nombreBebida, cantidad);
    }

    @Override
    public void actualizarInventario(String nombreBebida, int cantidad) {
        try {
            control.actualizarInventario(nombreBebida, cantidad);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeAdminInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getProductStock(String nombreBebida) {
        return control.getProductStock(nombreBebida);
    }

    @Override
    public List<NuevoProductoDTO> obtenerInventario(boolean soloStockLimit, int stockLimit, boolean filtrarPorStockAlto) {
        try {
            return control.obtenerInventario(soloStockLimit, stockLimit, filtrarPorStockAlto);
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<NuevoProductoDTO> obtenerInventarioCompleto() {
        try {
            return control.obtenerInventario();
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
