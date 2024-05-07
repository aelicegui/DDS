package entities.repositorio;

import entities.comunidad.Comunidad;
import entities.comunidad.Miembro;
import entities.comunidad.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoMiembros extends Repositorio{
    public List<Miembro> getAllMiembros(){
        return (List<Miembro>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Miembro.class.getName()).toString(), Miembro.class).getResultList();
    }

    public Miembro getMiembrosXNickname(String miembro){
        return (Miembro) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(Miembro.class.getName()).append(" where nickname = :nombre").toString(), Miembro.class)
                .setParameter("nombre", miembro)
                .getSingleResult();
    }

    public Miembro getMiembrosXUsuarioYComunidad(long usuario_id, long comunidad_id) {
        return (Miembro) ConexionBDD.crearEntityManager()
                .createQuery("from " + Miembro.class.getName() + " m where m.usuario.id = :IdUsuario and m.comunidad.id = :IdComunidad", Miembro.class)
                .setParameter("IdUsuario", usuario_id)
                .setParameter("IdComunidad", comunidad_id)
                .getSingleResult();
    }

    public List<Miembro> getMiembrosXComunidad(long id) {
        return (List<Miembro>) ConexionBDD.crearEntityManager()
                .createQuery("select m from " + Miembro.class.getName() + " m join "+ Usuario.class.getName() +" u on m.usuario.id = u.id "+
                        " where m.comunidad.id = :IdComunidad order by u.username", Miembro.class)
                .setParameter("IdComunidad", id)
                .getResultList();
    }

    public Miembro getMiembro(long mid) {
        return (Miembro) ConexionBDD.crearEntityManager()
                .createQuery("from " + Miembro.class.getName() + " m where m.id = :id", Miembro.class)
                .setParameter("id", mid)
                .getSingleResult();
    }

    public void delete(long id) {
        EntityManager em = ConexionBDD.crearEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();

        try {
            if (!tx.isActive())
                tx.begin();

            Miembro miembro = em.find(Miembro.class, id);
            em.merge(miembro); // Adjuntar la entidad
            em.remove(miembro); // Eliminar la entidad
            tx.commit();
        }
        catch (Exception exp){
            exp.printStackTrace();
            if(!tx.isActive())
                tx.rollback();
        }
    }
}
