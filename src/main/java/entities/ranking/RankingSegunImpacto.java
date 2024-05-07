package entities.ranking;

import entities.entidades.Entidad;
import entities.serviciosPub.Incidente;

import java.util.Comparator;
import java.util.List;
public class RankingSegunImpacto implements EstrategiaRanking{

    public Ranking calcularRanking(List<Entidad> entidades){
        List<Entidad> rankeadas = entidades.stream().sorted(Comparator.comparing(
                entidad -> this.impactoIncidentes(Ranker.listaDeIncidentesUltimaSemana(entidad))
                )).toList();
        return new Ranking(rankeadas, this);
    }

    public String toString(){
        return "SegunImpacto";
    }

    public int impactoIncidentes(List<Incidente> incidentes){
        //TODO
        return 0;
    }

}
