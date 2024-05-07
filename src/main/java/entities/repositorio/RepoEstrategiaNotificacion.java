package entities.repositorio;

import entities.notificador.EstrategiaNotificacion;
import java.util.List;

public class RepoEstrategiaNotificacion extends Repositorio{

    public List<EstrategiaNotificacion> getAllEstrategias(){
        return (List<EstrategiaNotificacion>) ConexionBDD.crearEntityManager()
                .createQuery(new StringBuilder().append("from ").append(EstrategiaNotificacion.class.getName()).toString(), EstrategiaNotificacion.class).getResultList();
    }

}
