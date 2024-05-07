package entities.calculadoraConfianza;

import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;

public class ClienteServicioDos {
    private InterfazAdapter adapter = new AdapterServicioDos();

    public String calcularGradoDeConfianzaIndividual(Usuario usuario, Comunidad comunidad) {
        // Acá podemos chequear previamente, que el usuario pasado por parámetro, pertenece a la comunidad pasada.
        // Sino, este chequeo lo podemos hacer en la funcion que llame a este metodo.
        return adapter.calcularConfianzaIndividual(usuario, comunidad);
    }

    public float calcularGradoDeConfianzaComunitario(Comunidad comunidad) {
        return adapter.calcularConfianzaComunitaria(comunidad);
    }

}
