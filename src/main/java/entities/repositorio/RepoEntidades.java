package entities.repositorio;

import entities.entidades.Entidad;
import entities.entidades.Establecimiento;

import java.util.List;

public class RepoEntidades extends Repositorio{
    public List<Entidad> getAllEntidades(){
        return (List<Entidad>) ConexionBDD.crearEntityManager()
                .createQuery("select e from "+Entidad.class.getName()+" e", Entidad.class).getResultList();
    }

    public Entidad getEntidadesXNombre(String entidad){
        return (Entidad) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Entidad.class.getName()).append(" where nombre = :nombre").toString(), Entidad.class)
                .setParameter("nombre", entidad)
                .getSingleResult();
    }

    public Entidad getEntidadesXId(long entidad) {
        return (Entidad) ConexionBDD.crearEntityManager()
                .createQuery("select e from " + Entidad.class.getName() + " e where e.id = :id", Entidad.class)
                .setParameter("id", entidad)
                .getSingleResult();
    }
}
