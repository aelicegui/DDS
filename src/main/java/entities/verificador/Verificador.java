package entities.verificador;

import entities.condicion.Condicion;
import entities.condicion.Credencial;
import entities.condicion.Longitud;
import entities.condicion.Top10000;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Verificador {

    private List<Condicion> condicionesContrasenia =
            Arrays.asList(new Longitud(), new Credencial(), new Top10000());

    public boolean validarContrasenia(String username, String contrasenia) {
        String password = Sanitizador.borrarEspacios(contrasenia);
        boolean esValida =
                this.condicionesContrasenia.stream().allMatch(
                        condicion -> condicion.verificar(username, password));

        if (esValida) {
            System.out.printf("Su contrasenia es valida.");
        }
        return esValida;
    }

    public void agregarCondiciones (Condicion ... condiciones) {
        Collections.addAll(this.condicionesContrasenia, condiciones);
    }
}