package entities.repositorio;

import entities.notificador.Notificacion;

import java.util.List;

public class RepoNotificacion extends Repositorio{
    public List<Notificacion> getAllNotificaciones(){
        return (List<Notificacion>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Notificacion.class.getName()).toString(), Notificacion.class).getResultList();
    }

    public List<Notificacion> getNotificacionXUsuario(String idUsuario){
        return (List<Notificacion>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Notificacion.class.getName()).append(" where usuario_id = :id").toString(), Notificacion.class)
                .setParameter("id", idUsuario)
                .getResultList();
    }
}
