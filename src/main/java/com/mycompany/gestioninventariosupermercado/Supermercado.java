/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

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

        // Cargar datos iniciales
        cargarDatosIniciales();
    }
    
    public void menu()
    {
        do{
        vereficarStockSupermercado();
        System.out.println();
                
        System.out.println("0. Agregar un producto");
        System.out.println("1. Agregar una seccion");
        System.out.println("2. Ingresar un proveedor");
        System.out.println("3. Agregar una venta");
        System.out.println("4. Agregar una compra");
        System.out.println("5. mostrar Productos PorSeccion");
        System.out.println("9. Salir");
        opcion = Integer.parseInt(lector.nextLine());
            switch(opcion){
                case 0:
                    agregarProducto();
                    break;
                case 1:
                    agregarSeccion();
                    break;
                case 2:
                    agregarProveedor();
                    break;
                case 3:
                    agregarVenta();
                    break;
                case 4:
                    realizarCompra();
                    break;
                case 5:
                    mostrarProductosPorSeccion();
                    break;
                case 9:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(opcion!=9);        
    }

    
    private void agregarProducto() {
        System.out.println("Ingresar id de la seccion");
        idSeccion = lector.nextLine();
        if(secciones.containsKey(idSeccion)){
            System.out.println("Ingresar id del producto");
            id = lector.nextLine();
            System.out.println("Ingresar nombre del producto");
            nombre = lector.nextLine();
            Producto ejemplo = new Producto(id, nombre);
            seccion = secciones.get(idSeccion);
            seccion.agregarProductoASeccion(ejemplo);
            System.out.println("Producto agregado correctamente");
        } else {
            System.out.println("Esta seccion no existe");
        }
    }

    private void agregarSeccion() {
        System.out.println("Ingresar id de la seccion");
        idSeccion = lector.nextLine();
        if(secciones.containsKey(idSeccion)){
            System.out.println("Esta seccion ya existe");
        } else {
            System.out.println("Ingresar nombre de la seccion");
            nombre = lector.nextLine();
            Seccion seccion = new Seccion(idSeccion, nombre);
            secciones.put(idSeccion, seccion);
            claves.add(idSeccion);
            System.out.println("Seccion ingresada");
        }
    }
   
    
    private void agregarProveedor() {
            System.out.println("Ingresar id del proveedor");
            String idProveedor = lector.nextLine();
            if(proveedores.containsKey(idProveedor)) {
                System.out.println("Este proveedor ya existe");
            } else {
                System.out.println("Ingresar nombre del proveedor");
                String nombreProveedor = lector.nextLine();
                Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor);
                proveedores.put(idProveedor, proveedor);
                System.out.println("Proveedor ingresado correctamente");
            }
        }

    private void realizarCompra() {
        System.out.println("Ingresar id de la compra");
        String idCompra = lector.nextLine();

        System.out.println("Ingresar id de la seccion");
        String idSeccion = lector.nextLine();
        Seccion seccion = secciones.get(idSeccion);
        if (seccion == null) {
            System.out.println("La seccion no existe");
            return;
        }

        System.out.println("Ingresar id del producto");
        String idProducto = lector.nextLine();

        if (!seccion.existeProducto(idProducto)) {
            System.out.println("El producto no existe en esta sección");
            return;
        }

        System.out.println("Ingresar id del proveedor");
        String idProveedor = lector.nextLine();
        Proveedor proveedor = proveedores.get(idProveedor);
        if (proveedor == null) {
            System.out.println("El proveedor no existe");
            return;
        }

        System.out.println("Ingresar cantidad a comprar");
        int cantidad = Integer.parseInt(lector.nextLine());

        seccion.modificarStockProducto(idProducto, cantidad);
        
        Producto productoTmp = seccion.copiarDatosProducto(idProducto);
        
        if (productoTmp != null) {
            Compra compra = new Compra(idCompra, seccion, productoTmp, proveedor, cantidad);
            compras.add(compra);
            System.out.println("Compra guardada correctamente");
        }
        System.out.println("Compra Finalizada correctamente");
    }

    private void agregarVenta() {
    System.out.println("Ingrese id de venta");
    String idVenta = lector.nextLine();

    System.out.println("Ingrese el id del producto");
    String idProducto = lector.nextLine();

    // Verificar si el producto está en el inventario general
    if (inventarioProductos.containsKey(idProducto)) {
        Seccion seccion;
        Producto producto = inventarioProductos.get(idProducto);

        // Recorrer las secciones 
        for (int i = 0; i < claves.size(); i++) {
            seccion = secciones.get(claves.get(i));

            if (seccion.existeProducto(idProducto)) {
                System.out.println("Ingrese la cantidad que se vendio");
                int cantidadVendida = Integer.parseInt(lector.nextLine());

                // Verificar si hay suficiente stock para realizar la venta
                if (producto.getStock() < cantidadVendida) {
                    System.out.println("No hay suficiente stock");
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

        System.out.println("El producto no existe en ninguna sección.");
    } else {
        System.out.println("El producto no existe en el inventario.");
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

    private void cargarDatosIniciales() {
        // Proveedores iniciales
        Proveedor proveedor1 = new Proveedor("P001", "Proveedor Alimentos");
        Proveedor proveedor2 = new Proveedor("P002", "Proveedor Bebidas");
        proveedores.put(proveedor1.getId(), proveedor1);
        proveedores.put(proveedor2.getId(), proveedor2);

        // Secciones iniciales
        Seccion seccionAlimentos = new Seccion("S001", "Alimentos");
        Seccion seccionBebidas = new Seccion("S002", "Bebidas");
        secciones.put(seccionAlimentos.getId(), seccionAlimentos);
        secciones.put(seccionBebidas.getId(), seccionBebidas);
        claves.add(seccionAlimentos.getId());
        claves.add(seccionBebidas.getId());

        // Productos iniciales
        Producto producto1 = new Producto("PR001", "Arroz");
        Producto producto2 = new Producto("PR002", "Coca Cola");
        producto1.aumentarStock(50);
        producto2.aumentarStock(30);

        // Asignar productos a secciones
        seccionAlimentos.agregarProductoASeccion(producto1);
        seccionBebidas.agregarProductoASeccion(producto2);

        // Agregar productos al inventario general
        inventarioProductos.put(producto1.getId(), producto1);
        inventarioProductos.put(producto2.getId(), producto2);

        System.out.println("Datos iniciales cargados correctamente.");
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
