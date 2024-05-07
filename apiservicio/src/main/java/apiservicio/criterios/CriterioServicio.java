package apiservicio.criterios;

import apiservicio.comunidad.*;

import java.util.List;
import java.util.stream.Stream;

public class CriterioServicio implements Criterio{
    private static int porcentajeMin = 75;

    public boolean cumpleCriterio(Comunidad comunidad, Comunidad otraComunidad) {
        List<Long> serviciosC1 = comunidad.getIncidentes().stream().map(Incidente::getServicio).toList();
        List<Long> serviciosC2 = comunidad.getIncidentes().stream().map(Incidente::getServicio).toList();
        List<Long> serviciosRepetidos = serviciosC1.stream().filter(s -> serviciosC2.contains(s)).toList();
        List<Long> serviciosSinRepetidos = Stream.concat(serviciosC2.stream(),serviciosC1.stream()).distinct().toList();

        int cantidadRepetidos = serviciosRepetidos.size();
        int cantidadTotalServicios = serviciosSinRepetidos.size();

        return ((cantidadRepetidos / cantidadTotalServicios) * 100) > porcentajeMin;
    }}
