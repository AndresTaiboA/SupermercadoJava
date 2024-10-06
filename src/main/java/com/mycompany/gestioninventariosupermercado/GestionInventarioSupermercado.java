/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestioninventariosupermercado;
import controlador.SeccionNoExisteException;
import vistas.Menu;
import com.opencsv.exceptions.CsvValidationException;
import controlador.Datos;
import controlador.ProductoNoExisteException;
import controlador.ProveedorNoExisteException;
import controlador.ProveedorYaExistenteException;
import controlador.SeccionYaExistenteException;
import controlador.StockNoSuficienteException;
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
        try{
            supermercado.agregarSeccion(id, nombre);
        }
        catch (SeccionYaExistenteException e){
            System.out.println(e.getMessage());
        }
    }
    public static void agregarProveedor(String id, String nombre){
        try{
            supermercado.agregarProveedor(id, nombre);
        }
        catch (ProveedorYaExistenteException e){
            System.out.println(e.getMessage());
        }
    }
    public static void agregarVenta(String idVenta, String idProducto, int cantidad){
        try{
            supermercado.agregarVenta(idVenta, idProducto, cantidad);
        }
        catch (StockNoSuficienteException | ProductoNoExisteException e){
            System.out.println(e.getMessage());
        }
    }
    public static void agregarCompra(String idCompra, String idSeccion, String idProducto, String idProveedor, int cantidad){
        try{
            supermercado.realizarCompra(idCompra, idSeccion, idProducto, idProveedor, cantidad);
        }
        catch (SeccionNoExisteException | ProveedorNoExisteException | ProductoNoExisteException e)
        {
            System.out.println(e.getMessage());
        }
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
