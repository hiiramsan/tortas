/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.entidades;

import com.mycompany.tortaspersistencia.dtos.Estado;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import java.util.List;
import com.mycompany.tortaspersistencia.dtos.Estado;
import java.util.Date;

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
    private Date fecha;

    public Orden() {
    }

    public Orden(float total, List<Producto> productos, String nombreCliente, Estado estado, int numeroOrden, Date fecha) {
        this.total = total;
        this.productos = productos;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.numeroOrden = numeroOrden;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Float.floatToIntBits(this.total);
        hash = 59 * hash + Objects.hashCode(this.productos);
        hash = 59 * hash + Objects.hashCode(this.nombreCliente);
        hash = 59 * hash + Objects.hashCode(this.estado);
        hash = 59 * hash + this.numeroOrden;
        hash = 59 * hash + Objects.hashCode(this.fecha);
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
        if (this.estado != other.estado) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden{");
        sb.append("total=").append(total);
        sb.append(", productos=").append(productos);
        sb.append(", nombreCliente=").append(nombreCliente);
        sb.append(", estado=").append(estado);
        sb.append(", numeroOrden=").append(numeroOrden);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }

}
