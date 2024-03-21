/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.dtos;

import java.util.List;

public class NuevaOrdenDTO {

    private float total;
    private List<NuevoProductoDTO> listaProductos;
    private String nombreCliente;

    public NuevaOrdenDTO() {
    }

    public NuevaOrdenDTO(String nombreCliente, List<NuevoProductoDTO> listaProductos, float total) {
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
    }

    public NuevaOrdenDTO(List<NuevoProductoDTO> productos, String nombreCliente) {
        this.listaProductos = productos;
        this.nombreCliente = nombreCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<NuevoProductoDTO> getProductos() {
        return listaProductos;
    }

    public void setProductos(List<NuevoProductoDTO> productos) {
        this.listaProductos = productos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
