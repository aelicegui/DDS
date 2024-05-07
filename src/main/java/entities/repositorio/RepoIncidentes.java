package entities.repositorio;

import entities.serviciosPub.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepoIncidentes extends Repositorio{
    public List<Incidente> getAllIncidentes(){
        return (List<Incidente>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Incidente.class.getName()).toString(), Incidente.class).getResultList();
    }

    public Incidente getIncidenteXId(long id) {
        return (Incidente) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Incidente.class.getName()).append(" where id = :id").toString(), Incidente.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Incidente> getIncidentesXEstado(String estado) {
            return (List<Incidente>) ConexionBDD.crearEntityManager()
                    .createQuery("select i from incidente i where i.estado = :estado", Incidente.class)
                    .setParameter("estado", estado)
                    .getResultList();
    }
}
