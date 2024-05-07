package entities.repositorio;

import entities.entidades.Entidad;
import entities.entidades.PrestadoraServicio;

import java.util.List;

public class RepoPrestadora extends Repositorio{

    public List<PrestadoraServicio> getAll(){
        return (List<PrestadoraServicio>) ConexionBDD.crearEntityManager()
                .createQuery("select p from "+PrestadoraServicio.class.getName()+" p", PrestadoraServicio.class).getResultList();
    }

    public PrestadoraServicio getPrestadoraXNombre(String prestadora){
        return (PrestadoraServicio) ConexionBDD.crearEntityManager()
                .createQuery("Select p from " + PrestadoraServicio.class.getName() + " p where p.nombre = :nombre", PrestadoraServicio.class)
                .setParameter("nombre", prestadora)
                .getSingleResult();
    }
}
