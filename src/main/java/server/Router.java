package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.*;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Router {

    public static void init() {
        Server.app().get("/401", ctx -> ctx.render("401.hbs"));

        Server.app().get("/login",context ->{
            if(context.sessionAttribute("usuario_id") != null){
                context.redirect("/home");
                return;
            }
            context.render("login.hbs");
        });
        Server.app().routes(()->{
            post("/login",((UsuarioController) FactoryController.controller("Usuario"))::login);
            get("/logout",((UsuarioController) FactoryController.controller("Usuario"))::logout);
            get("/home",((ComunidadController) FactoryController.controller("Comunidad"))::homeDelUsuario);
            get("/rankings", ((RankingController) FactoryController.controller("Ranking"))::index);
            get("/comunidad/{id}",((ComunidadController) FactoryController.controller("Comunidad"))::show);
            get("/comunidad",((ComunidadController) FactoryController.controller("Comunidad"))::index);
            post("/comunidad/{id}",((IncidenteController) FactoryController.controller("Incidente"))::filter);
            post("/comunidad/{cid}/incidente/crear",((IncidenteController) FactoryController.controller("Incidente"))::save);
            post("/comunidad/{cid}/incidente/{iid}",((IncidenteController) FactoryController.controller("Incidente"))::update);
            get("/comunidad/{cid}/incidente/crear",((IncidenteController) FactoryController.controller("Incidente"))::create);
            get("/comunidad/{cid}/miembros",((MiembroController) FactoryController.controller("Miembro"))::index);
            post("/comunidad/{cid}/miembro/{mid}",((MiembroController) FactoryController.controller("Miembro"))::delete);
            get("/perfil/{uid}",((UsuarioController) FactoryController.controller("Usuario"))::show);
            get("/csv", ((CSVController) FactoryController.controller("CSV"))::show);
            post("/csv",((CSVController) FactoryController.controller("CSV"))::upload);
            post("/comunidad/{id}/join", ((ComunidadController) FactoryController.controller("Comunidad"))::joinComunidad);
            post("/guardar-ubicacion", ctx -> {
                String requestBody = ctx.body();
                // Parsea los datos JSON en un objeto Java
                // Aquí asumimos que los datos JSON tienen una estructura { "latitud": valor, "longitud": valor }
                JsonObject json = new JsonParser().parse(requestBody).getAsJsonObject();
                double latitud = json.get("latitud").getAsDouble();
                double longitud = json.get("longitud").getAsDouble();

                // Guarda las coordenadas en la sesión
                ctx.sessionAttribute("latitud", latitud);
                ctx.sessionAttribute("longitud", longitud);

                ctx.result("Ubicación guardada en la sesión.");
            });

        });

/*
        Server.app().routes(() -> {
            get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            get("servicios/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create, TipoRol.ADMINISTRADOR);
            get("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            get("servicios/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
            post("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            post("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::save);
            delete("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);

            path("servicios/{id}/tareas", () -> {
                get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });
        });*/
    }
}
