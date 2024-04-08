/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.inventario;

/**
 *
 * @author Abe
 */
public class Inventario {
     private int cantidadPan = 10;
    private int cantidadCebolla = 10;
    private int cantidadTomate = 10;
    private int cantidadRepollo = 10;
    private int cantidadMayonesa = 10;
    private int cantidadMostaza = 10;
    private int cantidadJalapeno = 10;
    private int cantidadCarne = 10;

    // Métodos para aumentar la cantidad de ingredientes
    public void aumentarCantidadPan(int cantidad) {
        cantidadPan += cantidad;
    }

    public void aumentarCantidadCebolla(int cantidad) {
        cantidadCebolla += cantidad;
    }

    public void aumentarCantidadTomate(int cantidad) {
        cantidadTomate += cantidad;
    }

    public void aumentarCantidadRepollo(int cantidad) {
        cantidadRepollo += cantidad;
    }

    public void aumentarCantidadMayonesa(int cantidad) {
        cantidadMayonesa += cantidad;
    }

    public void aumentarCantidadMostaza(int cantidad) {
        cantidadMostaza += cantidad;
    }

    public void aumentarCantidadJalapeno(int cantidad) {
        cantidadJalapeno += cantidad;
    }

    public void aumentarCantidadCarne(int cantidad) {
        cantidadCarne += cantidad;
    }

    // Métodos para disminuir la cantidad de ingredientes
    public void disminuirCantidadPan(int cantidad) {
        cantidadPan -= cantidad;
    }

    public void disminuirCantidadCebolla(int cantidad) {
        cantidadCebolla -= cantidad;
    }

    public void disminuirCantidadTomate(int cantidad) {
        cantidadTomate -= cantidad;
    }

    public void disminuirCantidadRepollo(int cantidad) {
        cantidadRepollo -= cantidad;
    }

    public void disminuirCantidadMayonesa(int cantidad) {
        cantidadMayonesa -= cantidad;
    }

    public void disminuirCantidadMostaza(int cantidad) {
        cantidadMostaza -= cantidad;
    }

    public void disminuirCantidadJalapeno(int cantidad) {
        cantidadJalapeno -= cantidad;
    }

    public void disminuirCantidadCarne(int cantidad) {
        cantidadCarne -= cantidad;
    }
}
