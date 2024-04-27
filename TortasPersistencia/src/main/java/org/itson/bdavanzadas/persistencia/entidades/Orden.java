/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.entidades;

import java.util.Date;
import java.util.List;
import org.itson.bdavanzadas.dtos.Estado;

/**
 *
 * @author Abe
 */
public class Orden {

    private int numeroOrden;
    private float total;
    private List<Producto> listaProductos;
    private String nombreCliente;
    private Estado estado;
    private Date fecha;

    public Orden() {
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden{");
        sb.append("numeroOrden=").append(numeroOrden);
        sb.append(", total=").append(total);
        sb.append(", listaProductos=").append(listaProductos);
        sb.append(", nombreCliente=").append(nombreCliente);
        sb.append(", estado=").append(estado);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }
}
