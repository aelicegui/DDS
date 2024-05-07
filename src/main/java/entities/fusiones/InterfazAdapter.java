package entities.fusiones;

import entities.comunidad.Comunidad;

import java.util.List;

public interface InterfazAdapter {
    public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades);

    public Propuesta aceptar(Propuesta propuesta);

    public Propuesta rechazar(Propuesta propuesta);

}
