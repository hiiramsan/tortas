/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortasdominio.entidades.Orden;
import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class FacadeAdminOrden implements IAdminOrden {

    OrdenControl ordenControl = new OrdenControl();

    @Override
    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        ordenControl.generarOrden(nuevaOrden);
    }

    @Override
    public List<Orden> obtenerOrdenes() {
        return ordenControl.obtenerOrdenes();
    }

    @Override
    public void cancelarOrden(int index) {
        ordenControl.cancelarOrden(index);
    }

    @Override
    public void completarOrden(int index) {
        ordenControl.completarOrden(index);
    }

    @Override
    public void especificacionesOrden(NuevaOrdenDTO orden) {
        ordenControl.especificacionesOrden(orden);
    }

}
