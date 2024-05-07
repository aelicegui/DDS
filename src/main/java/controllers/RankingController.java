package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.ranking.*;
import entities.repositorio.RepoComunidades;
import entities.repositorio.RepoRankingSemanal;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingController extends Controller{
        private RepoRankingSemanal repositorio;
        public RankingController(RepoRankingSemanal repositorioRankingSemanal) {
            this.repositorio = repositorioRankingSemanal;
        }

    public void index(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();

                RankingSemanal rankingSemanal = this.repositorio.getLastRankingSemanal();
                Ranking rankingCantidad = rankingSemanal.getRankings().stream().filter(r -> r.getTipoRanking().getClass() == RankingSegunCantidad.class).toList().get(0);
                Ranking rankingImpacto = rankingSemanal.getRankings().stream().filter(r -> r.getTipoRanking().getClass() == RankingSegunImpacto.class).toList().get(0);
                Ranking rankingTiempo = rankingSemanal.getRankings().stream().filter(r -> r.getTipoRanking().getClass() == RankingSegunTiempo.class).toList().get(0);
                model.put("rankingCantidad", rankingCantidad.getEntidades());
                model.put("rankingImpacto", rankingImpacto.getEntidades());
                model.put("rankingTiempo", rankingTiempo.getEntidades());
                model.put("posicion", this.listNPosciones(rankingCantidad.getEntidades().size()));
                this.render(context,"rankings.hbs", model);
            }
            catch (Exception exp){
                exp.printStackTrace();
                // context.redirect("/comunidad/"+context.pathParam("cid")+"/miembros");
            }
        }
    }

    private List<Integer> listNPosciones(int n) {
        List<Integer> lista = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            lista.add(i);
        }
        return lista;
    }
}
