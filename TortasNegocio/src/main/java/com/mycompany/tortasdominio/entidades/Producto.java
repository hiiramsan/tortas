/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.entidades;

import java.util.List;
import java.util.Objects;



/**
 *
 * @author Ramosz
 */
public class Producto {

    private int id;
    private int cantidad;
    private String nombre;
    private String descripcion;
    private int precio;
    private List<String> ingredientes;
    private String notas;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, int precio) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(int id, int cantidad, String nombre, String descripcion, int precio, List<String> ingredientes, String notas) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ingredientes = ingredientes;
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

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.cantidad;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.descripcion);
        hash = 61 * hash + Objects.hashCode(this.precio);
        hash = 61 * hash + Objects.hashCode(this.ingredientes);
        hash = 61 * hash + Objects.hashCode(this.notas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        if (!Objects.equals(this.notas, other.notas)) {
            return false;
        }
        return Objects.equals(this.ingredientes, other.ingredientes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", ingredientes=").append(ingredientes);
        sb.append(", notas=").append(notas);
        sb.append('}');
        return sb.toString();
    }

}
