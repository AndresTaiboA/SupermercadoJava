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

    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado();
        Menu.iniciar();
        supermercado.menu();
    }
}
