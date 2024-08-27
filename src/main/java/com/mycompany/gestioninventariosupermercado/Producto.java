/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

/**
 *
 * @author Andres
 */
public class Producto {
    private String id;
    private String nombre;
    private int stock;

    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.stock = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void aumentarStock(int stock) {
        this.stock += stock;
    }
    public void disminuirStock(int stock){
        this.stock -= stock;
    }
    
}
