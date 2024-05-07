package apiservicio.criterios;

import apiservicio.comunidad.*;

import java.util.List;
import java.util.stream.Stream;

public class CriterioEstablecimiento implements Criterio{
    private static int porcentajeMin = 75;

    public boolean cumpleCriterio(Comunidad comunidad, Comunidad otraComunidad) {
        List<Long> establecimientosC1 = comunidad.getIncidentes().stream().map(Incidente::getEstablecimiento).toList();
        List<Long> establecimientosC2 = otraComunidad.getIncidentes().stream().map(Incidente::getEstablecimiento).toList();
        List<Long> establecimientosRepetidos =
                establecimientosC1.stream().filter(e -> establecimientosC2.contains(e)).toList();
        List<Long> establecimientosSinRepetidos =
                Stream.concat(establecimientosC1.stream(), establecimientosC2.stream()).distinct().toList();

        int cantidadRepetidos = establecimientosRepetidos.size();
        int cantidadTotalEstablecimientos = establecimientosSinRepetidos.size();

        return ((cantidadRepetidos / cantidadTotalEstablecimientos) * 100) > porcentajeMin;
    }
}
