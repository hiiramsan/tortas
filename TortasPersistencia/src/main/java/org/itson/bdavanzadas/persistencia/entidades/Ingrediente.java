/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.entidades;

/**
 *
 * @author Abel
 */
public class Ingrediente {
    private String nombreIngrediente;
    private int cantidad;

    public Ingrediente() {
    }

    public Ingrediente(String NombreIngrediente, int cantidad) {
        this.nombreIngrediente = NombreIngrediente;
        this.cantidad = cantidad;
    }
    
    

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String NombreIngrediente) {
        this.nombreIngrediente = NombreIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString() {
        return "Ingrediente{" + "nombreIngrediente=" + nombreIngrediente + ", cantidad=" + cantidad + '}';
    }
    
    
}
