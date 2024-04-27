/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.dtos;

import java.util.List;
import org.itson.bdavanzadas.persistencia.componentes.Fecha;

/**
 *
 * @author carlo
 */
public class NuevaOrdenDTO {

    private static int proximoNumeroOrden = 1;

    private int numeroOrden;
    private float total;
    private List<NuevoProductoDTO> listaProductos;
    private String nombreCliente;
    private Estado estado;
    private Fecha fecha;

    public NuevaOrdenDTO() {
        this.numeroOrden = proximoNumeroOrden++;
        this.estado = Estado.PENDIENTE;
    }

    public NuevaOrdenDTO(String nombreCliente, List<NuevoProductoDTO> listaProductos, float total, Fecha fecha) {
        this.numeroOrden = proximoNumeroOrden++;
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = Estado.PENDIENTE;
        this.fecha = fecha;
    }

    public static int getProximoNumeroOrden() {
        return proximoNumeroOrden;
    }

    public static void setProximoNumeroOrden(int proximoNumeroOrden) {
        NuevaOrdenDTO.proximoNumeroOrden = proximoNumeroOrden;
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

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

}
