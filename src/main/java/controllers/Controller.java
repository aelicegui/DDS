package controllers;

import io.javalin.http.Context;
import entities.comunidad.Usuario;
import entities.repositorio.ConexionBDD;

import java.util.HashMap;
import java.util.Map;

public abstract class Controller {

    protected Usuario usuarioLogueado(Context ctx) {
        if(ctx.sessionAttribute("usuario_id") == null)
            return null;
        return ConexionBDD.crearEntityManager()
                .find(Usuario.class, Long.parseLong(ctx.sessionAttribute("usuario_id")));
    }

    protected void render(Context ctx, String direccion, Map<String,Object> model) {
        model.put("usuarioLogueado", this.usuarioLogueado(ctx));
        ctx.render(direccion,model);
    }
    
}
