package entities.ranking;

import entities.entidades.Entidad;
import entities.serviciosPub.Incidente;

import java.util.Comparator;
import java.util.List;



public class RankingSegunTiempo implements EstrategiaRanking{

    public Ranking calcularRanking(List<Entidad> entidades){
        List<Entidad> rankeadas = entidades.stream().sorted(Comparator.comparing(
                entidad -> this.promedioCierre(Ranker.listaDeIncidentesUltimaSemana(entidad))
        )).toList(); // ver en que orden esta si de mayor a menor o menor a mayor
        return new Ranking(rankeadas, this);

    }

    public String toString(){
        return "SegunTiempo";
    }

    public long promedioCierre(List<Incidente> incidentes) {
        long tiempoTotalCierreIncidentes =
                incidentes.stream().mapToLong(incidente-> incidente.tiempoDeCierre()).sum();
        if(incidentes.size() != 0){
            return tiempoTotalCierreIncidentes / incidentes.size();
        }
        else{
            return 0;
        }

    }

}
