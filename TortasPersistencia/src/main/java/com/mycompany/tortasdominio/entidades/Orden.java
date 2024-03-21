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
public class Orden {

    private float total;
    private List<Producto> productos;
    private String nombreCliente;

    public Orden() {
    }

    public Orden(String nombreCliente, List<Producto> productos, float total) {
        this.total = total;
        this.productos = productos;
        this.nombreCliente = nombreCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Float.floatToIntBits(this.total);
        hash = 19 * hash + Objects.hashCode(this.productos);
        hash = 19 * hash + Objects.hashCode(this.nombreCliente);
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
        final Orden other = (Orden) obj;
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.nombreCliente, other.nombreCliente)) {
            return false;
        }
        return Objects.equals(this.productos, other.productos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden{");
        sb.append("total=").append(total);
        sb.append(", productos=").append(productos);
        sb.append(", nombreCliente=").append(nombreCliente);
        sb.append('}');
        return sb.toString();
    }

}
