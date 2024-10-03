/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.gestioninventariosupermercado.Producto;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class Datos {
    private final String path;
    private ArrayList<Producto> productos;
    public Datos(String path){
        this.path = path;
        productos = new ArrayList<>();
    }
    public Producto getProducto(int index){
        return this.productos.get(index);
    }
    public int getSize(){
        return this.productos.size();
    }
    public void leerProductos() throws CsvValidationException{
        File file = new File(this.path);
        try{
            FileReader inputFile = new FileReader(file);
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(inputFile).withCSVParser(parser).build();
            String[] datos;
            int i=0;
            while((datos = csvReader.readNext())!= null){
                if(i>0){
                    Producto producto = new Producto(datos[0],datos[1]);
                    productos.add(producto);
                }
                i++;
            }      
            csvReader.close();
            inputFile.close();
        }catch(IOException e){
            System.out.print("Error");
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public boolean escribirProducto(Producto p) {
    File file = new File(this.path);
    try {
        FileWriter outputFile = new FileWriter(file, true);
        CSVWriter csvWriter = new CSVWriter(outputFile, ',',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,"\n");
        productos.add(p);
        String[] registro = {p.getId(), p.getNombre()};
        csvWriter.writeNext(registro);
        
        outputFile.close();
        System.out.println("Nuevo registro añadido exitosamente.");
    } catch(IOException e) {
        System.out.print("Error al escribir en el archivo: ");
        e.printStackTrace();
    } finally {
        System.out.println("Operación de escritura completada.");
    }
    return true;
    } 
}
