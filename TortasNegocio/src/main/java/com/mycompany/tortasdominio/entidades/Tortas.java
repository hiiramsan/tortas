/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tortasdominio.entidades;

/**
 *
 * @author Ramosz
 */
public class Tortas {

    private int cantCebolla;
    private int cantTomate;
    private int cantRepollo;
    private int cantMayonesa;
    private int cantMostaza;
    private int cantJalapeño;
    private int cantCarne;

    public Tortas() {
    }

    // Torta sencilla
    public Tortas(int cantCebolla, int cantTomate, int cantRepollo, int cantMayonesa, int cantMostaza, int cantJalapeño, int cantCarne) {
        this.cantCebolla = 1;
        this.cantTomate = 1;
        this.cantRepollo = 1;
        this.cantMayonesa = 1;
        this.cantMostaza = 1;
        this.cantJalapeño = 1;
        this.cantCarne = 1;
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
