package entities.csv;

import entities.entidades.OrganismoDeControl;

import java.util.Collections;

import entities.repositorio.RepoODC;


public class EstrategiaODC implements EstrategiaImportacion{

    public void importar(String[] campos){
        OrganismoDeControl odc;

        if(this.organismoDeControlExistente(campos[0])){
        }
        else{
            odc = new OrganismoDeControl();
            odc.setNombre(campos[0]);
            this.guardarOrganismo(odc);
        }
    }

    public void guardarOrganismo(OrganismoDeControl organismoNuevo){
        new RepoODC().guardar(organismoNuevo);
    }

    private boolean organismoDeControlExistente(String nombreOrganizmoControl){
        return (new RepoODC().getPrestadoraXNombre(nombreOrganizmoControl) != null);
    }


}
