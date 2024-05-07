package entities.repositorio;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;


import javax.persistence.TypedQuery;
import java.util.List;

public class RepoComunidades extends Repositorio{
    public List<Comunidad> getAllComunidades(){
        return (List<Comunidad>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Comunidad.class.getName()).toString(), Comunidad.class).getResultList();
    }

    public List<Comunidad> buscarTodasPorUsuario(long usuarioId) {
        String jpql = "SELECT c FROM Comunidad c " +
                "WHERE c.id IN (SELECT m.comunidad.id FROM Miembro m WHERE m.usuario.id = :usuarioID)";
        TypedQuery<Comunidad> query = ConexionBDD.crearEntityManager().createQuery(jpql, Comunidad.class);
        query.setParameter("usuarioID", usuarioId);

        return query.getResultList();
    }

    public Comunidad getComunidad(long comunidadId) {
        return (Comunidad) ConexionBDD.crearEntityManager()
        .createQuery("select c from Comunidad c where c.id = :comunidadID", Comunidad.class).setParameter("comunidadID",comunidadId).getSingleResult();
    }
}
