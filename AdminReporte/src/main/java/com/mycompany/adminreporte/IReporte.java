/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.adminreporte;

import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface IReporte {
    public void generarReporteOrdenDeCompra(List<NuevoProductoDTO> listaInventario, String descripcion, String proveedor);
}
