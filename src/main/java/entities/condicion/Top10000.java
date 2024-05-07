package entities.condicion;

import entities.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Top10000 implements Condicion{
    private File archivo10000 = new File(Config.RUTA_EXPORTACION);

    public boolean buscarPalabraEn(String password, File archivo) {
        boolean estaEnElArchivo = false;
        try {
            if (archivo.exists()) { // Chequea que el archivo exista.
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo)); // Abre el archivo para realizar la
                // lectura.
                String palabraLeida; // En est truea variable se almacena la línea que se leyó del archivo.

                while ((palabraLeida = leerArchivo.readLine()) != null) { // Mientras la línea leída no sea nula, sigue leyendo.
                    if (palabraLeida.equals(password)) {
                        estaEnElArchivo = true;
                        break;
                    }
                }
                leerArchivo.close();
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el archivo.");
        }
        return estaEnElArchivo;
    }

    @Override
    public boolean verificar(String username, String constrasenia){
        boolean esValida = !this.buscarPalabraEn(constrasenia, archivo10000);

        if(!esValida){
            System.out.println(
                    "La contraseña esta en la lista de las peores contraseñas. Intente con otra contraseña");
        }

        return esValida;
    };
}
