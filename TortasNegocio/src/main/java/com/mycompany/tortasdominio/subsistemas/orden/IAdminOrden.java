/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortasdominio.entidades.Orden;
import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface IAdminOrden {

    public void generarOrden(NuevaOrdenDTO nuevaOrden);

    public List<Orden> obtenerOrdenes();

    public void cancelarOrden(int index);

    public void completarOrden(int index);

    public void especificacionesOrden(NuevaOrdenDTO orden);
}
