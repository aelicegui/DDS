package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;
import entities.repositorio.*;
import entities.serviciosPub.Incidente;
import entities.serviciosPub.Servicio;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidenteController extends Controller{
    private RepoIncidentes repositorio;
    public IncidenteController(RepoIncidentes repositorioIncidentes) {
        this.repositorio = repositorioIncidentes;
    }

    public void update(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                Incidente incidente = new RepoIncidentes().getIncidenteXId(Long.parseLong(context.pathParam("iid")));
                incidente.marcarComoResuelto(usuarioLogueado);
                this.repositorio.actualizar(incidente);
                context.redirect("/comunidad/"+context.pathParam("cid"));
            }
            catch (Exception exp){
                context.redirect("/comunidad/"+context.pathParam("cid"));
            }
        }
    }

    public void create(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try {
                Map<String, Object> model = new HashMap<>();
                model.put("comunidad", new RepoComunidades().getComunidad(Long.parseLong(context.pathParam("cid"))));
                model.put("servicios", new RepoServicios().getAllServicios());
                model.put("entidades", new RepoEntidades().getAllEntidades());
                model.put("establecimientos", new RepoEstablecimientos().getAllEstablecimientos());
                this.render(context, "comunidad/formIncidente.hbs", model);
            }catch (Exception exp) {
                this.render(context, "comunidad/comunidad.hbs", new HashMap<>());
            }
        }
    }

    public void save(Context context){
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try {
                RepoServicios repoServicios = new RepoServicios();
                Servicio servicio = repoServicios.getServiciosXId(Long.parseLong(context.formParam("servicio")));
                RepoComunidades repoComunidades = new RepoComunidades();
                Comunidad comunidad = repoComunidades.getComunidad(Long.parseLong(context.pathParam("cid")));
                Incidente incidente = new Incidente(comunidad, servicio,
                        new RepoEntidades().getEntidadesXId(Long.parseLong(context.formParam("entidad"))),
                        new RepoEstablecimientos().getEstablecimientosXId(Long.parseLong(context.formParam("establecimiento")))
                        , usuarioLogueado, context.formParam("descripcion"));

                this.repositorio.guardar(incidente);
                //comunidad.crearIncidente(incidente);
                comunidad.agregarIncidente(incidente);
                repoComunidades.actualizar(comunidad);
                repoServicios.actualizar(servicio);


                context.redirect("/comunidad/" + context.pathParam("cid"));
            }
            catch (Exception exp){
                exp.printStackTrace();
                context.redirect("/comunidad/" + context.pathParam("cid"));
            }
        }
    }
    public void filter(Context context){
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        Long comunidadId = Long.parseLong(context.pathParam("id"));
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            String estadoSeleccionado = context.formParam("filtroEstado");
            System.out.println(estadoSeleccionado);
            if (estadoSeleccionado.equals("todos")) {
                context.redirect("/comunidad/" + String.valueOf(comunidadId));
            }
            else{
                try {
                    Comunidad comunidad = new RepoComunidades().getComunidad(comunidadId);
                    List<Incidente> incidentes = comunidad.getIncidentes().stream().filter(incidente -> String.valueOf(incidente.getEstadoIncidente()).equals(estadoSeleccionado.toUpperCase())).toList();
                    Map<String, Object> model = new HashMap<>();
                    model.put("incidentes", incidentes);
                    model.put("comunidad", comunidad);
                    model.put("filtro", estadoSeleccionado);
                    this.render(context, "comunidad/comunidad.hbs", model);
                }
                catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        }



    }
}
