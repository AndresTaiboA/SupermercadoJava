/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

import controlador.ProductoNoExisteException;
import controlador.ProveedorNoExisteException;
import controlador.ProveedorYaExistenteException;
import controlador.SeccionNoExisteException;
import controlador.SeccionYaExistenteException;
import controlador.StockNoSuficienteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Supermercado {
    private String id, nombre, idSeccion;
    private int opcion;
    private int umbral;
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<String> claves = new ArrayList<>();
    private ArrayList<Compra> compras = new ArrayList<>();
    private Seccion seccion;
    private HashMap<String, Seccion> secciones = new HashMap<>();
    private HashMap<String, Proveedor> proveedores = new HashMap<>();
    //
    private HashMap<String, Producto> inventarioProductos = new HashMap<>();
    //
    
    Scanner lector = new Scanner(System.in);

    public Supermercado() {
        this.umbral = 20;
        this.id = "001";
        this.nombre = "Supermercado XYZ";
    }
    public void agregarProducto(String idSeccion, String idProducto, String nombre) throws SeccionNoExisteException{
        if(secciones.containsKey(idSeccion)){
            Producto ejemplo = new Producto(idProducto, nombre);
            seccion = secciones.get(idSeccion);
            seccion.agregarProductoASeccion(ejemplo);
            System.out.println("Producto agregado correctamente");
        } else {
            throw new SeccionNoExisteException();
        }
    }

    public void agregarSeccion(String id, String nombre) throws SeccionYaExistenteException{
        if(secciones.containsKey(idSeccion)){
            throw new SeccionYaExistenteException();
        } else {
            Seccion seccion = new Seccion(idSeccion, nombre);
            secciones.put(idSeccion, seccion);
            claves.add(idSeccion);
            System.out.println("Seccion ingresada");
        }
    }
   
    
    public void agregarProveedor(String idProveedor, String nombreProveedor) throws ProveedorYaExistenteException{
            if(proveedores.containsKey(idProveedor)) {
                throw new ProveedorYaExistenteException();
            } else {
                Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor);
                proveedores.put(idProveedor, proveedor);
                System.out.println("Proveedor ingresado correctamente");
            }
        }

    public void realizarCompra(String idCompra, String idSeccion, String idProducto, String idProveedor, int cantidad) throws SeccionNoExisteException, ProductoNoExisteException, ProveedorNoExisteException{
        Seccion seccion = secciones.get(idSeccion);
        if (seccion == null) {
            throw new SeccionNoExisteException();
        }
        if (!seccion.existeProducto(idProducto)) {
            throw new ProductoNoExisteException();
        }
        Proveedor proveedor = proveedores.get(idProveedor);
        if (proveedor == null) {
            throw new ProveedorNoExisteException();
        }
        seccion.modificarStockProducto(idProducto, cantidad);
        Producto productoTmp = seccion.copiarDatosProducto(idProducto);
        if (productoTmp != null) {
            Compra compra = new Compra(idCompra, seccion, productoTmp, proveedor, cantidad);
            compras.add(compra);
            System.out.println("Compra guardada correctamente");
        }
        System.out.println("Compra Finalizada correctamente");
    }

    public void agregarVenta(String idVenta, String idProducto, int cantidadVendida) throws ProductoNoExisteException, StockNoSuficienteException{
    // Verificar si el producto está en el inventario general
    if (inventarioProductos.containsKey(idProducto)) {
        Seccion seccion;
        Producto producto = inventarioProductos.get(idProducto);

        // Recorrer las secciones 
        for (int i = 0; i < claves.size(); i++) {
            seccion = secciones.get(claves.get(i));

            if (seccion.existeProducto(idProducto)) {
                // Verificar si hay suficiente stock para realizar la venta
                if (producto.getStock() < cantidadVendida) {
                    throw new StockNoSuficienteException();
                } else {
                    // Disminuir el stock del producto
                    producto.disminuirStock(cantidadVendida);

                    // Registrar la venta
                    Venta nuevaVenta = new Venta(idVenta, seccion.getId(), producto, cantidadVendida);
                    ventas.add(nuevaVenta);

                    System.out.println("Venta registrada correctamente.");
                }
                return; 
            }
        }

        throw new ProductoNoExisteException();
    } else {
        throw new ProductoNoExisteException();
    }
}

    private void mostrarProductosPorSeccion(){
        if (secciones.isEmpty()) {
            System.out.println("No hay secciones en el supermercado.");
            return;
        }
        
        Seccion seccionTmp;
        
        for(int i = 0;i<claves.size();i++){
            System.out.println("Seccion: "+secciones.get(claves.get(i)).getNombre());
            seccionTmp = secciones.get(claves.get(i));
            seccionTmp.mostrarProductos();
            System.out.println();
        }
    }
    public void llenarProductos(String id, Producto producto){
        inventarioProductos.put(id, producto);
    }

    /* se considera un valor base, en caso de que el stock de un producto sea menor a este valor, 
    se debe mostrar un mensaje de advertencia y buscar a un proveedor disponible para realizar la compra del producto
    usando una cantidad predefinida por el supermercado
    */
    private void vereficarStockSupermercado(){
        
        Seccion seccionTmp;
        
        ArrayList <String> IdProductosBajoStock = new ArrayList<String>();
        
        for(int i=0; i<claves.size();i++) {
            seccionTmp = secciones.get(claves.get(i));

            IdProductosBajoStock = seccionTmp.buscarStockBajo(IdProductosBajoStock, this.umbral);
            //Si la lista esta vacia es porque no hay productos con stock bajo
            if( !(IdProductosBajoStock.isEmpty()) ){
                realizarCompra(30, IdProductosBajoStock);
            }
        }
        
    }

    private void realizarCompra(int umbral, ArrayList<String> IdProductosBajoStock) {
    String claveProveedor;
    Seccion seccionTmp;
    Producto productoTmp;
    Compra compra;
    String idCompra;

    // Recorremos las secciones
    for (int i = 0; i < claves.size(); i++) {
        seccionTmp = secciones.get(claves.get(i));  

        // Se buscan los productos con bajo stock en esta sección
        for (int j = 0; j < IdProductosBajoStock.size(); j++) {
            // Si el producto está en la sección actual
            if (seccionTmp.existeProducto(IdProductosBajoStock.get(j))) {
                claveProveedor = buscarProveedorClave();
                idCompra = generarIdCompra();

                // Modificamos el stock del producto
                seccionTmp.modificarStockProducto(IdProductosBajoStock.get(j), umbral);

                productoTmp = seccionTmp.copiarDatosProducto(IdProductosBajoStock.get(j)); 
                if (productoTmp != null && claveProveedor != null && idCompra != null) {
                    compra = new Compra(idCompra, seccionTmp, productoTmp, proveedores.get(claveProveedor), umbral);
                    compras.add(compra);
                    System.out.println("Compra automatica guardada correctamente del producto: " + productoTmp.getNombre());
                }
            }
        }
    }
}
    
    //busca al primer proveedor disponible y retorna su clave
    private String buscarProveedorClave() {
        // Suponiendo que tenemos al menos un proveedor
        if (!proveedores.isEmpty()) {
            // Obtener el conjunto de claves del mapa
            return proveedores.keySet().iterator().next();
        }
        return null; // Retorna null si no hay proveedores
    }

    // Genera un UUID aleatorio y lo convierte a cadena
    private String generarIdCompra() {
        return UUID.randomUUID().toString();
    }
    
}
