package entities.repositorio;

import entities.comunidad.Comunidad;
import entities.comunidad.Usuario;


import java.util.List;


public class RepoUsuarios extends Repositorio{


    public List<Usuario> getAllUsuarios(){
        return (List<Usuario>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Usuario.class.getName()).toString(), Usuario.class).getResultList();
    }

    public Usuario getUsuarioXUsername(String username){
        return (Usuario) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Usuario.class.getName()).append(" where nombreUsuario = :username").toString(), Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Usuario getUsuario(long id){
        return (Usuario) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Usuario.class.getName()).append(" where id = :id").toString(), Usuario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Usuario getUsuarioLogueado(String username, String contrasenia) {
        return (Usuario) ConexionBDD.crearEntityManager()
                .createQuery("select u from Usuario u where u.username = :username and u.contrasenia = :contrasenia", Usuario.class)
                .setParameter("username", username)
                .setParameter("contrasenia", contrasenia)
                .getSingleResult();
    }
}

