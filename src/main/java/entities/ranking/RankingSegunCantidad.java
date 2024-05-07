package entities.ranking;

import entities.entidades.Entidad;
import entities.serviciosPub.Incidente;

import java.util.Comparator;
import java.util.List;

public class RankingSegunCantidad implements EstrategiaRanking{

    public Ranking calcularRanking(List<Entidad> entidades){
        List<Entidad> rankeadas = entidades.stream().sorted(Comparator.comparing(
                entidad -> this.cantidadIncidentesReportados(Ranker.listaDeIncidentesUltimaSemana(entidad))
        )).toList();
        return new Ranking(rankeadas, this);
    }

    public String toString(){
        return "SegunCantidad";
    }

    public int cantidadIncidentesReportados(List<Incidente> incidentes){
        int cantIncidentesRepetidos = incidentes.stream().map(
                incidente -> incidente.getServicio().getHistorialDeEstados().
                        stream().map(estado-> estado.getFechaHora()).
                        filter(fechaHora -> fechaHora.isAfter(incidente.getFechaHoraApertura().plusHours(24))
                        && fechaHora.isBefore(incidente.getFechaHoraCierre()))
            ).toList().size();
        return incidentes.size() + cantIncidentesRepetidos;
    }


}
