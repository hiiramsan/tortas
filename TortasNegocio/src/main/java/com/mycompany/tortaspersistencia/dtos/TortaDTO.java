/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortaspersistencia.dtos;

/**
 *
 * @author carlo
 */
public class TortaDTO extends NuevoProductoDTO {

    private int cantCebolla;
    private int cantTomate;
    private int cantRepollo;
    private int cantMayonesa;
    private int cantMostaza;
    private int cantJalapeño;
    private int cantCarne;
    private String descripcion;

    public TortaDTO(String nombre, int cantidad, int precio, String categoria) {
        super(nombre, cantidad, precio, categoria);
        cantCebolla = 1;
        cantTomate = 1;
        cantRepollo = 1;
        cantMayonesa = 1;
        cantMostaza = 1;
        cantJalapeño = 1;
        cantCarne = 1;
    }

    public TortaDTO(String nombre, int cantidad, int precio, int cantPan, int cantCebolla, int cantTomate, int cantRepollo, int cantMayonesa, int cantMostaza, int cantJalapeño, int cantCarne, String categoria) {
        super(nombre, cantidad, precio, categoria);
        this.cantCebolla = cantCebolla;
        this.cantTomate = cantTomate;
        this.cantRepollo = cantRepollo;
        this.cantMayonesa = cantMayonesa;
        this.cantMostaza = cantMostaza;
        this.cantJalapeño = cantJalapeño;
        this.cantCarne = cantCarne;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantCebolla() {
        return cantCebolla;
    }

    public void setCantCebolla(int cantCebolla) {
        this.cantCebolla = cantCebolla;
    }

    public int getCantTomate() {
        return cantTomate;
    }

    public void setCantTomate(int cantTomate) {
        this.cantTomate = cantTomate;
    }

    public int getCantRepollo() {
        return cantRepollo;
    }

    public void setCantRepollo(int cantRepollo) {
        this.cantRepollo = cantRepollo;
    }

    public int getCantMayonesa() {
        return cantMayonesa;
    }

    public void setCantMayonesa(int cantMayonesa) {
        this.cantMayonesa = cantMayonesa;
    }

    public int getCantMostaza() {
        return cantMostaza;
    }

    public void setCantMostaza(int cantMostaza) {
        this.cantMostaza = cantMostaza;
    }

    public int getCantJalapeño() {
        return cantJalapeño;
    }

    public void setCantJalapeño(int cantJalapeño) {
        this.cantJalapeño = cantJalapeño;
    }

    public int getCantCarne() {
        return cantCarne;
    }

    public void setCantCarne(int cantCarne) {
        this.cantCarne = cantCarne;
    }

}
