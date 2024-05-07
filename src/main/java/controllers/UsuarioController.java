package controllers;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.repositorio.RepoComunidades;
import entities.repositorio.RepoUsuarios;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController extends Controller{

    private final RepoUsuarios repositorio;

    public UsuarioController(RepoUsuarios repoUsuarios){
        this.repositorio = repoUsuarios;
    }

    public void login(Context ctx) {
        String usuario = ctx.formParam("usuario");
        String contrasenia = ctx.formParam("contrasenia");
        // Realiza la verificación de las credenciales aquí
        // Puedes consultar una base de datos o realizar cualquier otra lógica necesaria
        try{
            Usuario usuarioAutenticado = verificarCredenciales(usuario, contrasenia);

                // Autenticación exitosa, redirige al usuario a la página de inicio
                ctx.sessionAttribute("usuario_id", String.valueOf(usuarioAutenticado.getId()));

                ctx.redirect("/home");
        } catch(Exception exp){
            // Si las credenciales son incorrectas, muestra un mensaje de error
            ctx.render("login.hbs", Map.of("error", true));
        }
    }

    public void logout(Context ctx) {
        ctx.req().getSession().removeAttribute("usuario_id");
        ctx.redirect("/login");
    }



    public Usuario verificarCredenciales(String username, String contrasenia){
        return repositorio.getUsuarioLogueado(username, contrasenia);
    }

    public void show(Context context) {
        Usuario usuarioLogueado = this.usuarioLogueado(context);
        if(usuarioLogueado == null){
            context.redirect("/login");
        }else {
            try{
                Map<String, Object> model = new HashMap<>();
                Usuario usuario = this.repositorio.getUsuario(Long.parseLong(context.pathParam("uid")));
                model.put("usuario", usuario);
                this.render(context, "perfil.hbs", model);
            }
            catch (Exception exp){
                exp.printStackTrace();
                context.redirect("/comunidad/"+context.pathParam("cid")+"/miembros");
            }
        }
    }
}
