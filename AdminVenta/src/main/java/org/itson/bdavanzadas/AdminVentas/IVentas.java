/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.AdminVentas;

import java.util.List;
import org.itson.bdavanzadas.dtos.NuevaVentaDTO;

/**
 *
 * @author Abe
 */
public interface IVentas {
    
    public NuevaVentaDTO registrarVenta(NuevaVentaDTO nuevaVentaDTO);
}
