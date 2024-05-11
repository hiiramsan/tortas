/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.AdminVentas;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;

/**
 *
 * @author Abe
 */
public class FacadeAdminVentas implements IVentas {

    private ControVentas control;

    public FacadeAdminVentas() {
        control = new ControVentas();
    }
    

    @Override
    public NuevaVentaDTO registrarVenta(NuevaVentaDTO nuevaVentaDTO){
        NuevaVentaDTO ventaDTO=null;
        try {
            ventaDTO=control.registrarVenta(nuevaVentaDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventaDTO;
    }

   

}
