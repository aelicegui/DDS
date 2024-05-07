package entities.ranking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rankingsemanal")
public class RankingSemanal {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Fecha_inicial", columnDefinition = "DATE")
    @Getter @Setter private LocalDate fechaInicial;

    @Column(name = "Fecha_final", columnDefinition = "DATE")
    @Getter @Setter private LocalDate fechaFinal;

    @OneToMany
    @JoinColumn(name = "rankingSemanal_id", referencedColumnName = "id")
    @Getter @Setter private List<Ranking> rankings;


    public RankingSemanal(List<Ranking> rankings){
        this.rankings = rankings;
        this.fechaInicial = LocalDate.now().minusDays(7);
        this.fechaFinal = LocalDate.now();
    }

    public RankingSemanal() {
        this.rankings = new ArrayList<>();
    }

    public boolean rankingDe(LocalDate fecha) {
        return (fecha.isAfter(this.fechaInicial) && fecha.isBefore(this.fechaFinal));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
