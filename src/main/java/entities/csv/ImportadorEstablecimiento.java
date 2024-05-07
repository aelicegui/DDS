package entities.csv;

import entities.entidades.Entidad;
import entities.entidades.Establecimiento;


public class ImportadorEstablecimiento {
    public void importarEstablecimiento(Entidad entidad, String[] campos){
        Establecimiento establecimiento = null;

        if(!entidad.contieneEstablecimiento(campos[3])){
            establecimiento = new Establecimiento();
            establecimiento.setNombre(campos[3]);
            entidad.agregarEstablecimiento(establecimiento);
        }
        else{
            establecimiento = entidad.buscarEstablecimiento(campos[3]);
        }
        new ImportadorServicio().importarServicio(establecimiento, campos);

    }
}
