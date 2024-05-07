package entities.calculadoraConfianza;

import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;

public interface InterfazAdapter {
    String calcularConfianzaIndividual(Usuario usuario, Comunidad comunidad);

    float calcularConfianzaComunitaria(Comunidad comunidad);
}
