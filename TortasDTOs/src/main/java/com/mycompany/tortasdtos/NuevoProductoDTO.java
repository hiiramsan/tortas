/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdtos;

import java.util.Map;

/**
 *
 * @author carlitossssssssss
 */
public class NuevoProductoDTO {

    private int id;
    private int cantidad;
    private String nombre;
    private String descripcion;
    private int precio;
    private String categoria;
    private String notas;
    private Map<String, Integer> ingredientes;

    public NuevoProductoDTO() {
    }

    public NuevoProductoDTO(String nombre, int cantidad, int precio, String categoria) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public NuevoProductoDTO(int id, int cantidad, String nombre, String descripcion, int precio, String categoria, String notas) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}

