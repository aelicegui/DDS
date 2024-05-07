package entities.ranking;

import entities.Converters.EstrategiaRankingAttributeConverter;
import entities.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue
    private Long id;

    //TODO
    @Convert(converter = EstrategiaRankingAttributeConverter.class)
    @Getter @Setter private EstrategiaRanking tipoRanking;

    @ManyToMany
    @JoinTable(name = "ranking_entidad",
            joinColumns = @JoinColumn(name = "ranking_id"), inverseJoinColumns = @JoinColumn(name = "entidad_id"))
    @Getter @Setter private List<Entidad> entidades;

    public Ranking(List<Entidad> entidades, EstrategiaRanking tipoRanking){
        this.entidades = entidades;
        this.tipoRanking = tipoRanking;
    }


    public Ranking() {
        this.entidades = new ArrayList<>();
    }
}
