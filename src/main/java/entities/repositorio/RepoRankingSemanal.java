package entities.repositorio;

import entities.ranking.Ranking;
import entities.ranking.RankingSegunCantidad;
import entities.ranking.RankingSemanal;

import java.time.LocalDate;
import java.util.List;

public class RepoRankingSemanal extends Repositorio{

    public List<RankingSemanal> getAllRankingSemales(){
        return (List<RankingSemanal>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(RankingSemanal.class.getName()).toString(), RankingSemanal.class).getResultList();
    }

    public RankingSemanal getRankingXFecha(LocalDate fecha){
        return (RankingSemanal) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(RankingSemanal.class.getName()).append(" where Fecha_Inicial = :fecha").toString(), RankingSemanal.class)
                .setParameter("fecha", fecha)
                .getSingleResult();
    }

    public Ranking getRankingXTipo(String tipoRanking){
        return (Ranking) ConexionBDD.crearEntityManager()
                .createQuery("Select r from "+ Ranking.class.getName() +" r join "+ RankingSemanal.class.getName() + " rs on rs.id = r.rankingSemanal.id "+
                        "  where r.tipoRanking = :tipoRanking and rs.fechaFinal = (select max(rs1.fechaFinal) from "+ RankingSemanal.class.getName() +" rs1) ", Ranking.class)
                .setParameter("tipoRanking", tipoRanking)
                .getSingleResult();
    }

    public RankingSemanal getLastRankingSemanal(){
        return (RankingSemanal) ConexionBDD.crearEntityManager()
                .createQuery("Select rs from  "+ RankingSemanal.class.getName() + " rs "+
                        "  where rs.fechaFinal = (select max(rs1.fechaFinal) from "+ RankingSemanal.class.getName() +" rs1) ", RankingSemanal.class)
                .getSingleResult();
    }
}
