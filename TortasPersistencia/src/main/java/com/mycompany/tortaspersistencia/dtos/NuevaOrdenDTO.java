/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.dtos;

import java.util.List;

public class NuevaOrdenDTO {

    private float total;
    private List<NuevoProductoDTO> productos;
    private String nombreCliente;

    public NuevaOrdenDTO() {
    }

    public NuevaOrdenDTO(List<NuevoProductoDTO> productos, String nombreCliente) {
        this.productos = productos;
        this.nombreCliente = nombreCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<NuevoProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<NuevoProductoDTO> productos) {
        this.productos = productos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
