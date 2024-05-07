package apiservicio.criterios;

import apiservicio.comunidad.*;

import java.util.List;
import java.util.stream.Stream;

public class CriterioUsuario implements Criterio{
    private static int porcentajeMin = 5;

    public boolean cumpleCriterio(Comunidad comunidad, Comunidad otraComunidad) {
        List<Long> usuariosC1 = comunidad.getMiembros().stream().map(Miembro::getUsuario).toList();
        List<Long> usuariosC2 = otraComunidad.getMiembros().stream().map(Miembro::getUsuario).toList();
        List<Long> usuariosRepetidos =
                usuariosC1.stream().filter(e -> usuariosC2.contains(e)).toList();
        List<Long> usuariosSinRepetidos =
                Stream.concat(usuariosC1.stream(), usuariosC2.stream()).distinct().toList();

        int cantidadRepetidos = usuariosRepetidos.size();
        int cantidadTotalUsuarios = usuariosSinRepetidos.size();

        return ((cantidadRepetidos / cantidadTotalUsuarios) * 100) > porcentajeMin;
    }
}
