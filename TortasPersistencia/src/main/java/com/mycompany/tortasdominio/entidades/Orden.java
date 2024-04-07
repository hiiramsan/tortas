/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.entidades;

import com.mycompany.tortaspersistencia.dtos.Estado;
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
    private Estado estado;
    private int numeroOrden;

    public Orden() {
    }

    public Orden(float total, List<Producto> productos, String nombreCliente, Estado estado, int numeroOrden) {
        this.total = total;
        this.productos = productos;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.numeroOrden = numeroOrden;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Float.floatToIntBits(this.total);
        hash = 23 * hash + Objects.hashCode(this.productos);
        hash = 23 * hash + Objects.hashCode(this.nombreCliente);
        hash = 23 * hash + Objects.hashCode(this.estado);
        hash = 23 * hash + this.numeroOrden;
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
        if (this.numeroOrden != other.numeroOrden) {
            return false;
        }
        if (!Objects.equals(this.nombreCliente, other.nombreCliente)) {
            return false;
        }
        if (!Objects.equals(this.productos, other.productos)) {
            return false;
        }
        return this.estado == other.estado;
    }

    @Override
    public String toString() {
        return "Orden{" + "total=" + total + ", productos=" + productos + ", nombreCliente=" + nombreCliente + ", estado=" + estado + ", numeroOrden=" + numeroOrden + '}';
    }

}
