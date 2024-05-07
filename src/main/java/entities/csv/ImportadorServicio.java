package entities.csv;

import entities.entidades.Establecimiento;
import entities.serviciosPub.Servicio;
import entities.serviciosPub.ServicioSimple;

public class ImportadorServicio {
    public void importarServicio(Establecimiento establecimiento, String[] campos) {
        Servicio servicio;

        if(!establecimiento.contieneServicio(campos[4])){
            servicio = new ServicioSimple();
            servicio.setNombre(campos[4]);
            establecimiento.agregarServicio(servicio);
        }
    }
}
