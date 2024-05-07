package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.entidades.Establecimiento;
import entities.geoRef.entidades.Coordenadas;
import entities.geoRef.entidades.Localizador;
import entities.repositorio.RepoComunidades;

import entities.repositorio.RepoEstablecimientos;
import entities.repositorio.RepoMiembros;
import entities.serviciosPub.EstadoIncidente;
import entities.serviciosPub.Incidente;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadController extends Controller{
    private RepoComunidades repositorio;

    public ComunidadController(RepoComunidades repositorioComunidades) {
        this.repositorio = repositorioComunidades;
    }

    public void homeDelUsuario(Context ctx) {
        Usuario usuarioLogueado = this.usuarioLogueado(ctx);
        if(usuarioLogueado == null){
            ctx.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                List<Comunidad> comunidades = this.repositorio.buscarTodasPorUsuario(usuarioLogueado.getId());
                double latitud = ctx.sessionAttribute("latitud");
                double longitud = ctx.sessionAttribute("longitud");
                Coordenadas coordUser = new Coordenadas(latitud, longitud);
                model.put("comunidades", comunidades);
                List<Incidente> incidentes = comunidades.stream().flatMap(comunidad -> comunidad.getIncidentes().stream()).
                        filter(incidente -> incidente.getEstadoIncidente() == EstadoIncidente.ABIERTO && Localizador.getInstancia().estanCerca(coordUser,incidente.getEstablecimiento().getCoordenadas())).toList();
                System.out.println(incidentes.size());

                model.put("incidentes", incidentes);
                this.render(ctx,"/home.hbs", model);
            }
            catch (Exception exp){
                exp.printStackTrace();
                this.render(ctx,"/home.hbs", new HashMap<>());
            }
        }

    }

    public void show(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                Comunidad comunidad = this.repositorio.getComunidad(Long.parseLong(context.pathParam("id")));
                model.put("comunidad", comunidad);
                model.put("incidentes", comunidad.getIncidentes());
                this.render(context,"comunidad/comunidad.hbs", model);
            }
            catch (Exception exp){
                this.render(context,"comunidad/comunidad.hbs", new HashMap<>());
            }
        }
    }


    public void index(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                List<Comunidad> comunidades = this.repositorio.getAllComunidades();
                model.put("comunidades", comunidades);
                this.render(context,"comunidad/comunidades.hbs", model);
            }
            catch (Exception exp){
                this.render(context,"comunidad/comunidades.hbs", new HashMap<>());
            }
        }
    }

    public void joinComunidad(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                Comunidad comunidad = this.repositorio.getComunidad(Long.parseLong(context.pathParam("id")));
                if(comunidad.getMiembros().stream().noneMatch(miembro -> miembro.getUsuario().getId() == usuarioLogueado.getId())){
                    Miembro miembro = new Miembro(usuarioLogueado, comunidad);
                    comunidad.agregarMiembro(miembro);
                    new RepoMiembros().guardar(miembro);
                    new RepoComunidades().actualizar(comunidad);
                }
                context.redirect("/home");
            }
            catch (Exception exp){
                context.redirect("/home");
            }
        }
    }
}
