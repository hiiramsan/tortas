/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.List;

/**
 *
 * @author carlo
 */
public class Orden {

    private String nombre;
    private List<Producto> productos;
    private Float total;

    public Orden() {
    }

    public Orden(String nombre, List<Producto> productos, Float total) {
        this.nombre = nombre;
        this.productos = productos;
        this.total = total;
    }

    public Orden(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int calcularTotal(List<Producto> listaProductos) {
        int total = 0;
        for (Producto producto : listaProductos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden{");
        sb.append("nombre=").append(nombre);
        sb.append(", productos=").append(productos);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }

}
