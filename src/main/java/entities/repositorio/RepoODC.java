package entities.repositorio;

import entities.entidades.OrganismoDeControl;
import entities.entidades.PrestadoraServicio;

import java.util.List;

public class RepoODC extends Repositorio{
    public List<OrganismoDeControl> getAll(){
        return (List<OrganismoDeControl>) ConexionBDD.crearEntityManager()
                .createQuery("select o from "+OrganismoDeControl.class.getName()+" o", OrganismoDeControl.class).getResultList();
    }

    public OrganismoDeControl getPrestadoraXNombre(String odc){
        return (OrganismoDeControl) ConexionBDD.crearEntityManager()
                .createQuery("Select o from " + OrganismoDeControl.class.getName() + " o where o.nombre = :nombre", OrganismoDeControl.class)
                .setParameter("nombre", odc)
                .getSingleResult();
    }
}
