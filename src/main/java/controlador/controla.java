/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.gestioninventariosupermercado.GestionInventarioSupermercado;


/**
 *
 * @author Andres
 */
public class controla {
    public static void agregarProductoC(String idS, String idP, String nom){
        GestionInventarioSupermercado.agregarProducto(idS, idP, nom);
    }
    public static void agregarSeccionC(String id, String nombre){
        GestionInventarioSupermercado.agregarSeccion(id, nombre);
    }
    public static void agregarVentaC(String idV, String idP, int cantidad){
        GestionInventarioSupermercado.agregarVenta(idV, idP, cantidad);
    }
    public static void mostrarProductosC(){
        GestionInventarioSupermercado.mostrarProductos();
    }
}
