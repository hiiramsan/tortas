/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.dtos;

import java.util.List;

public class NuevaOrdenDTO {

    private static int proximoNumeroOrden = 1;
    
    private int numeroOrden;
    private float total;
    private List<NuevoProductoDTO> listaProductos;
    private String nombreCliente;
    private Estado estado;

    public NuevaOrdenDTO() {
        this.numeroOrden = proximoNumeroOrden++;
        this.estado = Estado.PENDIENTE;
    }

    public NuevaOrdenDTO(String nombreCliente, List<NuevoProductoDTO> listaProductos, float total) {
        this.numeroOrden = proximoNumeroOrden++;
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = Estado.PENDIENTE;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<NuevoProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<NuevoProductoDTO> listaProductos) {
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
}
