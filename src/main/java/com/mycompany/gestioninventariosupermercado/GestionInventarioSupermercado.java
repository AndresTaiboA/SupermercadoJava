/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestioninventariosupermercado;
import vistas.Menu;

/**
 *
 * @author Andres
 */
public class GestionInventarioSupermercado {
    private static Supermercado supermercado = new Supermercado();
    public static void main(String[] args) {
        Menu.iniciar();
    }
    public static void agregarProducto(String idSeccion, String idProducto, String nombre){
       supermercado.agregarProducto(idSeccion, idProducto, nombre);
    }
    public static void agregarSeccion(String id, String nombre){
        supermercado.agregarSeccion(id, nombre);
    }
    public static void agregarVenta(String idVenta, String idProducto, int cantidad){
        supermercado.agregarVenta(idVenta, idProducto, cantidad);
    }
}
