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
public class ControlaVenta {
    public static void agregarVentaC(String idV, String idP, int cantidad){
        GestionInventarioSupermercado.agregarVenta(idV, idP, cantidad);
    }
}
