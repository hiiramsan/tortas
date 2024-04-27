/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author Abe
 */
public class Producto {

    //se debe cambiar posteriormente por tipo int y no objectID
    //para que no este asociado solo a mongodb
    private ObjectId id;
    private int cantidad;
    private String nombre;
    private String descripcion;
    private int precio;
    private String categoria;
    private String notas;

    public Producto() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", cantidad=" + cantidad + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", categoria=" + categoria + ", notas=" + notas + '}';
    }

}
