/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;

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

}
