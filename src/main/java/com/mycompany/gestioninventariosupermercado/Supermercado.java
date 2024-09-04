/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestioninventariosupermercado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class Supermercado {
    private String id, nombre, idSeccion;
    private int opcion;
    private ArrayList<String> claves = new ArrayList();
    private Seccion seccion;
    private HashMap<String, Seccion> secciones = new HashMap<>();
    private HashMap<String, Producto> inventarioProductos = new HashMap<>();
    
    Scanner lector = new Scanner(System.in);
    
    public Supermercado(/*String id, String nombre*/) {
        //cambiar luego
        this.id = "super";
        this.nombre = "mercado";
    }
    public void menu()
    {
        do{
        System.out.println("0. Agregar un producto");
        System.out.println("1. Agregar una seccion");
        System.out.println("2. Ingresar un proveedor");
        System.out.println("3. Agregar una venta");
        System.out.println("4. Agregar una compra");
        System.out.println("5. Generar reporte de ventas");
        System.out.println("9. Salir");
        opcion = Integer.parseInt(lector.nextLine());
            switch(opcion){
                case 0:
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
                        System.out.println("Producto agregado correctametne");
                    }
                    else{
                    System.out.println("Esta seccion no existe");
                    }
                    break;
                case 1:
                    System.out.println("Ingresar id de la seccion");
                    idSeccion = lector.nextLine();
                    if(secciones.containsKey(idSeccion)){
                        System.out.println("Esta seccion ya existe");
                    }
                    else{
                        System.out.println("Ingresar nombre de la seccion");
                        nombre = lector.nextLine();
                        Seccion seccion = new Seccion(idSeccion, nombre);
                        secciones.put(idSeccion, seccion);
                        claves.add(idSeccion);
                        System.out.println("Seccion ingresada");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    agregarVenta();
                    break;
                case 4:
                    //agregar compra
                    break;
                case 5:
                    //generar reporte
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
    private void agregarVenta(){
        System.out.println("Ingrese id de venta");
                    String idVenta = lector.nextLine();
                    System.out.println("Ingrese el id del producto");
                    String idProducto = lector.nextLine();
                    if(inventarioProductos.containsKey(idProducto)){
                        for(int i = 0;i<claves.size();i++){
                            if((secciones.get(i)).existeProducto(idProducto)){
                                System.out.println("Ingrese la cantidad que se vendio");
                                String cantidadVendida = lector.nextLine();
                                if(Integer.parseInt(cantidadVendida)>inventarioProductos.get(idProducto).getStock())
                                {
                                    System.out.println("No hay suficiente stock");
                                }
                                else{
                                    Venta nuevaVenta = new Venta(idVenta, (secciones.get(i)).getId(), inventarioProductos.get(idProducto), Integer.parseInt(cantidadVendida));
                                }
                                  
                            }
                        }
                        
                    }
                    else{
                        System.out.println("El producto no existe.");
                    }
    }
}
