package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.repositorio.RepoComunidades;
import entities.repositorio.RepoMiembros;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembroController extends Controller{
    private RepoMiembros repositorio;
    public MiembroController(RepoMiembros repositorioMiembros) {
        this.repositorio = repositorioMiembros;
    }

    public void index(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                List<Miembro> miembros = this.repositorio.getMiembrosXComunidad(Long.parseLong(context.pathParam("cid")));
                Comunidad comunidad = miembros.get(0).getComunidad();
                model.put("miembros", miembros);
                model.put("esAdmin", new RepoMiembros().getMiembrosXUsuarioYComunidad(usuarioLogueado.getId(), comunidad.getId()).isAdmin());
                model.put("comunidad", comunidad);
                this.render(context,"comunidad/miembros.hbs", model);
            }
            catch (Exception exp){
                exp.printStackTrace();
                this.render(context,"comunidad/miembros.hbs", new HashMap<>());
            }
        }

    }

    public void delete(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Miembro miembro = this.repositorio.getMiembro(Long.parseLong(context.pathParam("mid")));
                System.out.println(miembro.getId());
                Comunidad comunidad = miembro.getComunidad();
                comunidad.getMiembros().remove(miembro);
                miembro.setComunidad(null);
                miembro.setUsuario(null);
                new RepoComunidades().actualizar(comunidad);
                repositorio.actualizar(miembro);
                repositorio.delete(miembro.getId());
                context.redirect("/comunidad/"+context.pathParam("cid")+"/miembros");
            }
            catch (Exception exp){
                exp.printStackTrace();
               // context.redirect("/comunidad/"+context.pathParam("cid")+"/miembros");
            }
        }
    }
}
