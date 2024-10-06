/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Andres
 */
public class ProveedorYaExistenteException extends Exception{
    public ProveedorYaExistenteException(){
        super("El proveedor que intenta ingresar ya existe");
    }
}
