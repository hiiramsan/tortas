/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.entidades;

import java.util.Date;
import org.bson.types.ObjectId;
import org.itson.bdavanzadas.dtos.MetodoPago;

/**
 *
 * @author Abe
 */
public class Venta {

    private ObjectId id;
    private ObjectId idOrden;
    private int numeroVenta;
    private MetodoPago metodoPago;
    private Date fechaVenta;
    private Double total;

        
    public Venta() {
    }
    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(ObjectId idOrden) {
        this.idOrden = idOrden;
    }
    

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append(", orden=").append(idOrden);
        sb.append(", metodoPago=").append(metodoPago);
        sb.append(", fechaVenta=").append(fechaVenta.getDay()+fechaVenta.getMonth());
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }

}
