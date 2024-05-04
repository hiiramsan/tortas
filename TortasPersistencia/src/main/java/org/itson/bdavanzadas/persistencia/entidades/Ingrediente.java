/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.entidades;

/**
 *
 * @author Abe
 */
public class Ingrediente {
    private String NombreIngrediente;
    private int cantidad;

    public Ingrediente() {
    }

    public Ingrediente(String NombreIngrediente, int cantidad) {
        this.NombreIngrediente = NombreIngrediente;
        this.cantidad = cantidad;
    }
    
    

    public String getNombreIngrediente() {
        return NombreIngrediente;
    }

    public void setNombreIngrediente(String NombreIngrediente) {
        this.NombreIngrediente = NombreIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
