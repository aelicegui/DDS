package entities.csv;

import entities.entidades.Entidad;
import entities.entidades.PrestadoraServicio;
import entities.repositorio.RepoEntidades;


public class ImportadorEntidad {

    public void importarEntidad(PrestadoraServicio prestadora, String[] campos){
        Entidad entidad;


        if(prestadora.contieneEntidad(campos[2])) {
            entidad = prestadora.buscarEntidad(campos[2]);
        }
        else{
            entidad = new Entidad();
            entidad.setNombre(campos[2]);
            prestadora.agregarEntidad(entidad);
        }

        new ImportadorEstablecimiento().importarEstablecimiento(entidad, campos);

    }
}
