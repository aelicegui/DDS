package entities.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Importador{

    private EstrategiaImportacion estrategia;

    public Importador(EstrategiaImportacion estrategia){
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaImportacion estrategia) { this.estrategia = estrategia; }

    public void importar(String ruta) {//Formato: Organismo de Control;Prestadora de Servicio;Entidad;Establecimiento;Servicio
        File archivoCSV = new File(ruta);
        try {
            if (archivoCSV.exists()) { // Chequea que el archivo exista.
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivoCSV)); // Abre el archivo para realizar la lectura
                String linea = leerArchivo.readLine(); //Lee una linea.

                while(null != linea) {
                    String[] campos = linea.split(";"); //Separa en Atributos

                    this.estrategia.importar(campos);

                    linea = leerArchivo.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el archivo.");
        }
    }
}