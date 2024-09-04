/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

/**
 *
 * @author Andres
 */
public class Venta {
    private String id, idSeccion;
    private Producto productoVenta;
    private int cantidad;

    public Venta(String id, String seccionPerteneciente, Producto productoVenta, int cantidad) {
        this.id = id;
        this.idSeccion = seccionPerteneciente;
        this.productoVenta = productoVenta;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public String getIdSeccion() {
        return idSeccion;
    }

    public String getProductoVenta() {
        return productoVenta.getId();
    }

    public int getCantidad() {
        return cantidad;
    }
    //hola
}
