/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.AdminVentas;

import org.itson.bdavanzadas.ObjetoNegocio.VentasBO;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;

/**
 *
 * @author Abel
 */
public class ControVentas {
    private VentasBO ventasBO;

    public ControVentas() {
        ventasBO = new VentasBO();
    }
    
    public NuevaVentaDTO registrarVenta(NuevaVentaDTO ventaDTO) throws NegocioException{
        NuevaVentaDTO ventaDTOObtenida;
        try {
            ventaDTOObtenida= ventasBO.registarVenta(ventaDTO);
        } catch (NegocioException negocioException) {
            throw new NegocioException("Error al registrar la venta en negocio", negocioException);
        }
        return ventaDTOObtenida;
    }
    
    

    
}
