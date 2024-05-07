package entities.repositorio;

import jdk.jfr.Percentage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class ConexionBDD {
    private static EntityManagerFactory emf = null;

    public static EntityManager crearEntityManager(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        }
        return emf.createEntityManager();
    }
}
