/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class Seccion {
    private String id;
    private String nombre;
    private ArrayList<Producto> productos;
    
    public Seccion(String id, String nombre){
        this.id=id;
        this.nombre=nombre;
        this.productos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void agregarProductoASeccion(Producto producto){
        this.productos.add(producto); 
    }
    public Boolean existeProducto(String id){
        for(int i=0;i<productos.size();i++){
            if(((productos.get(i)).getId()).equals(id)){
                return true;
            }
        }
        return false;
    }
    
}
