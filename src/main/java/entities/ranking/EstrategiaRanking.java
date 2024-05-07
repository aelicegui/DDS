package entities.ranking;

import entities.entidades.Entidad;

import java.util.List;

public interface EstrategiaRanking {

    public Ranking calcularRanking(List<Entidad> entidades);

    public String toString();
}
