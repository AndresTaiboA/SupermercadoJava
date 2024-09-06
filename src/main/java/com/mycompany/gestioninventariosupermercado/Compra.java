/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

/**
 *
 * @author Andres
 */
public class Compra {
    private String id;
    private Seccion seccionPerteneciente;
    private Producto productoCompra;
    private Proveedor proveedor;
    private int cantidad;
    
    public Compra(String id, Seccion seccionPerteneciente, Producto productoCompra, Proveedor proveedor, int cantidad) {
        this.id = id;
        this.seccionPerteneciente = seccionPerteneciente;
        this.productoCompra = productoCompra;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
}

    public String getId() {
        return id;
    }

    public String getSeccionPerteneciente() {
        return seccionPerteneciente.getId();
    }

    public String getProductoVenta() {
        return productoCompra.getId();
    }

    public int getCantidad() {
        return cantidad;
    }

}