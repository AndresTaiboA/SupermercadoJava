/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Andres
 */
public class SeccionNoExisteException extends Exception{

    public SeccionNoExisteException() {
        super("Seccion no existente");
    }
    
}
