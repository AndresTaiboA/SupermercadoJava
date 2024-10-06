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
public class ControlaCompra {
    public static void agregarCompraC(String idCompra, String idSeccion, String idProducto, String idProveedor, int cantidad){
        GestionInventarioSupermercado.agregarCompra(idCompra, idSeccion, idProducto, idProveedor, cantidad);
    }
}
