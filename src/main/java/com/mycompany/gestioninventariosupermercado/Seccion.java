/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

import java.util.ArrayList;

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

    public void modificarStockProducto(String idProducto, int cantidad) {
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                if (producto.getId().equals(idProducto)) {
                    producto.aumentarStock(cantidad); 
                    return;
                }
            }
        }

public Producto copiarDatosProducto(String idProducto) {
    for (int i = 0; i < productos.size(); i++) {
        Producto producto = productos.get(i);
        if (producto.getId().equals(idProducto)) {
            // Crear una copia del producto con los mismos valores
            Producto productoTmp = new Producto(producto.getId(), producto.getNombre());
            productoTmp.aumentarStock(producto.getStock()); 
            return productoTmp;
        }
    }
    return null;
}

    public Boolean existeProducto(String id){
        for(int i=0;i<productos.size();i++){
            if(((productos.get(i)).getId()).equals(id)){
                return true;
            }
        }
        return false;
    }

    public void mostrarProductos()
    {
        if(productos.isEmpty()){
            System.out.println("No hay Productos en esta seccion.");
            return;
        }
        for(int i=0; i<productos.size();i++){
            System.out.print("Producto: "+productos.get(i).getNombre());
            System.out.print(" Cantidad: "+productos.get(i).getStock());
        }
    }

    public ArrayList<String> buscarStockBajo(ArrayList<String> IdProductosBajoStock, int umbral){

        //Limpiar la lista en caso de llamadas consecutivas (2...n)
        if( !(IdProductosBajoStock.isEmpty() )){
            IdProductosBajoStock.clear();
        }
        
        for(int i=0; i<productos.size();i++){
            if(productos.get(i).getStock()<umbral){
                System.out.println("Atencion! Hay bajo stock de " + productos.get(i).getNombre()+ ": " + productos.get(i).getStock());
                IdProductosBajoStock.add(productos.get(i).getId());
            }
        }
        return IdProductosBajoStock;
    }
}
