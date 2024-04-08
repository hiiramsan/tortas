/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.tortasdominio.subsistemas.orden;

import com.mycompany.tortaspersistencia.dtos.NuevaOrdenDTO;
import com.mycompany.tortaspersistencia.dtos.NuevoProductoDTO;
import com.mycompany.tortaspersistencia.dtos.TortaDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abe
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrdenControl OC = new OrdenControl();
        List<NuevoProductoDTO> listaProductos = new ArrayList<>();
        listaProductos.add(new NuevoProductoDTO(0, 1, "Coca-Cola", "soda", 50, "soda", ""));
        listaProductos.add(new NuevoProductoDTO(1, 1, "pepsi", "soda", 50, "soda", ""));
        TortaDTO tortaDTO = new TortaDTO("Sencilla", 1, 80, 1, 1, 1, 1, 1, 1, 1, 1, "torta");
        listaProductos.add(tortaDTO);
        TortaDTO tortaDTO1 = new TortaDTO("Ahogada", 1, 100, 1, 2, 2, 0, 1, 1, 1, 1, "torta");
        listaProductos.add(tortaDTO1);
        NuevaOrdenDTO orden = new NuevaOrdenDTO("Abel", listaProductos, 200, new Date());

        OC.especificacionesOrden(orden);
    }

}
