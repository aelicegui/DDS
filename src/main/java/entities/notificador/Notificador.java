package entities.notificador;

import entities.comunidad.Miembro;
import entities.comunidad.Usuario;
import entities.repositorio.RepoUsuarios;
import entities.serviciosPub.Incidente;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Notificador {
    private static Notificador instancia = null;

    public static Notificador getInstancia() {
        if(instancia == null){
            instancia = new Notificador();
        }
        return instancia;
    }

    public void notificar(Incidente incidente, List<Miembro> miembros){
        Set<Usuario> usuarios = miembros.stream().map(Miembro::getUsuario).collect(Collectors.toSet());
        usuarios.addAll(new RepoUsuarios().getAllUsuarios().stream().filter(usuario -> usuario.estaInteresadoEn(incidente)).toList());
        usuarios.forEach(usuario -> usuario.getEstrategiaNotificacion().notificar(incidente, usuario));
    }
}
