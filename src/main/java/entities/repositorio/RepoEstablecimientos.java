package entities.repositorio;

import entities.entidades.Entidad;
import entities.entidades.Establecimiento;
import entities.serviciosPub.Incidente;

import java.util.List;

public class RepoEstablecimientos extends Repositorio{
    public List<Establecimiento> getAllEstablecimientos(){
        return (List<Establecimiento>) ConexionBDD.crearEntityManager()
                .createQuery("select e from "+Establecimiento.class.getName()+" e", Establecimiento.class).getResultList();
    }

        public Establecimiento getEstablecimientosXNombre(String establecimiento){
        return (Establecimiento) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Establecimiento.class.getName()).append(" where nombre = :nombre").toString(), Establecimiento.class)
                .setParameter("nombre", establecimiento)
                .getSingleResult();
    }

    public Establecimiento getEstablecimientosXId(long establecimiento) {
        return (Establecimiento) ConexionBDD.crearEntityManager()
                .createQuery("select e from " + Establecimiento.class.getName() + " e where e.id = :id", Establecimiento.class)
                .setParameter("id", establecimiento)
                .getSingleResult();
    }
}
