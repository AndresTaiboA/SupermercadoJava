/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestioninventariosupermercado;
import vistas.Menu;
import com.opencsv.exceptions.CsvValidationException;
import controlador.Datos;
/**
 *
 * @author Andres
 */
public class GestionInventarioSupermercado {
    private static Supermercado supermercado = new Supermercado();
    public static final String path ="src\\main\\java\\recursos\\productos.csv";
    public static Datos productos;
    public static void main(String[] args) throws CsvValidationException {
        productos = new Datos(path);
        
        //Leemos los usuarios y los copiamos en nuestro arraylist
        leerProductos(productos);

        Menu.iniciar();
    }
    public static void agregarProducto(String idSeccion, String idProducto, String nombre){
        Producto nuevo = new Producto(idProducto,nombre);
        try{
            supermercado.agregarProducto(idSeccion, idProducto, nombre);
            productos.escribirProducto(nuevo);
        }
        catch(SeccionNoExisteException e){
            System.out.println(e.getMessage());
        }
    }
    public static void agregarSeccion(String id, String nombre){
        supermercado.agregarSeccion(id, nombre);
    }
    public static void agregarVenta(String idVenta, String idProducto, int cantidad){
        supermercado.agregarVenta(idVenta, idProducto, cantidad);
    }
    public static void mostrarProductos(){
        for(int i=0;i<productos.getSize();i++){
            productos.getProducto(i).mostrarDatos();
        }
    }
    public static void leerProductos(Datos productos) throws CsvValidationException{
        
        productos.leerProductos();
        for(int i=0;i<productos.getSize();i++){
            supermercado.llenarProductos(String.valueOf(i),productos.getProducto(i));
        }
        
    }
}
