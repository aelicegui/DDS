package apiservicio.criterios;

import apiservicio.comunidad.Comunidad;

public class CriterioConfianza implements Criterio{
    public boolean cumpleCriterio(Comunidad comunidad, Comunidad otraComunidad) {
        return comunidad.getConfianza() == otraComunidad.getConfianza();
    }
}
