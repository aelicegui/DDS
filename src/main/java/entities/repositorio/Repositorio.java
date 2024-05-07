package entities.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class Repositorio {
    public void guardar(Object o) {
        EntityManager tm = ConexionBDD.crearEntityManager();
        EntityTransaction tx = tm.getTransaction();
        try {
            if (!tx.isActive())
                tx.begin();

            tm.persist(o);
            tx.commit();
        }
        catch (Exception exp){
            if(!tx.isActive())
                tx.rollback();
        }
    }

    public void actualizar(Object o) {
        EntityManager tm = ConexionBDD.crearEntityManager();
        EntityTransaction tx = tm.getTransaction();
        if(!tx.isActive())
            tx.begin();

        try {
            if (!tx.isActive())
                tx.begin();

            tm.merge(o);
            tx.commit();
        }
        catch (Exception exp){
            if(!tx.isActive())
                tx.rollback();
        }
    }

    public void eliminar(Object o) {
        EntityManager tm = ConexionBDD.crearEntityManager();
        EntityTransaction tx = tm.getTransaction();
        if(!tx.isActive())
            tx.begin();

        try {
            if (!tx.isActive())
                tx.begin();

            tm.remove(o);
            tx.commit();
        }
        catch (Exception exp){
            exp.printStackTrace();
            if(!tx.isActive())
                tx.rollback();
        }
    }
}
