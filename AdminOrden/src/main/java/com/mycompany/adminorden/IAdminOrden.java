/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.adminorden;

import com.mycompany.tortasdtos.NuevaOrdenDTO;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IAdminOrden {

    public void generarOrden(NuevaOrdenDTO nuevaOrden);

    public List<NuevaOrdenDTO> obtenerOrdenes();

    public void cancelarOrden(int index);

    public void completarOrden(int index);

    public void especificacionesOrden(NuevaOrdenDTO orden);
}