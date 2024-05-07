package entities.repositorio;

import entities.entidades.Entidad;
import entities.serviciosPub.Servicio;

import java.util.List;

public class RepoServicios extends Repositorio{
    public List<Servicio> getAllServicios(){
        return (List<Servicio>) ConexionBDD.crearEntityManager()
                .createQuery("select s from "+Servicio.class.getName()+" s", Servicio.class).getResultList();
    }

    public Servicio getServiciosXNombre(String servicio){
        return (Servicio) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Servicio.class.getName()).append(" where nombre = :nombre").toString(), Servicio.class)
                .setParameter("nombre", servicio)
                .getSingleResult();
    }

    public Servicio getServiciosXId(long servicio) {
        return (Servicio) ConexionBDD.crearEntityManager()
                .createQuery("select s from " + Servicio.class.getName() + " s where s.id = :id", Servicio.class)
                .setParameter("id", servicio)
                .getSingleResult();
    }
}
