package entities.fusiones;

import entities.comunidad.Comunidad;
import lombok.SneakyThrows;


import java.util.List;


public class AdapterServicioUno implements InterfazAdapter {
    private ServicioUno servicioUno = ServicioUno.instancia();

    @SneakyThrows
    @Override
    public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades){
        List<Propuesta> listadoDePropuestas = servicioUno.generarPropuestasFusion(comunidades);
        if(listadoDePropuestas.isEmpty()){
            System.out.println("No existe propuesta");
            return null;
        } else {
            return listadoDePropuestas;
        }
    }

    @SneakyThrows
    @Override
    public Propuesta aceptar(Propuesta propuesta) {
        return servicioUno.aceptar(propuesta);
    }

    @SneakyThrows
    @Override
    public Propuesta rechazar(Propuesta propuesta) {
        return servicioUno.rechazar(propuesta);
    }
}
