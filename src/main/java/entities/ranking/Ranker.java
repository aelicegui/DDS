package entities.ranking;
import entities.entidades.Entidad;
import entities.repositorio.RepoEntidades;
import entities.repositorio.RepoRankingSemanal;
import entities.serviciosPub.EstadoIncidente;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import entities.serviciosPub.Incidente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Ranker {
    private List<EstrategiaRanking> estrategiasRanking;
    private RepoEntidades repoEntidades;
    private RepoRankingSemanal repoRankingSemanal;
    private static LocalDateTime ultimaFechaActualizacion;

    public Ranker(){
        this.estrategiasRanking = List.of(new RankingSegunCantidad(), new RankingSegunImpacto(), new RankingSegunTiempo());
        this.repoEntidades = new RepoEntidades();
        this.repoRankingSemanal = new RepoRankingSemanal();
    }

    //
        @Scheduled(cron = "0 0 0 ? * MON") // Ejecución todos los lunes a las 0:00 AM
        public void generateWeeklyRanking() {
            RankingSemanal rankingSemanal;
            ultimaFechaActualizacion = LocalDateTime.now();
            List<Entidad> entidades = this.repoEntidades.getAllEntidades();
            // Lógica para generar el ranking semanal
            rankingSemanal = new RankingSemanal(estrategiasRanking.stream().map(
                    estrategiaRanking -> estrategiaRanking.calcularRanking(entidades)).toList());

            this.repoRankingSemanal.guardar(rankingSemanal.getRankings().get(0));
            this.repoRankingSemanal.guardar(rankingSemanal.getRankings().get(1));
            this.repoRankingSemanal.guardar(rankingSemanal.getRankings().get(2));
            this.repoRankingSemanal.guardar(rankingSemanal);



        }

        public static List<Incidente> listaDeIncidentesUltimaSemana(Entidad entidad){
            return entidad.getIncidentes().stream().filter(
                        incidente -> incidente.getEstadoIncidente() == EstadoIncidente.RESUELTO
                                && enEstaSemana(incidente.getFechaHoraCierre())).toList();
        }

        public static boolean enEstaSemana(LocalDateTime fechaHora){
            return (fechaHora.isBefore(ultimaFechaActualizacion)
                    && fechaHora.isAfter(ultimaFechaActualizacion.minusDays(7)));

        }

}

