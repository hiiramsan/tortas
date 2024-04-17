/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.admininventario;

/**
 *
 * @author carlo
 */
public class FacadeAdminInventario implements IInventario {
    private ControlInventario control;

    public FacadeAdminInventario() {
        this.control = new ControlInventario();
    }

    @Override
    public boolean verificarDisponibilidad(String nombreBebida, int cantidad) {
        return control.verificarDisponibilidad(nombreBebida, cantidad);
    }

    @Override
    public void actualizarInventario(String nombreBebida, int cantidad) {
        control.actualizarInventario(nombreBebida, cantidad);
    }
    
    @Override
    public int getProductStock(String nombreBebida) {
        return control.getProductStock(nombreBebida);
    }
}