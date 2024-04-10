/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tortaspersistencia.inventario;

/**
 *
 * @author Abe
 */
public interface IInventario {

    boolean verificarDisponibilidad(String nombreBebida, int cantidad);

    void actualizarInventario(String nombreBebida, int cantidad);

}
